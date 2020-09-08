package Java_Classes;
import java.io.Serializable;

import Exceptions.InvalidBuisnessNumber;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidPhoneException;
import Exceptions.InvalidStringException;

public abstract class Person implements Serializable {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	
	
	public Person(String firstName, String lastName, String email, String phoneNumber) 
			throws InvalidPhoneException, InvalidStringException, InvalidEmailException {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPhoneNumber(phoneNumber);
		
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) throws InvalidStringException {
		if(checkOnlyString(firstName))
			this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) throws InvalidStringException {
		if(checkOnlyString(lastName))
			this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws InvalidEmailException {
		checkEmail(email);
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) throws InvalidPhoneException {
		checkPhoneNumber(phoneNumber);
	}
	
	
	
	//function to check phone number.
	public void checkPhoneNumber(String str) throws InvalidPhoneException{
		if(((str.charAt(0) == '0') && (str.length() == 10 || str.length() == 9) && str.matches("[0-9]+"))) {
			this.phoneNumber = str;
		}
		else {
			throw new InvalidPhoneException("You need to enter a phone number");
		}
	}
	
	//function to check Email address
	public void checkEmail(String str) throws InvalidEmailException{
		if(str.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
			this.email = str;
		}
		else {
			throw new InvalidEmailException("Email address is Illegal");
		}
	}
	

	//function to check only strings.
	public boolean checkOnlyString(String str) throws InvalidStringException{
		if(str.matches("[a-zA-z]+")) {
			return true;
		}
		else {
			throw new InvalidStringException("Enter only letters, for two words put: _ between the words");
		}
	}

	
	
	
	//function that checks only digits 
	public boolean checkOnlyDigits(String str) throws InvalidBuisnessNumber {
		if(str.matches("[0-9]+"))
			return true;
		
		else
			throw new InvalidBuisnessNumber("Please Enter Only Digits ");
	}
		
}
