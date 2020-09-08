package Java_Classes;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidPhoneException;
import Exceptions.InvalidStringException;

public class Resident extends Person implements Comparable<Resident> {
	private int buildingID;
	
	


	//A Constructor to compare between the array lists.
	public Resident() throws InvalidPhoneException, InvalidStringException, InvalidEmailException {
		super("test","test","bbb@gmail.com","0542222222");
	}

	public Resident(String firstName, String lastName, String email, String phoneNumber,int buildingID)
			throws InvalidPhoneException, InvalidStringException, InvalidEmailException {
		super(firstName, lastName, email, phoneNumber);
		setBuildingID(buildingID);
	}
	
	
	public int getBuildingID() {
		return buildingID;
	}



	public void setBuildingID(int buildingID) {
		this.buildingID = buildingID;
	}
	
	public String toString() {
		return "[ Resident Name = "+this.getFirstName()+" "+this.getLastName()+", Email Address = "+
						 this.getEmail()+", Phone Number = "+this.getPhoneNumber()+", Building ID Number =  "
						 +this.getBuildingID()+" ]";
	}


	@Override
	public int compareTo(Resident o) {
		return this.getLastName().compareTo(o.getLastName());
	}

}
