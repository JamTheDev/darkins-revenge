/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge;

import darkinsrevenge.Champions.MageDuelyst;
import darkinsrevenge.Champions.MeleeDuelyst;
import darkinsrevenge.Champions.Pantheon;
import darkinsrevenge.Champions.SpearDuelyst;
import darkinsrevenge.Entity.Entity;
import javax.swing.ImageIcon;

/**
 *
 * @author Jam
 */
public class HeroPickScreen extends javax.swing.JPanel {

    /**
     * Creates new form HeroPickScreen
     */
    MeleeDuelyst meleeDuelyst;
    Pantheon pantheonChar;
    MageDuelyst mageDuelystChar;
    SpearDuelyst spearDuelystChar;
    
    MainScreen sc;
    
    
    
    public HeroPickScreen(MainScreen sc) {
        initComponents();
        initializeChampions();
        this.sc = sc;
        startGame.setVisible(false);
        startGame.setEnabled(false);
    }
    
    public void initializeChampions() {
        meleeDuelyst = new MeleeDuelyst();
        pantheonChar = new Pantheon();
        mageDuelystChar = new MageDuelyst();
        spearDuelystChar = new SpearDuelyst();
    } 
    
    private void selectCharacter(Entity character) {
        GameManager.championSelected = character;
        characterName.setText(GameManager.championSelected.getName());
        characterDescription.setText("<html>" + character.getCharacterDescription() + "</html>");
        skill1.setText(character.getSkillList()[0]);
        skill2.setText(character.getSkillList()[1]);
        skill3.setText(character.getSkillList()[2]);
        characterShowcase.setIcon(new ImageIcon(this.getClass().getResource(character.getCharacterStates().get(0))));
        startGame.setVisible(true);
        startGame.setEnabled(true);
    }
  
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        characterDescription = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        skill1 = new javax.swing.JLabel();
        skill2 = new javax.swing.JLabel();
        skill3 = new javax.swing.JLabel();
        startGame = new javax.swing.JButton();
        characterName = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        spearDuelyst = new javax.swing.JLabel();
        pantheon = new javax.swing.JLabel();
        mageDuelyst = new javax.swing.JLabel();
        fighterDuelyst = new javax.swing.JLabel();
        characterShowcase = new javax.swing.JLabel();

        setBackground(new java.awt.Color(14, 74, 114));
        setPreferredSize(new java.awt.Dimension(1200, 700));

        jPanel2.setBackground(new java.awt.Color(2, 87, 195));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Hover on a character to view its info!");

        characterDescription.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Skills");

        skill1.setForeground(new java.awt.Color(255, 255, 255));
        skill1.setPreferredSize(new java.awt.Dimension(34, 30));

        skill2.setForeground(new java.awt.Color(255, 255, 255));
        skill2.setPreferredSize(new java.awt.Dimension(34, 30));

        skill3.setForeground(new java.awt.Color(255, 255, 255));
        skill3.setPreferredSize(new java.awt.Dimension(34, 30));

