/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge.Champions;

import darkinsrevenge.Entity.Entity;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jam
 */
public class Pantheon extends Entity {
    
    
    
    public Pantheon() {
        ArrayList<String> icons = new ArrayList<>();
        icons.add("./Resources/pantheon.gif");
        icons.add("./Resources/pantheon.gif");
        icons.add("./Resources/pantheon.gif");
        this.setAtk(20);
        this.setDef(10);
        this.setHealth(10);
        this.setName("Pantheon");
        this.setCharacterStates(icons);
        this.setIconPath("./Resources/pantheon.gif");
        this.setCharacterDescription("The peerless warrior known as Pantheon is a nigh-unstoppable paragon of battle. "
                + "He was born among the Rakkor, a warlike people living on the flanks of Mount Targon, and after climbing the mountain's treacherous peak and being deemed worthy,"
                + "he was chosen to become the earthly incarnation of the celestial Aspect of War.");
        
    }

    @Override
    public int getBasicAttackDamage() {
        if(this.isStunned) return 0;
        return this.getAtk(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSkill1Damage() {
        if(this.isStunned) return 0;
        return this.getAtk() + new Random().nextInt(10);
    }

    @Override
    public int getSkill2Damage() {
        if(this.isStunned) return 0;
        return this.getAtk() + new Random().nextInt(5);
    }

    @Override
    public int getSkill3Damage() {
        if(this.isStunned) return 0;
        return this.getAtk() + new Random().nextInt(5); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getUltDamage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    public String[] getSkillList() {
        return new String[] {"Comet Spear", "Shield Vault", "Aegis Assault"};
    }
    
}
