package exeploreGame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.Math.random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GamePannel6 extends javax.swing.JPanel {

    final private rawBox board[][] = new rawBox[31][31];//board for values
    int win=0,levelwin=0,score=0,bestScore=0,totalmoves=0,level,maxlevel=3;

    final private Border darkborder = new LineBorder(Color.DARK_GRAY, 1);
    final private Border lightborder = new LineBorder(Color.lightGray, 1);
    final private Border nullborder = new LineBorder(Color.darkGray, 0);
    final private GamePannel gamePannel ;

    final private JPanel stage = new JPanel();

    final private JButton[][] box = new JButton[31][31];
    final private CardLayout card=new CardLayout();//layout for controlPannel
    
    //game variables
    int snakeDir=1,foodDir=1;
    rawBox headPtr,foodPtr;
    Timer snakeTimer,foodTimer;
    boolean lock=false;
    
    class rawBox{
        int type;//0 for null //1 for brick wall //2 for game ground area //3 for game object //4 for target
        int x,y;
        rawBox next;
        rawBox prev;
        
        rawBox() {
            this.next = null;
            this.prev = null;
        }
        
    }
    
    public GamePannel6(GamePannel gamePannel) {
        initComponents();
        this.gamePannel=gamePannel;
        myinit();
    }//constructor
    
    public final void myinit(){
        setSize(820, 620);//our standard game pannel size
        setOpaque(false);//to make buttons transparent
       
        
        controlPannel.setBounds(60,80,510,510);//ratio 510:510 :: 30:30
        controlPannel.setLayout(card);
        controlPannel.setFocusable(true);
        controlPannel.setOpaque(false);//to make buttons transparent
        controlPannel.setFocusTraversalKeysEnabled(false);
        controlPannel.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                keyInput(e);
            }
        });//calls keyInput()
        
        createBox();//creates boxes
        
        snakeTimer = new Timer(120,new moveSnake());
        foodTimer = new Timer(820,new moveFood());
        
        initBoard1();//level one start
        
    }//my init
    
    public boolean gameFinish(){
        snakeTimer.stop();
        if(level>1)
            foodTimer.stop();
        levelwin=1;
        if(levelwin==1){
            if(level<maxlevel){
                JOptionPane.showMessageDialog(controlPannel,"move to next level","Level Complete",JOptionPane.INFORMATION_MESSAGE);
                switch(level){
                    case 1:
                        initBoard2();
                        break;
                    case 2:
                        initBoard3();
                        break;
                }
            }
            else if(level==maxlevel){
                win=1;
                JOptionPane.showMessageDialog(controlPannel,"Your Score : "+score+" ","Game Complete",JOptionPane.INFORMATION_MESSAGE);
                initBoard1();
                gamePannel.home();
            }   
        }
        return true;
    }// called when game finishes
    
    public void createBox(){
        stage.setLayout(new GridLayout(30,30,0,0));
        for(int i=1;i<31;i++){
            for(int j=1;j<31;j++){
                box[i][j]=new JButton();
                box[i][j].setSize(20,20);
                box[i][j].setBorder(lightborder);
                stage.add(box[i][j]);
            }
        }
        stage.setOpaque(false);
        controlPannel.add(stage);
    } //creates boxes 
    
    public void updateGui(){
        for(int i=1;i<31;i++)
            for(int j=1;j<31;j++){
                if(board[i][j].type==0){//null area
                    box[i][j].setIcon(null);
                    box[i][j].setBorder(nullborder);
                    box[i][j].setFocusPainted( false );
                    box[i][j].setContentAreaFilled(false );//to make buttons transparent
               //     box[i][j].setOpaque(false);
                }
                if(board[i][j].type==1){//bricks walls
                    box[i][j].setBorder(nullborder);
                    box[i][j].setBackground(Color.black);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
                }
                if(board[i][j].type==2){//game ground area
                    box[i][j].setBorder(nullborder);
                    box[i][j].setIcon(null);
                    box[i][j].setBackground(Color.white);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
                }
                if(board[i][j].type==3){ //player head
                    box[i][j].setBackground(Color.red);
                    box[i][j].setIcon(null);
                    box[i][j].setBorder(lightborder);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
                }
                if(board[i][j].type==4){ //food
                    box[i][j].setBackground(Color.white);
                    box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apple.png")));
                    box[i][j].setBorder(nullborder);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
                }
            }
    } //update box display
    
    public void initBoard1(){
        levelwin=0;
        level = 1;
        score=0;
        scoreLabel.setText("Score : "+Integer.toString(score));
        levelLabel.setText("level : "+level+"/"+maxlevel);
        
        for(int i=0;i<31;i++)//initilize all 2
            for(int j=0;j<31;j++){
                board[i][j]=new rawBox();
                board[i][j].type=2;
                board[i][j].x=j;
                board[i][j].y=i;
            }
        
        for(int i=0;i<31;i++)//initilize all 1
            for(int j=0;j<31;j++){
                board[1][j].type=1;
                board[30][j].type=1;
                board[i][30].type=1;
                board[i][1].type=1;    
            }
        
        board[10][5].type=3;//head
        board[10][5].prev=board[10][6];//actually it is useless to define prev to head
        board[10][5].next=board[10][4];
        headPtr=board[10][5];
        
        board[10][4].type=3;//body
        board[10][4].prev=board[10][5];
        board[10][4].next=board[10][3];
        
        board[10][3].type=3;//tail
        board[10][3].prev=board[10][4];
        board[10][3].next=null;//means it is tail
        
        board[15][10].type=4;//food
        foodPtr=board[15][10];
        snakeDir = 1;
        
        updateGui();//calls update box
    } //initializes board1
    
    public void initBoard2(){
        levelwin=0;
        level = 2;
        scoreLabel.setText("Score : "+Integer.toString(score));
        levelLabel.setText("level : "+level+"/"+maxlevel);
        
        for(int i=0;i<31;i++)//initilize all 2
            for(int j=0;j<31;j++){
                board[i][j]=new rawBox();
                board[i][j].type=2;
                board[i][j].x=j;
                board[i][j].y=i;
            }
        
        for(int i=0;i<31;i++)//initilize all 1
            for(int j=0;j<31;j++){
                board[1][j].type=1;
                board[30][j].type=1;
                board[i][30].type=1;
                board[i][1].type=1;    
            }
        
        board[10][5].type=3;//head
        board[10][5].prev=null;//actually it is useless to define prev to head
        board[10][5].next=board[10][4];
        headPtr=board[10][5];
        
        board[10][4].type=3;//body
        board[10][4].prev=board[10][5];
        board[10][4].next=board[10][3];
        
        board[10][3].type=3;//tail
        board[10][3].prev=board[10][4];
        board[10][3].next=null;//means it is tail
        
        board[15][10].type=4;//food
        foodPtr=board[15][10];
        snakeDir = 1;
        
        updateGui();//calls update box
    } //initializes board2
    
    public void initBoard3(){
        levelwin=0;
        level = 3;
        scoreLabel.setText("Score : "+Integer.toString(score));
        levelLabel.setText("level : "+level+"/"+maxlevel);
        
        for(int i=0;i<31;i++)//initilize all 2
            for(int j=0;j<31;j++){
                board[i][j]=new rawBox();
                board[i][j].type=2;
                board[i][j].x=j;
                board[i][j].y=i;
            }
        
        for(int i=0;i<31;i++)//initilize all 1
            for(int j=0;j<31;j++){
                board[1][j].type=1;
                board[30][j].type=1;
                board[i][30].type=1;
                board[i][1].type=1;    
            }
        
        board[7][7].type=1;board[7][8].type=1;board[8][7].type=1;board[7][9].type=1;board[9][7].type=1;
        board[7][23].type=1;board[7][22].type=1;board[8][23].type=1;board[7][21].type=1;board[9][23].type=1;
        board[23][7].type=1;board[23][8].type=1;board[22][7].type=1;board[23][9].type=1;board[21][7].type=1;
        board[23][23].type=1;board[22][23].type=1;board[23][22].type=1;board[21][23].type=1;board[23][21].type=1;
         
        
        board[10][5].type=3;//head
        board[10][5].prev=board[10][6];//actually it is useless to define prev to head
        board[10][5].next=board[10][4];
        headPtr=board[10][5];
        
        board[10][4].type=3;//body
        board[10][4].prev=board[10][5];
        board[10][4].next=board[10][3];
        
        board[10][3].type=3;//tail
        board[10][3].prev=board[10][4];
        board[10][3].next=null;//means it is tail
        
        board[15][12].type=4;//food
        foodPtr=board[15][12];
        snakeDir = 1;
        
        updateGui();//calls update box
    } //initializes board3
        
    public class moveSnake implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            rawBox process = headPtr;
            boolean levelFinish=false;
            int x=headPtr.x,y=headPtr.y,localflag=0;
            switch(snakeDir){
                case 1:
                    if(board[y][x+1].type==2||board[y][x+1].type==4){
                        process.prev=board[y][x+1];
                    }
                    else{
                        levelFinish=true;
                        gameFinish();
                    }
                    break;
                case 2:
                    if(board[y+1][x].type==2||board[y+1][x].type==4){
                        process.prev=board[y+1][x]; 
                    }
                    else{
                        levelFinish=true;
                        gameFinish();
                    }
                    break;
                case 3:
                    if(board[y][x-1].type==2||board[y][x-1].type==4){
                        process.prev=board[y][x-1];  
                    }
                    else{
                        levelFinish=true;
                        gameFinish();
                    }
                    break;
                case 4:
                    if(board[y-1][x].type==2||board[y-1][x].type==4){
                        process.prev=board[y-1][x];                       
                    }
                    else{
                        levelFinish=true;
                        gameFinish();
                    }
                    break;
            }
            
            if(levelFinish==false){//if not finished
                if(process.prev.type==4) localflag=1;

                process.prev.type=3;
                process.prev.prev=null;
                process.prev.next=process;

                headPtr=process.prev;

                process=process.next;

                if(localflag==0){
                    while(process.next!=null&&localflag==0){
                        process=process.next;
                    }//end while

                    process.prev.next=null;
                    process.type=2;    
                }
                else{//localflag==1 i.e got food
                    scoreUpdate();
                    generateFood();
                }
                
                lock=false;
                updateGui();
            }
        }
    }
    
    public class moveFood implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            boolean localFlag=true;
            while(localFlag){
                int localProb = (int)(random()*10);
                int localX=foodPtr.x;int localY=foodPtr.y;

                if(localProb<5 && localProb>0){
                   switch(localProb){
                       case 1:
                            if(board[localY][localX+1].type==2){
                                foodDir=1;
                                foodPtr.type=2;
                                foodPtr=board[localY][localX+1]; 
                                localFlag=false;
                            }
                            break;
                       case 2:
                            if(board[localY+1][localX].type==2){
                                foodDir=2;
                                foodPtr.type=2;
                                foodPtr=board[localY+1][localX];
                                localFlag=false;
                            }
                            break;
                       case 3:
                            if(board[localY][localX-1].type==2){
                                foodDir=3;
                                foodPtr.type=2;
                                foodPtr=board[localY][localX-1];
                                localFlag=false;
                            }
                            break;
                       case 4:
                            if(board[localY-1][localX].type==2){
                                foodDir=4;
                                foodPtr.type=2;
                                foodPtr=board[localY-1][localX];
                                localFlag=false;
                            }
                            break;
                   }//end switch localProb                   
                }//end if
                else { // move on same direction
                    switch(foodDir){
                       case 1:
                            if(board[localY][localX+1].type==2){
                                foodDir=1;
                                foodPtr.type=2;
                                foodPtr=board[localY][localX+1]; 
                                localFlag=false;
                            }
                            break;
                       case 2:
                            if(board[localY+1][localX].type==2){
                                foodDir=2;
                                foodPtr.type=2;
                                foodPtr=board[localY+1][localX];
                                localFlag=false;
                            }
                            break;
                       case 3:
                            if(board[localY][localX-1].type==2){
                                foodDir=3;
                                foodPtr.type=2;
                                foodPtr=board[localY][localX-1];
                                localFlag=false;
                            }
                            break;
                       case 4:
                            if(board[localY-1][localX].type==2){
                                foodDir=4;
                                foodPtr.type=2;
                                foodPtr=board[localY-1][localX];
                                localFlag=false;
                            }
                            break;
                   }//end switch foodDir
                }
            }//end while
            foodPtr.type=4;
            updateGui();
        }
    }
    
    public void generateFood(){
        int x,y;
        while(true){
            x=(int)(random()*30)+1;
            y=(int)(random()*30)+1;

            if(board[y][x].type==2){
                foodPtr=board[y][x];
                foodPtr.type=4;
                break;
            }
        }
    }
   
    public void scoreUpdate(){
        score+=level;
        if(score>bestScore){
            bestScore=score;
            gamePannel.scoreUpdate();
            bestScoreLabel.setText("Best Score : "+String.format("%03d",bestScore));
        }
        scoreLabel.setText("Score : "+Integer.toString(score));
    }//updates moves
    
    public void keyInput(KeyEvent e){
       char input=e.getKeyChar();
        if(lock==false){
            if(input=='w' || input=='W'){
                if(snakeDir==1 || snakeDir==3){
                    snakeDir=4;
                    lock=true;
                }
            }
            if(input=='s' || input=='S'){
                if(snakeDir==1 || snakeDir==3){
                    snakeDir=2;
                    lock=true;
                }
            }
            if(input=='a' || input=='A'){
                if(snakeDir==4 || snakeDir==2){
                    snakeDir=3;
                    lock=true;
                }
            }
            if(input=='d' || input=='D'){
                if(snakeDir==4 || snakeDir==2){
                    snakeDir=1;
                    lock=true;
                }
            }
        }
        if(input=='l' || input=='L'){
            new moveSnake().actionPerformed(new ActionEvent(score, win, TOOL_TIP_TEXT_KEY));//debugg mover
        }
        if(input==' '){
            resumeButtonActionPerformed(new ActionEvent(score, win, TOOL_TIP_TEXT_KEY));
        }
    }//calls updateBoard if key recognised
    
    public void getFocus(){
        controlPannel.grabFocus();//get focus back to control pannel
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        heading = new javax.swing.JLabel();
        controlPannel = new javax.swing.JPanel();
        restartButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        resumeButton = new javax.swing.JButton();
        bestScoreLabel = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        footerLabel = new javax.swing.JLabel();
        background_Image = new javax.swing.JLabel();

        setLayout(null);

        heading.setFont(new java.awt.Font("DejaVu Serif", 1, 48)); // NOI18N
        heading.setForeground(new java.awt.Color(255, 25, 255));
        heading.setText("Snake");
        add(heading);
        heading.setBounds(220, -10, 250, 90);

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

        restartButton.setText("restart ");
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartButtonActionPerformed(evt);
            }
        });
        add(restartButton);
        restartButton.setBounds(670, 440, 110, 29);

        helpButton.setText("help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        add(helpButton);
        helpButton.setBounds(670, 510, 110, 29);

        resumeButton.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        resumeButton.setText("resume");
        resumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resumeButtonActionPerformed(evt);
            }
        });
        add(resumeButton);
        resumeButton.setBounds(670, 370, 110, 30);

        bestScoreLabel.setFont(new java.awt.Font("TakaoPGothic", 1, 24)); // NOI18N
        bestScoreLabel.setForeground(new java.awt.Color(252, 236, 236));
        bestScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bestScoreLabel.setText("Best Score : 000");
        add(bestScoreLabel);
        bestScoreLabel.setBounds(550, 20, 250, 50);

        levelLabel.setFont(new java.awt.Font("TakaoPGothic", 1, 24)); // NOI18N
        levelLabel.setForeground(new java.awt.Color(252, 236, 236));
        levelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        levelLabel.setText("level : 0/3");
        add(levelLabel);
        levelLabel.setBounds(630, 130, 180, 50);

        scoreLabel.setFont(new java.awt.Font("TakaoPGothic", 1, 24)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(252, 236, 236));
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreLabel.setText("Score : 0");
        add(scoreLabel);
        scoreLabel.setBounds(630, 210, 180, 50);

        footerLabel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        footerLabel.setForeground(new java.awt.Color(255, 244, 244));
        footerLabel.setText("made with love by Team .EXE");
        add(footerLabel);
        footerLabel.setBounds(50, 600, 250, 20);

        background_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gamePannelBackground6.jpg"))); // NOI18N
        add(background_Image);
        background_Image.setBounds(0, 0, 820, 620);
    }// </editor-fold>//GEN-END:initComponents

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        snakeTimer.stop();
        if(level>1)
            foodTimer.stop();
        JOptionPane.showMessageDialog(controlPannel,"* Use W A S D keys to move snake\n"
                                            +"* Press spaceBar key to resume if paused\n"
                                            +"* You can play game as many times you want\n"
                                            +"* Your best score will be added to final game\n"
                                            +"* You can switch to other game after completing a level\n"
                                            ,"Instructions"
                                            ,JOptionPane.INFORMATION_MESSAGE                                    
        );
        controlPannel.grabFocus();//get focus back to control pannel
    }//GEN-LAST:event_helpButtonActionPerformed

    private void resumeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resumeButtonActionPerformed
        if(snakeTimer.isRunning()==false){
            snakeTimer.start();
            if(level>1)
                foodTimer.start();
        }
        controlPannel.grabFocus();//get focus back to control pannel
    }//GEN-LAST:event_resumeButtonActionPerformed

    private void restartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartButtonActionPerformed
        snakeTimer.stop();
        if(level>1)
            foodTimer.stop();
        level=1;
        initBoard1();
        controlPannel.grabFocus();//get focus back to control pannel
    }//GEN-LAST:event_restartButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background_Image;
    private javax.swing.JLabel bestScoreLabel;
    private javax.swing.JPanel controlPannel;
    private javax.swing.JLabel footerLabel;
    private javax.swing.JLabel heading;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JButton restartButton;
    private javax.swing.JButton resumeButton;
    private javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables
}
