package quest;

import player.Player;

public class Enemy {

    private int health;

    public Enemy(int health){
        this.health = health;
    }

    public int getHealth(){
        return health;
    }

    public void attack(){

    }

    public boolean negotiate(){
        return true;
    }

    public boolean discover(Player player){
        return false;
    }



}
