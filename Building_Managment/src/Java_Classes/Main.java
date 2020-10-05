package Java_Classes;
import Exceptions.InvalidBuisnessNumber;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidPhoneException;
import Exceptions.InvalidStringException;
import GUI.Admin_Window;
import GUI.Login_Page;
import GUI.Resident_Window;


public class Main {
	
	//class variable
	static String chooseWin;
	static String logedInName = "Welcome Back ";
	public static boolean isLoggedIn = true;
	
	public static void main(String[] args) throws InvalidPhoneException, InvalidStringException, InvalidEmailException, InvalidBuisnessNumber {
		
		
		while(true) {
			//Starting the login page
			Login_Page login = new Login_Page();
			login.frmLoginPage.setVisible(true);
			while (login.frmLoginPage.isDisplayable()) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			chooseWin = login.windowType;
			logedInName += login.userName;
			//opening the User Window if the class variable in the Login window object is user.
			if(chooseWin.equalsIgnoreCase("user")) {
				Resident_Window r = new Resident_Window();
				r.frmUserWindow.setVisible(true);
				r.lblWelcomeBack.setText(logedInName);
				while(isLoggedIn == true) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
			
			//opening the Admin Window if the class variable in the Login window object is admin.
		
			else if(chooseWin.equalsIgnoreCase("admin")) {
				Admin_Window a = new Admin_Window();
				a.frmAdminWindow.setVisible(true);
				a.lblWelcomeBack.setText("Welcome Admin");
				
				while(isLoggedIn == true) {
					 
					}
			}
			
	
		}
				
		
		


		

	}

}
