package ramo.klevis.openrental.utils;

public enum Operator {

	LIKE("like"),
	LIKE_LEFT("like"),
	LIKE_RIGHT("like"),
	LIKE_SIMPLE("like"),
	EQ("="),
	GREATER_THAN(">"),
	GREATER_EQ_THAN(">="),
	LOWER_THAN("<"),
	LOWER_EQ_THAN("<=");
	
	
	
	String value;
	
	private Operator(String value){
		this.value = value;
	}
	
	
	public String getValue() {
		return value;
	}
}
