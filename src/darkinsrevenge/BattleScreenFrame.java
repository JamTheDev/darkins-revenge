/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge;

import darkinsrevenge.BuffDebuff.Buffs;
import darkinsrevenge.BuffDebuff.Debuffs;
import darkinsrevenge.Champions.Pantheon;
import darkinsrevenge.EnemyClasses.BossAatrox;
import darkinsrevenge.EnemyClasses.MonsterCactus;
import darkinsrevenge.EnemyClasses.MonsterScorpion;
import darkinsrevenge.Entity.Entity;
import darkinsrevenge.skills.SkillSelected;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jam
 */
enum state {
    downloadingData,
    downloadedData
}
public class BattleScreenFrame extends javax.swing.JPanel {

    state st = state.downloadingData;
    MonsterScorpion scorpion;
    SkillSelected skillSelected = SkillSelected.None;
    Entity hero;
    int selection = 0;
    int i = 0;
    Icon ic;
    QuizGenerator quiz;
    
    ArrayList<Entity> monsters = new ArrayList<>();
    Entity currentMonster;
    List<String> choices;
    Timer timer;
    DefaultListModel model;
    PlayerInventory inventory;
    
    Image img;
    
    static MainScreen main;
    
    private static BattleScreenFrame instance = null;
    
    public static BattleScreenFrame getInstance(MainScreen s) {
        BattleScreenFrame.main = s;
        if (instance == null)
            instance = new BattleScreenFrame();
        
        return instance;
    }
    
