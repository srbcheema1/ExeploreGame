package exeploreGame;

import javax.swing.JOptionPane;


public class GamePannel1 extends javax.swing.JPanel {
    
    public int comp_wins,bot,me,you_win,turn,filler;
    int bestScore=0;
    public int board[][];
    GamePannel gamePannel ;
    
    public GamePannel1(GamePannel gamePannel) {
        this.gamePannel=gamePannel;
        this.turn = 1;
        this.board = new int [3][3];
        this.comp_wins = 0;
        this.bot=-1;
        this.me=1;
        this.you_win=0;
        
        
        initComponents();
        resetButton.setVisible(false);
    }
    
    public int wincheck(){
       if(board[0][0]==me&&board[1][0]==me&&board[2][0]==me)return me;
       else if(board[0][1]==me&&board[1][1]==me&&board[2][1]==me)return me;
       else if(board[0][2]==me&&board[1][2]==me&&board[2][2]==me)return me;
       else if(board[0][0]==me&&board[0][1]==me&&board[0][2]==me)return me;
       else if(board[1][0]==me&&board[1][1]==me&&board[1][2]==me)return me;
       else if(board[2][0]==me&&board[2][1]==me&&board[2][2]==me)return me;
       else if(board[0][0]==me&&board[1][1]==me&&board[2][2]==me)return me;
       else if(board[2][0]==me&&board[1][1]==me&&board[0][2]==me)return me;
        
       else if(board[0][0]==bot&&board[1][0]==bot&&board[2][0]==bot)return bot;
       else if(board[0][1]==bot&&board[1][1]==bot&&board[2][1]==bot)return bot;
       else if(board[0][2]==bot&&board[1][2]==bot&&board[2][2]==bot)return bot;
       else if(board[0][0]==bot&&board[0][1]==bot&&board[0][2]==bot)return bot;
       else if(board[1][0]==bot&&board[1][1]==bot&&board[1][2]==bot)return bot;
       else if(board[2][0]==bot&&board[2][1]==bot&&board[2][2]==bot)return bot;
       else if(board[0][0]==bot&&board[1][1]==bot&&board[2][2]==bot)return bot;
       else if(board[2][0]==bot&&board[1][1]==bot&&board[0][2]==bot)return bot;
        
       else return 0;   
    }
    
    public int ctfill(){//counts filled boxes
        int ct=0;
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(board[i][j]!=0) ct++;
        return ct;
    }
  
    public int box(int i,int j){
        if(i==0&&j==0)
            return 1;
        if(i==0&&j==1)
            return 2;
        if(i==0&&j==2)
            return 3;
        if(i==1&&j==0)
            return 4;
        if(i==1&&j==1)
            return 5;
        if(i==1&& j==2) return 6;
        if(i==2&&j==0) return 7;
        if(i==2&&j==1) return 8;
        if(i==2&&j==2) return 9;
        return 0;
    }
    
    public void movegen(int turn){
        int index = 0,sum=0,n1,n2; int marked[]=new int[9];
        int random;
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
                marked[sum]=board[i][j];
                sum++;
            }
        
        
        if (marked[4]==0 && ctfill()==0) index=4;
        else if (marked[4]==0 && ctfill()==1) index=4;

        else if (marked[4]==1 && ctfill()==1) {
            random=(int)(Math.random()*4);
        if (random==0 && ctfill()==1) index=2;
        else if (random==1 && ctfill()==1) index=6;
        else if (random==2 && ctfill()==1) index=8;
        else if (random==3 && ctfill()==1) index=0;
        }

        else if(marked[0]==bot && marked[1]==bot && marked[2]==0) index=2;
        else if(marked[0]==bot && marked[2]==bot && marked[1]==0 ) index=1;
        else if(marked[1]==bot && marked[2]==bot && marked[0]==0) index=0;
        else if(marked[3]==bot && marked[4]==bot && marked[5]==0) index=5;
        else if(marked[3]==bot && marked[5]==bot && marked[4]==0) index=4;
        else if(marked[4]==bot && marked[5]==bot && marked[3]==0) index=3;
        else if(marked[6]==bot && marked[7]==bot && marked[8]==0) index=8;
        else if(marked[6]==bot && marked[8]==bot && marked[7]==0) index=7;
        else if(marked[7]==bot && marked[8]==bot && marked[6]==0) index=6;
        
