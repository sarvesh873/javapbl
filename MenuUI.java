/**
 * Monthly expense manager
 * The application keeps a track of the expenses made by the user, making a note of the date, the item on which expenditure is done and the cost
 * User can enter budget for every month
 * Every time user makes a new entry, the budget is checked, and a message is flashed, if expenses made exceeds the set budget
 * User can enter a date and get all the entries made on that particular date
 * User can enter a month and get all the expenses done on the particular month
 * User can get the expenses done from the time, the budget has been reset
 * User can delete a particular month's log
 * User is asked for password before he can make entries
 * User is given an option to change the password, and the password is changed if and only if the old password is entered correctly
 */

/**
 * The program employs six classes
 * The Log class instantiates an object which holds the data and is passed while communicating between different classes
 * The Class CostOperation has methods that do the cost related operations, it communicates only with Class Manager
 * The Class FileOperations has methods which does file related operations, it Communicates only with Class Manager
 * The Class Manager does all the managing part and communicates with the MenuUI Class and all the other classes.
 * The Class Password manages functions that are employed to check the current password by MenuUI, and set the new password by MenuUI, it communicates with MenuUI, and FileOperations to read and write from .txt file
 * The Class MenuUI is the main class, which tells the program which operation to perform and communicates with the user and the Class Manager
 */

/**
 * To perform any operation, the text file "password.txt" should be present at the start, or else, error will be thrown
 */

import java.io.IOException;
import java.util.Scanner;
// Main class accesses the Data class, Cost Manager, and the FileManager

public class MenuUI {
    
	public static void main(String[] args) throws IOException {

		
		
		Scanner input = new Scanner(System.in);
		int ch;
		char userChoice = 'N';
		Registration reg = new Registration();
		// reg.register();
		int choice;
        System.out.println("1. Registration. ");
        System.out.println("2. Login. ");
         
        System.out.println("Enter your Choice");
        choice=input.nextInt();
        // input.nextLine();
         
        if(choice==1)
        {
            // Registration user = new Registration();
            reg.register();
        }
        else if(choice==2)
        {
            // Registration user = new Registration();
            reg.login();
        }
        else
        {
            System.out.println("Choose Proper Option");
        }
		
		
		if(reg.checkStatus() == true	){
			do{
				
				System.out.print("\n Options available are: \n1. Make an Entry \n2. Get the expenses done for a particular date \n3. Get the log for a particular month \n4. Check Expense done for a particular month  \n5. Set budget \n6. Delete a particular month's log \n7. Change Password \n Enter your choice: ");
				ch = input.nextInt();
				Manager manage = new Manager();
				switch(ch)
				{
				case 1:
					//getting the data from the user, and updating 
					manage.makeDailyLog();
					break;
				case 2: 
					//Get the expense details for a particular date
					manage.getDayExpenseDetails();
					break;
				case 3:
					//Get a particular month's log
					manage.getMonthExpenseDetails();
					break;
				case 4:
					// Display the expense done till date to the user
					manage.displayMonthExpense();
					break;
				case 5:
					// Set the budget for the month
					manage.setBudget();
					break;
				case 6:
					// Delete a complete log
					manage.deleteMonthLog();
					break;
				case 7:
					//Change password
					String cmdu[] = {"attrib","-h","Registration.txt"};
            		Runtime.getRuntime().exec(cmdu);
					reg.changePassWord();
				}
				System.out.print("\nAnything Else?(y/n): ");
				userChoice  = input.next().charAt(0);
				}while(userChoice == 'y' || userChoice == 'Y' );
		}
		else
			System.out.print("The entered password is incorrect! ");
		input.close();
	}

}
