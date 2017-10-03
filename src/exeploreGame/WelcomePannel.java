
package exeploreGame;

import java.awt.Color;

/**
 *
 * @author srb
 */
public class WelcomePannel extends javax.swing.JPanel {
    private int shade=0;
    final private GamePannel gamePannel;
    
    public WelcomePannel(GamePannel gamePannel) {
        initComponents();
        this.gamePannel=gamePannel;
        myinit();
    }

    public void myinit(){
        final Thread shader = new Thread() {
            public void run() {
                shadeChange();
            }
        };
        shader.start();
    }
    
    public void shadeChange(){
        try {Thread.sleep(1000);} catch (InterruptedException ex) { }
        welcomeLabel.setText("Welcome");
        for(int i=0;i<150;i++){
            shade=i;
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    welcomeLabel.setForeground(new Color(222,222,222,shade));
                }
            });
            try {Thread.sleep(50);} catch (InterruptedException ex) { }
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    welcomeLabel.setForeground(new Color(0,0,0,0));
                }
        });
        try {Thread.sleep(1000);} catch (InterruptedException ex) { }
        welcomeLabel.setText("Exeplore");
        for(int i=0;i<150;i++){
            shade=i;
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    welcomeLabel.setForeground(new Color(255,255,255,shade));
                }
            });
            try {Thread.sleep(50);} catch (InterruptedException ex) { }
        }
        if(gamePannel.timerlock==0)//till now homw button not pressed
            gamePannel.home();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomeLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(34, 34, 34));
        setLayout(null);

        welcomeLabel.setFont(new java.awt.Font("Ubuntu Light", 1, 60)); // NOI18N
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(welcomeLabel);
        welcomeLabel.setBounds(0, 220, 820, 120);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
