package Address;

import java.io.Serializable;


public class AddressBook implements Comparable<AddressBook> {
    private  String name; // Stores name of Contact
    private  String phonenumber; // Stores phone number of contact
    private  int bday;  // Stores birthday in an int
    private  String address;  // Stores address
    private  String email;  // Stores email
    private  int dayb;
    private  int monthb;
    
    public  int getDayb() {
        return dayb;
    }

    public void setDayb(int db) {
        dayb = db;
    }

    public int getMonthb() {
        return monthb;
    }

    public  void setMonthb(int mb) {
        monthb = mb;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setBday(int bday) {
        this.bday = bday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
    // Creates Contact object based on parameters.
    public AddressBook(String n, String p, int day, int month, String a, String e) {
		name = n;
		phonenumber = p;
		dayb = day;
                monthb = month;
                bday = 100*month + day;
                address = a;
		email = e;
                
    }
    // Creates Contact object based on parameters.
    public AddressBook() {

                
    }

    // Changes phone number of contact
    public void changeNumber(String newnum) {
		phonenumber = newnum;
    }


    // Returns the name of a Contact
    public String getName() {
		return name;
    }

   // Returns the Address of a Contact
    public String getAdress() {
                return address;
    }
   // Returns the email of a Contact
    public String getEmail() {
                return email;
    }


    // Returns the phone number of a Contact
    public String getPhoneNumber() {
		return phonenumber;
    }

    // Prints all information about a contact out.
    public void printContact() {
		System.out.print("Name: " + name +  " Phone#: " + phonenumber);
		System.out.println(" Birthday: " + getDayb()+ "/" + getMonthb());
    }

    // Returns month of Contact's birthday
    public int getBdayMonth() {
		return bday/100;
    }
    
    // Returns day of the month of Contact's birthday
    public int getBdayDay() {
		return bday%100;
    }
    
    public  String toString(){
        return ("("+ name + ","+phonenumber+","+dayb+","+monthb+","+address+","+email+ ")");
    }
  public int compareTo(AddressBook o) {
        return toString().compareTo(o.toString());
    }
}
