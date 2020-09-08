package Java_Classes;
import java.io.Serializable;

import Enums.Cities;
import Enums.professionalField;
import Exceptions.InvalidBuisnessNumber;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidPhoneException;
import Exceptions.InvalidStringException;

public class Contractor extends Person {
	
	private String businessNumber;
	private String field;
	private String city;
	
	
	
	//Empty Constructor to compare between the arraylists
	public Contractor() throws InvalidPhoneException, InvalidStringException, InvalidEmailException {
		super("test","test","bbb@gmail.com","0542222222");
	}
	
	
	public Contractor(String firstName, String lastName, String email, String phoneNumber,String city,
						String field,String businessNumber)
			throws InvalidPhoneException, InvalidStringException, InvalidEmailException, InvalidBuisnessNumber {
		
		
		super(firstName, lastName, email, phoneNumber);
		setProfessionalField(field);
		setBusinessNumber(businessNumber);
		setCity(city);
	}
	
	
	
	
	public String getField() {
		return field;
	}




	public void setField(String field) {
		this.field = field;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	private void setBusinessNumber(String businessNumber) throws InvalidBuisnessNumber {
		if(checkOnlyDigits(businessNumber))
			this.businessNumber = businessNumber;
		
		else {
			throw new InvalidBuisnessNumber("Invalid Buisnness Number, please enter an new one");
		}
	}


	public String getBusinessNumber() {
		return businessNumber;
	}




	private void setProfessionalField(String field2) {
		this.field = field2;
		
	}



}
