package player;

import java.util.HashMap;
import java.util.Map;
import equipment.*;
import weapon.*;
import edible.Cupboard;
import item.*;

public class Player {
    private int healthPoint;
    private int experiencePoint; 
    private int level = 1;
    private int nextLevelCap = 100;
    private Map<ArmorType, Boolean> allowedArmorTypes = new HashMap<ArmorType, Boolean>();
    private Map<String, Boolean> allowedWeaponTypes = new HashMap<String,Boolean>();
    private Map<String, Gear> gear = new HashMap<String, Gear>();
    private Inventory playerInventory = new Inventory();
    private Cupboard cupboard;
    
    //private PlayerClass playerClass;
    //private Race race; //Beror på om vi vill göra klasserna Class och Race, jag kör med String sålänge
    private String playerClass;
    private String race;

    public Player(String playerClass, String race, int healthPoint, int experiencePoint){
        this.playerClass = playerClass;
        this.race = race;
        setHealthPoint(healthPoint);
        setExperiencePoint(experiencePoint);
        setArmorTypeHashMap();
        setWeaponTypeHashMap();
    }



    public int getHealthPoint() {
        return healthPoint;
    }

    public int getExperiencePoint() {
        return experiencePoint;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public String getRace() {
        return race;
    }


    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
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
    	this.nextLevelCap = this.nextLevelCap*2;
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
    public void equipWeapon(Weapon weapon) throws Exception {
    	playerInventory.isInInventory(weapon);
    	isPlayerLeveledHighlyEnoughToEquip(weapon);
    	checkIfWeaponToBeEquippedIsAllowedType(weapon);
    }
    private boolean isPlayerLeveledHighlyEnoughToEquip(Item item) throws Exception {
    	if(this.level < item.getRequiredLevel()) {throw new Exception();}else {return true;}
    }
    private void equippingArmor(Equipment armor) {
    	if(!this.gear.containsKey(armor.getSlot())) {
			this.gear.put(armor.getSlot(), armor);
		}else {
			Gear replacedPiece = (Gear) this.gear.replace(armor.getSlot(), armor);
			this.playerInventory.addItem(replacedPiece);
		}
    }
    private void handleShieldsInWeaponSwap() {
    	if(gear.get("shield")==null) {return;}else {
    		Gear shield = gear.get("shield");
    		playerInventory.addItem(shield);
    		gear.remove("shield");
    	}
    }
    private void equippingWeapon(Weapon weapon) {
    	if(weapon.getSize() == WeaponSize.TwoHanded) {handleShieldsInWeaponSwap();}
    	if(!this.gear.containsKey("weapon")) {
			this.gear.put("weapon", weapon);
		}else {
			Gear replacedPiece = this.gear.replace("weapon", weapon);
			this.playerInventory.addItem(replacedPiece);
		}
    }
    private void checkIfArmorToBeEquippedIsAllowedType(Equipment armor) throws Exception {
    	if(this.allowedArmorTypes.get(armor.getArmorType())) {
    		equippingArmor(armor);
    		this.playerInventory.getOutItem(armor);
    	}else {
    		throw new Exception("Armor type not allowed.");
    	}
    }
    private void checkIfWeaponToBeEquippedIsAllowedType(Weapon weapon) throws Exception {
    	if(this.allowedWeaponTypes.get(weapon.getType())) {
    		equippingWeapon(weapon);
    		this.playerInventory.getOutItem(weapon);
    	}else {
    		throw new Exception("Weapon type not allowed.");
    	}
    }
    public void equipArmor(Equipment armor) throws Exception{
    	playerInventory.isInInventory(armor);
    	isPlayerLeveledHighlyEnoughToEquip(armor);
    	checkIfArmorToBeEquippedIsAllowedType(armor);
    }

	public Cupboard getCupboard() {
		return cupboard;
	}

	public void setCupboard(Cupboard cupboard) {
		this.cupboard = cupboard;
	}

    // methods added by Christoffer


    //Jennifer
    public void addToInventory(Item item) {
        playerInventory.addItem(item);
    }

    public void removeFromInventory(Item item) {
        playerInventory.removeItem(item);
    }

    public boolean isInInventory(Item item) {
        return playerInventory.isInInventory(item);
    }

    public int getInventoryCount(Item item){
        return playerInventory.getCount(item);
    }
}
