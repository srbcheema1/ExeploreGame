package exeploreGame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.Math.random;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author srb
 */
public class GamePannel4 extends javax.swing.JPanel {
    
    int board[][] = new int[10][10];//board for values //1 is ccupied
    int memoryBoard[][] = new int[10][10];//board for last move 
    int playerX,playerY;
    int turn;//-1 for bot 1 for player
    int diff=1000,depth;//difficulty
    int box1x,box1y,box2x,box2y;
    int win=0,levelwin,moves=0,totalmoves=0,memoryMoves,level,maxlevel=3;
    
    //virtaul
    int vplayerX,vplayerY;
    int vboard[][] = new int[10][10];//board for values //1 is ccupied
    
    Border darkborder = new LineBorder(Color.DARK_GRAY, 1);
    Border lightborder = new LineBorder(Color.lightGray, 1);
    Border whiteborder = new LineBorder(Color.white, 1);
    Border nullborder = new LineBorder(Color.darkGray, 0);
    KeyAdapter keyInputAdapter;
    GamePannel gamePannel ;
    
    JPanel stage = new JPanel();
    
    JButton[][] box = new JButton[10][10];
    CardLayout card=new CardLayout();//layout for controlPannel
    Timer botTimer;
    int lock=0;

    public GamePannel4(GamePannel gamePannel) {
        initComponents();
        this.gamePannel=gamePannel;
        myinit();
    }

