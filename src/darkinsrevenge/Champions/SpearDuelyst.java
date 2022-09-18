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
public class SpearDuelyst extends Entity {
    public SpearDuelyst() {
        ArrayList<String> icons = new ArrayList<>();
        icons.add("./Resources/SpearDuelyst/spearDuelystIdle.gif");
        icons.add("./Resources/SpearDuelyst/spearDuelystAttack.gif");
        icons.add("./Resources/SpearDuelyst/spearDuelystAttack.gif");
        this.setAtk(20);
        this.setDef(3);
        this.setName("Anadius");
        this.setHealth(100);
       
        this.setCharacterStates(icons);
        this.setCharacterDescription("Anadius is cross, eager and conceited. Which isn't out of the ordinary for someone with his dreadful past.\n" +
"\n" +
"He was born and grew up in a fairly rich family in an important town, he lived comfortably until he was about 12 years old, but at that point life changed drastically.\n" +
"\n" +
"He lost his home when it was destroyed after a brutal war and was captured\n" +
"\n" +
"Still affected by the past, he now works as a travelling trader. By doing so, he hopes to find answers to the events of the past and finally find joy and love for life he has never had.");
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
        return new String[] {"Spear Shock", "Criti-Shock", "Heaven Piercer"};
    }
}
