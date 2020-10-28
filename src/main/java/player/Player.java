package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import equipment.*;
import quest.Coordinates;
import quest.Quest;
import quest.QuestLog;
import unit.*;
import weapon.*;
import edible.Cupboard;
import item.*;
import magic.ValueCheck;

public class Player extends Unit{
	
    private final String playerClass;
    private final String race;
    private static final int MAX_ALLOWED_EXPERIENCE = 500070023;
    private static final int MAX_ALLOWED_STR_AGI_INT_STA = 5000;
    private static final int MAX_ALLOWED_ARMOR = 50000;
    private int experiencePoint;
    private int level = 1;
    private int nextLevelCap = 100;
    private int armor;
    private int strength;
    private int usedStrength = 0;
    private int stamina;
    private int usedStamina = 0;
    private int intelligence;
    private int agility;
    private Map<ArmorType, Boolean> allowedArmorTypes = new HashMap<ArmorType, Boolean>();
    private Map<String, Boolean> allowedWeaponTypes = new HashMap<String,Boolean>();
    private Map<String, Gear> gear = new HashMap<String, Gear>();
    private Inventory playerInventory = new Inventory();
    private final Cupboard cupboard = new Cupboard(this);
    private QuestLog questLog = new QuestLog();
    private Coordinates coordinates;
    
