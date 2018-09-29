package lvh.naheulbeuk;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lvh.naheulbeuk.model.Action;
import lvh.naheulbeuk.model.Character;
import lvh.naheulbeuk.model.Choice;
import lvh.naheulbeuk.model.ConditionType;
import lvh.naheulbeuk.model.Equipement;
import lvh.naheulbeuk.model.LocalisationObject;
import lvh.naheulbeuk.model.Page;
import lvh.naheulbeuk.model.PageAccess;
import lvh.naheulbeuk.model.User;
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
			PageAccess pageAccess = currentPage.getPageAccesses().stream().filter(access -> choice.getTargetPageNumberId().equals(access.getTargetPageNumer())).findFirst().orElse(null);
			for(Action action : pageAccess.getActions()) {
				int quantity;
				switch (action.getActionType()) {
					case addCarac: quantity = (int) PropertyUtils.getSimpleProperty(perso, action.getCaract()) + action.getQuantity(); PropertyUtils.setSimpleProperty(perso, action.getCaract(), quantity < 20 ? quantity : 19); break;
					case removeCarac: quantity = (int) PropertyUtils.getSimpleProperty(perso, action.getCaract()) - action.getQuantity(); PropertyUtils.setSimpleProperty(perso, action.getCaract(), quantity > 0 ? quantity : 1); break;
					case addObject: perso.getObjects().add(action.getObject()); break;
					case removeObject: removeObject(perso, action.getObject()); break;
					case removeAllCarriedObject: removeAllObjectsByLocalisation(perso, LocalisationObject.body); break;
					case removeBag: removeAllObjectsByLocalisation(perso, LocalisationObject.bag); break;
					case end: perso.setCompanions(new ArrayList<Character>()); perso.setPageId(null); break;
					default: break;
				}
			}
		}
		for(Action action : newPage.getActions()) {
			int quantity;
			switch (action.getActionType()) {
				case addCarac: quantity = (int) PropertyUtils.getSimpleProperty(perso, action.getCaract()) + action.getQuantity(); PropertyUtils.setSimpleProperty(perso, action.getCaract(), quantity < 20 ? quantity : 19); break;
				case removeCarac: quantity = (int) PropertyUtils.getSimpleProperty(perso, action.getCaract()) - action.getQuantity(); PropertyUtils.setSimpleProperty(perso, action.getCaract(), quantity > 0 ? quantity : 1); break;
				case addObject: perso.getObjects().add(action.getObject()); break;
				case removeObject: removeObject(perso, action.getObject()); break;
				case removeAllCarriedObject: removeAllObjectsByLocalisation(perso, LocalisationObject.body); break;
				case removeBag: removeAllObjectsByLocalisation(perso, LocalisationObject.bag); break;
				case end: perso.setCompanions(new ArrayList<Character>()); perso.setPageId(null); break;
				default: break;
			}
		}
		perso.setPageId(newPage.getId());
		return characterRepository.save(perso);
		
	}
	
	private void removeObject(final Character perso, final lvh.naheulbeuk.model.Object object) {
		if (object instanceof Equipement) {
			final Equipement equipement = (Equipement) object;
			List<Equipement> toRemove = perso.getObjects().stream().filter(obj -> obj instanceof Equipement)
			.map(obj -> (Equipement) obj)
			.filter(eq -> eq.same(equipement))
			.collect(Collectors.toList());
			for (Equipement eq: toRemove) {
				perso.getObjects().remove(eq);
			}
		} else {
			List<lvh.naheulbeuk.model.Object> toRemove = perso.getObjects().stream()
					.filter(obj -> obj.getId().equals(object.getId()))
					.collect(Collectors.toList());
			for (lvh.naheulbeuk.model.Object eq: toRemove) {
				perso.getObjects().remove(eq);
			}
		}
	}
	
	private void removeAllObjectsByLocalisation(final Character perso, final LocalisationObject localisation) {
			perso.getObjects().removeIf(obj -> localisation.equals(obj.getLocalisation()));
	}
	
	public void setPageAccess(final Character perso, final Page page) {
		page.getPageAccesses().forEach(pageAccess -> {
			pageAccess.getConditions().forEach(condition -> {
				if (ConditionType.test.equals(condition.getConditionType())){
					try {
						condition.setDoLessThan((int) PropertyUtils.getSimpleProperty(perso, condition.getCaract()), (int) PropertyUtils.getSimpleProperty(perso, condition.getBasedModificatorCaract()));
					} catch (Exception e) {
						page.setHasEncounterPb(true);
						pageRepository.save(page);
					}
				}
			});
		});
	}

}