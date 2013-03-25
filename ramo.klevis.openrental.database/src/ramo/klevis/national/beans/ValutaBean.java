package ramo.klevis.national.beans;

import java.util.HashMap;

import ramo.klevis.openrental.utils.Money;

public class ValutaBean {

	private Money baseMoney;
	private Money firstMoney;
	private Money secondMoney;
	

	private HashMap<Money, Double> first = new HashMap<Money, Double>();
	private HashMap<Money, Double> second = new HashMap<Money, Double>();

	public Money getBaseMoney() {
		return baseMoney;
	}

	public void setBaseMoney(Money baseMoney) {
		this.baseMoney = baseMoney;
	}

	public HashMap<Money, Double> getFirst() {
		return first;
	}

	public void setFirst(HashMap<Money, Double> first) {
		this.first = first;
	}

	public HashMap<Money, Double> getSecond() {
		return second;
	}

	public void setSecond(HashMap<Money, Double> second) {
		this.second = second;
	}

	public Money getFirstMoney() {
		return firstMoney;
	}

	public void setFirstMoney(Money firstMoney) {
		this.firstMoney = firstMoney;
	}

	public Money getSecondMoney() {
		return secondMoney;
	}

	public void setSecondMoney(Money secondMoney) {
		this.secondMoney = secondMoney;
	}
}
