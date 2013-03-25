package ramo.klevis.openrental.entity;

public enum RoleEnum {

	CHECK_IN("CHECK IN"),
	CHECK_OUT("CHECK OUT"),
	PERDORUESIT("PERDORUESIT"),
	MAKINA("MAKINA"),
	STATISTIKA("STATISTIKA"),
	KLIENTET("KLIENTET")
	;
	

	private String type;

	private RoleEnum(String type) {
		// TODO Auto-generated constructor stub
		this.setType(type);
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}