    private void myinit() {
        setSize(820, 620);//our standard game pannel size
        setOpaque(false);//to make buttons transparent
        
        controlPannel.setBounds(80,100,550,550);//ratio 10:10
        controlPannel.setLayout(card);
        controlPannel.setFocusable(true);
        controlPannel.setOpaque(false);//to make buttons transparent
        controlPannel.setFocusTraversalKeysEnabled(false);
        keyInputAdapter=new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                keyInput(e);
            }
        };//calls keyInput()
        controlPannel.addKeyListener(keyInputAdapter);
        
        createBox();//creates boxes
        
        initBoard1();//level one start
    }
    
    public void createBox(){
        stage.setLayout(new GridLayout(10,10,0,0));
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                box[i][j]=new JButton();
                box[i][j].setSize(50,50);
                box[i][j].setBorder(lightborder);
                stage.add(box[i][j]);
            }
        }
        stage.setOpaque(false);
        controlPannel.add(stage);
    } //creates boxes 
    
    public void initBoard1(){
        levelwin=0;
        level = 1;
        moves=0;
        scoreLabel.setText("Score : "+Integer.toString(moves));
        
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++){
                board[i][j]=2;
            }
        
        for(int i=0;i<10;i++){ //borders 
            board[0][i]=0;
            board[i][0]=0;
            board[i][9]=0;
            board[9][i]=0;
        }
        
        for(int i=0;i<10;i++){ //debugg  
            board[8][i]=0;
            board[i][8]=0;
        } 
        
        board[1][1]=3;
        
        turn=1;
        playerX=1;playerY=1;
        lastMove();
        updateBox();
    }
    
    public void updateBox(){
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++){
                if(board[i][j]==0){
                    box[i][j].setIcon(null);
                    box[i][j].setBorder(nullborder);
                    box[i][j].setFocusPainted( false );
                    box[i][j].setContentAreaFilled( false );//to make buttons visible
                }
                if(board[i][j]==1){
                    box[i][j].setIcon(null);
                    box[i][j].setBorder(nullborder);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled( true );//to make buttons transparent
                    box[i][j].setBackground(Color.darkGray);
               //     box[i][j].setOpaque(false);
                }
                if(board[i][j]==2){
                    box[i][j].setIcon(null);
                    box[i][j].setBorder(lightborder);
                    box[i][j].setBackground(Color.white);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
                }
                if(board[i][j]==3){
                    box[i][j].setBorder(lightborder);
                    box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boy2.png")));
                    box[i][j].setBackground(Color.white);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
                    playerX=j;playerY=i;
                }
            }
    }//updates Box
    
    public void wincheck(){
        int a=0;
        if(board[playerY][playerX+1]!=2)
            if(board[playerY+1][playerX]!=2)
                if(board[playerY][playerX-1]!=2)
                    if(board[playerY-1][playerX]!=2)
                        a=1;
        if(a==1&&turn==1){
            levelwin=1;
            JOptionPane.showMessageDialog(controlPannel,"Hurrey you won"+"your score : "+moves+" ","Level Complete",JOptionPane.INFORMATION_MESSAGE);
        }  
        if(a==1&&turn==-1){
            JOptionPane.showMessageDialog(controlPannel,"your score : "+moves+" ","Level Complete",JOptionPane.INFORMATION_MESSAGE);
        }
    }//checks if player or bot wins or not
    
    public void updateBoard(){
        lastMove();
        if(board[box2y][box2x]==2){//if moveable
            board[box2y][box2x]=3;
            board[box1y][box1x]=1;//it is to be 1
            updateBox();
            moveUpdate();
            wincheck();
            if(levelwin==1)
                return;

            turn=-1;
            Thread botThread=new Thread(){
                public void run(){
                    botMove();
                }
            };
            botThread.start();
        }
    }//updates the board
    
    public void botMove(){
        depth=0;//set depth
        
        //think code
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                   moveTeller.setText("bot thinking .");
                }
            });
        vplayerX=playerX;
        vplayerY=playerY;
        for(int i=0;i<10;i++)//copied into itself
            System.arraycopy(board[i], 0, vboard[i], 0, 10);  
        ArrayList<Integer> moveable_ind,good_ind;
        moveable_ind = new ArrayList<>();
        good_ind = new ArrayList<>();
        
        int a,b,c,d;
        
        if(vboard[vplayerY+1][vplayerX]==2){//down
            a=userVirtualMove(playerY+1,playerX);
            vboard[playerY+1][playerX]=2;
            moveable_ind.add(0);
            if(a == 1)
                good_ind.add(0);
        }
        if(vboard[vplayerY][vplayerX+1]==2){//rght
            b=userVirtualMove(playerY,playerX+1);
            vboard[playerY][playerX+1]=2;
            moveable_ind.add(1);
            if(b == 1)
                good_ind.add(1);
        }
        if(vboard[vplayerY-1][vplayerX]==2){//up
            c=userVirtualMove(playerY-1,playerX);
            vboard[playerY-1][playerX]=2;
            moveable_ind.add(2);
            if(c == 1)
                good_ind.add(2);
        }
        if(vboard[vplayerY][vplayerX-1]==2){//left
            d=userVirtualMove(playerY,playerX-1);
            vboard[playerY][playerX-1]=2;
            moveable_ind.add(3);
            if(d == 1)
                good_ind.add(3);
        }
        
        
        if(good_ind.isEmpty()  || diff <= 999){
            int rand=(((int)(random()*100))%moveable_ind.size());
            if(moveable_ind.get(rand)==0)
                rand=(((int)(random()*100))%moveable_ind.size());

            switch(moveable_ind.get(rand)){
                case 0:
                    box2x=playerX;
                    box2y=playerY+1;
                    board[playerY+1][playerX]=3;
                    break;
                case 1:
                    box2x=playerX+1;
                    box2y=playerY;
                    board[playerY][playerX+1]=3;
                    break;
                case 2:
                    box2x=playerX;
                    box2y=playerY-1;
                    board[playerY-1][playerX]=3;
                    break;
                case 3:
                    box2x=playerX-1;
                    box2y=playerY;
                    board[playerY][playerX-1]=3;
                    break;
                default:
                    System.out.println("random got out of control");     
            }
            board[playerY][playerX]=1;
        }
        else{
            int rand=(((int)(random()*100))%good_ind.size());

            switch(good_ind.get(rand)){
                case 0:
                    box2x=playerX;
                    box2y=playerY+1;
                    board[playerY+1][playerX]=3;
                    break;
                case 1:
                    box2x=playerX+1;
                    box2y=playerY;
                    board[playerY][playerX+1]=3;
                    break;
                case 2:
                    box2x=playerX;
                    box2y=playerY-1;
                    board[playerY-1][playerX]=3;
                    break;
                case 3:
                    box2x=playerX-1;
                    box2y=playerY;
                    board[playerY][playerX-1]=3;
                    break;
                default:
                    System.out.println("random got out of control");     
            }
            board[playerY][playerX]=1;    
        }//end else
        
        moveUpdate(); 
        
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                   moveTeller.setText("bot thinking . .");
                }
            });
            try {Thread.sleep(200);}
            catch (InterruptedException ex) {} 
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                   moveTeller.setText("bot thinking . . .");
                }
            });
            try {Thread.sleep(200);}
            catch (InterruptedException ex) {} 
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                   moveTeller.setText("bot thinking . ");
                }
            });
            try {Thread.sleep(200);}
            catch (InterruptedException ex) {} 
        if(moves>4){
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                   moveTeller.setText("bot thinking . .");
                }
            });
            try {Thread.sleep(200);}
            catch (InterruptedException ex) {} 
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                   moveTeller.setText("bot thinking . . .");
                }
            });
            try {Thread.sleep(200);}
            catch (InterruptedException ex) {} 
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                   updateBox();
                   moveTeller.setText("your move");
                   wincheck();
                   turn=1;//give turn to player
                   getlock();
                }
            });
        
        controlPannel.grabFocus();
    }
    
    public synchronized int getlock(){
        int temp = lock;
        int a=lock;
        if(lock==1)a=0;
        else {
            a=1;
        }
        lock=a;
        return temp;
    }
    
    public int botVirtualMove(int x,int y){
        vboard[x][y]=0;
        int a,b,c,d;
        depth++;
        if(depth>diff){
            depth=0;
            return 0;
        }    
        if(vboard[x][y+1]==2){
            d=userVirtualMove(x,y+1);
            vboard[x][y+1]=2;
            if(d==1)
                return 1;
        }
        if(vboard[x+1][y]==2){
            a=userVirtualMove(x+1,y);
            vboard[x+1][y]=2;
            if(a==1)
                return 1;        
        }
        if(vboard[x][y-1]==2){
            b=userVirtualMove(x,y-1);
            vboard[x][y-1]=2;
            if(b==1)
                return 1;
        }
        if(vboard[x-1][y]==2){
            c=userVirtualMove(x-1,y);
            vboard[x-1][y]=2;
            if(c==1)
                return 1;
        }
        return 0;    
    }

    public int userVirtualMove(int x,int y){
        vboard[x][y]=0; 
        int a,b,c,d;
        
        if(vboard[x][y+1]==2){
            d=botVirtualMove(x,y+1);
            vboard[x][y+1]=2;
            if(d==0)
                return 0;
        }
        if(vboard[x+1][y]==2){
            a=botVirtualMove(x+1,y);
            vboard[x+1][y]=2;
            if(a==0)
                return 0;
        }
        if(vboard[x][y-1]==2){
            b=botVirtualMove(x,y-1);
            vboard[x][y-1]=2;
            if(b==0)
                return 0;
        }
        if(vboard[x-1][y]==2){
            c=botVirtualMove(x-1,y);
            vboard[x-1][y]=2;
            if(c==0)
                return 0;
        }
        return 1;  
    }
        
    public void moveUpdate(){
        totalmoves++;
        moves++;
        scoreLabel.setText("Score : "+Integer.toString(moves));
    }//updates moves
    
    public void lastMove(){
        for(int i=0;i<10;i++)
            System.arraycopy(board[i], 0, memoryBoard[i], 0, 10); 
        memoryMoves=moves;
    }//copy array into memoryBoard
    
    public void keyInput(KeyEvent e){
        char input=e.getKeyChar();
        if(turn==1&&getlock()==0){
            if(input=='w' || input=='W'){
                box1x=playerX;box1y=playerY;
                box2x=playerX;box2y=playerY-1;
                updateBoard();
            }
            if(input=='s' || input=='S'){
                box1x=playerX;box1y=playerY;
                box2x=playerX;box2y=playerY+1;
                updateBoard();
            }
            if(input=='a' || input=='A'){
                box1x=playerX;box1y=playerY;
                box2x=playerX-1;box2y=playerY;
                updateBoard();
            }
            if(input=='d' || input=='D'){
                box1x=playerX;box1y=playerY;
                box2x=playerX+1;box2y=playerY;
                updateBoard();
            }
        }
        else{
        }
    }//calls updateBoard if key recognised
    
    public void getFocus(){
        controlPannel.grabFocus();//get focus back to control pannel
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPannel = new javax.swing.JPanel();
        heading1 = new javax.swing.JLabel();
        heading2 = new javax.swing.JLabel();
        heading3 = new javax.swing.JLabel();
        moveTeller = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        undoButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        footerLabel = new javax.swing.JLabel();
        background_image = new javax.swing.JLabel();

        setLayout(null);

        javax.swing.GroupLayout controlPannelLayout = new javax.swing.GroupLayout(controlPannel);
        controlPannel.setLayout(controlPannelLayout);
        controlPannelLayout.setHorizontalGroup(
            controlPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        controlPannelLayout.setVerticalGroup(
            controlPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        add(controlPannel);
        controlPannel.setBounds(130, 150, 400, 400);
        controlPannel.getAccessibleContext().setAccessibleName("");

        heading1.setFont(new java.awt.Font("DejaVu Serif", 2, 55)); // NOI18N
        heading1.setForeground(new java.awt.Color(183, 183, 183));
        heading1.setText("VS");
        add(heading1);
        heading1.setBounds(290, 20, 90, 90);

        heading2.setFont(new java.awt.Font("DejaVu Serif", 1, 47)); // NOI18N
        heading2.setForeground(new java.awt.Color(248, 241, 248));
        heading2.setText("white");
        add(heading2);
        heading2.setBounds(400, 20, 160, 90);

        heading3.setFont(new java.awt.Font("DejaVu Serif", 1, 50)); // NOI18N
        heading3.setForeground(new java.awt.Color(1, 1, 1));
        heading3.setText("black");
        add(heading3);
        heading3.setBounds(120, 20, 160, 90);

        moveTeller.setForeground(new java.awt.Color(191, 174, 174));
        moveTeller.setText("your move ");
        add(moveTeller);
        moveTeller.setBounds(160, 110, 130, 30);

        scoreLabel.setFont(new java.awt.Font("TakaoPGothic", 1, 24)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(252, 236, 236));
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreLabel.setText("Score : 0");
        add(scoreLabel);
        scoreLabel.setBounds(630, 120, 180, 50);

        helpButton.setText("help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        add(helpButton);
        helpButton.setBounds(670, 440, 110, 29);

        undoButton.setText("undo");
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });
        add(undoButton);
        undoButton.setBounds(670, 360, 110, 29);

        newButton.setText("reset");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });
        add(newButton);
        newButton.setBounds(670, 290, 110, 29);

        footerLabel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        footerLabel.setForeground(new java.awt.Color(255, 244, 244));
        footerLabel.setText("made with love by Team .EXE");
        add(footerLabel);
        footerLabel.setBounds(40, 590, 250, 20);

        background_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gamePannelBackground4.jpg"))); // NOI18N
        add(background_image);
        background_image.setBounds(0, 0, 820, 620);
    }// </editor-fold>//GEN-END:initComponents

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        JOptionPane.showMessageDialog(controlPannel,"* use W A S D keys to move player\n"
            +"* you and bot will get alternative turns\n"
            +"* white boxes are to be turned black by visiting them\n"
            +"* you can visit only adjacent white box\n"
            +"* each black box carries 1 point\n"
            +"* you can undo only the last move\n"
            +"* computer will try to block your move\n"
            +"* game finishes if you are not able to move further\n"
            ,"Instructions"
            ,JOptionPane.INFORMATION_MESSAGE
        );
        controlPannel.grabFocus();//get focus back to control pannel
    }//GEN-LAST:event_helpButtonActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        
        moves=memoryMoves;
        scoreLabel.setText("Score : "+Integer.toString(moves));
        for(int i=0;i<10;i++){
            System.arraycopy(memoryBoard[i], 0, board[i], 0, 10);
        updateBox();
        controlPannel.grabFocus();//get focus back to control pannel
        }
    }//GEN-LAST:event_undoButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        initBoard1();
        controlPannel.grabFocus();//get focus back to control pannel
    }//GEN-LAST:event_newButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background_image;
    private javax.swing.JPanel controlPannel;
    private javax.swing.JLabel footerLabel;
    private javax.swing.JLabel heading1;
    private javax.swing.JLabel heading2;
    private javax.swing.JLabel heading3;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel moveTeller;
    private javax.swing.JButton newButton;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JButton undoButton;
    // End of variables declaration//GEN-END:variables


}
