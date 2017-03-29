package exeploreGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GamePannel7 extends javax.swing.JPanel {
    
    /*   new
    -1 ==null 0==greenball  1== red ball  2==blue ball,3==blackBall,4=pink ball    22==gray    23==green    24==brown    25==yellow
    */
     
    
    JButton[][] box ;
    int[][] boxvalue;
    int ball=0;
    int oldball=0;
    int temp1=0,temp2=0,temp3=0,temp4=0,temp5=0,prevb1i=0,prevb1j=0,prevb2i=0,prevb2j=0,prevb3i=0,prevb3j=0,temp=0,one,two,prevb4i=0,prevb4j=0,prevb5i=0,prevb5j=0;
    int n=0,leveli,levelj,levelno=1,score=0,bestScore=0;
    KeyAdapter hello=new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                keyInput(e);
            }
        }; 
    int click=1;
    GamePannel gamePannel;
    
    public GamePannel7(GamePannel gamePannel) { 
        initComponents();
        this.gamePannel=gamePannel;
        myinit();   
    }
    
    public final void myinit(){
        setSize(820, 620);//our standard game pannel size
        setOpaque(false);//to make buttons transparent
        controlPannel.setBounds(10,70,700,480);
        if(levelno==1) level1();
        if(levelno==2) level2();
        controlPannel.setFocusable(true);
        controlPannel.setOpaque(false);
        controlPannel.setFocusTraversalKeysEnabled(false);
        createBox();//creates boxes     
    }//my init
    
    public void level1(){   
      if(click==1)
       updateBall();
       box=new JButton[11][16];
       leveli=11;
       levelj=16;
       levelno=1;
       boxvalue=new int[12][17];
       n=3;
       for(int i=1;i<leveli;i++)
           for(int j=1;j<levelj;j++)
           {   
               if(i==1  || i==10 || j==1 || j==15 || j==5 || (j==10 && i!=3) ||( (i==6 && j!=2) && (i==6 && j!=7) && (i==6 && j!=12)) )
                   
               { 
                   boxvalue[i][j]=22;
               }
               else           
               boxvalue[i][j]=-1;
           }
      boxvalue[9][2]=0;
      boxvalue[2][9]=1;
      boxvalue[9][13]=2;
      boxvalue[2][2]=23;
      boxvalue[5][6]=25;
      boxvalue[5][9]=25;
      boxvalue[9][4]=25;
      boxvalue[9][9]=25;
      boxvalue[6][2]=24;
      boxvalue[6][7]=24;
      boxvalue[6][12]=24;
      boxvalue[3][10]=24;
   }
    
    public void level2(){
       box=new JButton[11][21];
       leveli=11;
       levelj=21;
       boxvalue=new int[12][22];
       levelno=2;
       ball=0;temp1=0;temp2=0;temp3=0;temp4=0;temp5=0;prevb1i=0;prevb1j=0;prevb2i=0;prevb2j=0;prevb3i=0;prevb3j=0;temp=0;prevb4i=0;prevb4j=0;prevb5i=0;prevb5j=0;
       for(int i=1;i<leveli;i++)
           for(int j=1;j<levelj;j++)
           {
               if(i==1  || i==10 || j==1 || j==20 || j==7 || (i==6 && j<7) || (i<7 && j==16)|| (i==7 && j>7) || (i==4 && (j>7 && j<16)))
                   
               { 
                   boxvalue[i][j]=22;
               }
               else           
               boxvalue[i][j]=-1;
           }
       boxvalue[8][6]=0;
       boxvalue[5][4]=1;
       boxvalue[2][12]=2;
       boxvalue[5][12]=3;
       boxvalue[8][19]=4;
       boxvalue[9][2]=25;
       boxvalue[7][2]=25;
       boxvalue[2][2]=25;
       boxvalue[2][19]=23;
       boxvalue[3][15]=25;
       boxvalue[6][8]=25;
       boxvalue[8][9]=24;
       boxvalue[9][15]=25;
       boxvalue[2][16]=24;
       boxvalue[2][7]=24;
       boxvalue[8][7]=24;
       boxvalue[7][11]=24;
       boxvalue[5][2]=25;
       boxvalue[5][7]=24;
       boxvalue[3][8]=25;
       boxvalue[5][15]=25;
       boxvalue[8][8]=24;
       updateBall();
       n=5;
    }
    
    public void createBox(){
        controlPannel.setLayout(new GridLayout(10,15,0,0));
        for(int i=1;i<leveli;i++)
            for(int j=1;j<levelj;j++){
                box[i][j]=new JButton();
                box[i][j].setSize(45,45);
                box[i][j].setBackground(Color.white);
                controlPannel.add(box[i][j]);
            }
        controlPannel.grabFocus();
        /*controlPannel.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                keyInput(e);
            }
        });*/
        controlPannel.addKeyListener(hello);
     
        drawbox();
    }
 
    public void drawbox(){    
           //JOptionPane.showMessageDialog(null,"");
         for(int i=1;i<leveli;i++)
            for(int j=1;j<levelj;j++){
             if(boxvalue[i][j]==0)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/greenball.png")));
              if(boxvalue[i][j]==1)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/redball.png")));
              if(boxvalue[i][j]==2)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blueball.png")));
              if(boxvalue[i][j]==3)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blackball.png")));
              if(boxvalue[i][j]==4)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pinkball.png")));
              if(boxvalue[i][j]==22)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graybox.png")));
              if(boxvalue[i][j]==23)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/greenbox.png")));
              if(boxvalue[i][j]==24)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/brownbox.png")));
              if(boxvalue[i][j]==25)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/yellowbox.png")));
             if(boxvalue[i][j]==-1)
                 box[i][j].setIcon(null);
            }
    }
    
    public void keyInput(KeyEvent e){
        char input=e.getKeyChar();
      
            if(input=='a' || input=='A')
            {
              
                level1:
                for(int i=1;i<leveli;i++)
                    for(int j=1;j<levelj;j++)
                    {
                        if(boxvalue[i][j-1]!=0 && boxvalue[i][j-1]!=1 && boxvalue[i][j-1]!=2 && boxvalue[i][j-1]!=3 &&boxvalue[i][j-1]!=4 ){
                        if(boxvalue[i][j]==ball && j>1 && boxvalue[i][j-1]!=22 && boxvalue[i][j-1]!=24 )
                        {
                            temp=111;
                           int re= wincheck(i,j-1);
                           if(re==1) return; 
                           if(boxvalue[i][j-1]==25)
                           {
                              if(ball==0)
                              {
                                  temp1++;
                                   prevb1i=i;
                                  prevb1j=j-1;
                              }
                              if(ball==1)
                              {
                                  temp2++;
                                   prevb2i=i;
                                  prevb2j=j-1; 
                              }
                              if(ball==2)
                              {
                                  temp3++;
                                   prevb3i=i;
                                  prevb3j=j-1; 
                              }
                              if(ball==3)
                              {
                                  temp4++;
                                  prevb4i=i;
                                  prevb4j=j-1;
                              }
                              if(ball==4)
                              {
                                  temp5++;
                                  prevb5i=i;
                                  prevb5j=j-1; 
                              }
                                
                           }
                            boxvalue[i][j]=-1;
                            boxvalue[i][j-1]=ball;
                             if(levelno==1) level1open(i,j-1);
                             if(levelno==2) level2open(i,j-1);
                            break level1;
                        }
                        }
                       
                        
                    }
               if(temp1==0 && ball ==0 && temp==111 ) prev(prevb1i,prevb1j);
               if(temp2==0 && ball ==1 && temp==111 ) prev(prevb2i,prevb2j);
               if(temp3==0 && ball ==2 && temp==111 ) prev(prevb3i,prevb3j);
               if(temp4==0 && ball ==3 && temp==111 ) prev(prevb4i,prevb4j);
               if(temp5==0 && ball ==4 && temp==111 ) prev(prevb5i,prevb5j);
               drawbox();
                if(ball==0 && temp==111)  temp1=0;
                if(ball==1 && temp==111)  temp2=0;
                if(ball==2 && temp==111)  temp3=0;
                if(ball==3 && temp==111)  temp4=0;
                if(ball==4 && temp==111)  temp5=0;
                temp=0;
            
                 
            }
            if(input=='w' || input=='W')
            {
                  
                level1:
                for(int i=1;i<leveli;i++)
                    for(int j=1;j<levelj;j++)
                    {
                       if(boxvalue[i-1][j]!=0 && boxvalue[i-1][j]!=1 && boxvalue[i-1][j]!=2 && boxvalue[i-1][j]!=3 && boxvalue[i-1][j]!=4 ){
                        if(boxvalue[i][j]==ball && i>1 && boxvalue[i-1][j]!=22 && boxvalue[i-1][j]!=24)
                        {
                            temp=222;
                            int re= wincheck(i-1,j); 
                            if(re==1) return;
                          if(boxvalue[i-1][j]==25)
                           {
                              if(ball==0)
                              {
                                  temp1++;
                                   prevb1i=i-1;
                                  prevb1j=j;
                              }
                              if(ball==1)
                              {
                                  temp2++;
                                   prevb2i=i-1;
                                  prevb2j=j; 
                              }
                              if(ball==2)
                              {
                                  temp3++;
                                   prevb3i=i-1;
                                  prevb3j=j; 
                              }
                               if(ball==3)
                              {
                                  temp4++;
                                   prevb4i=i-1;
                                  prevb4j=j; 
                              }
                              if(ball==4)
                              {
                                  temp5++;
                                   prevb5i=i-1;
                                  prevb5j=j; 
                              }

                               
                           }
                           
                          boxvalue[i][j]=-1;
                            boxvalue[i-1][j]=ball;
                             if(levelno==1) level1open(i-1,j);
                            if(levelno==2) level2open(i-1,j);
                            break level1;
                        }}
                        
                    }
               if(temp1==0 && ball ==0 && temp==222 )  prev(prevb1i,prevb1j);
               if(temp2==0 && ball ==1 && temp==222 )  prev(prevb2i,prevb2j); 
               if(temp3==0 && ball ==2 && temp==222 )  prev(prevb3i,prevb3j);
               if(temp4==0 && ball ==3 && temp==222 )  prev(prevb4i,prevb4j); 
               if(temp5==0 && ball ==4 && temp==222 )  prev(prevb5i,prevb5j); 
               drawbox();
                if(ball==0 && temp==222)   temp1=0;
                if(ball==1 && temp==222)   temp2=0;
                if(ball==2 && temp==222)   temp3=0;
                if(ball==3 && temp==222)   temp4=0;
                if(ball==4 && temp==222)   temp5=0;
                temp=0;
                 
            }
            if(input=='s' || input=='S')
            {
                
                level1:
                for(int i=0;i<leveli;i++)
                    for(int j=1;j<levelj;j++)
                    {
                       if(boxvalue[i+1][j]!=0 && boxvalue[i+1][j]!=1 && boxvalue[i+1][j]!=2 && boxvalue[i+1][j]!=3 && boxvalue[i+1][j]!=4 ){
                        if(boxvalue[i][j]==ball && i<10 && boxvalue[i+1][j]!=22 && boxvalue[i+1][j]!=24)
                        {
                            temp=333;
                            int re= wincheck(i+1,j);
                            if(re==1) return ;
                            if(boxvalue[i+1][j]==25)
                           {
                                if(ball==0)
                              {
                                  temp1++;
                                   prevb1i=i+1;
                                  prevb1j=j;
                              }
                              if(ball==1)
                              {
                                  temp2++;
                                   prevb2i=i+1;
                                  prevb2j=j; 
                              }
                              if(ball==2)
                              {
                                  temp3++;
                                   prevb3i=i+1;
                                  prevb3j=j; 
                              }
                              if(ball==3)
                              {
                                  temp4++;
                                   prevb4i=i+1;
                                  prevb4j=j; 
                              }
                              if(ball==4)
                              {
                                  temp5++;
                                   prevb5i=i+1;
                                  prevb5j=j; 
                              }
                             
                           }
                           
                            boxvalue[i][j]=-1;
                            boxvalue[i+1][j]=ball;
                             if(levelno==1) level1open(i+1,j);
                             if(levelno==2) level2open(i+1,j);
                            break level1;
                        }}
                        
                    }
              
                if(temp1==0 && ball ==0 && temp==333 ) prev(prevb1i,prevb1j);
                if(temp2==0 && ball ==1 && temp==333 )  prev(prevb2i,prevb2j);
                if(temp3==0 && ball ==2 && temp==333 )  prev(prevb3i,prevb3j);
                if(temp4==0 && ball ==3 && temp==333 )  prev(prevb4i,prevb4j);
                if(temp5==0 && ball ==4 && temp==333 )  prev(prevb5i,prevb5j);
                drawbox();   
                if(ball==0 && temp==333)  temp1=0;
                if(ball==1 && temp==333)  temp2=0;  
                if(ball==2 && temp==333)  temp3=0;
                if(ball==3 && temp==333)  temp4=0;  
                if(ball==4 && temp==333)  temp5=0;
                temp=0;
            }
            if(input=='d' || input=='D')
            { 
                level1:
                for(int i=1;i<leveli;i++)
                    for(int j=1;j<levelj;j++)
                    {
                       if(boxvalue[i][j+1]!=0 && boxvalue[i][j+1]!=1 && boxvalue[i][j+1]!=2 && boxvalue[i][j+1]!=3 && boxvalue[i][j+1]!=4 ){
                        if(boxvalue[i][j]==ball && j<levelj-1 && boxvalue[i][j+1]!=22 && boxvalue[i][j+1]!=24)
                        {
                            temp=444;
                            int re=wincheck(i,j+1);
                            if(re==1) return;
                            if(boxvalue[i][j+1]==25)
                           {
                                if(ball==0)
                              {
                                  temp1++;
                                   prevb1i=i;
                                  prevb1j=j+1;
                              }
                              if(ball==1)
                              {
                                  temp2++;
                                   prevb2i=i;
                                  prevb2j=j+1; 
                              }
                              if(ball==2)
                              {
                                  temp3++;
                                   prevb3i=i;
                                  prevb3j=j+1; 
                              }
                              if(ball==3)
                              {
                                  temp4++;
                                   prevb4i=i;
                                  prevb4j=j+1; 
                              }
                              if(ball==4)
                              {
                                  temp5++;
                                   prevb5i=i;
                                  prevb5j=j+1; 
                              }
                              
                           }
                            
                               boxvalue[i][j]=-1;
                            boxvalue[i][j+1]=ball;
                            if(levelno==1) level1open(i,j+1);
                            if(levelno==2) level2open(i,j+1);
                            break level1;
                        }}
                    }
                if(temp1==0 && ball ==0 && temp==444 )      prev(prevb1i,prevb1j);
                if(temp2==0 && ball ==1 && temp==444 )      prev(prevb2i,prevb2j);
                if(temp3==0 && ball ==2 && temp==444 )      prev(prevb3i,prevb3j);
                 if(temp4==0 && ball ==3 && temp==444 )      prev(prevb4i,prevb4j);
                if(temp5==0 && ball ==4 && temp==444 )      prev(prevb5i,prevb5j);
                drawbox();
                if(ball==0 && temp==444)    temp1=0;
                if(ball==1 && temp==444)    temp2=0;
                if(ball==2 && temp==444)    temp3=0;
                if(ball==3 && temp==444)    temp4=0;
                if(ball==4 && temp==444)    temp5=0;
                temp=0;
              
            }
            if(input==KeyEvent.VK_SPACE) {
                ball=(ball+1)%n;
                    updateBall();
            }
                               
    }
    
     public void prev(int i,int j){   
         if(boxvalue[i][j]!=0 && boxvalue[i][j]!=1 && boxvalue[i][j]!=2 && boxvalue[i][j]!=3 && boxvalue[i][j]!=4)
             boxvalue[i][j]=25;
         
     }
     
    public void level1open(int i, int j){
                    if(ball==0 && i==9 && j==4 ) boxvalue[6][12]=-1;
                    if(ball==0 && (i!=9 || j!=4))boxvalue[6][12]=24;  
                    if(ball==1 && i==5 && j==9) boxvalue[3][10]=-1;
                    if(ball==1 && (i!=5 || j!=9)) boxvalue[3][10]=24;
                    if(ball==2 && i==5 && j==6) boxvalue[6][7]=-1;
                    if(ball==2 &&(i!=5 || j!=6)) boxvalue[6][7]=24;
                    if(ball==1 && i==9 && j==9) boxvalue[6][2]=-1;
                    if(ball==1 && (i!=9 || j!=9)) boxvalue[6][2]=24;
    }
    
    
      public void level2open(int i,int j){
      if(ball==2 && i==3 && j==15) boxvalue[2][16]=-1;
      if(ball==2 && (i!=3 || j!=15)) boxvalue[2][16]=24;
      if(ball==1 && i==2 && j==2) boxvalue[2][7]=-1;
      if(ball==1 && (i!=2 || j!=2)) boxvalue[2][7]=24;
      if(ball==3 && (i==6 && j==8)) boxvalue[5][7]=-1;
      if(ball==3 && (i!=6 || j!=8)) boxvalue[5][7]=24;
      if(ball==1 && i==5 && j==2 &&(boxvalue[8][7]!=0)) boxvalue[8][7]=-1;
      if(ball==1 && (i!=5 || j!=2)&&(boxvalue[8][7]!=0)) boxvalue[8][7]=24; 
      if(ball==2 && i==3 && j==8 && (boxvalue[8][9]!=0 && boxvalue[8][9]!=4))   boxvalue[8][9]=-1;
      if(ball==2 && (i!=3 || j!=8) && (boxvalue[8][9]!=0 && boxvalue[8][9]!=4)) boxvalue[8][9]=24;
      if(ball==4 && i==9 && j==15) boxvalue[7][11]=-1;
      if(ball==4 && (i!=9 || j!=15)) boxvalue[7][11]=24;
      if(ball==3 && i==5 && j==15) boxvalue[8][8]=-1;
      if(ball==3 && (i!=5 || j!=15)) boxvalue[8][8]=24; 
      if(ball==0 && i==9 && j==2) boxvalue[8][8]=-1;
      if(ball==0 && (i!=9 || j!=2) && boxvalue[5][15]!=3) boxvalue[8][8]=24;
      if(ball==0 && i==7 && j==2) boxvalue[8][9]=-1;
      if(ball==0 && (i!=7 || j!=2) && boxvalue[3][8]!=2) boxvalue[8][9]=24;
    }
        
    public int  wincheck(int i,int j){
        if(boxvalue[i][j]==23)
        {
            
            if(levelno==1)
            {    
                score=20;
                scoreLabel.setText("score : "+score);
                if(score>bestScore){
                    bestScore=score;
                    gamePannel.scoreUpdate();
                    bestScoreLabel.setText("Best Score : "+String.format("%02d",bestScore));
                }
                levelLabel.setText("level : 2/2");
                JOptionPane.showMessageDialog(controlPannel,"Yeah level 1 cleared, Go to next level");
                levelno=2;
                controlPannel.removeAll();
                controlPannel.repaint();
                controlPannel.removeKeyListener(hello);
                level2();
                createBox();
                return 1;    
                                    
            }
            if(levelno==2)
            {
                score=60;
                if(score>bestScore){
                    bestScore=score;
                    gamePannel.scoreUpdate();
                    bestScoreLabel.setText("Best Score : "+String.format("%02d",bestScore));
                }
                scoreLabel.setText("score : "+score);
                JOptionPane.showMessageDialog(controlPannel,"Yeah you win this game go and play other games");
                gamePannel.home();
            }
        }
     return 0;
    }
    
    public void updateBall(){   
        String str="";
        switch(ball)
        {
            case 0:
            {
                str="SkyBlue";
                break;
            }
            case 1:
            {
                str="Red";
                break;
            }
            case 2:
            {
                str="Blue";
                break;
            }
            case 3:
            {
                str="Black";
                break;
            }
            case 4:
            {
                str="Pink";
                break;
            }
        }
        BallLabel.setText("Current Ball is :- "+str);
    }
    
    public void showInfo(){
        String str="1) Use W to move ball up\n"
                + "2) Use A to move ball left\n"
                + "3) Use S to move ball down\n"
                + "4) Use D to move ball right\n"
                + "5) Use SPACE to move to other ball\n"
                + "6) To win make AnyColor Ball to reach green box\n"
                + "7) Put specific ball on specific Yellow box that will open a specific Brown box\n"
                + "8) Current ball shows the current ball in movement\n"
                + "9) RESET button to reset the game\n"
                + "10) First level carry 20 points\n"
                + "11) Second level carry 40 points\n"
                + "12) If keys not working Or you loose your focus from\n"
                + "    main game use FOCUS button given in bottom-right";
        JOptionPane.showMessageDialog(controlPannel, str, "INSTRUCTIONS", HEIGHT);
        click++;
    }
    
    public void getFocus(){
        controlPannel.grabFocus();//get focus back to control pannel
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPannel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        bestScoreLabel = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        BallLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        background_Image = new javax.swing.JLabel();

        setLayout(null);

        controlPannel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controlPannelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                controlPannelMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout controlPannelLayout = new javax.swing.GroupLayout(controlPannel);
        controlPannel.setLayout(controlPannelLayout);
        controlPannelLayout.setHorizontalGroup(
            controlPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        controlPannelLayout.setVerticalGroup(
            controlPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(controlPannel);
        controlPannel.setBounds(20, 60, 560, 440);
        controlPannel.getAccessibleContext().setAccessibleName("");

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(720, 320, 90, 29);

        bestScoreLabel.setFont(new java.awt.Font("TakaoPGothic", 0, 20)); // NOI18N
        bestScoreLabel.setForeground(new java.awt.Color(252, 236, 236));
        bestScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bestScoreLabel.setText("Best Score : 00");
        add(bestScoreLabel);
        bestScoreLabel.setBounds(610, 20, 240, 30);

        levelLabel.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        levelLabel.setForeground(new java.awt.Color(222, 222, 222));
        levelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        levelLabel.setText("level : 1/2");
        add(levelLabel);
        levelLabel.setBounds(720, 150, 90, 30);

        scoreLabel.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(222, 222, 222));
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreLabel.setText("score : 0");
        add(scoreLabel);
        scoreLabel.setBounds(710, 210, 100, 30);

        BallLabel.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        BallLabel.setForeground(java.awt.Color.white);
        BallLabel.setText("Current Ball is :- ");
        add(BallLabel);
        BallLabel.setBounds(170, 560, 300, 20);

        jLabel1.setBackground(java.awt.Color.white);
        jLabel1.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Balls and Bridges");
        add(jLabel1);
        jLabel1.setBounds(160, 20, 420, 29);

        jButton2.setText("Rules");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(720, 380, 90, 29);

        jButton3.setText("Focus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(720, 450, 90, 29);

        background_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gamePannelBackground7.jpg"))); // NOI18N
        add(background_Image);
        background_Image.setBounds(0, 0, 820, 620);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        if(levelno==1)
            {    
                    levelno=1;
                    controlPannel.removeAll();
                    controlPannel.repaint();
                    controlPannel.removeKeyListener(hello);
                    level1();
                    createBox();
                   
                                    
            }
        if(levelno==2)
        {
                    levelno=2;
                    controlPannel.removeAll();
                    controlPannel.repaint();
                    controlPannel.removeKeyListener(hello);
                    level2();
                    createBox();
        }
        controlPannel.grabFocus(); 
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void controlPannelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_controlPannelMouseEntered
        controlPannel.grabFocus();  // TODO add your handling code here:
    }//GEN-LAST:event_controlPannelMouseEntered

    private void controlPannelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_controlPannelMouseClicked
        controlPannel.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_controlPannelMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
              showInfo(); 
              controlPannel.grabFocus();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            controlPannel.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BallLabel;
    private javax.swing.JLabel background_Image;
    private javax.swing.JLabel bestScoreLabel;
    private javax.swing.JPanel controlPannel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables
}
