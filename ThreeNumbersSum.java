
import java.io.File;
import java.util.Scanner;

public class ThreeNumbersSum
{

 public static void main(String[] args) throws Exception 
 {   
	 System.out.println("Reading Numbers from the file 3nums.txt is done..!!"); 
     File fileName = new File("3nums.txt");
     Scanner fileInputStream = new Scanner(fileName);
     int sum=0;
     String msg="";
     while (fileInputStream.hasNextLine())
   {
     int data =Integer.parseInt(fileInputStream.nextLine());        
     sum+=data;            
     if(fileInputStream.hasNextLine())
     msg+=data+" + ";
     else
     msg+=data;
     }
     System.out.println(msg+" = " + sum);
   }
 
}