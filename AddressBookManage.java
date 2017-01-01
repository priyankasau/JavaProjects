package Address;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;



public class AddressBookManage {

    private static ArrayList friends; // An array of Contacts - each stores info for one friend
    private static int numfriends; // Number of friends currently in AddressBook

    // Print out info on all contacts using method Contact class.
    private  static void printContacts() {
        Iterator freiter = friends.iterator();
		while (freiter.hasNext()) {
                AddressBook ab = (AddressBook) freiter.next();
	    	ab.printContact();
                }
    }

    // Returns the number of friends currently in AddressBook
    private static int numContacts() {
		return numfriends;
    }

  private static String xor(String s,String key){
     StringBuilder sb = new StringBuilder();
    for(int i = 0; i < s.length(); i++)
    sb.append((char)(s.charAt(i) ^ key.charAt(i % key.length())));
    String result = sb.toString();
    return result;
    }
  
// Encrypts Address Book and write it into new File 
  
      private static void encryptAddressBook(String pwd, String addressfile, String addressfileen) throws IOException {
          BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
          BufferedReader br = null;
          String line = "";
          String encryptedline ="";
          ClassLoader cLoader = AddressBookManage.class.getClassLoader();        
          InputStream i = cLoader.getSystemResourceAsStream(addressfile);   
          br = new BufferedReader(new InputStreamReader(i));
          
            File file = new File(addressfileen);
            // if file doesnt exists, then create it
            if (!file.exists()) {
               file.createNewFile();
            }
            PrintWriter writefile = new PrintWriter(addressfileen, "UTF-8");

            while ((line = br.readLine()) != null)
            {
            encryptedline = xor(line,pwd);
            writefile.println(encryptedline);
            System.out.println(encryptedline);
            }
            br.close();
            writefile.close();
          System.out.println(" Contents of File "+addressfile+" encrypted to "+ addressfileen);
    }
      
    // Deletes a contact with name s, if one is in the AddressBook.
    private static void deleteContact(String s) {
		
        Iterator freiter = friends.iterator();
        int contactfound = 0 ;
            while (freiter.hasNext()) {
	    	AddressBook ab = (AddressBook) freiter.next();
                System.out.println("Name entered"+s);
                if( ab.getName().equals(s)){
                    contactfound = 1;
                     freiter.remove();
                     numfriends--;
                }
                }
            if(contactfound ==0){
                System.out.println("Contact Not Found");
                }else{
                System.out.println("Contact Deleted");
                System.out.println("Current Address Book Data");
                printContacts();
            }
    }
    
      // Deletes a contact with name s, if one is in the AddressBook.
    private static void searchContact(String s) {
	Iterator freiter = friends.iterator();
        int contactfound = 0 ;
            while (freiter.hasNext()) {
	    	AddressBook ab = (AddressBook) freiter.next();
                if(ab.getName().startsWith(s)){
                    contactfound = 1;
                System.out.println("Contact Found \n");
                System.out.println("Name : " + ab.getName());
                System.out.println("Phone Number : " + ab.getPhoneNumber());
                System.out.println("Birthday : " + ab.getBdayDay()+ "."+ab.getBdayMonth());
                System.out.println("Address : " + ab.getAdress());
                System.out.println("email : " + ab.getEmail());    
                }
                }
            if(contactfound ==0){
                System.out.println("Contact Not Found");
                }
    }
    private static Date constructDate(int birthday, int month) throws ParseException {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String birthds = Integer.toString(birthday);
        String months = "";
        if(month<10){
             months = "0"+Integer.toString(month);
        }else{
            months = Integer.toString(month);
        }
        String years = Integer.toString(year);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = years+"-"+month+"-"+birthds;
        Date dob =sdf.parse(dateString);
        return dob;
    }
      
