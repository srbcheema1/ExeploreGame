package exeploreGame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


/**
 *
 * @author srb
 */
public class GamePannel3 extends javax.swing.JPanel implements ActionListener {
    int [][] board;
    int queencount;
    Border buttonborder=new LineBorder(Color.black,1);
    queenbutton checker[][]=new queenbutton[8][8];
    GamePannel gamePannel ;
    int score =0;

    public GamePannel3(GamePannel gamePannel) {
        this.setSize(820,620);
        this.gamePannel=gamePannel;
        this.queencount = 0;
        this.board=new int[8][8];
        initComponents();
        cellpanel.setLayout(new java.awt.GridLayout(8, 8));
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                checker[i][j]=new queenbutton(i,j);
          
                if((i+j)%2==0)
                {
                    checker[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boardwhite.jpg")));
                }
                else
                {   
                    checker[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boardblack.jpg")));
                }
                checker[i][j].setBorder(buttonborder);
                cellpanel.add(checker[i][j]);
               checker[i][j].addActionListener(this);
            }
        }
    }
    
        public void wincheck(){
        queencount=0;
       for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board[i][j]==1){
                    queencount++;
                }
            }
       }
       if(queencount==8){
           score=30;
           gamePannel.home();
           JOptionPane.showMessageDialog(null,"You won");
       }
       
       }
    public void checkqueen(){
        int sum=0,sub=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board[i][j]!=0){
                    board[i][j]=1;
                    sum=i+j;
                    sub=i-j;
                    System.out.println("i="+i+"j="+j);
                    for(int k=0;k<8;k++){
                        for(int l=0;l<8;l++){
                            if(board[k][l]!=0&&(k!=i||l!=j)){
                            if(k+l==sum||(k-l)==sub||k==i||l==j){
                               // checker[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/queencross.png")));
                                //checker[k][l].setIcon(new javax.swing.ImageIcon(getClass().getResource("/queencross.png")));
                               // board[i][j]=2;
                                board[k][l]=2;
                                board[i][j]=2;
                                queencount--;
                                //System.out.println("k="+k+"l="+l);
                            }
                           
                                   
                          
                        }
                        }
                    }
                }
            }
        }
          for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                
          if(board[i][j]==2){
              checker[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/queencross.png")));
          }
          if(board[i][j]==1){
              checker[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/queen.jpg")));
          }
            }
          }
    }
    public void toggle(int r,int c){
        if(board[r][c]==0 ){
            board[r][c]=1;
          //  checker[r][c].setIcon(new javax.swing.ImageIcon(getClass().getResource("/queen.jpg")));
          //  queencount++;
            checkqueen();
            wincheck();
        }
        else{
            board[r][c]=0;
            if((r+c)%2==0)
            checker[r][c].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boardwhite.jpg")));
            else
            checker[r][c].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boardblack.jpg")));
        //    queencount--;
            checkqueen();
            wincheck();
        }
    }
    public void actionPerformed(ActionEvent evt){
        queenbutton but;
        but = (queenbutton)evt.getSource();
        toggle(but.getrow(),but.getcol());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        New = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cellpanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        back = new javax.swing.JLabel();

        setLayout(null);

        jLabel2.setFont(new java.awt.Font("TakaoPGothic", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(205, 205, 205));
        jLabel2.setText("Eight Queen");
        add(jLabel2);
        jLabel2.setBounds(340, 20, 250, 40);

        New.setForeground(new java.awt.Color(1, 1, 1));
        New.setText("New");
        New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewActionPerformed(evt);
            }
        });
        add(New);
        New.setBounds(730, 230, 70, 29);

        jPanel2.setLayout(null);

        cellpanel.setBackground(new java.awt.Color(172, 63, 1));

        javax.swing.GroupLayout cellpanelLayout = new javax.swing.GroupLayout(cellpanel);
        cellpanel.setLayout(cellpanelLayout);
        cellpanelLayout.setHorizontalGroup(
            cellpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        cellpanelLayout.setVerticalGroup(
            cellpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        jPanel2.add(cellpanel);
        cellpanel.setBounds(25, 20, 440, 440);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boardoutline.jpg"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(0, 0, 490, 480);

        add(jPanel2);
        jPanel2.setBounds(50, 90, 490, 480);

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gamePannelBackground3.jpg"))); // NOI18N
        add(back);
        back.setBounds(-460, -100, 1310, 1200);
    }// </editor-fold>//GEN-END:initComponents

    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                board[i][j]=0;
                if((i+j)%2==0)
                {
                    checker[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boardwhite.jpg")));
                }
                else
                {
                    checker[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boardblack.jpg")));
                }

            }
        }  // TODO add your handling code here:
    }//GEN-LAST:event_NewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton New;
    private javax.swing.JLabel back;
    private javax.swing.JPanel cellpanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

public class queenbutton extends JButton
{
    int value,row,col;
    public queenbutton(int r,int c)
    {
        row=r; 
        col=c;
        value=0;
    }
    int getrow() 
    {
        return row;
    }
    int getcol()
    {
        return col;
    }
    int getvalue()
    {
        return value;
    }
    void setrow(int r)
    {
        row=r;
    }
    void setcol(int c)
    {
        col=c;
    }
    void setvalue(int val)
    {
        value=val;
    }
}
}
