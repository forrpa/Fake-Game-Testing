package quest;

import player.Player;

public class Attack {

    //FIXA, INTEGRERA OSV

    private int timer;
    private Player player;
    private Enemy enemy;

    public Attack(int timer, Player player, Enemy enemy){
        this.timer = timer;
        this.player = player;
        this.enemy = enemy;
    }

    public int getTimer() {
        return timer;
    }

    public void decreaseTimerByOne(){
        if (timer == 0){
            return;
        } else {
            this.timer --;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void attackEnemy(Enemy enemy){

    }

}
