package lvh.naheulbeuk.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lvh.naheulbeuk.model.list.Competence;
import lvh.naheulbeuk.model.list.Job;
import lvh.naheulbeuk.model.list.Race;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Character {

	@Id
    private final String id;
    
	private String name;
	
	private Race race;
	
	private Job job;
	
	private int destinyPoint;
	
	private int level;
	
	private int xp;
	
	private String gender;
	
	private int courage;
	
	private int intelligence;
	
	private int charisma;
	
	private int dexterity;
	
	private int strenght;
	
	private int vitality;
	
	private int vitalityMax;
	
	private int attack;
	
	private int parry;
	
	private int protection;
	
	private int money;
	
	private int magicProtection;
	
	private int maxWeight; //grammes
	
	private String pageId;
	
	private boolean onOfficialMission;
	
	private final Date creationDate;
	
	private String userId;
	
	private List<lvh.naheulbeuk.model.Object> objects;
	
	private Fight fight;
	
	private List<Character> companions;
	
	private List<Competence> competences;

	public Character() {
		this.id = UUID.randomUUID().toString();
		this.creationDate = new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public int getDestinyPoint() {
		return destinyPoint;
	}

	public void setDestinyPoint(int destinyPoint) {
		this.destinyPoint = destinyPoint;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getCourage() {
		return courage;
	}

	public void setCourage(int courage) {
		this.courage = courage;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getStrenght() {
		return strenght;
	}

	public void setStrenght(int strenght) {
		this.strenght = strenght;
	}

	public int getVitality() {
		return vitality;
	}

	public void setVitality(int vitality) {
		this.vitality = vitality;
	}

	public int getVitalityMax() {
		return vitalityMax;
	}

	public void setVitalityMax(int vitalityMax) {
		this.vitalityMax = vitalityMax;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getParry() {
		return parry;
	}

	public void setParry(int parry) {
		this.parry = parry;
	}

	public int getProtection() {
		return protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public int getMagicProtection() {
		return magicProtection;
	}

	public void setMagicProtection(int magicProtection) {
		this.magicProtection = magicProtection;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public String getId() {
		return id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public boolean isOnOfficialMission() {
		return onOfficialMission;
	}

	public void setOnOfficialMission(boolean onOfficialMission) {
		this.onOfficialMission = onOfficialMission;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

	public Fight getFight() {
		return fight;
	}

	public void setFight(Fight fight) {
		this.fight = fight;
	}

	public List<Character> getCompanions() {
		return companions;
	}

	public void setCompanions(List<Character> companions) {
		this.companions = companions;
	}
	
	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public boolean hasObject(final lvh.naheulbeuk.model.Object object) {
			return this.getObjects().stream()
				.filter(obj -> obj.same(object))
				.count() > 0;
	}
	
	public boolean hasCompetence(final Competence competence) {
		return this.competences.contains(competence);
	}

	
}
