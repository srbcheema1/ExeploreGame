package exeploreGame;

import javax.swing.JOptionPane;

public class ExeploreGame {
    static String userId;
    static String password;
    
    public static void main(String[] args) {
        while(true){
                password =JOptionPane.showInputDialog(null,"Enter Password","Passwrord",1);
                if(password==null)continue;
                if(password.compareTo("cheema")==0)break;
        }
        while(true){
                userId =JOptionPane.showInputDialog(null,"Enter User Id","user id",1);
                if(userId==null)continue;
                if(userId.compareTo("")!=0)break;
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    MainFrame mainframe = new MainFrame();
                    mainframe.setVisible(true); 
                }
            });
        
    }
    
}
