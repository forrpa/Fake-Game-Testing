package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import equipment.*;
import weapon.*;
import edible.Item;

public class Player {
	private int manaPoint;
    private int healthPoint;
    private int experiencePoint; 
    private Map<ArmorType, Boolean> allowedArmorTypes = new HashMap<ArmorType, Boolean>();
    private Map<String, Equipment> gear = new HashMap<String, Equipment>();
    private Map<Item, Boolean> inventory2 = new HashMap<Item, Boolean>();
    
    //private PlayerClass playerClass;
    //private Race race; //Beror på om vi vill göra klasserna Class och Race, jag kör med String sålänge
    private String playerClass;
    private String race;

    private ArrayList<String> inventory = new ArrayList<>(); //Bör vara hash här eller något ist för String

    public Player (String playerClass, String race, int healthPoint, int experiencePoint){
        this.playerClass = playerClass;
        this.race = race;
        this.healthPoint = healthPoint;
        this.experiencePoint = experiencePoint;
        setArmorTypeHashMap();
    }

	public int getManaPoint() {
		return manaPoint;
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

    public ArrayList<String> getInventory() {
        return inventory;
    }

	public void setManaPoint(int manaPoint) {
		this.manaPoint = manaPoint;
	}

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void setExperiencePoint(int experiencePoint) {
        this.experiencePoint = experiencePoint;
    }
    private void setArmorTypeHashMap() {
    	switch(this.playerClass) {
    	case "Mage":
    		this.allowedArmorTypes.put(ArmorType.CLOTH, true);
    		this.allowedArmorTypes.put(ArmorType.LEATHER, false);
    		this.allowedArmorTypes.put(ArmorType.MAIL, false);
    		this.allowedArmorTypes.put(ArmorType.PLATE, false);
    		break;
    	case "Warrior":
    		this.allowedArmorTypes.put(ArmorType.CLOTH, true);
    		this.allowedArmorTypes.put(ArmorType.LEATHER, true);
    		this.allowedArmorTypes.put(ArmorType.MAIL, true);
    		this.allowedArmorTypes.put(ArmorType.PLATE, true);
    		break;
    	}
    }
    public void equipWeapon(Weapon weapon) {
    	//Work in progress
    }
    public void equipArmor(Equipment armor) throws Exception {
    	if(this.inventory2.get(armor)) {}else {return;}
    	if(this.allowedArmorTypes.get(armor.getArmorType())) {
    		if(armor instanceof Chest) {
    			if(this.gear.get("chest")==null) {
    				this.gear.put("chest", armor);
    			}else {
    				Equipment replacedPiece = this.gear.replace("chest", armor);
    				//replacedPiece will be handled later
    			}
    		}
    	}else {
    		throw new Exception();
    	}
    }

    // methods added by Christoffer
}
