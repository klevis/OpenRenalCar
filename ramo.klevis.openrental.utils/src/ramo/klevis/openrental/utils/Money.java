package ramo.klevis.openrental.utils;

public enum Money {

	EURO("€"), DOLLAR("$"), LEK("Lek");

	private String type;

	Money(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
