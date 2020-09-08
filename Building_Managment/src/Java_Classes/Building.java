package Java_Classes;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;

import Enums.Cities;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidPhoneException;
import Exceptions.InvalidStringException;

public class Building implements Serializable {
	
	
	private int buildingID;
	private String city;
	private String streetName;
	private int buildingNumber;
	private Resident representative;
	private int totalApartments;
	private final int max_num_of_Appartments;
	
	//shalom

	//creates a building with a representative
	public Building(String city, String streetName, int buildingNumber, Resident representative,
					int max_num_of_Appartments) throws InvalidStringException {
		
		setCity(city);
		setStreetName(streetName);
		setBuildingNumber(buildingNumber);
		this.representative = null;
		this.max_num_of_Appartments = max_num_of_Appartments;

	}
	
	
	//another constructors that creates a representative in the set method.
	public Building(String city, String streetName, int buildingNumber,
			String firstName, String lastName, String email, String phoneNumber,
			int max_num_of_Appartments)
			throws InvalidStringException, InvalidPhoneException, InvalidEmailException {
		
			setRepresentative(new Resident(firstName, lastName, email, phoneNumber,buildingNumber));
			setCity(city);
			setStreetName(streetName);
			setBuildingNumber(buildingNumber);
			
			this.representative = null;
			this.max_num_of_Appartments = max_num_of_Appartments;
	}
	
	
	
	//creates a building without  a representative
	public Building(String city, String streetName, int buildingNumber,int max_num_of_Appartments)
			throws InvalidStringException
		{
			setCity(city);
			setStreetName(streetName);
			setBuildingNumber(buildingNumber);
			this.representative = null;
			this.max_num_of_Appartments = max_num_of_Appartments;
	}
	
	
	
	
	
	
	public int getMax_num_of_Appartments() {
		return this.max_num_of_Appartments;
	}



	public int getBuildingID() {
		return this.buildingID;
	}

	public void setBuildingID(int buildingID) {
		this.buildingID = buildingID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city2) {
		this.city = city2;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) throws InvalidStringException {
		if(checkOnlyString(streetName))
			this.streetName =streetName;
	}

	public int getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(int buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public Resident getRepresentative() {
		return representative;
	}

	public void setRepresentative(Resident res) {
		this.representative = res;
	}
	

	
	public int getTotalApartments() {
		return totalApartments;
	}

	public void setTotalApartments(int totalApartments) {
		this.totalApartments = totalApartments;
	}
	
	

	
	
	public String toString() {
		String str = "[ Building ID = "+this.getBuildingID()+", City = "+this.getCity()+
				", Street = "+this.getStreetName()+", Number = "+this.getBuildingNumber()+
				", Max Number of Appartments = "+this.getMax_num_of_Appartments();
		
		if (this.getRepresentative() == null) {
			return str+" ]";
		}
		
		else {
			return str + ", Represantitve Name: "+this.getRepresentative().getFirstName() + " "+
			this.getRepresentative().getLastName()+" ]";
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
	
	

}
