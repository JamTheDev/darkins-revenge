/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge.Champions;

import darkinsrevenge.Entity.Entity;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Jam
 */
public class MeleeDuelyst extends Entity {
    
    public MeleeDuelyst() {
        ArrayList<String> icons = new ArrayList<>();
        icons.add("./Resources/MeleeDuelyst/duelystIdle.gif");
        icons.add("./Resources/MeleeDuelyst/duelystAttack1.gif");
        icons.add("./Resources/MeleeDuelyst/duelystAttack2.gif");
        this.setAtk(12);
        this.setDef(3);
        this.setName("Kogthamauth");
        this.setHealth(100);
        this.setCharacterStates(icons);
        this.setCharacterDescription("Kogthamauth, is self-reliant, cross and eager. Which isn't out of the ordinary\nfor someone with his unsettling past.\n" +
"\n" +
"He was born and grew up in a poor family in a normal city, he lived happily until\nhe was about 14 years old, but at that point life changed.\n" +
"\n" +
"He destroyed someone's life during a rebellion and was headed for a life of crime. While persued by a criminal gang he had to survive in a pitiless world.\nBut with his inginuity and courage, he managed to conquer all fears and doubts and keep ahead of the curve. This has turned him into the man he is today.\n" +
"\n" +
"While still constantly on the move, he now works on helping people. By doing so, he hopes to start life over on a good note and finally find friends he has never had.");
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
        return new String[] {"Rapid Punch", "Criti-Strike", "Upper Crunch"};
    }
}