    public Player(String playerClass, String race, int maxHealthPoint, int experiencePoint){
        super(maxHealthPoint, 1, true);
        makeSureClassAndRaceAreNotNull(playerClass,race);
        this.playerClass = playerClass;
        this.race = race;
        setExperiencePoint(experiencePoint);
        setArmorTypeHashMap();
        setWeaponTypeHashMap();
        updateAttributes();
        setCoordinates(new Coordinates(0, 0));
    }
    private void makeSureClassAndRaceAreNotNull(String playerClass, String race) {
    	ValueCheck.stringCheck(playerClass);
    	ValueCheck.stringCheck(race);
    }
    public Gear getGearFromGear(String slot) {
    	Gear gearPiece = this.gear.get(slot);
    	return gearPiece;
    }
    private void updateAttributes() { 
    	updateHealthBasedOnStamina();
    	updateDamageBasedOnStrength();
    }
    private void updateHealthBasedOnStamina() {
    	if(this.stamina==this.usedStamina) {return;}else {
    		int stamDiff = this.stamina-this.usedStamina;
    		this.maxHealthPoint = this.maxHealthPoint + stamDiff*10;
    		this.healthPoint = this.healthPoint + stamDiff*10;
    		this.usedStamina = this.stamina;
    	}
    }
    private void updateDamageBasedOnStrength() {
    	if(this.strength==this.usedStrength) {return;}else {
    		int strDiff = this.strength-this.usedStrength;
    		this.attackPower = this.attackPower + strDiff*5;
    		this.usedStrength = this.strength;
    	}
    }
    private void removeAttributesFromOldGear(Gear piece) {
    	if(piece instanceof Equipment) {
    		this.armor = this.armor - ((Equipment) piece).getArmor();
    	}
    	if(piece instanceof Weapon){
    		this.attackPower = this.attackPower - (((Weapon) piece).getDamage());
    	}
    	this.strength -= piece.getAttributes()[0];
    	this.agility -= piece.getAttributes()[1];
    	this.intelligence -= piece.getAttributes()[2];
    	this.stamina -= piece.getAttributes()[3];
    }
    private void addAttributesFromNewGear(Gear piece) {
    	if(piece instanceof Equipment) {
    		if(this.armor+((Equipment) piece).getArmor()>Player.MAX_ALLOWED_ARMOR || this.armor+((Equipment) piece).getArmor()<-10000) {this.armor=Player.MAX_ALLOWED_ARMOR;}else {
    			this.armor = this.armor + ((Equipment) piece).getArmor();
    		}
    	}
    	if(piece instanceof Weapon){
    		this.attackPower = this.attackPower + ((Weapon) piece).getDamage();
    	}
    	if(this.strength+piece.getAttributes()[0]>Player.MAX_ALLOWED_STR_AGI_INT_STA || this.strength+piece.getAttributes()[0]<-10000) {this.strength = Player.MAX_ALLOWED_STR_AGI_INT_STA;}else {
    		this.strength += piece.getAttributes()[0];
    	}
    	if(this.agility+piece.getAttributes()[1]>Player.MAX_ALLOWED_STR_AGI_INT_STA || this.agility+piece.getAttributes()[1]<-10000) {this.agility = Player.MAX_ALLOWED_STR_AGI_INT_STA;}else {
        	this.agility += piece.getAttributes()[1];
    	}
    	if(this.intelligence+piece.getAttributes()[2]>Player.MAX_ALLOWED_STR_AGI_INT_STA || this.intelligence+piece.getAttributes()[2]<-10000) {this.intelligence = Player.MAX_ALLOWED_STR_AGI_INT_STA;}else {
    		this.intelligence += piece.getAttributes()[2];
    	}
    	if(this.stamina+piece.getAttributes()[3]>Player.MAX_ALLOWED_STR_AGI_INT_STA || this.stamina+piece.getAttributes()[3]<-10000) {this.stamina = Player.MAX_ALLOWED_STR_AGI_INT_STA;}else {
    		this.stamina += piece.getAttributes()[3];
    	}
    }
    public int getArmor() {return this.armor;}
    public int getStrength() {return this.strength;}
    public int getAgility() {return this.agility;}
    public int getIntelligence() {return this.intelligence;}
    public int getStamina() {return this.stamina;}
    public int getExperiencePoint() {
        return experiencePoint;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public String getRace() {
        return race;
    }

    public void setExperiencePoint(int experiencePoint) {
    	if(experiencePoint > Player.MAX_ALLOWED_EXPERIENCE) {this.experiencePoint = Player.MAX_ALLOWED_EXPERIENCE;}else {
    		this.experiencePoint = experiencePoint;
    	}
        controlIfExperienceIsEnoughForLevelUp();
    }
    private void controlIfExperienceIsEnoughForLevelUp() {
    	if(this.experiencePoint >= this.nextLevelCap) {
        	do{
        		increaseLevel();
        	}while(this.experiencePoint >= this.nextLevelCap);
        }
    }
    public int getLevel() {
    	return this.level;
    }
    public void increaseLevel() {
    	if(this.level==40) {return;}
    	this.level++;
    	if(this.playerClass == "Mage") {
    		this.strength += 1;
    		this.agility += 1;
    		this.intelligence += 2;
    		this.stamina += 1;
    	}
    	if(this.playerClass == "Warrior") {
    		this.strength += 2;
    		this.agility += 1;
    		this.intelligence += 1;
    		this.stamina += 2;
    	}  //Bortkommenterade delen vill jag implementera men kan sabba andras tester där karaktärer levlar upp. //Christian
    	updateAttributes();
    	this.nextLevelCap = this.nextLevelCap+(this.nextLevelCap/2);
    }
    public int getNextLevelCap() {return this.nextLevelCap;}

    public void increaseExperiencePoint(int experiencePoint){
    	if(this.experiencePoint+experiencePoint > Player.MAX_ALLOWED_EXPERIENCE) {this.experiencePoint=Player.MAX_ALLOWED_EXPERIENCE;}else {
    		this.experiencePoint += experiencePoint;
    	}
        controlIfExperienceIsEnoughForLevelUp();
    }
    
    private void setArmorTypeHashMap() {
    	switch(this.playerClass) {
    	case "Mage":
    		this.allowedArmorTypes.put(ArmorType.CLOTH, true);
    		this.allowedArmorTypes.put(ArmorType.LEATHER, false);
    		this.allowedArmorTypes.put(ArmorType.MAIL, false);
    		this.allowedArmorTypes.put(ArmorType.PLATE, false);
    		this.allowedArmorTypes.put(ArmorType.SHIELD, false);
    		break;
    	case "Warrior":
    		this.allowedArmorTypes.put(ArmorType.CLOTH, true);
    		this.allowedArmorTypes.put(ArmorType.LEATHER, true);
    		this.allowedArmorTypes.put(ArmorType.MAIL, true);
    		this.allowedArmorTypes.put(ArmorType.PLATE, true);
    		this.allowedArmorTypes.put(ArmorType.SHIELD, true);
    		break;
    	}
    }
    private void setWeaponTypeHashMap() {
    	switch(this.playerClass) {
    	case "Mage":
    		this.allowedWeaponTypes.put("OneHandedSword", true);
    		this.allowedWeaponTypes.put("TwoHandedSword", false);
    		break;
    	case "Warrior":
    		this.allowedWeaponTypes.put("OneHandedSword", true);
    		this.allowedWeaponTypes.put("TwoHandedSword", true);
    		break;
    	}
    }
    public void unEquipGear(Gear gear) {
    	Gear wornGear = null;
    	String slot = null;
    	if(gear instanceof Weapon) {slot = "weapon";
    	wornGear = this.gear.get("weapon");}
    	if(gear instanceof Equipment){slot = ((Equipment) gear).getSlot();wornGear = this.gear.get(slot);}
    	if(gear.equals(wornGear)) {this.gear.remove(slot);}else {return;}
    	this.playerInventory.addItem(wornGear);
    	removeAttributesFromOldGear(wornGear);
    	updateAttributes();
    }
    public void equipWeapon(Weapon weapon) throws Exception {
    	isPlayerLeveledHighlyEnoughToEquip(weapon);
    	checkIfWeaponToBeEquippedIsAllowedType(weapon);
    	updateAttributes();
    }
    private boolean isPlayerLeveledHighlyEnoughToEquip(Item item) throws Exception {
    	if(this.level < item.getRequiredUnitLevel()) {throw new ArithmeticException("This item requires a higher level to use!");}else {return true;}
    }
    private void equippingArmor(Equipment armor) {
    	if(!this.gear.containsKey(armor.getSlot())) {
			this.gear.put(armor.getSlot(), armor);
		}else {
			Gear replacedPiece = (Gear) this.gear.replace(armor.getSlot(), armor);
			removeAttributesFromOldGear(replacedPiece);
			this.playerInventory.addItem(replacedPiece);
		}
		addAttributesFromNewGear(armor);
    }
    private void handleShieldsInWeaponSwap() {
    	if(gear.get("shield")==null) {return;}else {
    		Gear shield = gear.get("shield");
    		playerInventory.addItem(shield);
			removeAttributesFromOldGear(shield);
    		gear.remove("shield");
    	}
    }
    private void equippingWeapon(Weapon weapon) {
    	if(weapon.getSize() == WeaponSize.TwoHanded) {handleShieldsInWeaponSwap();}
    	if(!this.gear.containsKey("weapon")) {
			this.gear.put("weapon", weapon);
		}else {
			Gear replacedPiece = this.gear.replace("weapon", weapon);
			removeAttributesFromOldGear(replacedPiece);
			this.playerInventory.addItem(replacedPiece);
		}
		addAttributesFromNewGear(weapon);
    }
    private void checkIfArmorToBeEquippedIsAllowedType(Equipment armor) throws Exception {
    	if(this.allowedArmorTypes.get(armor.getArmorType())) {
    		this.playerInventory.getOutItem(armor);
    		equippingArmor(armor);
    	}else {
    		throw new IllegalArgumentException("Armor type not allowed.");
    	}
    }
    private void checkIfWeaponToBeEquippedIsAllowedType(Weapon weapon) throws Exception {
    	if(this.allowedWeaponTypes.get(weapon.getType())) {
    		this.playerInventory.getOutItem(weapon);
    		equippingWeapon(weapon);
    	}else {
    		throw new IllegalArgumentException("Weapon type not allowed.");
    	}
    }
    public void equipArmor(Equipment armor) throws Exception{
    	playerInventory.isInInventory(armor);
    	isPlayerLeveledHighlyEnoughToEquip(armor);
    	checkIfArmorToBeEquippedIsAllowedType(armor);
    	updateAttributes();
    }

	public Cupboard getCupboard() {
		return cupboard;
	}

    // methods added by Christoffer

    public void addToInventory(Item item) {
        playerInventory.addItem(item);
    }

    public void removeFromInventory(Item item) {
        playerInventory.getOutItem(item);
    }

    public boolean isInInventory(Item item) {
        return playerInventory.isInInventory(item);
    }

    public int getInventoryCount(Item item) {
        return playerInventory.getCount(item);
    }

    public QuestLog getQuestLog() {return questLog; }

	public int getXCoordinate(){
    	return coordinates.getX();
	}

	public int getYCoordinate(){
    	return coordinates.getY();
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public boolean attack(Combatant enemy, Attack attack) {
        if(!isAlive()){
            throw new IllegalStateException("The player is dead");
        }else if(!enemy.isAlive()){
            throw new IllegalStateException("The unit receiving damage is already dead");
        }else if(!attack.isRanged() && isGrounded() && !enemy.isGrounded()){
            return false;
        }else {
            enemy.takeDamage(attack);
            if(!enemy.isAlive() && enemy instanceof NPC){
                NPC npcenemy = (NPC)enemy;
                increaseExperiencePoint(npcenemy.getExperiencePoints());
            }
            return true;
        }
    }
    @Override
    public boolean attack(Combatant enemy){
        return attack(enemy, new Attack(getAttackPower()));
    }

    public void takeDamage(Attack attack){
        int damage = attack.getAttackPower(null, null);
        decreaseHealth(damage);
    }

    public void loot(Monster monster){
    	ArrayList<Item> lootedItems = new ArrayList();
    	lootedItems.addAll(monster.getLooted());
    	for(Item i:lootedItems){
    		getCupboard().store(i);
		}
	}

	public void takeQuest(Questgiver questgiver, Quest quest){
    	if(questgiver != null && quest != null){
    		questgiver.takeQuest(quest, this);
		}else{
    		throw new IllegalArgumentException("Neither quest or questgiver can't be null");
		}
	}

	public void takeAllQuests(Questgiver questgiver){
    	if(questgiver != null){
    		questgiver.takeAvailableQuests(this);
		}else{
    		throw new IllegalArgumentException("Questgiver cant be null");
		}
	}

    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Player) {
    		Player player = (Player) obj;
    		return player.playerClass == playerClass && player.race == race;
    	} else {
    		return false;
    	}
    }
}
