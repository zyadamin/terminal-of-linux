/*
zyad mohamed amin      20180111
manar atef sayed       20180290
nourhan farag ibrahim  20180320

*/

package assignment.os1;


import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author zezo
 */
public class AssignmentOs1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
     Scanner input=new Scanner(System.in);
     
     while(true){
          System.out.println("  "); 
          System.out.println(Terminal.location);  //location is ststic variable

        String param=input.nextLine();
         parser cmd=new parser();
         cmd.parse(param);
        
        
     }

    }
    }

