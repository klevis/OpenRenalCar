package ramo.klevis.openrental.utils;

public class Expression {

	private String column;
	private Object value;
	private Operator operator;
	
	
	public Expression(String column, Object value, Operator operator) {
		super();
		this.column = column;
		this.value = value;
		this.operator = operator;
		
		if (operator.equals(Operator.LIKE_LEFT)) this.value =this.value + "%";
		else if (operator.equals(Operator.LIKE_RIGHT)) this.value ="%" + this.value;
		else if (operator.equals(Operator.LIKE)) this.value ="%" + this.value + "%";
	}


	public String getColumn() {
		return column;
	}


	public void setColumn(String column) {
		this.column = column;
	}


	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}


	public Operator getOperator() {
		return operator;
	}


	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	
	
}
