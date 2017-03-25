package exeploreGame;

import javax.swing.JOptionPane;
import sun.security.x509.X500Name;

public class ExeploreGame {
    static String userId;
    
    public static void main(String[] args) {
        while(true){
                userId =JOptionPane.showInputDialog(null,"Enter User Id","user id",1);
                if(userId==null)continue;
                if(userId.compareTo("")!=0)break;
        }
        MainFrame mainframe = new MainFrame();
        mainframe.setVisible(true); 
    }
    
}
