package exeploreGame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author srb
 */
public class GamePannel8 extends javax.swing.JPanel {

    int board[][] = new int[13][15];//board for values
    int[][] memoryBoard1 = new int[13][15];//board for last move
    int[][] memoryBoard2 = new int[13][15];//board for last second move
    int[][] memoryBoard3 = new int[13][15];//board for last third move
    
    int playerX,playerY;
    int box1x,box1y,box2x,box2y,box3x,box3y;
    int win=0,levelwin,score=0,bestScore=0,level,maxlevel=3;
    
    Border darkborder = new LineBorder(Color.DARK_GRAY, 1);
    Border lightborder = new LineBorder(Color.lightGray, 1);
    Border nullborder = new LineBorder(Color.darkGray, 0);
    GamePannel gamePannel ;
    
    JPanel stage = new JPanel();
    
    JButton[][] box = new JButton[13][15];
    CardLayout card=new CardLayout();//layout for controlPannel
    
    public GamePannel8(GamePannel gamePannel) {
        initComponents();
        this.gamePannel=gamePannel;
        myinit();
    }//constructor
    
    public final void myinit(){
        setSize(820, 620);//our standard game pannel size
        setOpaque(false);//to make buttons transparent
        
        
        controlPannel.setBounds(40,85,585,495);//ratio 585:495 :: 13:11
        controlPannel.setLayout(card);
        controlPannel.setFocusable(true);
        controlPannel.setOpaque(false);//to make buttons transparent
        controlPannel.setFocusTraversalKeysEnabled(false);
        controlPannel.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                keyInput(e);
            }
        });//calls keyInput()
        
        createBox();//creates boxes
        
        initBoard1();//level one start
        
    }//my init
    
    public boolean wincheck(){
        for(int i=1;i<12;i++)
            for(int j=1;j<14;j++){
               if(board[i][j]==4 || board[i][j]==7)
                   return false;
            }
        levelwin=1;
        switch(level){
            case 1:
                score=30;
                break;
            case 2:
                score=70;
                break;
            case 3:
                score=120;
                break;
        }
        scoreLabel.setText("score : "+score);
        if(score>bestScore){
            bestScore=score;
            gamePannel.scoreUpdate();
            bestScoreLabel.setText("Best Score : "+String.format("%03d",bestScore));
        }
        if(levelwin==1){
            if(level<maxlevel){
                JOptionPane.showMessageDialog(controlPannel,"move to next level","Level Complete",JOptionPane.INFORMATION_MESSAGE);
                if(level==1)
                    initBoard2();
                else if(level==2)
                    initBoard3();
            }
            else if(level==maxlevel){
                win=1;
                gamePannel.scoreUpdate();
                JOptionPane.showMessageDialog(controlPannel,"Yippee you won the game","Game Complete",JOptionPane.INFORMATION_MESSAGE);
                initBoard1();
                gamePannel.home();
            }   
        }
        return true;
    }// return true if level completes
    
    public void createBox(){
        stage.setLayout(new GridLayout(11,13,0,0));
        for(int i=1;i<12;i++){
            for(int j=1;j<14;j++){
                box[i][j]=new JButton();
                box[i][j].setSize(45,45);
                box[i][j].setBorder(lightborder);
                stage.add(box[i][j]);
            }
        }
        stage.setOpaque(false);
        controlPannel.add(stage);
    } //creates boxes 
    
    public void updateBox(){
        for(int i=1;i<12;i++)
            for(int j=1;j<14;j++){
                if(board[i][j]==0){
                    box[i][j].setIcon(null);
                    box[i][j].setBorder(nullborder);
                    box[i][j].setFocusPainted( false );
                    box[i][j].setContentAreaFilled(false );//to make buttons transparent
               //     box[i][j].setOpaque(false);
                }
                if(board[i][j]==1){
                    box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bricks1.jpeg")));
                    box[i][j].setBorder(nullborder);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
                }
                if(board[i][j]==2){
                    box[i][j].setIcon(null);
                    box[i][j].setBorder(lightborder);
                    box[i][j].setBackground(Color.white);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
                }
                if(board[i][j]==3 || board[i][j]==7){
                    box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boy2.png")));
                    box[i][j].setBorder(lightborder);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
                    playerX=j;playerY=i;
                }
                if(board[i][j]==4){
                    box[i][j].setBorder(lightborder);
                    box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red_dot.jpg")));
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
                }
                if(board[i][j]==5){
                    box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/box4.png")));
                    box[i][j].setBorder(darkborder);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
                }
                if(board[i][j]==6){
                    box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/box5.png")));
                    box[i][j].setBorder(darkborder);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
                }
            }
    } //update box display
   
    public void initBoard3(){
        levelwin=0;
        level = 3;
        levelLabel.setText("Level : "+Integer.toString(level)+"/"+Integer.toString(maxlevel));
        
        for(int i=0;i<13;i++)//initilize all 0
            for(int j=0;j<15;j++){
                board[i][j]=0;
            }
   
        board[1][1]=0; board[1][2]=0; board[1][3]=0; board[1][4]=1; board[1][5]=1; board[1][6]=1; board[1][7]=1; board[1][8]=1; board[1][9]=1; board[1][10]=1; board[1][11]=0; board[1][12]=0; board[1][13]=0; 
        board[2][1]=1; board[2][2]=1; board[2][3]=1; board[2][4]=1; board[2][5]=2; board[2][6]=2; board[2][7]=2; board[2][8]=2; board[2][9]=2; board[2][10]=1; board[2][11]=0; board[2][12]=0; board[2][13]=0;
        board[3][1]=1; board[3][2]=2; board[3][3]=2; board[3][4]=2; board[3][5]=4; board[3][6]=1; board[3][7]=1; board[3][8]=1; board[3][9]=2; board[3][10]=1; board[3][11]=0; board[3][12]=0; board[3][13]=0;
        board[4][1]=1; board[4][2]=2; board[4][3]=1; board[4][4]=2; board[4][5]=1; board[4][6]=2; board[4][7]=2; board[4][8]=2; board[4][9]=2; board[4][10]=1; board[4][11]=1; board[4][12]=0; board[4][13]=0;
        board[5][1]=1; board[5][2]=2; board[5][3]=1; board[5][4]=2; board[5][5]=5; board[5][6]=2; board[5][7]=5; board[5][8]=1; board[5][9]=4; board[5][10]=2; board[5][11]=1; board[5][12]=0; board[5][13]=0;
        board[6][1]=1; board[6][2]=2; board[6][3]=1; board[6][4]=2; board[6][5]=2; board[6][6]=6; board[6][7]=2; board[6][8]=2; board[6][9]=1; board[6][10]=2; board[6][11]=1; board[6][12]=0; board[6][13]=0;
        board[7][1]=1; board[7][2]=2; board[7][3]=4; board[7][4]=1; board[7][5]=5; board[7][6]=2; board[7][7]=5; board[7][8]=2; board[7][9]=1; board[7][10]=2; board[7][11]=1; board[7][12]=0; board[7][13]=0;
        board[8][1]=1; board[8][2]=1; board[8][3]=2; board[8][4]=2; board[8][5]=2; board[8][6]=2; board[8][7]=1; board[8][8]=2; board[8][9]=1; board[8][10]=2; board[8][11]=1; board[8][12]=1; board[8][13]=1;
        board[9][1]=0; board[9][2]=1; board[9][3]=2; board[9][4]=1; board[9][5]=1; board[9][6]=1; board[9][7]=4; board[9][8]=2; board[9][9]=2; board[9][10]=2; board[9][11]=2; board[9][12]=3; board[9][13]=1;
        board[10][1]=0;board[10][2]=1;board[10][3]=2;board[10][4]=2;board[10][5]=2;board[10][6]=2;board[10][7]=2;board[10][8]=1;board[10][9]=1;board[10][10]=2;board[10][11]=2;board[10][12]=2;board[10][13]=1;
        board[11][1]=0;board[11][2]=1;board[11][3]=1;board[11][4]=1;board[11][5]=1;board[11][6]=1;board[11][7]=1;board[11][8]=1;board[11][9]=1;board[11][10]=1;board[11][11]=1;board[11][12]=1;board[11][13]=1;
   
        playerX=12;playerY=9;
        
        lastMove();
        lastMove();
        lastMove();
        updateBox();//calls update box
    } //initializes board1 
    
    public void initBoard2(){
        levelwin=0;
        level = 2;
        levelLabel.setText("Level : "+Integer.toString(level)+"/"+Integer.toString(maxlevel));
        
        for(int i=0;i<13;i++)//initilize all 0
            for(int j=0;j<15;j++){
                board[i][j]=0;
            }
        
        board[2][1]=0; board[2][2]=0; board[2][3]=1; board[2][4]=1; board[2][5]=1; board[2][6]=1; board[2][7]=1; board[2][8]=1; board[2][9]=1; board[2][10]=1; board[2][11]=1; board[2][12]=0; board[2][13]=0;
        board[3][1]=0; board[3][2]=0; board[3][3]=1; board[3][4]=2; board[3][5]=2; board[3][6]=1; board[3][7]=1; board[3][8]=2; board[3][9]=2; board[3][10]=2; board[3][11]=1; board[3][12]=0; board[3][13]=0;
        board[4][1]=0; board[4][2]=0; board[4][3]=1; board[4][4]=2; board[4][5]=2; board[4][6]=2; board[4][7]=5; board[4][8]=2; board[4][9]=2; board[4][10]=2; board[4][11]=1; board[4][12]=0; board[4][13]=0;
        board[5][1]=0; board[5][2]=0; board[5][3]=1; board[5][4]=5; board[5][5]=2; board[5][6]=1; board[5][7]=1; board[5][8]=1; board[5][9]=2; board[5][10]=5; board[5][11]=1; board[5][12]=0; board[5][13]=0;
        board[6][1]=0; board[6][2]=0; board[6][3]=1; board[6][4]=2; board[6][5]=1; board[6][6]=4; board[6][7]=4; board[6][8]=4; board[6][9]=1; board[6][10]=2; board[6][11]=1; board[6][12]=0; board[6][13]=0;
        board[7][1]=0; board[7][2]=1; board[7][3]=1; board[7][4]=2; board[7][5]=1; board[7][6]=4; board[7][7]=4; board[7][8]=4; board[7][9]=1; board[7][10]=2; board[7][11]=1; board[7][12]=1; board[7][13]=0;
        board[8][1]=0; board[8][2]=1; board[8][3]=2; board[8][4]=5; board[8][5]=2; board[8][6]=2; board[8][7]=5; board[8][8]=2; board[8][9]=2; board[8][10]=5; board[8][11]=2; board[8][12]=1; board[8][13]=0; 
        board[9][1]=0; board[9][2]=1; board[9][3]=2; board[9][4]=2; board[9][5]=2; board[9][6]=2; board[9][7]=2; board[9][8]=1; board[9][9]=2; board[9][10]=2; board[9][11]=3; board[9][12]=1; board[9][13]=0;
        board[10][1]=0;board[10][2]=1;board[10][3]=1;board[10][4]=1;board[10][5]=1;board[10][6]=1;board[10][7]=1;board[10][8]=1;board[10][9]=1;board[10][10]=1;board[10][11]=1;board[10][12]=1;board[10][13]=0;   
        playerX=12;playerY=9;
        
        lastMove();
        lastMove();
        lastMove();
        updateBox();//calls update box
    } //initializes board2 
    
    public void initBoard1(){
        levelwin=0;
        level = 1;
        levelLabel.setText("Level : "+Integer.toString(level)+"/"+Integer.toString(maxlevel));
        
        for(int i=0;i<13;i++)//initilize all 0
            for(int j=0;j<15;j++){
                board[i][j]=0;
            }
   
        board[4][1]=0; board[4][2]=1; board[4][3]=1; board[4][4]=1; board[4][5]=1; board[4][6]=0; board[4][7]=0; board[4][8]=1; board[4][9]=1; board[4][10]=1; board[4][11]=1; board[4][12]=1; board[4][13]=0; 
        board[5][1]=1; board[5][2]=1; board[5][3]=2; board[5][4]=2; board[5][5]=1; board[5][6]=0; board[5][7]=0; board[5][8]=1; board[5][9]=2; board[5][10]=2; board[5][11]=2; board[5][12]=1; board[5][13]=0; 
        board[6][1]=1; board[6][2]=2; board[6][3]=5; board[6][4]=2; board[6][5]=1; board[6][6]=1; board[6][7]=1; board[6][8]=1; board[6][9]=5; board[6][10]=2; board[6][11]=2; board[6][12]=1; board[6][13]=0;
        board[7][1]=1; board[7][2]=2; board[7][3]=2; board[7][4]=5; board[7][5]=4; board[7][6]=4; board[7][7]=4; board[7][8]=4; board[7][9]=2; board[7][10]=5; board[7][11]=2; board[7][12]=1; board[7][13]=0;
        board[8][1]=1; board[8][2]=1; board[8][3]=2; board[8][4]=2; board[8][5]=2; board[8][6]=2; board[8][7]=1; board[8][8]=2; board[8][9]=3; board[8][10]=2; board[8][11]=1; board[8][12]=1; board[8][13]=0;
        board[9][1]=0; board[9][2]=1; board[9][3]=1; board[9][4]=1; board[9][5]=1; board[9][6]=1; board[9][7]=1; board[9][8]=1; board[9][9]=1; board[9][10]=1; board[9][11]=1; board[9][12]=0; board[9][13]=0;  

        playerX=9;playerY=8;
        
        lastMove();
        lastMove();
        lastMove();
        
        updateBox();//calls update box
    } //initializes board3 
    
    public void updateBoard(){
        lastMove();
        if(board[box1y][box1x]==3){
            if(board[box2y][box2x]==2){
                board[box1y][box1x]=2;
                board[box2y][box2x]=3;
                updateBox();
                wincheck();
            }
            else if(board[box2y][box2x]==4){
                board[box1y][box1x]=2;
                board[box2y][box2x]=7;
                updateBox();
                wincheck();
            }
            else if(board[box2y][box2x]==5){
                if(board[box3y][box3x]==2){
                    board[box1y][box1x]=2;
                    board[box2y][box2x]=3;
                    board[box3y][box3x]=5;
                    updateBox();
                    wincheck();
                }   
                else if(board[box3y][box3x]==4){
                    board[box1y][box1x]=2;
                    board[box2y][box2x]=3;
                    board[box3y][box3x]=6;
                    updateBox();
                    wincheck();
                }
            }
            else if(board[box2y][box2x]==6){
                if(board[box3y][box3x]==2){
                    board[box1y][box1x]=2;
                    board[box2y][box2x]=7;
                    board[box3y][box3x]=5;
                    updateBox();
                    wincheck();
                }
                else if(board[box3y][box3x]==4){
                    board[box1y][box1x]=2;
                    board[box2y][box2x]=7;
                    board[box3y][box3x]=6;
                    updateBox();
                    wincheck();
                }                  
            }
        }
        //user on target
        else if(board[box1y][box1x]==7){
            if(board[box2y][box2x]==2){
                board[box1y][box1x]=4;
                board[box2y][box2x]=3;
                updateBox();
                wincheck();
            }
            else if(board[box2y][box2x]==4){
                board[box1y][box1x]=4;
                board[box2y][box2x]=7;
                updateBox();
                wincheck();
            }
            else if(board[box2y][box2x]==5){
                if(board[box3y][box3x]==2){
                    board[box1y][box1x]=4;
                    board[box2y][box2x]=3;
                    board[box3y][box3x]=5;
                    updateBox();
                    wincheck();
                }   
                else if(board[box3y][box3x]==4){
                    board[box1y][box1x]=4;
                    board[box2y][box2x]=3;
                    board[box3y][box3x]=6;
                    updateBox();
                    wincheck();
                }
            }
            else if(board[box2y][box2x]==6){
                if(board[box3y][box3x]==2){
                    board[box1y][box1x]=4;
                    board[box2y][box2x]=7;
                    board[box3y][box3x]=5;
                    updateBox();
                    wincheck();
                }
                else if(board[box3y][box3x]==4){
                    board[box1y][box1x]=4;
                    board[box2y][box2x]=7;
                    board[box3y][box3x]=6;
                    updateBox();
                    wincheck();
                }                  
            }
        }   
    } //update board 
    
    public void lastMove(){
        for(int i=0;i<12;i++)
            System.arraycopy(memoryBoard2[i], 0, memoryBoard3[i], 0, 15);
        for(int i=0;i<12;i++)
            System.arraycopy(memoryBoard1[i], 0, memoryBoard2[i], 0, 15);
        for(int i=0;i<12;i++)
            System.arraycopy(board[i], 0, memoryBoard1[i], 0, 15);
                
    }//copy array into memoryBoard
    
    public void getFocus(){
        controlPannel.grabFocus();//get focus back to control pannel
    }
    
    public void keyInput(KeyEvent e){
        char input=e.getKeyChar();
        if(input=='w' || input=='W'){
            box1x=playerX;box1y=playerY;
            box2x=playerX;box2y=playerY-1;
            box3x=playerX;box3y=playerY-2;
            updateBoard();
        }
        if(input=='s' || input=='S'){
            box1x=playerX;box1y=playerY;
            box2x=playerX;box2y=playerY+1;
            box3x=playerX;box3y=playerY+2;
            updateBoard();
        }
        if(input=='a' || input=='A'){
            box1x=playerX;box1y=playerY;
            box2x=playerX-1;box2y=playerY;
            box3x=playerX-2;box3y=playerY;
            updateBoard();
        }
        if(input=='d' || input=='D'){
            box1x=playerX;box1y=playerY;
            box2x=playerX+1;box2y=playerY;
            box3x=playerX+2;box3y=playerY;
            updateBoard();
        }
    }//calls updateBoard if key recognised

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        heading = new javax.swing.JLabel();
        controlPannel = new javax.swing.JPanel();
        newButton = new javax.swing.JButton();
        undoButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        bestScoreLabel = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        footerLabel = new javax.swing.JLabel();
        background_Image = new javax.swing.JLabel();

        setLayout(null);

        heading.setFont(new java.awt.Font("DejaVu Serif", 1, 48)); // NOI18N
        heading.setForeground(new java.awt.Color(255, 25, 255));
        heading.setText("Push Box");
        add(heading);
        heading.setBounds(180, 0, 300, 90);

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
        controlPannel.setBounds(40, 85, 585, 495);
        controlPannel.getAccessibleContext().setAccessibleName("");

        newButton.setText("reset");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });
        add(newButton);
        newButton.setBounds(670, 290, 110, 29);

        undoButton.setText("undo");
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });
        add(undoButton);
        undoButton.setBounds(670, 360, 110, 29);

        helpButton.setText("help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        add(helpButton);
        helpButton.setBounds(670, 440, 110, 29);

        bestScoreLabel.setFont(new java.awt.Font("TakaoPGothic", 1, 24)); // NOI18N
        bestScoreLabel.setForeground(new java.awt.Color(252, 236, 236));
        bestScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bestScoreLabel.setText("Best Score : 000");
        add(bestScoreLabel);
        bestScoreLabel.setBounds(580, 20, 240, 50);

        levelLabel.setFont(new java.awt.Font("TakaoPGothic", 1, 24)); // NOI18N
        levelLabel.setForeground(new java.awt.Color(252, 236, 236));
        levelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        levelLabel.setText("Level : 0/3");
        add(levelLabel);
        levelLabel.setBounds(630, 140, 180, 50);

        scoreLabel.setFont(new java.awt.Font("TakaoPGothic", 1, 24)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(252, 236, 236));
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreLabel.setText("Scores : 0");
        add(scoreLabel);
        scoreLabel.setBounds(630, 210, 180, 40);

        footerLabel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        footerLabel.setForeground(new java.awt.Color(255, 244, 244));
        footerLabel.setText("made with love by Team .EXE");
        add(footerLabel);
        footerLabel.setBounds(40, 590, 250, 20);

        background_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gamePannelBackground8.jpg"))); // NOI18N
        add(background_Image);
        background_Image.setBounds(0, 0, 820, 620);
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        switch (level) {
            case 1:
                initBoard1();
                break;
            case 2:
                initBoard2();
                break;
            case 3:
                initBoard3();
                break;
            default:
                break;
        }
        controlPannel.grabFocus();//get focus back to control pannel
    }//GEN-LAST:event_newButtonActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        for(int i=0;i<12;i++)
            System.arraycopy(memoryBoard1[i], 0, board[i], 0, 15);
        for(int i=0;i<12;i++)
            System.arraycopy(memoryBoard2[i], 0, memoryBoard1[i], 0, 15);
        for(int i=0;i<12;i++)
            System.arraycopy(memoryBoard3[i], 0, memoryBoard2[i], 0, 15);
        updateBox();
            controlPannel.grabFocus();//get focus back to control pannel
    }//GEN-LAST:event_undoButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        JOptionPane.showMessageDialog(controlPannel,"* Use W A S D keys to move player\n"
                                            +"* Red dots are your target point\n"
                                            +"* Yellow boxes are to be placed on target points (Red Dot)\n"
                                            +"* You can undo only the last 3 moves\n"
                                            +"* There are total 3 levels\n"
                                            +"* First level carries 30 points\n"
                                            +"* Second level carries 40 points\n"
                                            +"* Third level carries 50 points\n"
                                            ,"Instructions"
                                            ,JOptionPane.INFORMATION_MESSAGE                                    
        );
        controlPannel.grabFocus();//get focus back to control pannel
    }//GEN-LAST:event_helpButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background_Image;
    private javax.swing.JLabel bestScoreLabel;
    private javax.swing.JPanel controlPannel;
    private javax.swing.JLabel footerLabel;
    private javax.swing.JLabel heading;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JButton newButton;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JButton undoButton;
    // End of variables declaration//GEN-END:variables
}
