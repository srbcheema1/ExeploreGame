package exeploreGame;
import java.io.*;

/**
 * Created by akatsuki(https://github.com/Akatsuki06) on 3/10/17.
 */
public class Score {
/*Use Score.getScore(fileName) to get the score
   and Score.saveScore(score,fileName) to save,*/
   public static int getScore(String  fileName) throws IOException {
       int score=0;
       try {
           DataInputStream dis = new DataInputStream(new FileInputStream(fileName));
            score=dis.readInt();
           dis.close();

       }
       catch(FileNotFoundException ex) {
           System.out.println("File not found : " +fileName + "");
       }
       catch(IOException ex) {
           System.out.println("Error reading file :"+ fileName + "");
       }
       return score;
   }
   public static void saveScore(int score, String fileName){
       try {

           DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName));
           dos.writeInt(score);
           dos.close();

       }
       catch(IOException ex) {
           System.out.println("Error writing file "+ fileName + " ");
       }
   }

}
