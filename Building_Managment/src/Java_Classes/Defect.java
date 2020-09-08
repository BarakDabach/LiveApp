package Java_Classes;




import java.util.Date;

import Enums.Defect_Status;
import Enums.professionalField;

public class Defect  {
	
	
	private int defectID;
	private int buildingID;
	private String field;
	private String status;
	private Date openingDate;
	private String businessNumber;
	private String description;
    private String creator;
	


	public Defect(int buildingID,String field,String status,String businessNumber,String description,String creator) {
		
		
		setbuildingID(buildingID);
		setField(field);
		setStatus(status);
		setBusinessNumber(businessNumber);
		setDescription(description);
		setCreator(creator);
		openingDate = new Date();
		
		
	}
	
	


	//A Constructor that creates a Defect without a contractor.
	public Defect(int buildingID, String field,
				   String status,String description,String creator) 
		{
			
			
			setbuildingID(buildingID);
			setField(field);
			setStatus(status);
			setDescription(description);
			setCreator(creator);
			openingDate = new Date();
			
		
	}
	

	public void setCreator(String creator) {
		this.creator = creator;
		
	}
	
	
	public String getCreator() {
		return this.creator;
	}



	public void setDescription(String description) {
		this.description = description;
		
	}
	
	

	public String getDescription() {
		return this.description;
		
	}
	
	public String getBusinessNumber() {
		return businessNumber;
	}


	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	
	
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	
	public int getDefectID() {
		return defectID;
	}
	public void setDefectID(int defectID) {
		this.defectID = defectID;
	}


	public int getbuildingID() {
		return buildingID;
	}
	public void setbuildingID(int buildingID) {
		this.buildingID = buildingID;
	}
	public String getField() {
		return field;
	}
	public void setField(String field2) {
		this.field = field2;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status2) {
		this.status = status2;
	}

	

	
	
	
	
	
	
	
	
	
}