        else if(marked[0]==bot && marked[3]==bot && marked[6]==0) index=6;
        else if(marked[0]==bot && marked[6]==bot && marked[3]==0) index=3;
        else if(marked[3]==bot && marked[6]==bot && marked[0]==0) index=0;
        else if(marked[1]==bot && marked[4]==bot && marked[7]==0) index=7;
        else if(marked[1]==bot && marked[7]==bot && marked[4]==0) index=4;
        else if(marked[4]==bot && marked[7]==bot && marked[1]==0) index=1;
        else if(marked[2]==bot && marked[5]==bot && marked[8]==0) index=8;
        else if(marked[2]==bot && marked[8]==bot && marked[5]==0) index=5;
        else if(marked[5]==bot && marked[8]==bot && marked[2]==0) index=2;
        
        else if(marked[0]==bot && marked[4]==bot && marked[8]==0) index=8;
        else if(marked[0]==bot && marked[8]==bot && marked[4]==0) index=4;
        else if(marked[4]==bot && marked[8]==bot && marked[0]==0) index=0;
        else if(marked[2]==bot && marked[4]==bot && marked[6]==0) index=6;
        else if(marked[2]==bot && marked[6]==bot && marked[4]==0) index=4;
        else if(marked[4]==bot && marked[6]==bot && marked[2]==0) index=2;
        //other winning
        else if(marked[0]==me && marked[1]==me && marked[2]==0) index=2;
        else if(marked[0]==me && marked[2]==me && marked[1]==0 ) index=1;
        else if(marked[1]==me && marked[2]==me && marked[0]==0) index=0;
        else if(marked[3]==me && marked[4]==me && marked[5]==0) index=5;
        else if(marked[3]==me && marked[5]==me && marked[4]==0) index=4;
        else if(marked[4]==me && marked[5]==me && marked[3]==0) index=3;
        else if(marked[6]==me && marked[7]==me && marked[8]==0) index=8;
        else if(marked[6]==me && marked[8]==me && marked[7]==0) index=7;
        else if(marked[7]==me && marked[8]==me && marked[6]==0) index=6;
        
        else if(marked[0]==me && marked[3]==me && marked[6]==0) index=6;
        else if(marked[0]==me && marked[6]==me && marked[3]==0) index=3;
        else if(marked[3]==me && marked[6]==me && marked[0]==0) index=0;
        else if(marked[1]==me && marked[4]==me && marked[7]==0) index=7;
        else if(marked[1]==me && marked[7]==me && marked[4]==0) index=4;
        else if(marked[4]==me && marked[7]==me && marked[1]==0) index=1;
        else if(marked[2]==me && marked[5]==me && marked[8]==0) index=8;
        else if(marked[2]==me && marked[8]==me && marked[5]==0) index=5;
        else if(marked[5]==me && marked[8]==me && marked[2]==0) index=2;
        
        else if(marked[0]==me && marked[4]==me && marked[8]==0) index=8;
        else if(marked[0]==me && marked[8]==me && marked[4]==0) index=4;
        else if(marked[4]==me && marked[8]==me && marked[0]==0) index=0;
        else if(marked[2]==me && marked[4]==me && marked[6]==0) index=6;
        else if(marked[2]==me && marked[6]==me && marked[4]==0) index=4;
        else if(marked[4]==me && marked[6]==me && marked[2]==0) index=2;
        else{
            randomfirst();
            this.turn=-turn;
            
            return; 
            
        }
        
