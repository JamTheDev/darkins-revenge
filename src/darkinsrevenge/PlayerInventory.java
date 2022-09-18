/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge;

/**
 *
 * @author Jam
 */
public class PlayerInventory {
    static PlayerInventory instance = null;
    
    private int playerCash = 10;
    private int healthPots = 0;
    private int playerStr = 0;
    private int playerDef = 0;
    
    
    private PlayerInventory() {
        
    }
    
    public static PlayerInventory getInstance() {
        if(instance == null)
            instance = new PlayerInventory();
        
        return instance;
    }
    
    public void increaseHealthPot() {
        healthPots++;
    }
    
    public void increasePlayerDef() {
        playerDef++;
    }
    
    public void increasePlayerStrength() {
        playerStr++;
    }
    
    public void increasePlayerCash(int i) {
        playerCash += i;
    }
    
    public void decreasePlayerCash(int i) {
        playerCash -= i;
    }

    public int getHealthPots() {
        return healthPots;
    }

    public int getPlayerCash() {
        return playerCash;
    }

    public int getPlayerStr() {
        return playerStr;
    }
    
    public int getPlayerDef() {
        return playerDef;
    }
    
    
}
