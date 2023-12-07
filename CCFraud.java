import java.util.*;
public class CCFraud{
     public static void main(String[] args){
          ArrayList<String> info = takeInput();
             
          boolean nameValid = nameValidate(info);
          
          if (nameValid){
               boolean numValid = ccNumberValidate(info);
               if (numValid){
                    boolean dateValid = dateValidate(info);
                    if (dateValid){
                         boolean secValid = secValidate(info);
                         if (secValid){
                              System.out.println("Valid Credit Card");
                         }
                    }
               }
          }       
    }
    
    
    
    public static boolean secValidate(ArrayList<String> info){
         String secCode = info.get(3);
         
         boolean digitsValid = secCode.matches("[0-9]+");
         if (!digitsValid || secCode.length() != 4){
              System.out.println("Invalid secCode");
              return false;
         }
         
         return true;
    }
    
    
    public static boolean dateValidate(ArrayList<String> info){
         String date = info.get(2);
         
         if (date.length() == 5){
              date = date.substring(0,2) + date.substring(3);
         }
                 
         boolean digitsValid = date.matches("[0-9]+");         
         if (!digitsValid || date.length() != 4){
               System.out.println("Invalid Date");
               return false;
         }
    
         String month = date.substring(0,2);
         int monthNumber = Integer.parseInt(month);
         
         if(monthNumber < 1 || monthNumber > 12){
               System.out.println("Invalid Date");
               return false;
         }
         
         return true;
    }
    
       
     public static boolean ccNumberValidate(ArrayList<String> info){
          String ccNumber = info.get(1);
          
          if(ccNumber.length() == 19){
               ccNumber = formatCC(ccNumber);
          }
          
          if (ccNumber.length() != 16){
               System.out.println("Invalid Number");
               return false;
          }
                   
          boolean digitsValid = ccNumber.matches("[0-9]+");         
          if (!digitsValid){
               System.out.println("Invalid Number");
               return false;
          }
          
          ArrayList<Integer> ccArray = new ArrayList<Integer>();
          
          for(int i = 0; i < ccNumber.length(); i++){
               String ccDigit = ccNumber.substring(i,i+1);
               int ccDigitInt = Integer.parseInt(ccDigit);
               ccArray.add(ccDigitInt);
          }
          
          for(int i = 0; i < ccNumber.length(); i = i + 2){
               int element = 2 * ccArray.get(i);
               if (element > 9){
                    element = 1 + element % 10;
               }
               ccArray.set(i, element);
          }
          
          int ccSum = 0;
          
          for(int i = 0; i < ccNumber.length(); i++){
               ccSum = ccSum + ccArray.get(i);
          }
          
          if (ccSum % 10 == 0){
               return true;
          }
          
          System.out.println("Invalid Number");
          return false;
          
     }
     
         
     public static boolean nameValidate(ArrayList<String> info){
          String name = info.get(0);
          boolean charsValid = name.matches("^[\\p{L} .'-]+$"); //Checks if string is made of only letters or spaces

          int numberOfSpaces = 0;
          int spaceIndex = 999;
          for(int i = 0; i < name.length(); i++){
               String nameChar = name.substring(i,i+1);
               if (nameChar.equals(" ")){
                    numberOfSpaces++;
                    spaceIndex = i;
               }
          }
                    
          if (numberOfSpaces != 1 || spaceIndex == 0 || spaceIndex == name.length()-1 || charsValid != true){
               System.out.println("Invalid Name");
               return false;     
          }
          
          return true;
    }
        
    
     public static ArrayList takeInput(){   
          Scanner console = new Scanner(System.in);
          ArrayList<String> info = new ArrayList<String>();
                    
          System.out.println("Please Enter Full Name");
          String name = console.nextLine();         
          
          System.out.println("Please Enter Credit Card Number");
          String number = console.nextLine();
          info.add(number);
          
          System.out.println("Please Enter Expiration Date");
          String expDate = console.nextLine();
          info.add(expDate);
          
          System.out.println("Please Enter Security Code");
          String secCode = console.nextLine();         
          info.add(secCode);
          
          return info;
    }
    
    
        public static String formatCC(String ccNumber){
             String formattedNumber = "";
             formattedNumber = ccNumber.substring(0,4) + ccNumber.substring(5,9) + ccNumber.substring(10,14) + ccNumber.substring(15,19);
             return formattedNumber;
    } 
}