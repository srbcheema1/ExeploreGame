package exeploreGame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Math.random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GamePannel5 extends javax.swing.JPanel {

    final private rawBox board[][] = new rawBox[31][31];//board for values
    int win=0,levelwin=0,movesLeft=300,totalmoves=0,level,maxlevel=1,bestScore=0;

    final private Border darkborder = new LineBorder(Color.DARK_GRAY, 1);
    final private Border lightborder = new LineBorder(Color.lightGray, 1);
    final private Border nullborder = new LineBorder(Color.darkGray, 0);
    final private GamePannel gamePannel;

    final private JPanel stage = new JPanel();

    final private JButton[][] box = new JButton[6][6];
    final private CardLayout card=new CardLayout();//layout for controlPannel
    
    //game variables
    int picnum=0;//randomly generated pic num
    rawBox mainBox=null;
    
    class rawBox{
        int type;//0 for null //2 for game area 
        int x,y;
        int picIndex;
    }
    
    public GamePannel5(GamePannel gamePannel) {
        initComponents();
        this.gamePannel=gamePannel;
        myinit();
    }//constructor
    
    public final void myinit(){
        setSize(820, 620);//our standard game pannel size
        setOpaque(false);//to make buttons transparent
        
        controlPannel.setBounds(80,100,480,480);
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
        
        picnum=(int)(random()*4)+1;
    //    picnum=2;//debugging
        initBoard();//level one start
        
    }//my init
    
    public boolean gameFinish(){
        levelwin=1;
        if(levelwin==1){
            if(level<maxlevel){
                JOptionPane.showMessageDialog(controlPannel,"move to next level","Level Complete",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(level==maxlevel){
                win=1;                
                if(movesLeft>bestScore){
                    if(movesLeft<150){
                        bestScore=movesLeft;
                    }
                    else{
                       bestScore=150; 
                    }
                    bestScoreLabel.setText("Best Score : "+String.format("%03d",bestScore));
                    gamePannel.scoreUpdate();
                }
                JOptionPane.showMessageDialog(controlPannel,"Your Score : "+bestScore+" ","Game Complete",JOptionPane.INFORMATION_MESSAGE);
            }   
        }
        return true;
    }// called when game finishes
    
    public void createBox(){
        stage.setLayout(new GridLayout(4,4,0,0));
        for(int i=1;i<5;i++){
            for(int j=1;j<5;j++){
                box[i][j]=new JButton();
                box[i][j].setSize(120,120);
                box[i][j].setBorder(lightborder);
                box[i][j].addMouseListener(new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mouseInput(e);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {    
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {    
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {     
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {    
                    }
                    
                });//calls mouseInput
                stage.add(box[i][j]);
            }
        }
        stage.setOpaque(false);
        controlPannel.add(stage);
    } //creates boxes 
    
    public void updateGui(){
        String iconurl;
        
        for(int i=1;i<5;i++)
            for(int j=1;j<5;j++){
                    box[i][j].setBorder(nullborder);
                    iconurl="/images/slice ";
                    iconurl = iconurl+board[i][j].picIndex+" pic "+picnum+".jpg";
                    box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource(""+iconurl)));
                    if(board[i][j].picIndex==0)box[i][j].setIcon(null);
                    box[i][j].setBackground(Color.lightGray);
                    box[i][j].setFocusPainted( true );
                    box[i][j].setContentAreaFilled(true );//to make buttons visible
            }
    } //update box display
    
    public void initBoard(){
        levelwin=0;
        level = 1;
        movesLeft=300;
        movesLabel.setText("Moves Left : "+Integer.toString(movesLeft));
        
        for(int i=0;i<6;i++)//initilize all 0
            for(int j=0;j<6;j++){
                board[i][j]=new rawBox();
                board[i][j].x=j;
                board[i][j].y=i;
                board[i][j].type=0;
            }
        
        for(int i=1;i<5;i++)
            for(int j=1;j<5;j++)
                board[i][j].type=2;//game area
        
        puzzleGenerate();
        
        mainBox=board[4][4];
        
        String iconurl;
        iconurl = "/images/hintpic"+picnum+".jpg";
        hintLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(""+iconurl)));
        
        updateGui();//calls update box
    } //initializes board1
   
    public void movesUpdate(){
        movesLeft--;
        movesLabel.setText("Moves Left : "+Integer.toString(movesLeft));
    }//updates moves
    
    public void mouseInput(MouseEvent e){
        JButton raw=((JButton)e.getSource());
        int x=0,y=0,temp;
        for(int i=1;i<5;i++)
            for(int j=0;j<5;j++)
                if(raw==box[i][j]){
                    x=j;
                    y=i;
                }
        
        if(board[y-1][x]==mainBox){//main box up
            temp = board[y-1][x].picIndex;
            board[y-1][x].picIndex=board[y][x].picIndex;
            board[y][x].picIndex=temp;
            mainBox=board[y][x];
            movesUpdate();
        }
        else if(board[y+1][x]==mainBox){//main box down
            temp = board[y+1][x].picIndex;
            board[y+1][x].picIndex=board[y][x].picIndex;
            board[y][x].picIndex=temp;
            mainBox=board[y][x];
            movesUpdate();
        }
        else if(board[y][x-1]==mainBox){//mainbox left
            temp = board[y][x-1].picIndex;
            board[y][x-1].picIndex=board[y][x].picIndex;
            board[y][x].picIndex=temp;
            mainBox=board[y][x];
            movesUpdate();
        }
        else if(board[y][x+1]==mainBox){//main box right
            temp = board[y][x+1].picIndex;
            board[y][x+1].picIndex=board[y][x].picIndex;
            board[y][x].picIndex=temp;
            mainBox=board[y][x];
            movesUpdate();
        }
        updateGui();
        wincheck();
    }
    
    public void keyInput(KeyEvent e){
        char input=e.getKeyChar();
        int x,y,temp;
        x=mainBox.x;y=mainBox.y;
        switch(input){
            case 'w':
                if(board[y-1][x].type==2){
                    temp = board[y-1][x].picIndex;
                    board[y-1][x].picIndex=board[y][x].picIndex;
                    board[y][x].picIndex=temp;
                    mainBox=board[y-1][x];
                    movesUpdate();
                }
                break;
            case 'a':
                if(board[y][x-1].type==2){
                    temp = board[y][x-1].picIndex;
                    board[y][x-1].picIndex=board[y][x].picIndex;
                    board[y][x].picIndex=temp;
                    mainBox=board[y][x-1];
                    movesUpdate();
                }
                break;
            case 's':
                if(board[y+1][x].type==2){
                    temp = board[y+1][x].picIndex;
                    board[y+1][x].picIndex=board[y][x].picIndex;
                    board[y][x].picIndex=temp;
                    mainBox=board[y+1][x];
                    movesUpdate();
                }
                break;
            case 'd':
                if(board[y][x+1].type==2){
                    temp = board[y][x+1].picIndex;
                    board[y][x+1].picIndex=board[y][x].picIndex;
                    board[y][x].picIndex=temp;
                    mainBox=board[y][x+1];
                    movesUpdate();
                }
                break;
        }
        updateGui();
        wincheck();
    }//calls updateBoard if key recognised
    
    public void wincheck(){//checks whether game finished or not
        if(movesLeft==0){
            JOptionPane.showMessageDialog(controlPannel,"Game Over !\n"
                                                ,"Instructions"
                                                ,JOptionPane.INFORMATION_MESSAGE                                    
            );
            initBoard();
            controlPannel.grabFocus();//get focus back to control pannel
            return;
        }
        int temp=1;
        for(int i=1;i<5;i++)
            for(int j=1;j<5;j++){
                if(i==4&&j==4)break;
                if(board[i][j].picIndex!=temp)
                    return;
                temp++;
            }
        gameFinish();
        
    }
    
    public void puzzleGenerate(){ //it will generate a random puzzle
        int inversion,flag;
        while(true){
            inversion=0;

            for(int i=1;i<5;i++)//set all 0
                for(int j=1;j<5;j++)
                    board[i][j].picIndex=0;

            for(int i=1;i<5;i++)
                for(int j=1;j<5;j++){
                    flag=0;
                    if(i==4 && j==4) break;
                    int rand=(int)(random()*16);
                    if(rand==0) {
                        j--;
                        continue;
                    }
                    else{
                        for(int k=1;k<5;k++)
                            for(int l=1;l<5;l++)
                                if(rand==board[k][l].picIndex) flag=1;
                    }
                    if(flag==1) j--;
                    else board[i][j].picIndex=rand;
                }
            int forinversion[]=new int[15],index=0;
            for(int i=1;i<5;i++)
                for(int j=1;j<5;j++){
                    if(i==4 && j==4 ) break;
                    forinversion[index++]=board[i][j].picIndex;
                }
            for(int i=0;i<14;i++)
                for(int j=i+1;j<15;j++)
                    if(forinversion[i]>forinversion[j]) inversion++;
            if(inversion%2==0) break;
        }
    }
    
    public void getFocus(){
        controlPannel.grabFocus();//get focus back to control pannel
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        heading = new javax.swing.JLabel();
        controlPannel = new javax.swing.JPanel();
        hintLabel = new javax.swing.JLabel();
        newButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        newPicButton = new javax.swing.JButton();
        bestScoreLabel = new javax.swing.JLabel();
        movesLabel = new javax.swing.JLabel();
        footerLabel = new javax.swing.JLabel();
        background_Image = new javax.swing.JLabel();

        setLayout(null);

        heading.setFont(new java.awt.Font("DejaVu Serif", 1, 48)); // NOI18N
        heading.setForeground(new java.awt.Color(255, 25, 255));
        heading.setText("Puzzle 4x4");
        add(heading);
        heading.setBounds(170, 0, 330, 90);

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
        controlPannel.setBounds(80, 100, 480, 480);
        controlPannel.getAccessibleContext().setAccessibleName("");

        hintLabel.setText("jLabel1");
        add(hintLabel);
        hintLabel.setBounds(600, 160, 200, 200);

        newButton.setText("reset");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });
        add(newButton);
        newButton.setBounds(650, 430, 110, 29);

        helpButton.setText("help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        add(helpButton);
        helpButton.setBounds(650, 550, 110, 29);

        newPicButton.setText("new pic");
        newPicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPicButtonActionPerformed(evt);
            }
        });
        add(newPicButton);
        newPicButton.setBounds(650, 490, 110, 29);

        bestScoreLabel.setFont(new java.awt.Font("TakaoPGothic", 1, 24)); // NOI18N
        bestScoreLabel.setForeground(new java.awt.Color(252, 236, 236));
        bestScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bestScoreLabel.setText("Best Score : 000");
        add(bestScoreLabel);
        bestScoreLabel.setBounds(580, 30, 240, 50);

        movesLabel.setFont(new java.awt.Font("TakaoPGothic", 1, 24)); // NOI18N
        movesLabel.setForeground(new java.awt.Color(252, 236, 236));
        movesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        movesLabel.setText("Moves Left : 300");
        add(movesLabel);
        movesLabel.setBounds(580, 90, 240, 50);

        footerLabel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        footerLabel.setForeground(new java.awt.Color(255, 244, 244));
        footerLabel.setText("made with love by Team .EXE");
        add(footerLabel);
        footerLabel.setBounds(50, 600, 250, 20);

        background_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gamePannelBackground5.jpg"))); // NOI18N
        add(background_Image);
        background_Image.setBounds(0, 0, 820, 620);
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        initBoard();
        
        controlPannel.grabFocus();//get focus back to control pannel
    }//GEN-LAST:event_newButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed

        JOptionPane.showMessageDialog(controlPannel,"* Click on the pic adjacent to blank pic to exchange its position\n"
                                            +"* You can also use W A S D keys to move blank pic in respective direction\n"
                                            +"* Game must be finished within 300 moves\n"
                                            +"* Score(equal to leftover moves) will only be awarded when whole pic is solved\n"
                                            +"* Max 150 score will be given \n"
                                            ,"Instructions"
                                            ,JOptionPane.INFORMATION_MESSAGE                                    
        );
        controlPannel.grabFocus();//get focus back to control pannel
    }//GEN-LAST:event_helpButtonActionPerformed

    private void newPicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPicButtonActionPerformed
        int temp=picnum;
        while(true){
        picnum=(int)(random()*4)+1;
        if(picnum!=temp)break;
        }
        String iconurl;
        iconurl = "/images/hintpic"+picnum+".jpg";
        hintLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(""+iconurl)));
        updateGui();
        controlPannel.grabFocus();//get focus back to control pannel
    }//GEN-LAST:event_newPicButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background_Image;
    private javax.swing.JLabel bestScoreLabel;
    private javax.swing.JPanel controlPannel;
    private javax.swing.JLabel footerLabel;
    private javax.swing.JLabel heading;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel hintLabel;
    private javax.swing.JLabel movesLabel;
    private javax.swing.JButton newButton;
    private javax.swing.JButton newPicButton;
    // End of variables declaration//GEN-END:variables
}
