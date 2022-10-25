import java.util.Scanner;
import java.io.*;
 
public class Registration {
    
    private static String userId ; 
    public static String userpass ; 
    private static int status ; 
    Scanner input = new Scanner(System.in);
    public void register() throws IOException
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter your Credentials");
        System.out.println("Enter User Name: ");
        String Uname=sc.nextLine();
        System.out.println(Uname);
         
        System.out.println("Enter Password: ");
        String Pass=sc.nextLine();
        System.out.println(Pass);
         
        System.out.println("Confirm Password: ");
        String ConPass=sc.nextLine();
        System.out.println(ConPass);
        Uname=Uname.trim();
        Pass=Pass.trim();
        ConPass=ConPass.trim();
        File f = new File("Registration.txt");
        if(!f.exists()){
			f.createNewFile();
		}
         
 
        String x= Uname;
        if(Pass.equals(ConPass))
        {
             
            //   File f = new File("Registration.txt");
              Scanner content = new Scanner(f);
               
               
              int flag=0;
              while (content.hasNextLine()) {
                String data = content.nextLine();
                String arr[] = data.split(" ", 2);
                if(arr[0].equals(x))
                {
                    System.out.println("Already Registered");
                    flag=1;
                    System.out.println("1. Registration. ");
                    System.out.println("2. Login. ");
                     
                    System.out.println("Enter your Choice");
                    int choice=sc.nextInt();
                    if(choice==1)
                    {
                        this.register();
                    }
                    else if(choice==2)
                    {
                        this.login();
                    }
                    else
                    {
                        System.out.println("Choose Proper Option");
                    }
                    break;
                }
                // content.close();
              }
                if(flag==0)
                {
                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter("Registration.txt", true)); 
                        out.write(Uname+" "+Pass+"\n");
                        out.close();
                        File f1 = new File(Uname);     
                        //Creating a folder using mkdirs() method  
                        boolean bool2 = f1.mkdirs();  
                        if(bool2){  
                            System.out.println("Folder is created successfully");  
                        }else{  
                            System.out.println("Error Found!");  
                        }  
                    }
                    catch (IOException e) {
                        System.out.println("exception occoured" + e);
                    }
                     
                    System.out.println("Successfully Registered");
                    System.out.println("Please login");
                    this.login();
                }
             
             
        }
        else
        {
            System.out.println("Recheck");
            System.out.println("1. Registration. ");
            System.out.println("2. Login. ");
             
            System.out.println("Enter your Choice");
            int choice=sc.nextInt();
            if(choice==1)
            {
                this.register();
            }
            else if(choice==2)
            {
                this.login();
            }
            else
            {
                System.out.println("Choose Proper Option");
            }
        }
        // sc.close();
    }
     
    public void login () throws IOException
    {
         
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter your Credentials");
        System.out.println("Enter User Name: ");
        String Uname=sc.nextLine();
        System.out.println(Uname);
         
        System.out.println("Enter Password: ");
        String Pass=sc.nextLine();
        System.out.println(Pass);
        Uname=Uname.trim();
        Pass=Pass.trim();
        
        String x= Uname+" "+Pass;
         
         
        try {
             
              File f = new File("Registration.txt");
              Scanner content = new Scanner(f);
              int flag=0;
              while (content.hasNextLine()) {
                String data = content.nextLine();
                if(data.equals(x))
                {
                    userId =Uname;
                    userpass = Pass;
                    System.out.println("Login Successful");
                    System.out.println("userid"+userId);
                    System.out.println("Welcome to the Application.");
                    flag=1;
                    status=1;
                    checkStatus();
                    // break;
                    
                }
              }
                if(flag==0)
                {
                    System.out.println("Login Failed");
                    System.out.println("1. Registration. ");
                    System.out.println("2. Login. ");
                     
                    System.out.println("Enter your Choice");
                    int choice=sc.nextInt();
                    if(choice==1)
                    {
                        this.register();
                    }
                    else if(choice==2)
                    {
                        this.login();
                    }
                    else
                    {
                        System.out.println("Choose Proper Option");
                    }
                }
               
             content.close();
            } 
            catch (FileNotFoundException e) {
                System.out.println("Please Register first.");
                System.out.println("Redirected to register please enter your credentials");
                this.register();
            //   System.out.println("Error.");
            //   e.printStackTrace();
            }
         
        // sc.close();
    }

    public boolean checkStatus() {
        if(status==1)return true;
        else return false;
			
	}
    public String getUser() {
        return userId;
    }
    public boolean checkPassWord() throws IOException{
		/**
		 * public method used by the MenuUI to check the password with the one in the file
		 * */
		System.out.print("\nEnter the password: ");
		String pass = input.next();
		checkUntilPasswordIsCorrect(pass);
		//the checkUntilPasswordIsCorrect() method checks the password until it is correct and the else condition need not be checked
		//Thus, true can be returned, without checking any other condition
		return true;
			
	}
    private boolean checkUntilPasswordIsCorrect(String oldPass) throws IOException {
		/**
		 * A private method to loop until the correct password is entered
		 * Used by both checkPassword and changePassword
		 * */
		// While loop will execute till the time the password entered is the old password
		while(oldPass.equals(userpass) == false){
			System.out.print("The entered password is incorrect! \n\nRe-enter the password: ");
			oldPass = input.next();
			// if the password entered is equal to the old password from the file, then the if statement will break the loop.
			if(oldPass.equals(userpass))
				break;
			}
		return true;	
	}
    public void changePassWord() throws IOException{
		/**
		 * public method used by the MenuUI to set the new password
		 * the method writes the new password in the password.txt using file.setPassWord()
		 * */
        
		System.out.print("\nEnter the old Password: ");
		String oldPass = input.next();
		
		if(checkUntilPasswordIsCorrect(oldPass)){
		System.out.print("\nEnter the new Password: ");
		String newPass = input.next();
		this.setPassWord(oldPass,newPass);
		System.out.print("\nThe password has been changed successfully!");
		}
	}
    public void setPassWord(String oldString, String newString) throws IOException {
            /**
             * Method is used by the PassWord class
             * deletes the old password from the password.txt and writes the new password in it
             * */
            File fileToBeModified = new File("Registration.txt");
         
            String oldContent = "";
             
            BufferedReader reader = null;
             
            FileWriter writer = null;
             
            try
            {
                reader = new BufferedReader(new FileReader(fileToBeModified));
                 
                //Reading all the lines of input text file into oldContent
                String x=userId;
                String line = reader.readLine();
                // String arr[] = line.split(" ", 2);
                // if(arr[0].equals(x))
                    while (line != null) 
                {
                    oldContent = oldContent + line + System.lineSeparator();
                     
                    line = reader.readLine();
                }
                 
                //Replacing oldString with newString in the oldContent
                String k= userId+" "+oldString;
                String newContent = oldContent.replaceAll(k,userId+" "+newString);
                 
                //Rewriting the input text file with newContent
                 
                writer = new FileWriter(fileToBeModified);
                 
                writer.write(newContent);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    //Closing the resources
                     
                    reader.close();
                     
                    writer.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
            System.out.print("\nYour password has been changed!");
    }
}
    
