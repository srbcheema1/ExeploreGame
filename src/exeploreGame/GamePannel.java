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

    int cardPannel=0;//index of card pannel being displayed
    
    Border darkborder = new LineBorder(Color.DARK_GRAY, 1);
    Border lightborder = new LineBorder(Color.lightGray, 1);
    Border nullborder = new LineBorder(Color.darkGray, 0);
    
    //gamepanels
    public HomePannel homePannel;
    public GamePannel1 gamePannel1;
    public GamePannel2 gamePannel2;
    public GamePannel3 gamePannel3;
    public GamePannel4 gamePannel4;
    public GamePannel5 gamePannel5;
    public GamePannel6 gamePannel6;
    public GamePannel7 gamePannel7;
    public GamePannel8 gamePannel8;
    
    
    CardLayout card=new CardLayout();//layout for controlPannel
    
    //game variables
    int hours=2,minutes=0,seconds=0;
    Timer clockTimer;
    
    public GamePannel() {
        initComponents();
        myinit();
    }//constructor
    
    public final void myinit(){
        setSize(970, 690);//our standard game pannel size
        setOpaque(false);//to make buttons transparent
        
        controlPannel.setLayout(card);
        
        clockTimer = new Timer(1000,new ClockUpdater());
        clockTimer.start();
        
        createGames();//creates games
        
        initBoard();//level one start 
        
    }//my init
    
    public void createGames(){
        homePannel=new HomePannel(this);
        controlPannel.add(homePannel,"homePannel");
        gamePannel1=new GamePannel1(this);
        controlPannel.add(gamePannel1,"gamePannel1");
        gamePannel2=new GamePannel2(this);
        controlPannel.add(gamePannel2,"gamePannel2");
        gamePannel3=new GamePannel3(this);
        controlPannel.add(gamePannel3,"gamePannel3");
        gamePannel4=new GamePannel4(this);
        controlPannel.add(gamePannel4,"gamePannel4");
        gamePannel5=new GamePannel5(this);
        controlPannel.add(gamePannel5,"gamePannel5");
        gamePannel6=new GamePannel6(this);
        controlPannel.add(gamePannel6,"gamePannel6");
        gamePannel7=new GamePannel7(this);
        controlPannel.add(gamePannel7,"gamePannel7");
        gamePannel8=new GamePannel8(this);
        controlPannel.add(gamePannel8,"gamePannel8");
    } //creates Games
    
    public void updateGui(){
        
    } //update box display
    
    public void openGame(int index){
        switch(index){
            case 1:
                cardPannel=1;
                if(gamePannel1.score<30){
                    card.show(controlPannel,"gamePannel1");
                }    
                else{
                    JOptionPane.showMessageDialog(controlPannel, "Game is completed");
                }
                break;
            case 2:
                cardPannel=2;
                if(gamePannel2.score<45){
                    card.show(controlPannel,"gamePannel2");
                    gamePannel2.getFocus();
                }
                else{
                    JOptionPane.showMessageDialog(controlPannel, "Game is completed");
                } 
                break;
            case 3:
                cardPannel=3;
                if(gamePannel3.score<30){
                    card.show(controlPannel,"gamePannel3");
                }
                else{
                    JOptionPane.showMessageDialog(controlPannel, "Game is completed");
                } 
                break;
            case 4:
                cardPannel=4;
                card.show(controlPannel,"gamePannel4");
                gamePannel4.getFocus();
                break;
            case 5:
                cardPannel=5;
                card.show(controlPannel,"gamePannel5");
                gamePannel5.getFocus();
                break;
            case 6:
                cardPannel=6;
                card.show(controlPannel,"gamePannel6");
                gamePannel6.getFocus();
                break;
            case 7:
                cardPannel=7;
                if(gamePannel7.score<50){
                    card.show(controlPannel,"gamePannel7");
                    gamePannel7.getFocus();
                }
                else{
                    JOptionPane.showMessageDialog(controlPannel, "Game is completed");
                }              
                break;
            case 8:
                cardPannel=8;
                if(gamePannel8.score<90){
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
                    }
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
        card.show(controlPannel,"homePannel");
    }//home
    
    public void initBoard(){
        card.show(controlPannel,"homePannel");
        
        updateGui();//calls update box
    } //initializes board1
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        homeButton = new javax.swing.JButton();
        TaskBarPannel = new javax.swing.JPanel();
        rollnumLabel = new javax.swing.JLabel();
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

        scoreLabel.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreLabel.setText("score : 00");

        timeLabel.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("0:00:00");
        timeLabel.setToolTipText("");
        timeLabel.setAlignmentY(1.0F);

        javax.swing.GroupLayout TaskBarPannelLayout = new javax.swing.GroupLayout(TaskBarPannel);
        TaskBarPannel.setLayout(TaskBarPannelLayout);
        TaskBarPannelLayout.setHorizontalGroup(
            TaskBarPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TaskBarPannelLayout.createSequentialGroup()
                .addComponent(rollnumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TaskBarPannelLayout.setVerticalGroup(
            TaskBarPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TaskBarPannelLayout.createSequentialGroup()
                .addGroup(TaskBarPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rollnumLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JLabel rollnumLabel;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