        startGame.setText("Start Game!");
        startGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameActionPerformed(evt);
            }
        });

        characterName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        characterName.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startGame, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(characterName)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(skill3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addComponent(skill2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(skill1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(characterDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(characterName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(characterDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(skill1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(skill2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(skill3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(startGame)
                .addGap(35, 35, 35))
        );

        jPanel3.setBackground(new java.awt.Color(110, 171, 245));

        spearDuelyst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkinsrevenge/Resources/SpearDuelyst/spearDuelystIcon.gif"))); // NOI18N
        spearDuelyst.setPreferredSize(new java.awt.Dimension(100, 100));
        spearDuelyst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spearDuelystMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                spearDuelystMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                spearDuelystMouseExited(evt);
            }
        });

        pantheon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkinsrevenge/Resources/pantheonIdle.gif"))); // NOI18N
        pantheon.setPreferredSize(new java.awt.Dimension(100, 100));
        pantheon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pantheonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pantheonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pantheonMouseExited(evt);
            }
        });

        mageDuelyst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkinsrevenge/Resources/MageDuelyst/mageDuelystIcon.gif"))); // NOI18N
        mageDuelyst.setPreferredSize(new java.awt.Dimension(100, 100));
        mageDuelyst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mageDuelystMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mageDuelystMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mageDuelystMouseExited(evt);
            }
        });

        fighterDuelyst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkinsrevenge/Resources/MeleeDuelyst/duelystIcon.gif"))); // NOI18N
        fighterDuelyst.setPreferredSize(new java.awt.Dimension(100, 100));
        fighterDuelyst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fighterDuelystMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fighterDuelystMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fighterDuelystMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fighterDuelyst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pantheon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spearDuelyst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mageDuelyst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(fighterDuelyst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mageDuelyst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pantheon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spearDuelyst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(characterShowcase, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(characterShowcase, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void startGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameActionPerformed
        sc.switchToBattleScreen();
    }//GEN-LAST:event_startGameActionPerformed

    private void fighterDuelystMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fighterDuelystMouseEntered
        if (GameManager.championSelected != null) return;
        characterDescription.setText("<html>" + meleeDuelyst.getCharacterDescription() + "</html>");
        characterName.setText(meleeDuelyst.getName());
        skill1.setText(meleeDuelyst.getSkillList()[0]);
        skill2.setText(meleeDuelyst.getSkillList()[1]);
        skill3.setText(meleeDuelyst.getSkillList()[2]);
        characterShowcase.setIcon(new ImageIcon(this.getClass().getResource(meleeDuelyst.getCharacterStates().get(0))));
        
    }//GEN-LAST:event_fighterDuelystMouseEntered

    private void fighterDuelystMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fighterDuelystMouseExited
        if (GameManager.championSelected != null) return;
        characterDescription.setText("");
        characterName.setText("");
        skill1.setText("");
        skill2.setText("");
        skill3.setText("");
        characterShowcase.setIcon(null);
    }//GEN-LAST:event_fighterDuelystMouseExited

    private void mageDuelystMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mageDuelystMouseEntered
        if (GameManager.championSelected != null) return;
        characterDescription.setText("<html>" + mageDuelystChar.getCharacterDescription() + "</html>");
        characterName.setText(mageDuelystChar.getName());
        skill1.setText(mageDuelystChar.getSkillList()[0]);
        skill2.setText(mageDuelystChar.getSkillList()[1]);
        skill3.setText(mageDuelystChar.getSkillList()[2]);
        characterShowcase.setIcon(new ImageIcon(this.getClass().getResource(mageDuelystChar.getCharacterStates().get(0))));
        
    }//GEN-LAST:event_mageDuelystMouseEntered

    private void mageDuelystMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mageDuelystMouseExited
        if (GameManager.championSelected != null) return;
        characterDescription.setText("");
        characterName.setText("");
        skill1.setText("");
        skill2.setText("");
        skill3.setText("");
        characterShowcase.setIcon(null);
    }//GEN-LAST:event_mageDuelystMouseExited

    private void pantheonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pantheonMouseClicked
        selectCharacter(pantheonChar);
    }//GEN-LAST:event_pantheonMouseClicked

    private void pantheonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pantheonMouseEntered
        if (GameManager.championSelected != null) return;
        characterDescription.setText("<html>" + pantheonChar.getCharacterDescription() + "</html>");
        characterName.setText(pantheonChar.getName());
        skill1.setText(pantheonChar.getSkillList()[0]);
        skill2.setText(pantheonChar.getSkillList()[1]);
        skill3.setText(pantheonChar.getSkillList()[2]);
        characterShowcase.setIcon(new ImageIcon(this.getClass().getResource(pantheonChar.getCharacterStates().get(0))));
        
    }//GEN-LAST:event_pantheonMouseEntered

    private void pantheonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pantheonMouseExited
        if (GameManager.championSelected != null) return;
        characterDescription.setText("");
        characterName.setText("");
        skill1.setText("");
        skill2.setText("");
        skill3.setText("");
        characterShowcase.setIcon(null);
    }//GEN-LAST:event_pantheonMouseExited

    private void fighterDuelystMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fighterDuelystMouseClicked
        selectCharacter(meleeDuelyst);
    }//GEN-LAST:event_fighterDuelystMouseClicked

    private void mageDuelystMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mageDuelystMouseClicked
        selectCharacter(mageDuelystChar);
    }//GEN-LAST:event_mageDuelystMouseClicked

    private void spearDuelystMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spearDuelystMouseClicked
        selectCharacter(spearDuelystChar);
    }//GEN-LAST:event_spearDuelystMouseClicked

    private void spearDuelystMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spearDuelystMouseEntered
        if (GameManager.championSelected != null) return;
        characterDescription.setText("<html>" + spearDuelystChar.getCharacterDescription() + "</html>");
        skill1.setText(spearDuelystChar.getSkillList()[0]);
        skill2.setText(spearDuelystChar.getSkillList()[1]);
        skill3.setText(spearDuelystChar.getSkillList()[2]);
        characterShowcase.setIcon(new ImageIcon(this.getClass().getResource(spearDuelystChar.getCharacterStates().get(0))));
    }//GEN-LAST:event_spearDuelystMouseEntered

    private void spearDuelystMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spearDuelystMouseExited
        if (GameManager.championSelected != null) return;
        characterDescription.setText("");
        skill1.setText("");
        skill2.setText("");
        skill3.setText("");
        characterShowcase.setIcon(null);
    }//GEN-LAST:event_spearDuelystMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel characterDescription;
    private javax.swing.JLabel characterName;
    private javax.swing.JLabel characterShowcase;
    private javax.swing.JLabel fighterDuelyst;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel mageDuelyst;
    private javax.swing.JLabel pantheon;
    private javax.swing.JLabel skill1;
    private javax.swing.JLabel skill2;
    private javax.swing.JLabel skill3;
    private javax.swing.JLabel spearDuelyst;
    private javax.swing.JButton startGame;
    // End of variables declaration//GEN-END:variables
}
