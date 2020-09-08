package Enums;

public enum Defect_Status {
	opend("Opend"),
	inProgress("In Progress"),
	closed("Closed");
	
	
	String statusValue;
	
	Defect_Status(String statusValue){
		this.statusValue = statusValue;
	}
}
