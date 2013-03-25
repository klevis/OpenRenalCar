package ramo.klevis.openrental.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ramo.klevis.openrental.utils.Money;

@Entity
public class Investime {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String description;
	private double amount;

	@Enumerated(EnumType.STRING)
	private Money money;

	@Column(name = "car", updatable = false, insertable = false)
	private int car;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "car")
	private Car cars;

	public int getId() {
		return id;
	}

	public Investime copy() {
		Investime investime = new Investime();

		investime.setAmount(amount);
		investime.setDescription(description);
		investime.setDate(date);
		investime.setMoney(money);
		return investime;

	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getCar() {
		return car;
	}

	public void setCar(int car) {
		this.car = car;
	}

	public Car getCars() {
		return cars;
	}

	public void setCars(Car cars) {
		this.cars = cars;
	}

}
