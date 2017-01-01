

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class EncryptDecrypt	 
{
 public static void encrypt(String strName,String key,String outputFileName) throws IOException
 {
  String encryptedData="";
  int j=0;
  char charArr[] =strName.toCharArray();
  for(int i=0;i<charArr.length;i++)
  {
   if(j>=key.length())  
   j=0;
   encryptedData += (char)((int)charArr[i]^ (int)key.charAt(j));
   j++;
    }
		
   File outputfile = new File(outputFileName);
   FileOutputStream fos = new FileOutputStream(outputfile);
   BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(fos)); 
   
   writer1.write(encryptedData);
   writer1.flush();
   System.out.println(encryptedData);
   writer1.close();
   }
  public static void decrypt(String inputFileName,String key) throws IOException
  {
   String decryptedData="";     
   File file = new File(inputFileName);
   Scanner fileInputStream = new Scanner(file);
   int j=0;
   while(fileInputStream.hasNextLine())
    {
     String data = fileInputStream.nextLine();       
     char charArr[] =data.toCharArray();
     for(int i=0;i<charArr.length;i++)
     {
      if(j>=key.length())              
      j=0;
      decryptedData += (char)((int)charArr[i]^(int)key.charAt(j));      
      j++;
      }  
     if(fileInputStream.hasNextLine())     
     {
      if(j>key.length()-1)              
      j=0;
      decryptedData +=(char)((int)'\n'^(int)key.charAt(j));      
      j++;
      }
     }
     System.out.println("Decrypted Data is:"+decryptedData);  
     fileInputStream.close();
    }
  public static void main(String[] args) throws  IOException
  {
   String strName,strPassword,fileNameToSave="",fileNameToDec="";     
   int option;
   Scanner sc = new Scanner(System.in);
   System.out.println("Enter the small password :");
   strPassword = sc.nextLine();

   do
   {       
    System.out.println("1.Encrypt your name and save in file 2. Enter a file to load  3.Exit");        
    option = sc.nextInt();
	 if( option == 1) {
    System.out.println("Enter your name :");
	
    sc = new Scanner(System.in);
    strName = sc.nextLine();
    System.out.println("Enter the file name to save :");
	
    sc = new Scanner(System.in);
    fileNameToSave = sc.nextLine();
	EncryptDecrypt.encrypt(strName, strPassword, fileNameToSave);                     
   } else if ( option == 2 ) {
	System.out.println("Enter the file name to decrypt :");
	sc = new Scanner(System.in);
    fileNameToDec = sc.nextLine();
	EncryptDecrypt.decrypt(fileNameToDec,strPassword);
   } 
     
    }while(option!=3);
   }

 }
