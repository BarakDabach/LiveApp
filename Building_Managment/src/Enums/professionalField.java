package Enums;

public enum professionalField {
	Electricity("Electricity"),
	Plumbing("Plumbing"),
	Plaster("Plaster"),
	Flooring("Flooring");
	
	String fieldValue;
	
	professionalField(String fieldValue){
		this.fieldValue = fieldValue; 
	}
}
