
import java.util.Scanner;
public class Largestnumber
{
public static void main(String args[])
{
  int counter, num, array[],i;
  Scanner input = new Scanner(System.in);
  System.out.println("Enter number of elements :\n");
  num = input.nextInt(); 
  array = new int[num]; 
  System.out.println("Enter " + num + " integers");
  for (counter = 0; counter < num; counter++){
	array[counter] = input.nextInt();
  }
		int largetst = array[0];
			for(i=0; i< array.length; i++)
			{
					if(array[i] > largetst){
							largetst = array[i];
					}
			}
System.out.println("Largest Number is : " + largetst);
for(i=0; i< array.length; i++)
			{
					if( largetst==array[i])
						{
						System.out.println("position of the number in array is:" +i);
						}
			}
for(i=0; i< array.length; i++)
{
	System.out.print(array[i]+"\t ");
}
System.out.println();
for(i=0; i< array.length; i++)
{
			
if(array[i] ==largetst)
System.out.print( "^");
 System.out.print("\t ");
				   
}
System.out.println();
}
 }		   
