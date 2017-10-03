package exeploreGame;

import javax.swing.JOptionPane;

public class ExeploreGame {
    final static String userId;

    public static void main(String[] args) {
        while(true){
            final String password = JOptionPane.showInputDialog(null, "Enter Password", "Password", JOptionPane.INFORMATION_MESSAGE);
                if(password ==null)continue;
                if(password.compareTo("cheema")==0)break;
        }
        while(true){
                final userId =JOptionPane.showInputDialog(null,"Enter User Id","user id",JOptionPane.INFORMATION_MESSAGE);
                if(userId==null)continue;
                if(userId.compareTo("")!=0)break;
        }
        java.awt.EventQueue.invokeLater(() -> {
            final MainFrame mainframe = new MainFrame();
            mainframe.setVisible(true);
        });
        
    }
    
}