  private static void checkBirthdays() throws ParseException {
      Iterator freiter = friends.iterator();
      ArrayList nextdayb = new ArrayList();
      ArrayList next2dayb = new ArrayList();
      ArrayList next3dayb = new ArrayList();
            while (freiter.hasNext()) {
	    	AddressBook ab = (AddressBook) freiter.next();
                int birthday = ab.getDayb();
                int month = ab.getMonthb();
                Date birthdate = constructDate(birthday,month);
                Date currentd = new Date();
               if( birthdate.getMonth() ==  currentd.getMonth()){
                   if(birthdate.getDay() ==  currentd.getDay()){
                       System.out.println("Today is "+ab.getName()+"'s birthday"); 
                   } else if(birthdate.getDay() ==  currentd.getDay()+1){
                       nextdayb.add(ab.getName());
                       
                   } else if(birthdate.getDay() ==  currentd.getDay()+2){
                       next2dayb.add(ab.getName());
                       
                   } else if(birthdate.getDay() ==  currentd.getDay()+3){
                       next3dayb.add(ab.getName());
                   }
                   
               }
                   
            }
            if(nextdayb.size()>0){
                System.out.println("Following contacts have Birthdays in Next 1 day");
                Iterator breiter = nextdayb.iterator();
                while (breiter.hasNext()) {
                    System.out.println(breiter.next().toString());
                }
            }
            else{
                System.out.println("No Contact has Birthdays in Next 1 day");
            }

            if(next2dayb.size()>0){
                System.out.println("Following contacts have Birthdays in Next 2 days");
                Iterator breiter2 = next2dayb.iterator();
                while (breiter2.hasNext()) {
                    System.out.println(breiter2.next().toString());
                }
            }else{
                System.out.println("No Contact has Birthdays in Next 2 days");
            }

            if(next3dayb.size()>0){
                System.out.println("Following contacts have Birthdays in Next 3 days");
               Iterator breiter3 = next3dayb.iterator();
                while (breiter3.hasNext()) {
                    System.out.println(breiter3.next().toString());
                }
            }else{
                System.out.println("No Contact has Birthdays in Next 3 days");
            }

    }
    
    private static void editContact(String s) throws IOException {
         Iterator freiter = friends.iterator();
         BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
         int contactfound = 0 ;
         try{
             while(freiter.hasNext()){
            
            	AddressBook ab = (AddressBook) freiter.next();
                if(ab.getName().endsWith(s)){
                    contactfound =1;
                System.out.println("Current Contact Data \n");
                System.out.println("Name : " + ab.getName());
                System.out.println("Phone Number : " + ab.getPhoneNumber());
                System.out.println("Birthday : " + ab.getDayb()+ "."+ab.getMonthb());
                System.out.println("Address : " + ab.getAdress());
                System.out.println("email : " + ab.getEmail());
                
                System.out.println("Enter New Contact Data \n ");
               
                System.out.println("Enter your friend\'s name:");
                String name = stdin.readLine();
                ab.setName(name);
                System.out.println("Enter their phone number.");
                String number = stdin.readLine();
                ab.setPhonenumber(number);
                System.out.println("Enter the birthday, Day on one line, then Month on the next.");
                int day = Integer.parseInt(stdin.readLine());
                int mon = Integer.parseInt(stdin.readLine());
                ab.setDayb(mon);
                ab.setMonthb(day);
                System.out.println("Enter your friend\'s Address:");
                String address = stdin.readLine();
                ab.setAddress(address);
                System.out.println("Enter your friend\'s Email:");
                String email = stdin.readLine();
                ab.setAddress(email);

                }
        }
         }catch(Exception e){
             System.out.println("Exception occurred , please try again");
         }
        if(contactfound ==1){
          System.out.println("Contact Edited");
          System.out.println("Current Address Book Data");
          printContacts();
          }else{
              System.out.println("Contact not found");
          }
    }
    
  private static void loadAddressBook() throws IOException {
                    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                        String line = "";
                        String txtSplitBy = ",";
                        String filename ="";
                        //Scanner textfile = new Scanner(new File("src/f.txt"));
                        BufferedReader br = null;
                        System.out.println("Enter file name with extension to load from");
                        filename =  stdin.readLine();
                        try{
                        ClassLoader cLoader = AddressBookManage.class.getClassLoader();        
                        InputStream i = cLoader.getSystemResourceAsStream(filename);         
                        br = new BufferedReader(new InputStreamReader(i));
                                 
                       while ((line = br.readLine()) != null)
                       {
                           // line=br.nextLine();
                            String[] splitLine = line.split(txtSplitBy);
                            String Name = splitLine[0].trim();
                            String phone = splitLine[1].trim();
                            int bday = Integer.parseInt(splitLine[2]);
                            int bmonth = Integer.parseInt(splitLine[3]);
                            String address = splitLine[4].trim();
                            String email = splitLine[5].trim();
                            AddressBook ab = new AddressBook();
                            ab.setName(Name);
                            ab.setPhonenumber(phone);
                            ab.setDayb(bday);
                            ab.setMonthb(bmonth);
                            ab.setAddress(address);
                            ab.setEmail(email);
                            friends.add(ab);
                            numfriends++;
                             }
                System.out.println("Address Book Loaded");
                System.out.println("Current Address Book Data");
                checkBirthdays();
                printContacts();
                        }catch(Exception e){
                            System.out.println("Exception occurred, Please try again");
                        }
    }
    
