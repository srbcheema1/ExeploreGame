package exeploreGame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GamePannel extends javax.swing.JPanel {

    final private Border darkborder = new LineBorder(Color.DARK_GRAY, 1);
    final private Border lightborder = new LineBorder(Color.lightGray, 1);
    final private Border nullborder = new LineBorder(Color.darkGray, 0);
    
    //gamepanels
    public WelcomePannel welcomePannel;
    public HomePannel homePannel;
    public InstructionPannel instructionPannel;
    public GamePannel1 gamePannel1;
    public GamePannel2 gamePannel2;
    public GamePannel3 gamePannel3;
    public GamePannel4 gamePannel4;
    public GamePannel5 gamePannel5;
    public GamePannel6 gamePannel6;
    public GamePannel7 gamePannel7;
    public GamePannel8 gamePannel8;
    public FinalPannel finalPannel;
    
    
    final private CardLayout card=new CardLayout();//layout for controlPannel
    
    //game variables
    final int hours=2,minutes=0,seconds=0;
    final int timerlock=0;
    final Timer clockTimer;
    int totalScore;
    
    public GamePannel() {
        initComponents();
        myinit();
    }//constructor
    
    public final void myinit(){
        setSize(970, 690);//our standard game pannel size
        setOpaque(false);//to make buttons transparent
        TaskBarPannel.setBackground(new Color(34,34,34));
        controlPannel.setLayout(card);
        clockTimer = new Timer(1000,new ClockUpdater());
        userIdLabel.setText(ExeploreGame.userId);
        
        createGames();//creates games
        
        initBoard();//level one start 
        
    }//my init
    
    public void createGames(){
        welcomePannel=new WelcomePannel(this);
        controlPannel.add(welcomePannel,"welcomePannel");
        final homePannel=new HomePannel(this);
        controlPannel.add(homePannel,"homePannel");
        instructionPannel=new InstructionPannel(this);
        controlPannel.add(instructionPannel,"instructionPannel");
        
        final gamePannel1=new GamePannel1(this);
        controlPannel.add(gamePannel1,"gamePannel1");
        final gamePannel2=new GamePannel2(this);
        controlPannel.add(gamePannel2,"gamePannel2");
        final gamePannel3=new GamePannel3(this);
        controlPannel.add(gamePannel3,"gamePannel3");
        final gamePannel4=new GamePannel4(this);
        controlPannel.add(gamePannel4,"gamePannel4");
        final gamePannel5=new GamePannel5(this);
        controlPannel.add(gamePannel5,"gamePannel5");
        final gamePannel6=new GamePannel6(this);
        controlPannel.add(gamePannel6,"gamePannel6");
        final gamePannel7=new GamePannel7(this);
        controlPannel.add(gamePannel7,"gamePannel7");
        final gamePannel8=new GamePannel8(this);
        controlPannel.add(gamePannel8,"gamePannel8");
        
        final finalPannel=new FinalPannel(this);
        controlPannel.add(finalPannel,"finalPannel");
    } //creates Games
    
    public void updateGui(){
        
    } //update box display
    
    public void openGame(int index){
        switch(index){
            case 1://tic tac toe
                int cardPannel = 1;
                if(gamePannel1.bestScore<30){
                    card.show(controlPannel,"gamePannel1");
                }    
                else{
                    JOptionPane.showMessageDialog(controlPannel, "Game is completed");
                }
                break;
            case 2://memory
                cardPannel =2;
                if(gamePannel2.bestScore<45){
                    card.show(controlPannel,"gamePannel2");
                    gamePannel2.resetLevel();
                    gamePannel2.getFocus();
                }
                else{
                    JOptionPane.showMessageDialog(controlPannel, "Game is completed");
                } 
                break;
            case 3://queen8
                cardPannel =3;
                if(gamePannel3.bestScore<40){
                    card.show(controlPannel,"gamePannel3");
                }
                else{
                    JOptionPane.showMessageDialog(controlPannel, "Game is completed");
                } 
                break;
            case 4://black vs white
                cardPannel =4;
                if(gamePannel4.bestScore<48){
                    card.show(controlPannel,"gamePannel4");
                    gamePannel4.getFocus();
                }
                else{
                    JOptionPane.showMessageDialog(controlPannel, "Game is completed");
                }
                break;
            case 5://pic 4x4
                cardPannel =5;
                if(gamePannel5.bestScore<150){
                    card.show(controlPannel,"gamePannel5");
                    gamePannel5.getFocus();
                }
                else{
                    JOptionPane.showMessageDialog(controlPannel, "Game is completed");
                }
                break;
            case 6://snake
                cardPannel =6;
                if(gamePannel6.score<5000){
                    card.show(controlPannel,"gamePannel6");
                    gamePannel6.getFocus();
                }
                else{
                    JOptionPane.showMessageDialog(controlPannel, "Game is completed");
                }
                break;
            case 7://
                cardPannel =7;
                if(gamePannel7.score<60){
                    card.show(controlPannel,"gamePannel7");
                    gamePannel7.getFocus();
                }
                else{
                    JOptionPane.showMessageDialog(controlPannel, "Game is completed");
                }              
                break;
            case 8:
                cardPannel =8;
                if(gamePannel8.score<120){
                    card.show(controlPannel,"gamePannel8");
                    gamePannel8.getFocus();
                }
                else{
                    JOptionPane.showMessageDialog(controlPannel, "Game is completed");
                }
                break;
        }
        
    }
    
    class ClockUpdater implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            timeLabel.setText(String.valueOf(hours)+":"+String.format("%02d",minutes)+":"+String.format("%02d",seconds));
            if(seconds==0){
                if(minutes==0) {
                    if(hours==0&&minutes==0&&seconds==0) {
                        clockTimer.stop();
                        timerlock=2;
                        JOptionPane.showMessageDialog(controlPannel,"TimeUp");
                        card.show(controlPannel,"finalPannel");
                        finalPannel.shader.start();                    }
                    minutes=60;
                    hours--;
                }
                seconds=60;
                minutes--;
            }
            seconds--;
        }
    }
    
    public void home(){
        if(timerlock==0){
            clockTimer.start();
            TaskBarPannel.setBackground(new Color(99,99,99));
            timerlock=1;
        }
        if(timerlock==1)
            card.show(controlPannel,"homePannel");
    }//home
    
    public void scoreUpdate(){
        totalScore=gamePannel1.bestScore+gamePannel2.bestScore+gamePannel3.bestScore+gamePannel4.bestScore
                +gamePannel5.bestScore+gamePannel6.bestScore+gamePannel7.bestScore+gamePannel8.bestScore;
        scoreLabel.setText("Score : "+String.format("%03d",totalScore));
    }
    
    public void initBoard(){
        card.show(controlPannel,"welcomePannel");
        
        updateGui();//calls update box
    } //initializes board1
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        homeButton = new javax.swing.JButton();
        TaskBarPannel = new javax.swing.JPanel();
        userIdLabel = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        controlPannel = new javax.swing.JPanel();
        background_Image = new javax.swing.JLabel();

        setLayout(null);

        homeButton.setBackground(new java.awt.Color(222, 222, 222));
        homeButton.setForeground(new java.awt.Color(21, 21, 21));
        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/homeButton.jpg"))); // NOI18N
        homeButton.setBorder(nullborder);
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });
        add(homeButton);
        homeButton.setBounds(900, 340, 50, 50);

        TaskBarPannel.setBackground(new java.awt.Color(101, 101, 101));

        userIdLabel.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        userIdLabel.setForeground(new java.awt.Color(36, 36, 36));

        scoreLabel.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(36, 36, 36));
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreLabel.setText("Score : 000");

        timeLabel.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(36, 36, 36));
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("2:00:00");
        timeLabel.setToolTipText("");
        timeLabel.setAlignmentY(1.0F);

        javax.swing.GroupLayout TaskBarPannelLayout = new javax.swing.GroupLayout(TaskBarPannel);
        TaskBarPannel.setLayout(TaskBarPannelLayout);
        TaskBarPannelLayout.setHorizontalGroup(
            TaskBarPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TaskBarPannelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(userIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TaskBarPannelLayout.setVerticalGroup(
            TaskBarPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TaskBarPannelLayout.createSequentialGroup()
                .addGroup(TaskBarPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userIdLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(TaskBarPannel);
        TaskBarPannel.setBounds(60, 30, 820, 20);

        javax.swing.GroupLayout controlPannelLayout = new javax.swing.GroupLayout(controlPannel);
        controlPannel.setLayout(controlPannelLayout);
        controlPannelLayout.setHorizontalGroup(
            controlPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        controlPannelLayout.setVerticalGroup(
            controlPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        add(controlPannel);
        controlPannel.setBounds(60, 50, 820, 620);

        background_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Nexus.png"))); // NOI18N
        add(background_Image);
        background_Image.setBounds(0, 0, 970, 690);
    }// </editor-fold>//GEN-END:initComponents

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        home();
    }//GEN-LAST:event_homeButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TaskBarPannel;
    private javax.swing.JLabel background_Image;
    public javax.swing.JPanel controlPannel;
    private javax.swing.JButton homeButton;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel userIdLabel;
    // End of variables declaration//GEN-END:variables
}