         switch (index) {
             case 0:
                 n1=0;
                 n2=0;
                 break;
             case 1:
                 n1=0;
                 n2=1;
                 break;
             case 2:
                 n1=0;
                 n2=2;
                 break;
             case 3:
                 n1=1;
                 n2=0;
                 break;
             case 4:
                 n1=1;
                 n2=1;
                 break;
             case 5:
                 n1=1;
                 n2=2;
                 break;
             case 6:
                 n1=2;
                 n2=0;
                 break;
             case 7:
                 n1=2;
                 n2=1;
                 break;
             case 8:
                 n1=2;
                 n2=2;
                 break;
             default:
                 n1=0;
                 n2=0;
                 break;
         }


        board[n1][n2]=turn;

        if(box(n1,n2)==1)
            box1.setText("X");
        if(box(n1,n2)==2)
            box2.setText("X");
        if(box(n1,n2)==3)
            box3.setText("X");
        if(box(n1,n2)==4)
            box4.setText("X");
        if(box(n1,n2)==5)
            box5.setText("X");
        if(box(n1,n2)==6)
            box6.setText("X");
        if(box(n1,n2)==7)
            box7.setText("X");
        if(box(n1,n2)==8)
            box8.setText("X");
        if(box(n1,n2)==9)
            box9.setText("X");
        this.turn=-turn;
   
    }
    
    public boolean checkboard(int i,int j){
        if(board[i][j]==0){
            return true;
        }
        else 
            return false;
    }
    
    public void randomfirst(){
        int i,j;
        i=(int)(Math.random()*3);
        j=(int)(Math.random()*3);

        if(board[i][j]==0){
            board[i][j]=-1;
            if(box(i,j)==1)
                box1.setText("X");
            if(box(i,j)==2)
                box2.setText("X");
            if(box(i,j)==3)
                box3.setText("X");
            if(box(i,j)==4)
                box4.setText("X");
            if(box(i,j)==5)
                box5.setText("X");
            if(box(i,j)==6)
                box6.setText("X");
            if(box(i,j)==7)
                box7.setText("X");
            if(box(i,j)==8)
                box8.setText("X");
            if(box(i,j)==9)
                box9.setText("X");
        }
        else if(ctfill()<9)
            randomfirst();
             
    }
    
    public void resete(){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                 board[i][j]=0;
       
        
        box1.setText("");
        box2.setText("");
        box3.setText("");
        box4.setText("");
        box5.setText("");
        box6.setText("");
        box7.setText("");
        box8.setText("");
        box9.setText("");
    }
    
    public void showe(){
        int result=wincheck();
        int fill=ctfill();
        
        if(result!=0){
    
    
            if(result==me){
                you_win++;
                if(you_win-comp_wins==1&&bestScore==0){
                    bestScore=10;
                    gamePannel.scoreUpdate();
                    JOptionPane.showMessageDialog(dialog, "you win");
                }
                else if(you_win-comp_wins==2&&bestScore==10){
                    bestScore=20;
                    gamePannel.scoreUpdate();
                    JOptionPane.showMessageDialog(dialog, "you win");
                }
                else if(you_win-comp_wins>=3&&bestScore==20){
                    bestScore=30;
                    you_win=0;
                    comp_wins=0;
                    gamePannel.scoreUpdate();
                    JOptionPane.showMessageDialog(dialog, "you win");
                    gamePannel.home();
                //    setVisible(false); //you can't see me!
                //    dispose(); //Destroy the JFrame object
                }
                else{
                    JOptionPane.showMessageDialog(dialog, "you win");
                }
                
            }
            else{               
                JOptionPane.showMessageDialog(dialog, "BOT wins");
                comp_wins++;
            /*    if(you_win-comp_wins==1)score=10;
                if(you_win-comp_wins==2)score=20;
                if(you_win-comp_wins<=0)score=0;
                if(you_win-comp_wins==3)score=30;   */
            }
            jLabel4.setText("YOU : "+you_win+"");
            jLabel2.setText("COMP : "+comp_wins+"");
            scoreLabel.setText("score : "+bestScore);
            resete();
        }
        if(fill==9){
            JOptionPane.showMessageDialog(dialog, "Draw!");
            resete();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resetButton = new javax.swing.JButton();
        scoreLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dialog = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        box8 = new javax.swing.JButton();
        box9 = new javax.swing.JButton();
        box1 = new javax.swing.JButton();
        box2 = new javax.swing.JButton();
        box3 = new javax.swing.JButton();
        box4 = new javax.swing.JButton();
        box5 = new javax.swing.JButton();
        box6 = new javax.swing.JButton();
        box7 = new javax.swing.JButton();
        back = new javax.swing.JLabel();

        setToolTipText("");
        setName(""); // NOI18N
        setOpaque(false);
        setLayout(null);

        resetButton.setText("reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        add(resetButton);
        resetButton.setBounds(632, 325, 110, 40);

        scoreLabel.setFont(new java.awt.Font("TakaoPGothic", 1, 24)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(252, 236, 236));
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreLabel.setText("Score : 0");
        add(scoreLabel);
        scoreLabel.setBounds(580, 130, 180, 50);

        jLabel5.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(236, 236, 236));
        jLabel5.setText("TIC TAC TOE");
        add(jLabel5);
        jLabel5.setBounds(310, 30, 160, 40);

        jLabel2.setFont(new java.awt.Font("Ume P Gothic S5", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(230, 230, 230));
        jLabel2.setText("COMP : 0");
        add(jLabel2);
        jLabel2.setBounds(100, 510, 100, 40);
        add(dialog);
        dialog.setBounds(550, 260, 0, 0);

        jLabel4.setFont(new java.awt.Font("UnGungseo", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(211, 211, 211));
        jLabel4.setText("YOU : 0");
        add(jLabel4);
        jLabel4.setBounds(320, 500, 90, 50);

        helpButton.setText("help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        add(helpButton);
        helpButton.setBounds(630, 429, 110, 40);

        jPanel2.setBackground(new java.awt.Color(17, 1, 1));
        jPanel2.setLayout(null);

        box8.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        box8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box8ActionPerformed(evt);
            }
        });
        jPanel2.add(box8);
        box8.setBounds(111, 221, 110, 110);

        box9.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        box9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box9ActionPerformed(evt);
            }
        });
        jPanel2.add(box9);
        box9.setBounds(221, 221, 110, 110);

        box1.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        box1.setMaximumSize(new java.awt.Dimension(18, 12));
        box1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box1ActionPerformed(evt);
            }
        });
        jPanel2.add(box1);
        box1.setBounds(0, 0, 110, 110);

        box2.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        box2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box2ActionPerformed(evt);
            }
        });
        jPanel2.add(box2);
        box2.setBounds(111, 0, 110, 110);

        box3.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        box3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box3ActionPerformed(evt);
            }
        });
        jPanel2.add(box3);
        box3.setBounds(221, 0, 110, 110);

        box4.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        box4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box4ActionPerformed(evt);
            }
        });
        jPanel2.add(box4);
        box4.setBounds(0, 111, 110, 110);

        box5.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        box5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box5ActionPerformed(evt);
            }
        });
        jPanel2.add(box5);
        box5.setBounds(111, 111, 110, 110);

        box6.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        box6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box6ActionPerformed(evt);
            }
        });
        jPanel2.add(box6);
        box6.setBounds(221, 111, 110, 110);

        box7.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        box7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box7ActionPerformed(evt);
            }
        });
        jPanel2.add(box7);
        box7.setBounds(0, 221, 110, 110);

        add(jPanel2);
        jPanel2.setBounds(79, 138, 332, 332);

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gamePannelBackground1.jpg"))); // NOI18N
        add(back);
        back.setBounds(0, 0, 820, 620);
    }// </editor-fold>//GEN-END:initComponents

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        resete();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void box8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box8ActionPerformed
        if(checkboard(2,1)){
            board[2][1]=turn;
            box8.setText("O");

            turn=-turn;
            showe();

            movegen(turn);

            showe();
        }
        else JOptionPane.showMessageDialog(null,"Position filled");
    }//GEN-LAST:event_box8ActionPerformed

    private void box9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box9ActionPerformed
        if(checkboard(2,2)){
            board[2][2]=turn;

            box9.setText("O");

            turn=-turn;
            showe();
            //if(ctfill()==0)
            //  randomfirst();
            //else
            movegen(turn);
            showe();
        }
        else JOptionPane.showMessageDialog(null,"Position filled");
    }//GEN-LAST:event_box9ActionPerformed

    private void box1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box1ActionPerformed
        if(checkboard(0,0)){
            board[0][0]=turn;

            box1.setText("O");
            turn=-turn;
            showe();
            //if(ctfill()==0)
            // randomfirst();
            //else
            movegen(turn);
            showe();
        }
        else JOptionPane.showMessageDialog(null,"Position filled");
    }//GEN-LAST:event_box1ActionPerformed

    private void box2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box2ActionPerformed
        if(checkboard(0,1)){
            board[0][1]=turn;

            box2.setText("O");
            turn=-turn;
            showe();
            //if(ctfill()==0)
            //  randomfirst();
            //else
            movegen(turn);
            showe();
        }
        else JOptionPane.showMessageDialog(null,"Position filled");
    }//GEN-LAST:event_box2ActionPerformed

    private void box3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box3ActionPerformed
        if(checkboard(0,2)){
            board[0][2]=turn;

            box3.setText("O");
            turn=-turn;
            showe();
            //if(ctfill()==0)
            //  randomfirst();
            //else
            movegen(turn);
            showe();
        }
        else JOptionPane.showMessageDialog(null,"Position filled");
    }//GEN-LAST:event_box3ActionPerformed

    private void box4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box4ActionPerformed
        if(checkboard(1,0)){
            board[1][0]=turn;

            box4.setText("O");
            turn=-turn;
            showe();
            //if(ctfill()==0)
            //  randomfirst();
            //else
            movegen(turn);
            showe();
        }
        else JOptionPane.showMessageDialog(null,"Position filled");
    }//GEN-LAST:event_box4ActionPerformed

    private void box5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box5ActionPerformed

        if(checkboard(1,1)){
            board[1][1]=turn;

            box5.setText("O");
            turn=-turn;
            showe();
            //if(ctfill()==0)
            //randomfirst();
            //else
            movegen(turn);
            showe();
        }
        else
        JOptionPane.showMessageDialog(null,"Position filled");
    }//GEN-LAST:event_box5ActionPerformed

    private void box6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box6ActionPerformed
        if(checkboard(1,2)){
            board[1][2]=turn;

            box6.setText("O");
            turn=-turn;
            showe();
            //if(ctfill()==0)
            //  randomfirst();
            //else
            movegen(turn);
            showe();
        }
        else JOptionPane.showMessageDialog(null,"Position filled");
    }//GEN-LAST:event_box6ActionPerformed

    private void box7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box7ActionPerformed
        if(checkboard(2,0)){
            board[2][0]=turn;

            box7.setText("O");
            turn=-turn;
            showe();
            //if(ctfill()==0)
            //  randomfirst();
            //else
            movegen(turn);
            showe();
        }
        else JOptionPane.showMessageDialog(null,"Position filled");
    }//GEN-LAST:event_box7ActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        JOptionPane.showMessageDialog(jPanel2,"* you will have to make lead of complete the game\n"
            +"* every lead of one win will give you 10 points\n"
            ,"Instructions"
            ,JOptionPane.INFORMATION_MESSAGE
        );
        //controlPannel.grabFocus();//get focus back to control pannel
    }//GEN-LAST:event_helpButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JButton box1;
    private javax.swing.JButton box2;
    private javax.swing.JButton box3;
    private javax.swing.JButton box4;
    private javax.swing.JButton box5;
    private javax.swing.JButton box6;
    private javax.swing.JButton box7;
    private javax.swing.JButton box8;
    private javax.swing.JButton box9;
    private javax.swing.JLabel dialog;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton resetButton;
    private javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables
}
