package exeploreGame;

import javax.swing.JOptionPane;

public class ExeploreGame {
    static String userId;

    public static void main(String[] args) {
        while(true){
            String password = JOptionPane.showInputDialog(null, "Enter Password", "Password", JOptionPane.INFORMATION_MESSAGE);
                if(password ==null)continue;
                if(password.compareTo("cheema")==0)break;
        }
        while(true){
                userId =JOptionPane.showInputDialog(null,"Enter User Id","user id",JOptionPane.INFORMATION_MESSAGE);
                if(userId==null)continue;
                if(userId.compareTo("")!=0)break;
        }
        java.awt.EventQueue.invokeLater(() -> {
            MainFrame mainframe = new MainFrame();
            mainframe.setVisible(true);
        });
        
    }
    
}