   private static void saveAddressBook() throws IOException {
       BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("Enter file name with extension to load from");
                    String filename =  stdin.readLine();
                    File file = new File(filename);
                    String txtSplitBy = ",";
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
                        FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
                        
                    for(int i=0;i<numfriends;i++){
                        String line = "";
                        AddressBook ab = (AddressBook)friends.get(i);
                        String Name = ab.getName();
                        String phone = ab.getPhoneNumber();
                        int  dayb = ab.getDayb();
                        int monthb = ab.getMonthb();
                        String adress = ab.getAdress();
                        String email = ab.getEmail();
                        line = Name+txtSplitBy+phone+txtSplitBy+dayb+txtSplitBy+monthb+txtSplitBy+adress+txtSplitBy+email;
                        bw.write(line);
                        bw.newLine();
                    }
                    bw.close();
                    fw.close();
    }
   
  private static void addNewContact() throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        try{
             if (numContacts() < 10) {
		    		//Reads in all appropriate information.");
		    		System.out.println("Enter your friend\'s name:");
		    		String name = stdin.readLine();
		    		System.out.println("Enter their phone number.");
		    		String number = stdin.readLine();
		    		System.out.println("Enter the Birth day on one line, then Month on the next.");
		    		int day = Integer.parseInt(stdin.readLine());
                                int mon = Integer.parseInt(stdin.readLine());
                                System.out.println("Enter your friend\'s Address:");
		    		String address = stdin.readLine();     
                                System.out.println("Enter your friend\'s Email:");
		    		String email = stdin.readLine();
		    		AddressBook ab = new AddressBook(name,number,day,mon,address,email);
                                friends.add(ab);
                                numfriends++;
                                System.out.println("Contact Added");
                                System.out.println("Current Address Book Data");
                                printContacts();
                        }
                        	else
		    		System.out.println("Sorry, can not add anyone, your AddressBook is full.");
          
        }        catch(Exception e){
             System.out.println("Exception occurred , please try again");
         }
    }
  
        private static void sortAdressBook() throws IOException {
            
             Collections.sort(friends);
             printContacts();
            
    }
        
    public static void main(String[] args) throws IOException, ClassNotFoundException {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                friends = new ArrayList();
		// Menu driven loop.
		menu();
		int choice = Integer.parseInt(stdin.readLine());
           try{
               while (choice!=9) {
	    
	    // Load contacts from Addresses file .
            
            if (choice == 1) {
                
                loadAddressBook();
            }
            //Save Added contacts into file
            else if(choice == 2){
                saveAddressBook();
            }
            else if (choice == 3) {
                addNewContact();
	    	}
	    	// Remove a Contact 
            else if (choice == 4) {

            System.out.println("What is the name of the contact you want to delete?");
            String name = stdin.readLine();
            deleteContact(name);
            }
            // Edit a Contact 
            else if (choice == 5) {

                            System.out.println("What is the name of the contact you want to edit    ?");
                            String name = stdin.readLine();
                            editContact(name);
            }
          // sort the Adress book  
            else if (choice == 6) {
                            System.out.println("Sorting the Address Book by Name");
                            sortAdressBook();
            }
            //Search for a specific entry
            else if (choice == 7) {
                            System.out.println("What is the name of the contact you want to search for ?");
                            String name = stdin.readLine();
                            searchContact(name);
            }
            //Search for a specific entry
            else if (choice == 8) {
            System.out.println("Please Choose a Password to Encrypt the AddressBook");
            String pwd = stdin.readLine();
            System.out.println("Please Enter the File Name in which AddressBook is Present");
            String addressfile = stdin.readLine();
            System.out.println("Please Enter the File Name to which Encrypted Addresbook is to be written");
            String addressfileen = stdin.readLine();
            encryptAddressBook(pwd,addressfile,addressfileen);
            }
            
            else if (choice !=9) {
                            System.out.println("Sorry, that was an invalid menu choice, try again.");
            }
            menu();
            choice = Integer.parseInt(stdin.readLine());
            }
           }catch(Exception e){
               System.out.println("An Exception Occurred, Please Try Again");
               System.out.println("Exception Details"+e.toString());
           }
		
        }

    private static void menu() {
		System.out.println("1.Load from File");
		System.out.println("2.Save to File");
		System.out.println("3.Add an entry.");
		System.out.println("4.Remove an entry");
		System.out.println("5.Edit an existing entry");
                System.out.println("6.Sort the Address Book");
                System.out.println("7. Search for specific entry");
                System.out.println("8. Encrypt Your AdressBook ");
                System.out.println("9.Quit.");
                
		System.out.println("Enter your menu choice:");
    }







}
