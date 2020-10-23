package player;

import java.util.HashMap;
import java.util.Map;
import equipment.*;
import unit.Attack;
import unit.Combatant;
import unit.NPC;
import weapon.*;
import edible.Cupboard;
import item.*;
import unit.Unit;

public class Player extends Unit{
	
    //private PlayerClass playerClass;
    //private Race race; //Beror på om vi vill göra klasserna Class och Race, jag kör med String sålänge
    private final String playerClass;
    private final String race;
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
    
    public Player(String playerClass, String race, int maxHealthPoint, int experiencePoint){
        super(maxHealthPoint, 1, true);
        this.playerClass = playerClass;
        this.race = race;
        setExperiencePoint(experiencePoint);
        setArmorTypeHashMap();
        setWeaponTypeHashMap();
        updateAttributes();
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
    	}else if(piece instanceof Weapon){
    		this.attackPower = this.attackPower - (((Weapon) piece).getDamage());
    	}
    	this.strength -= piece.getAttributes()[0];
    	this.agility -= piece.getAttributes()[1];
    	this.intelligence -= piece.getAttributes()[2];
    	this.stamina -= piece.getAttributes()[3];
    }
    private void addAttributesFromNewGear(Gear piece) {
    	if(piece instanceof Equipment) {
    		this.armor = this.armor + ((Equipment) piece).getArmor();
    	}else if(piece instanceof Weapon){
    		this.attackPower = this.attackPower + ((Weapon) piece).getDamage();
    	}
    	this.strength += piece.getAttributes()[0];
    	this.agility += piece.getAttributes()[1];
    	this.intelligence += piece.getAttributes()[2];
    	this.stamina += piece.getAttributes()[3];
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
        this.experiencePoint = experiencePoint;
        if(this.experiencePoint > this.nextLevelCap) {
        	do{
        		increaseLevel();
        	}while(this.experiencePoint > this.nextLevelCap);
        }
    }
    public void increaseLevel() {
    	this.level++;
    	/*if(this.playerClass == "Mage") {
    		this.strength += 1;
    		this.agility += 1;
    		this.intelligence += 2;
    		this.stamina += 1;
    	}else if(this.playerClass == "Warrior") {
    		this.strength += 2;
    		this.agility += 1;
    		this.intelligence += 1;
    		this.stamina += 2;
    	} */ //Bortkommenterade delen vill jag implementera men kan sabba andras tester där karaktärer levlar upp. //Christian
    	this.nextLevelCap = this.nextLevelCap*2;
    }
    public int getNextLevelCap() {return this.nextLevelCap;}

    public void increaseExperiencePoint(int experiencePoint){
        this.experiencePoint += experiencePoint;
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
    	else if(gear instanceof Equipment){slot = ((Equipment) gear).getSlot();wornGear = this.gear.get(slot);}
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
    	if(this.level < item.getRequiredLevel()) {throw new ArithmeticException("This item requires a higher level to use!");}else {return true;}
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


    //Jennifer
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


    public boolean attack(Combatant enemy, Attack attack) {
        if(!isAlive()){
            throw new IllegalStateException("The player is dead");
        }else if(!enemy.isAlive()){
            throw new IllegalStateException("The unit receiving damage is already dead");
        }else if(isGrounded() && !enemy.isGrounded()){
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
        int tempHealth = getHealthPoint() - damage;
        setHealthPoint(tempHealth);
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
