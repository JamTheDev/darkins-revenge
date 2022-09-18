/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge.Entity;

import darkinsrevenge.BuffDebuff.Buffs;
import darkinsrevenge.BuffDebuff.Debuffs;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Jam
 */
public abstract class Entity {
    private String name;
    private int health, atk, def;
    private String iconPath;
    private static Buffs entityBuff;
    private static Debuffs entityDebuff;
    public boolean isStunned;
    public ArrayList<String> characterStates;
    private String characterDescription;
    
    
    public Entity() {
        entityBuff = Buffs.None;
        entityDebuff = Debuffs.None;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtk() {
        if (Buffs.IncreasedDamage == entityBuff && Debuffs.None == entityDebuff)
            return atk + new Random().nextInt(9);
        if (Debuffs.DecreasedDamage == entityDebuff && Buffs.None == entityBuff)
            return atk - new Random().nextInt(9);
        return atk;
    }

    public int getDef() {
        if (Buffs.Regeneration == entityBuff && Debuffs.None == entityDebuff)
            return health + new Random().nextInt(5);
        if (Debuffs.Bleed == entityDebuff && Buffs.None == entityBuff)
            return atk - new Random().nextInt(5);
        return def;
    }

    public int getHealth() {
        if (Buffs.Regeneration == entityBuff && Debuffs.None == entityDebuff)
            return health + 10;
        if (Debuffs.Bleed == entityDebuff && Buffs.None == entityBuff)
            return atk - 10;
        return health;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getName() {
        return name;
    }
    
    public void setBuff(Buffs buff) {
        Entity.entityBuff = buff;
    }
    
    public void setDebuff(Debuffs debuff) {
        Entity.entityDebuff = debuff;
    }

    public static Buffs getEntityBuff() {
        return entityBuff;
    }

    public static Debuffs getEntityDebuff() {
        return entityDebuff;
    }
    
    
    public void setStunned(boolean val) {
        this.isStunned = val;
    }
    
    public void setCharacterStates(ArrayList<String> states) {
        this.characterStates = states;
    }
    
    public ArrayList<String> getCharacterStates() {
        return characterStates;
    }
    
    public int getCharacterStateCount() {
        return characterStates.size();
    }
    
    public void setCharacterDescription(String s) {
        this.characterDescription = s;
    }
    
    public String getCharacterDescription() {
        return this.characterDescription;
    }
    
    
    
    public abstract int getBasicAttackDamage();
    public abstract int getSkill1Damage();
    public abstract int getSkill2Damage();
    public abstract int getSkill3Damage();
    public abstract int getUltDamage();
    public abstract String[] getSkillList();
    
}
