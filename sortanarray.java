
import java.util.Scanner;
public class sortanarray
{
  public static void main(String[] args) {
 int counter, num, array[];
  Scanner input = new Scanner(System.in);
  System.out.println("Enter number of elements :\n");
  num = input.nextInt(); 
  array = new int[num]; 
  System.out.println("Enter " + num + " integers");
  for (counter = 0; counter < num; counter++){
	array[counter] = input.nextInt();
  }
                System.out.println("Array Before Bubble Sort");
                for(int i=0; i < array.length; i++){
                       System.out.print(array[i]+"\t ");
                }
                bubbleSort(array);
                System.out.println("");
                System.out.println("Array After Bubble Sort");
                for(int i=0; i < array.length; i++){
                        System.out.print(array[i] + "\t ");
                }
 
        }
 
        private static void bubbleSort(int[] array) {
               int n = array.length;
                int temp = 0;
         for(int i=0; i < n; i++){
                        for(int j=1; j < (n-i); j++){
                          if(array[j-1] < array[j]){
                                        temp = array[j-1];
                                        array[j-1] = array[j];
                                        array[j] = temp;
                                }
                               
                        }
                }
       
        }
}
 