
import java.util.Scanner;

public class Assignment1 {

    public static void main(String[] args) {
     
        double bodymassindex=0; 
        try{
        String username;
        System.out.println("Enter your Name: ");
        Scanner readname = new Scanner(System.in);
        username = readname.nextLine();
        
        //Invoking the bmicalculation class 
        bmicalculation bmic = new bmicalculation();
        bodymassindex = bmic.calculatebmi();   
        
        //Display ouput after BodyMassIndex Calculation 
        System.out.print("\n \t Name :  " + username);
        System.out.printf("\n \t BMI : %f", bodymassindex);
        if (bodymassindex < 18.5) {
            System.out.print("\n \t Under Weight");
        } else if (bodymassindex >= 18.5 && bodymassindex < 24.9) {
            System.out.print("\n \t Normal Weight");
        } else if (bodymassindex >= 25.0 && bodymassindex <= 29.9) {
            System.out.print("\n \t  Over Weight");
        } else if (bodymassindex >= 30.0) {
            System.out.print("\n \t Obese");
        }
        } catch(Exception e){
            System.out.println("An Exception Ocurred, Please try Again");
            System.exit(0);
        }
    }
}

class bmicalculation {

     double calculatebmi() {
        
        //to be used for conversion     
        final double KILOGRAMS_PER_POUND = 0.453;
        final double MTRS_PER_FOOT = 3.28084;
        
        //Variables for Height and Weight    
        double bodyweight = 0.0;
        double bodyheight = 0.0;
        double bodymassindex = 0.0;
        
        String weightType = null;
        String heightType = null;

        //Read Input from User
        System.out.println("Press 1 or 2 to choose Weight Type: ");
        System.out.println("1.KILOGRAMS");
        System.out.println("2.POUNDS");
        Scanner readweightType = new Scanner(System.in);
        weightType = readweightType.next().trim();
        if (weightType.equalsIgnoreCase("1")) {
            System.out.println("Enter Weight in Kilograms:");
        } else if (weightType.equalsIgnoreCase("2")) {
            System.out.println("Enter Weight in Pounds:");
        } else {
            System.out.println("Weight Type Entry is Incorrect");
            System.exit(0);
        }
        Scanner getWeight = new Scanner(System.in);
        bodyweight = getWeight.nextDouble();

        if (weightType.equalsIgnoreCase("2")) {
            bodyweight = bodyweight * KILOGRAMS_PER_POUND;
            //convert pounds to KG
        }
        System.out.println("Press 1 or 2 to choose Height Type: ");
        System.out.println("1.METERS");
        System.out.println("2.FEET");

        Scanner readHeightType = new Scanner(System.in);
        heightType = readHeightType.next().trim();

        if (heightType.equalsIgnoreCase("1")) {
            System.out.println("Enter Height in Meters:");
        } else if (heightType.equalsIgnoreCase("2")) {
            System.out.println("Enter Height in Feet:");
        } else {
            System.out.println("Height Type Entry is Incorrect");
            System.exit(0);
        }

        Scanner readHeight = new Scanner(System.in);
        bodyheight = readHeight.nextDouble();

        if (heightType.equalsIgnoreCase("2")) {
            bodyheight = bodyheight/ MTRS_PER_FOOT;
        }
        //Calculating BodyMassIndex based on Input Height and Weight values     
        bodymassindex = Math.abs((bodyweight) / (bodyheight * bodyheight));
        
        //return bodymassindex calculated
        return bodymassindex;

    }
}
