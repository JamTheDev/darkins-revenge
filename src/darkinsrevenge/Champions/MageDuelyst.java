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
public class MageDuelyst extends Entity {
    
    public MageDuelyst() {
        ArrayList<String> icons = new ArrayList<>();
        icons.add("./Resources/MageDuelyst/mageDuelystIdle.gif");
        icons.add("./Resources/MageDuelyst/mageDuelystAttack1.gif");
        icons.add("./Resources/MageDuelyst/mageDuelystAttack2.gif");
        this.setAtk(12);
        this.setDef(3);
        this.setName("Kogthamauth");
        this.setHealth(100);
        this.setCharacterStates(icons);
        this.setCharacterDescription("Graeyam is dauntless, skillful and fierce. But this is all just a facade, a mechanism to deal with her terrible past.\n" +
"\n" +
"She was born and grew up in a great family in a major city, she lived free of trouble until she was about 5 years old, but at that point things took a turn for the worst.\n" +
"\n" +
"She lost her mother in a freak fire and was forgotten by everybody. When she grew up while trying to forget the past, she met a psycho-crazy wizard. The wizard promised her power, in exchange for her identity. Out of desperation, she agreed. \n" +
"\n" +
"With her new power acquired, she promises to protect humanity with her power. And she hopes to find joy and happiness in life and finally find joys and comforts of life she has never had.");
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
        return new String[] {"Fire punch", "Criti-Fire", "Ground Shaker"};
    }
}
