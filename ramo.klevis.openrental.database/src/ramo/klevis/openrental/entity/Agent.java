package ramo.klevis.openrental.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;

/**
 * The persistent class for the agents database table.
 * 
 */
@Entity
@Table(name = "agents")
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String address1;

	@Column(name="nrPikeve")
	private int nrPikeve;

	private String address2;

	private String city;

	private double commrate;

	private String companyname;

	@Transient
	private String nameSuranme;

	private String country;

	private String email;

	private String fax;

	private BigDecimal fixedamount;

	private boolean inclchrg;

	private String phone;

	private String salesperson;

	private String surname;

	private String state;

	private String zipcode;

	public Agent() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getCommrate() {
		return this.commrate;
	}

	public void setCommrate(double commrate) {
		this.commrate = commrate;
	}

	public String getCompanyname() {
		return this.companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public BigDecimal getFixedamount() {
		return this.fixedamount;
	}

	public void setFixedamount(BigDecimal fixedamount) {
		this.fixedamount = fixedamount;
	}

	public boolean getInclchrg() {
		return this.inclchrg;
	}

	public void setInclchrg(boolean inclchrg) {
		this.inclchrg = inclchrg;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalesperson() {
		return this.salesperson;
	}

	public void setSalesperson(String salesperson) {
		this.salesperson = salesperson;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNameSuranme() {
		nameSuranme = salesperson + "  " + surname;
		return nameSuranme;
	}

	public void setNameSuranme(String nameSuranme) {
		this.nameSuranme = nameSuranme;
	}

	public int getNrPikeve() {
		return nrPikeve;
	}

	public void setNrPikeve(int nrPikeve) {
		this.nrPikeve = nrPikeve;
	}

}