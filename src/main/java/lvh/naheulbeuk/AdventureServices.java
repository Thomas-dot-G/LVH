package lvh.naheulbeuk;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import lvh.naheulbeuk.model.Action;
import lvh.naheulbeuk.model.Character;
import lvh.naheulbeuk.model.Condition;
import lvh.naheulbeuk.model.Page;
import lvh.naheulbeuk.model.PageAccess;
import lvh.naheulbeuk.model.Test;
import lvh.naheulbeuk.model.User;
import lvh.naheulbeuk.model.input.Choice;
import lvh.naheulbeuk.model.list.ConditionApply;
import lvh.naheulbeuk.model.list.ConditionType;
import lvh.naheulbeuk.repository.CharacterRepository;
import lvh.naheulbeuk.repository.PageRepository;
import lvh.naheulbeuk.repository.UserRepository;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdventureServices {
	
	@Autowired
	private CharacterRepository characterRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PageRepository pageRepository;
	
	
	public void checkToken(final String token, final String userId) throws Exception {
		if (token == null) {
			throw new Exception("No Token");
		}
		Optional<User> user = userRepository.findByToken(token);
		if (user.isPresent()){
			if (!user.get().getId().equals(userId)) {
				throw new Exception("Bad Token");
			}
		} else {
			throw new Exception("Bad Token");
		}
	}
	
	public Page checkPageAccess(final Page currentPage, final Choice choice) throws Exception {
		if (currentPage == null) {
			return pageRepository.findByStoryIdAndEntryPointTrue(choice.getTargetStoryId()).orElse(null);
		}
		final PageAccess pageAccess= currentPage.getPageAccess(choice.getTargetPageNumberId());
		if (pageAccess == null || (pageAccess.getCorrectInput() != null && !pageAccess.getCorrectInput().equals(choice.getInput()))) {
			return null;
		}
		return pageRepository.findByStoryIdAndPageNumber(currentPage.getStoryId(), choice.getTargetPageNumberId()).orElse(null);
	}
	
	public Character checkActions(final Page currentPage, final Page newPage, final Choice choice, final Character perso) throws Exception {
		if(currentPage != null) {
			PageAccess pageAccess = currentPage.getPageAccesses().stream().filter(access -> choice.getTargetPageNumberId().equals(access.getTargetPageNumber())).findFirst().orElse(null);
			executeActions(perso, pageAccess.getActions(), false);
		}
		executeActions(perso, newPage.getActions(), false);
		perso.setPageId(newPage.getId());
		return characterRepository.save(perso);
		
	}
	
	public void executeActions(final Character perso, final List<Action> actions, final boolean savePerso) throws Exception {
		for(Action action : actions) {
			if (action.getConditions() == null || action.getConditions().size() == 0 || isAllConditionsPassed(perso, action.getConditions())) {
				int quantity;
				int actionQuantity = action.getQuantity() + action.getD6Quantity() * (int)(Math.random()*6 + 1) +  action.getD20Quantity() * (int)(Math.random()*20 + 1);
				switch (action.getActionType()) {
					case MODIFY_D20_CARACT: quantity = (int) PropertyUtils.getSimpleProperty(perso, action.getCaract()) + actionQuantity; if (quantity > 20) quantity = 20; if(quantity < 0) quantity = 0; PropertyUtils.setSimpleProperty(perso, action.getCaract(), quantity); break;
					case MODIFY_CARACT: quantity = (int) PropertyUtils.getSimpleProperty(perso, action.getCaract()) + actionQuantity; if(quantity < 0) quantity = 0; PropertyUtils.setSimpleProperty(perso, action.getCaract(), quantity); break;
					case ADD_OBJECT: perso.getObjects().add(action.getObject()); break;
					case REMOVE_OBJECT: removeObject(perso, action.getObject()); break;
					case END: perso.setCompanions(new ArrayList<Character>()); perso.setPageId(null); lvh.naheulbeuk.model.Object ob = new lvh.naheulbeuk.model.Object(); ob.setQuestObject(true); removeObject(perso, ob); break;
					default: break;
				}
				if (savePerso) {
					characterRepository.save(perso);
				}
			}
		}
	}
	
	private void removeObject(final Character perso, final lvh.naheulbeuk.model.Object object) {
			List<lvh.naheulbeuk.model.Object> toRemove = perso.getObjects().stream()
			.filter(obj -> obj.same(object))
			.collect(Collectors.toList());
			for (lvh.naheulbeuk.model.Object ob: toRemove) {
				perso.getObjects().remove(ob);
			}
	}
	
	public void setPageAccess(final Character perso, final Page page) {
		try {
			if (page.getTests() != null){
				for (Test test: page.getTests()) {
					test.setDoLessThan((int) PropertyUtils.getSimpleProperty(perso, test.getCaract()), (int) PropertyUtils.getSimpleProperty(perso, test.getBasedModificatorCaract()));
				}
			}
		} catch (Exception e) {
			page.setHasEncounterPb(true);
			pageRepository.save(page);
		}
		page.getPageAccesses().forEach(pageAccess -> {
			boolean isPageAccessible = true;
			try {
				pageAccess.setUnAccessible(!isAllConditionsPassed(perso, pageAccess.getConditions()));
			} catch (Exception e) {
				page.setHasEncounterPb(true);
				pageRepository.save(page);
			}
		});
	}
	
	private boolean isAllConditionsPassed(final Character perso, final List<Condition> conditions) throws Exception {
		boolean isAllConditionsPassed = true;
		for(Condition condition: conditions) {
			isConditionTrue(perso, condition);
			if (condition.isUnAccessible() && condition.getConditionApply() == null) isAllConditionsPassed = false;
			if (condition.isUnAccessible() && ConditionApply.AND.equals(condition.getConditionApply())) isAllConditionsPassed = false;
			if (!condition.isUnAccessible() && ConditionApply.OR.equals(condition.getConditionApply())) isAllConditionsPassed = true;
		};
		return isAllConditionsPassed;
	}
	
	private boolean isConditionTrue(final Character perso, final Condition condition) throws Exception {
		condition.setUnAccessible(false);
		if (ConditionType.CARACT.equals(condition.getConditionType())){
			if (condition.getCaractCondition().getCaract() != null) {
				final int caract = (int) PropertyUtils.getSimpleProperty(perso, condition.getCaractCondition().getCaract());
				final Integer givenPoint = condition.getCaractCondition().getPoints();
				final String givenStringCaract = condition.getCaractCondition().getCaractValue();
				if (givenPoint != null) {
					switch (condition.getCaractCondition().getCaractLogic()) {
						case LESS_THAN: if (caract >= givenPoint) condition.setUnAccessible(true); break;
						case LESS_OR_EQUALS_THAN: if (caract > givenPoint) condition.setUnAccessible(true); break;
						case MORE_THAN: if (caract <= givenPoint) condition.setUnAccessible(true); break;
						case MORE_OR_EQUALS_THAN: if (caract < givenPoint) condition.setUnAccessible(true); break;
						case EQUALS: if (caract != givenPoint) condition.setUnAccessible(true); break;
						default: break;
					}
				} else if (givenStringCaract != null) {
					if (!givenStringCaract.equals((String) PropertyUtils.getSimpleProperty(perso, givenStringCaract))){
						condition.setUnAccessible(true);
					}
				}
			}
		} else if (ConditionType.OBJECT.equals(condition.getConditionType()) && condition.getObject() != null) {
			if (!perso.hasObject(condition.getObject())){
				condition.setUnAccessible(true);
			}
		} else if (ConditionType.COMPETENCE.equals(condition.getConditionType()) && condition.getCompetence() != null) {
			if (!perso.hasCompetence(condition.getCompetence())){
				condition.setUnAccessible(true);
			}
		}
		if (condition.getInverseCondition() == true) condition.setUnAccessible(!condition.isUnAccessible());
		return !condition.isUnAccessible();
	}

}