/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge;

import javax.swing.ImageIcon;

/**
 *
 * @author Jam
 */
public abstract class ChampionClass {
    String championName;
    String championIcon;
    ImageIcon skill1, skill2, skill3;
    public int hp, atk, def;
    int championLevel;

    public String getChampionIcon() {
        return championIcon;
    }

    public String getChampionName() {
        return championName;
    }

    public int getDef() {
        return def;
    }

    public int getAtk() {
        return atk;
    }

    public ImageIcon getSkill1() {
        return skill1;
    }

    public ImageIcon getSkill2() {
        return skill2;
    }

    public ImageIcon getSkill3() {
        return skill3;
    }

    public void setChampionIcon(String championIcon) {
        this.championIcon = championIcon;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setSkill1(ImageIcon skill1) {
        this.skill1 = skill1;
    }

    public void setSkill2(ImageIcon skill2) {
        this.skill2 = skill2;
    }

    public void setSkill3(ImageIcon skill3) {
        this.skill3 = skill3;
    }
    
    
    

    public void setChampionLevel(int championLevel) {
        this.championLevel = championLevel;
    }

    public int getChampionLevel() {
        return championLevel;
    }

    public int getHp() {
        return hp;
    }
}
