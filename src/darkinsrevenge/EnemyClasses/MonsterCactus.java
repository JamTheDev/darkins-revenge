/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge.EnemyClasses;

import darkinsrevenge.Entity.Entity;
import java.awt.EventQueue;

/**
 *
 * @author Jam
 */
public class MonsterCactus extends Entity {
    
    public MonsterCactus() {
        loadMonsterProperty();
    }
    public void loadMonsterProperty() {
        this.setAtk(5);
        this.setDef(5);
        this.setName("Cactus");
        this.setHealth(100);
        this.setIconPath("./Resources/monsterCactus.gif");
    }

    @Override
    public int getBasicAttackDamage() {
        return this.getAtk();
    }

    @Override
    public int getSkill1Damage() {
        return this.getAtk() + 10;
    }

    @Override
    public int getSkill2Damage() {
        
        return this.getAtk() + 15;
    }

    @Override
    public int getSkill3Damage() {
        return -1;
    }

    @Override
    public int getUltDamage() {
        return -1;
    }

    @Override
    public String[] getSkillList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