    /**
     * Creates new form BattleScreen
     */
    private BattleScreenFrame() {
        this.setBackground(Color.GREEN);
        inventory = PlayerInventory.getInstance();
       
         try {
            img = ImageIO.read(this.getClass().getResourceAsStream("./Resources/battleback1.png"));
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            MusicThread m = MusicThread.getInstance();
            m.stopMusic();
            m.startMusic("./src/darkinsrevenge/Resources/battleMusic.wav");
            m.play();
            initComponents();
            init();
            initializeQuizComponents();
           
            
            generateEnemy();
            generatePlayer();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(BattleScreenFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initializeQuizComponents() {
        Runnable r = () -> {
          if(timer != null) timer.cancel();
          questionHolder.setText("Loading Question...");
          opt1.setText("Loading...");
          opt2.setText("Loading...");
          opt3.setText("Loading...");
          opt4.setText("Loading...");
          quiz = new QuizGenerator("https://opentdb.com/api.php?amount=2&category=18&difficulty=easy&type=boolean", "https://opentdb.com/api.php?amount=5&category=18&difficulty=easy&type=multiple"); 
          i = new Random().nextInt(5);
          questionHolder.setText("<html>"+quiz.getMultipleChoiceQuestion().get(i).getQuestion()+"</html>");
          choices = quiz.getMultipleChoiceQuestion().get(i).getChoices();
          opt1.setText("<html>" + choices.get(0) + "</html>");
          opt2.setText("<html>" + choices.get(1) + "</html>");
          opt3.setText("<html>" + choices.get(2) + "</html>");
          opt4.setText("<html>" + choices.get(3) + "</html>");
          st = state.downloadedData;
          countdownLabel.setText("15");
          timer = new Timer();
          timer.scheduleAtFixedRate(new TimerTask() {
              int i = 15;
              @Override
              public void run() {
                  if(i >= 0) {
                      countdownLabel.setText(String.valueOf(i));
                      i--;
                  } else {
                      
                      initializeQuizComponents();
                      damagePlayer();
                  }
              }
          }, 0, 1000);
        };
        
        
        
        Thread t = new Thread(r, "thread2");
        t.start();
        
       
    }
    
    private void nextStage() {
        stageLabel.setText("Stage " + String.valueOf(GameManager.currentStage++));
        healPlayer(10);
        
        if(GameManager.currentStage % 5 == 0) {
            inventory.increasePlayerCash(10);
        }
        
    }
      
     public void init() {
        monsters.add(new MonsterScorpion());
        monsters.add(new MonsterCactus());
        
        hero = GameManager.championSelected;
        model = new DefaultListModel();
      
        stageLabel.setText("Stage " + String.valueOf(GameManager.currentStage));
        cash.setText(inventory.getPlayerCash() + "");
        attack.setText(inventory.getPlayerStr() + "");
    
        s1.setText(hero.getSkillList()[0]);
        s2.setText(hero.getSkillList()[1]);
        s3.setText(hero.getSkillList()[2]);
        playerNameLabel.setText(hero.getName());
        
    }
     
    public void updateStats() {
        cash.setText(inventory.getPlayerCash() + "");
        attack.setText(inventory.getPlayerStr() + "");
        def.setText(inventory.getPlayerDef() + "");
    }
    
    public void generatePlayer() {
        ic = new ImageIcon(this.getClass().getResource(hero.getCharacterStates().get(0)));
        playerLabel1.setIcon(ic);
    }
    
    public void generateEnemy() {
        if(GameManager.currentStage == 30) {
             aatroxVoiceLines();
             currentMonster = new BossAatrox();
        } else {
            selection = new Random().nextInt(monsters.size());
            currentMonster = monsters.get(selection);
        }
        
        ImageIcon c = new ImageIcon(this.getClass().getResource(currentMonster.getIconPath()));
        
        enemyName.setText(currentMonster.getName());
        enemyLabel.setIcon(c);
        playerLabel1.setIcon(ic);
    }
    
    public void applyBuff(Entity entity, Buffs buff) {
        entity.setBuff(buff);
    }
    
    public void applyDebuff(Entity entity, Debuffs buff) {
        entity.setDebuff(buff);
    }

    public void damagePlayer() {
        int skillUse = new Random().nextInt(3);
        if(currentMonster.isStunned) {
            model.addElement("Enemy is not stunned anymore!");
            stunEntity(currentMonster);
            return;
        }
        Runnable run = () -> {
            for(int j = 0 ; j < getMonsterDamage(skillUse) + GameManager.currentStage - hero.getDef() / 2 ; j++) {
                playerHealth.setValue(playerHealth.getValue() - 1);
                if(playerHealth.getValue() <= 0) {
                    JOptionPane.showMessageDialog(null, "You lost!", "Alert", JOptionPane.INFORMATION_MESSAGE);
                    instance = new BattleScreenFrame();
                    main.resetBattleScreen();
                    return;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleScreenFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        Thread th = new Thread(run, "damagePlayer");
        th.start();
    }

    public void healPlayer(int amount) {
         model.addElement("Moving on to next Stage!");
         Runnable run = () -> {
            for(int j = 0 ; j < amount + new Random().nextInt(GameManager.currentStage) ; j++) {
                playerHealth.setValue(playerHealth.getValue() + 1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleScreenFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        Thread th = new Thread(run, "Heal Player");
        th.start();
    }
    
    public void aatroxVoiceLines() {
        Runnable r = () -> {
            String[] voiceLines = {"./src/darkinsrevenge/Resources/aatroxEnt1.wav", "./src/darkinsrevenge/Resources/aatroxEnt2.wav", "./src/darkinsrevenge/Resources/aatroxEnt3.wav", "./src/darkinsrevenge/Resources/aatroxEnt4.wav", "./src/darkinsrevenge/Resources/aatroxEnt5.wav", "./src/darkinsrevenge/Resources/aatroxEnt6.wav"};
              
        while (true) {
            try {
                File url = new File(voiceLines[new Random().nextInt(voiceLines.length - 1)]).getAbsoluteFile();
                AudioInputStream a = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(a);
                clip.start();
                Thread.sleep(15000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        };
        
        new Thread(r, "aatroxVC").start();
    }
    
    public void damageEnemy() {
        if(hero.isStunned) {
            model.addElement("Player is not stunned anymore!");
            stunEntity(hero);
            return;
        }
        
        Runnable run = () -> {
            if (skillSelected == SkillSelected.Skill1) {
                     playerLabel1.setIcon(new ImageIcon(this.getClass().getResource(hero.getCharacterStates().get(1))));
                }
                if (skillSelected == SkillSelected.Skill2) {
                    playerLabel1.setIcon(new ImageIcon(this.getClass().getResource(hero.getCharacterStates().get(2))));
                }
                if (skillSelected == SkillSelected.Skill3) {
                    playerLabel1.setIcon(new ImageIcon(this.getClass().getResource(hero.getCharacterStates().get(2))));
                }
            for(int j = 0 ; j < getSkillDamage() + new Random().nextInt(GameManager.currentStage) + inventory.getPlayerStr() ; j++) {
                if (enemyHealth.getValue() <= 0) {
                    resetEnemyHealth();
                    nextStage();
                    return;
                }
                enemyHealth.setValue(enemyHealth.getValue() - 1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleScreenFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
              
                 try {
                    Thread.sleep(1200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleScreenFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                playerLabel1.setIcon(new ImageIcon(this.getClass().getResource(hero.getCharacterStates().get(0))));
                inventory.increasePlayerCash(new Random().nextInt(4) + 1);
                cash.setText(inventory.getPlayerCash() + "");
        };
        
        Thread th = new Thread(run, "damageEnemy");
        th.start();
    }
    
    public int getMonsterDamage(int skillUse) {
        
        switch (skillUse) {
            case 1:
                model.addElement("Enemy used Skill 1!");
                return currentMonster.getSkill1Damage();
            case 2:
                model.addElement("Enemy used Skill 2!");
                if (currentMonster.getName().equals("Cactus") && !hero.isStunned) {  
                    stunEntity(hero);
                }
                return currentMonster.getSkill2Damage();
            case 3:
                return currentMonster.getBasicAttackDamage();
            default:
                return currentMonster.getBasicAttackDamage();
        }
    }
    
    
   
    
    
    
    public int getSkillDamage() {
        
        if (skillSelected == SkillSelected.Skill1) {
            skillSelected = SkillSelected.None;
            return hero.getSkill1Damage();
        }
        
        if (skillSelected == SkillSelected.Skill2) {
            skillSelected = SkillSelected.None;
            if (!currentMonster.isStunned) {
              
                model.addElement("Enemy is stunned!");
                stunEntity(currentMonster);
            }
                
            return hero.getSkill2Damage();
        }
        if (skillSelected == SkillSelected.Skill3) {
            skillSelected = SkillSelected.None;
            return hero.getSkill3Damage();
        }
        return hero.getBasicAttackDamage();
    }
    
    public void resetEnemyHealth() {
        GameManager.currentStage++;
        Runnable run = () -> {
            while(enemyHealth.getValue() < 100) {
                enemyHealth.setValue(enemyHealth.getValue() + 1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleScreenFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        };
        
        Thread th = new Thread(run, "resetEnemyHealth");
        th.start();
        
        enemyLabel.setIcon(null);
        generateEnemy();
    }
    
    

    
    public void stunEntity(Entity entity) {
        model.addElement(entity.getName() + " is now stunned!");
        entity.isStunned = !entity.isStunned;
    }
    
    
    
    
    
    public boolean checkAnswer(String s) {
        return s.equals(quiz.getMultipleChoiceQuestion().get(i).getCorrectAnswer());
    }
    
    
    

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        enemyLabel = new javax.swing.JLabel();
        playerNameLabel = new javax.swing.JLabel();
        enemyHealth = new javax.swing.JProgressBar();
        enemyName = new javax.swing.JLabel();
        stageLabel = new javax.swing.JLabel();
        playerLabel1 = new javax.swing.JLabel();
        playerHealth = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        questionHolder = new javax.swing.JLabel();
        opt1 = new javax.swing.JButton();
        opt2 = new javax.swing.JButton();
        opt3 = new javax.swing.JButton();
        opt4 = new javax.swing.JButton();
        countdownLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Skills = new javax.swing.JLabel();
        s1 = new javax.swing.JLabel();
        s2 = new javax.swing.JLabel();
        s3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        skillSelectedLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        at = new javax.swing.JLabel();
        bag = new javax.swing.JLabel();
        cash = new javax.swing.JLabel();
        attack = new javax.swing.JLabel();
        at1 = new javax.swing.JLabel();
        def = new javax.swing.JLabel();

        enemyLabel.setMaximumSize(new java.awt.Dimension(350, 350));
        enemyLabel.setMinimumSize(new java.awt.Dimension(350, 350));
        enemyLabel.setPreferredSize(new java.awt.Dimension(350, 350));

        playerNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        playerNameLabel.setText("Player Name");

        enemyHealth.setForeground(new java.awt.Color(255, 255, 255));
        enemyHealth.setValue(100);
        enemyHealth.setStringPainted(true);

        enemyName.setForeground(new java.awt.Color(255, 255, 255));
        enemyName.setText("Enemy Name");

        stageLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        stageLabel.setForeground(new java.awt.Color(255, 255, 255));

        playerLabel1.setMaximumSize(new java.awt.Dimension(350, 350));
        playerLabel1.setMinimumSize(new java.awt.Dimension(350, 350));
        playerLabel1.setPreferredSize(new java.awt.Dimension(350, 350));

        playerHealth.setValue(100);
        playerHealth.setStringPainted(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(playerNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(enemyHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enemyName)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(playerLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(enemyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(playerHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(836, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playerNameLabel)
                            .addComponent(enemyName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enemyHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(stageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(enemyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(playerLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(playerHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(364, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Question");

        questionHolder.setForeground(new java.awt.Color(255, 255, 255));
        questionHolder.setText("Loading Question...");

        opt1.setText("jButton1");
        opt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt1ActionPerformed(evt);
            }
        });

        opt2.setText("jButton2");
        opt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt2ActionPerformed(evt);
            }
        });

        opt3.setText("jButton3");
        opt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt3ActionPerformed(evt);
            }
        });

        opt4.setText("jButton4");
        opt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt4ActionPerformed(evt);
            }
        });

        countdownLabel.setForeground(new java.awt.Color(255, 255, 255));
        countdownLabel.setText("0");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("second(s) left!");

        jPanel3.setBackground(new java.awt.Color(102, 153, 255));

        Skills.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Skills.setForeground(new java.awt.Color(255, 255, 255));
        Skills.setText("Select a Skill");

        s1.setForeground(new java.awt.Color(255, 255, 255));
        s1.setText("Skill 1");
        s1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                s1MouseClicked(evt);
            }
        });

        s2.setForeground(new java.awt.Color(255, 255, 255));
        s2.setText("Skill 2");
        s2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                s2MouseClicked(evt);
            }
        });

        s3.setForeground(new java.awt.Color(255, 255, 255));
        s3.setText("Skill3");
        s3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                s3MouseClicked(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Skill Selected:");

        skillSelectedLabel.setForeground(new java.awt.Color(255, 255, 255));
        skillSelectedLabel.setText("None");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cash: ");

        at.setForeground(new java.awt.Color(255, 255, 255));
        at.setText("ATK: ");

        bag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkinsrevenge/Resources/bag.png"))); // NOI18N
        bag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bagMouseClicked(evt);
            }
        });

        cash.setForeground(new java.awt.Color(255, 255, 255));
        cash.setText("0");

        attack.setForeground(new java.awt.Color(255, 255, 255));
        attack.setText("0");

        at1.setForeground(new java.awt.Color(255, 255, 255));
        at1.setText("DEF:");

        def.setForeground(new java.awt.Color(255, 255, 255));
        def.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(Skills)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(s1)
                                    .addComponent(s2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cash))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(at)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(attack)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(at1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(def)))
                                .addGap(16, 16, 16)))
                        .addComponent(bag, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(skillSelectedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(s3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Skills)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s1)
                            .addComponent(jLabel1)
                            .addComponent(cash))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s2)
                            .addComponent(at)
                            .addComponent(attack)
                            .addComponent(at1)
                            .addComponent(def))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(skillSelectedLabel)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bag, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(countdownLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(questionHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(opt3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(opt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(opt2, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(opt4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(countdownLabel)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(questionHolder)
                        .addComponent(opt1))
                    .addComponent(opt2))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opt3)
                    .addComponent(opt4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void opt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt1ActionPerformed

        if(st == state.downloadingData || skillSelected == SkillSelected.None) return;

        if(checkAnswer(choices.get(0))) {
            damageEnemy();
            
            initializeQuizComponents();
            
        }else {
            damagePlayer();
            
            initializeQuizComponents();
        }
      
    }//GEN-LAST:event_opt1ActionPerformed

    private void opt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt2ActionPerformed

        if(st == state.downloadingData || skillSelected == SkillSelected.None) return;
        if(checkAnswer(choices.get(1))) {
            damageEnemy();
            
            initializeQuizComponents();
        }else {
            damagePlayer();
            
           initializeQuizComponents();
        }
        
    }//GEN-LAST:event_opt2ActionPerformed

    private void opt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt3ActionPerformed

        if(st == state.downloadingData || skillSelected == SkillSelected.None) return;
        if(checkAnswer(choices.get(2))) {
            damageEnemy();
            
            initializeQuizComponents();
        }else {
            damagePlayer();
            
            initializeQuizComponents();
        }
      
    }//GEN-LAST:event_opt3ActionPerformed

    private void opt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt4ActionPerformed
        if(st == state.downloadingData || skillSelected == SkillSelected.None) return;
        if(checkAnswer(choices.get(3))) {
            damageEnemy();
            timer.cancel();
            initializeQuizComponents();
        } else {
            damagePlayer();
            
            initializeQuizComponents();
        }
        
    }//GEN-LAST:event_opt4ActionPerformed

    private void s1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_s1MouseClicked
        skillSelected = SkillSelected.Skill1;
        skillSelectedLabel.setText(hero.getSkillList()[0]);
      
    }//GEN-LAST:event_s1MouseClicked

    private void s2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_s2MouseClicked
        skillSelected = SkillSelected.Skill2;
        skillSelectedLabel.setText(hero.getSkillList()[1]);
      
    }//GEN-LAST:event_s2MouseClicked

    private void s3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_s3MouseClicked
        skillSelected = SkillSelected.Skill3;
        skillSelectedLabel.setText(hero.getSkillList()[2]);
      
    }//GEN-LAST:event_s3MouseClicked

    private void bagMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bagMouseClicked
        
        JOptionPane.showMessageDialog(null, new ShopPanel(this), "Player Bag", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_bagMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Skills;
    private javax.swing.JLabel at;
    private javax.swing.JLabel at1;
    private javax.swing.JLabel attack;
    private javax.swing.JLabel bag;
    private javax.swing.JLabel cash;
    private javax.swing.JLabel countdownLabel;
    private javax.swing.JLabel def;
    private javax.swing.JProgressBar enemyHealth;
    private javax.swing.JLabel enemyLabel;
    private javax.swing.JLabel enemyName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton opt1;
    private javax.swing.JButton opt2;
    private javax.swing.JButton opt3;
    private javax.swing.JButton opt4;
    private javax.swing.JProgressBar playerHealth;
    private javax.swing.JLabel playerLabel1;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JLabel questionHolder;
    private javax.swing.JLabel s1;
    private javax.swing.JLabel s2;
    private javax.swing.JLabel s3;
    private javax.swing.JLabel skillSelectedLabel;
    private javax.swing.JLabel stageLabel;
    // End of variables declaration//GEN-END:variables
}
