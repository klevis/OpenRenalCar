package ramo.klevis.openrental.utils;

public enum StatisticsType {

	
SIPAS_MUAJIT("Sipas Muajve"),
SIPAS_MAKINAVE("Sipas Makinave"),
FATURA_DITORE("Faturime ditore"),
SIPAS_MAKINAVE_KLIENT("Sipas Makinave dhe Klienteve"),
SIPAS_EFICENCES("Sipas efices se makinave"),
SIPAS_GJENDJES_MAKINAVE("Gjendja e dokumentacionit te makinave");
;
	
private String type;


private StatisticsType(String type) {
	// TODO Auto-generated constructor stub

this.type=type;}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}
}
