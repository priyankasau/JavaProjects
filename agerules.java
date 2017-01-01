
import java.io.*;
import java.util.*;
import java.util.Scanner;
public class agerules 
{
  public static void main(String[] arguments)
  {
    System.out.println("Hello! What is your Name: ");
    Scanner namescan = new Scanner(System.in);
    String name = namescan.nextLine();
    System.out.println("ok, " +name+", Enter your age:");
    Scanner agescan = new Scanner(System.in);
    Integer age = agescan.nextInt();
    System.out.println("Thanks for entering your age, your age is "+age+ " and");
    if(age<16)
    {
      System.out.println(" You are not allowed to drive at the moment, " +name );
    }
    if(age<18)
    {
      System.out.println(" You are not allowed to vote at the moment, " +name );
    }
    if(age<=25)
    {
      System.out.println(" You are not allowed to rent a car at the moment, " +name );
    }
    if(age>25)
    {
      System.out.println(" You can do anything that is legal, " +name );
    }
  }
}
