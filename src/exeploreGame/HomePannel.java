package exeploreGame;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author srb
 */
public class HomePannel extends javax.swing.JPanel {
    
    Border darkborder = new LineBorder(Color.DARK_GRAY, 1);
    Border lightborder = new LineBorder(Color.lightGray, 1);
    Border nullborder = new LineBorder(Color.darkGray, 0);
    
    CardLayout card=new CardLayout();
    
    
    GamePannel gamePannel ;
    
    public HomePannel(GamePannel gamePannel) {
        initComponents();
        this.gamePannel=gamePannel;
        myinit();
    }//constructor
    
    public final void myinit(){
        setSize(820, 620);//our standard game pannel size
        
        initBoard();
    }//my init

    public void initBoard(){
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameIcon1 = new javax.swing.JButton();
        gameIcon2 = new javax.swing.JButton();
        gameIcon3 = new javax.swing.JButton();
        gameIcon4 = new javax.swing.JButton();
        gameIcon8 = new javax.swing.JButton();
        gameIcon7 = new javax.swing.JButton();
        gameIcon6 = new javax.swing.JButton();
        gameIcon5 = new javax.swing.JButton();
        backgroundLabel = new javax.swing.JLabel();

        setLayout(null);

        gameIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameIcon1.jpg"))); // NOI18N
        gameIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameIcon1ActionPerformed(evt);
            }
        });
        add(gameIcon1);
        gameIcon1.setBounds(44, 90, 150, 150);

        gameIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameIcon2.jpg"))); // NOI18N
        gameIcon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameIcon2ActionPerformed(evt);
            }
        });
        add(gameIcon2);
        gameIcon2.setBounds(238, 90, 150, 150);

        gameIcon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameIcon3.jpg"))); // NOI18N
        gameIcon3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameIcon3ActionPerformed(evt);
            }
        });
        add(gameIcon3);
        gameIcon3.setBounds(432, 90, 150, 150);

        gameIcon4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameIcon4.jpg"))); // NOI18N
        gameIcon4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameIcon4ActionPerformed(evt);
            }
        });
        add(gameIcon4);
        gameIcon4.setBounds(626, 90, 150, 150);

        gameIcon8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameIcon8.png"))); // NOI18N
        gameIcon8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameIcon8ActionPerformed(evt);
            }
        });
        add(gameIcon8);
        gameIcon8.setBounds(626, 284, 150, 150);

        gameIcon7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameIcon7.jpg"))); // NOI18N
        gameIcon7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameIcon7ActionPerformed(evt);
            }
        });
        add(gameIcon7);
        gameIcon7.setBounds(432, 284, 150, 150);

        gameIcon6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameIcon6.gif"))); // NOI18N
        gameIcon6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameIcon6ActionPerformed(evt);
            }
        });
        add(gameIcon6);
        gameIcon6.setBounds(238, 284, 150, 150);

        gameIcon5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameIcon5.png"))); // NOI18N
        gameIcon5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameIcon5ActionPerformed(evt);
            }
        });
        add(gameIcon5);
        gameIcon5.setBounds(44, 284, 150, 150);

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/homePannelBackground.jpg"))); // NOI18N
        add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 820, 620);
    }// </editor-fold>//GEN-END:initComponents

    private void gameIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameIcon1ActionPerformed
        gamePannel.openGame(1);
    }//GEN-LAST:event_gameIcon1ActionPerformed

    private void gameIcon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameIcon2ActionPerformed
        gamePannel.openGame(2);
    }//GEN-LAST:event_gameIcon2ActionPerformed

    private void gameIcon3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameIcon3ActionPerformed
        gamePannel.openGame(3);
    }//GEN-LAST:event_gameIcon3ActionPerformed

    private void gameIcon4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameIcon4ActionPerformed
        gamePannel.openGame(4);
    }//GEN-LAST:event_gameIcon4ActionPerformed

    private void gameIcon8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameIcon8ActionPerformed
        gamePannel.openGame(8);
    }//GEN-LAST:event_gameIcon8ActionPerformed

    private void gameIcon7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameIcon7ActionPerformed
        gamePannel.openGame(7);
    }//GEN-LAST:event_gameIcon7ActionPerformed

    private void gameIcon6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameIcon6ActionPerformed
        gamePannel.openGame(6);
    }//GEN-LAST:event_gameIcon6ActionPerformed

    private void gameIcon5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameIcon5ActionPerformed
        gamePannel.openGame(5);
    }//GEN-LAST:event_gameIcon5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JButton gameIcon1;
    private javax.swing.JButton gameIcon2;
    private javax.swing.JButton gameIcon3;
    private javax.swing.JButton gameIcon4;
    private javax.swing.JButton gameIcon5;
    private javax.swing.JButton gameIcon6;
    private javax.swing.JButton gameIcon7;
    private javax.swing.JButton gameIcon8;
    // End of variables declaration//GEN-END:variables
}
