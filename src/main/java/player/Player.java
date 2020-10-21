package player;

import java.util.HashMap;
import java.util.Map;
import equipment.*;
import unit.Attack;
import unit.Combatant;
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
    private int strength = 2;
    private int stamina;
    private int intelligence = 2;
    private int agility = 2;
    private Map<ArmorType, Boolean> allowedArmorTypes = new HashMap<ArmorType, Boolean>();
    private Map<String, Boolean> allowedWeaponTypes = new HashMap<String,Boolean>();
    private Map<String, Gear> gear = new HashMap<String, Gear>();
    private Inventory playerInventory = new Inventory();
    private final Cupboard cupboard = new Cupboard(this);

    public Player(String playerClass, String race, int healthPoint, int experiencePoint){
        super(healthPoint, 1, true);
        this.playerClass = playerClass;
        this.race = race;
        setExperiencePoint(experiencePoint);
        setArmorTypeHashMap();
        setWeaponTypeHashMap();
    }

    //Ny Player-konstruktor så jag inte förstör allas tester med en ny variabel
    public Player(String playerClass, String race, int healthPoint, int maxHealthPoint, int experiencePoint){
        super(healthPoint, 1, true);
        this.playerClass = playerClass;
        this.race = race;
        setExperiencePoint(experiencePoint);
        setArmorTypeHashMap();
        setWeaponTypeHashMap();
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

    public void fillHealthBar(){
        setHealthPoint(getMaxHealthPoint());
    }

    public void setHealthPoint(int healthPoint){
        if(healthPoint <= 0){
            super.setHealthPoint(0);
        }else {
            super.setHealthPoint(healthPoint);
        }
    }

    public void increaseExperiencePoint(int experiencePoint){
        this.experiencePoint += experiencePoint;
    }

    public void increaseMaxHealthPoint(int healthPoint){
        this.maxHealthPoint += healthPoint;
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

    @Override
    public boolean attack(Combatant enemy) {
        return false;
    }

    public void takeDamage(Attack attack){

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
