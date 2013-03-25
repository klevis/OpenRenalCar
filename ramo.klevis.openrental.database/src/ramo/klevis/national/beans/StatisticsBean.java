package ramo.klevis.national.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ramo.klevis.openrental.entity.Rental;

public class StatisticsBean {

	private List<Rental> listRentals = new ArrayList<Rental>();

	private double sum;
	private String licenseCar;
	private String vin;
	private String desc;

	private double sumTeTjera;
	private int sumKm;
	private double sumInvestime;

	private int tempKm;

	private int nrDiteve;
	
	private Date startime;

	int drate1;
	int drate2;
	int drate3;
	int drate4;
	int drate5;

	public int getDrate1() {
		return drate1;
	}

	public void setDrate1(int drate1) {
		this.drate1 = drate1;
	}

	public int getDrate2() {
		return drate2;
	}

	public void setDrate2(int drate2) {
		this.drate2 = drate2;
	}

	public int getDrate3() {
		return drate3;
	}

	public void setDrate3(int drate3) {
		this.drate3 = drate3;
	}

	public int getDrate4() {
		return drate4;
	}

	public void setDrate4(int drate4) {
		this.drate4 = drate4;
	}

	public int getDrate5() {
		return drate5;
	}

	public void setDrate5(int drate5) {
		this.drate5 = drate5;
	}

	String clazzi;

	String eficencaDite;

	String eficeKm;
	String efficeLek;

	public String getEfficeLek() {
		return efficeLek;
	}

	public void setEfficeLek(String efficeLek) {
		this.efficeLek = efficeLek;
	}

	public String getEficeKm() {
		return eficeKm;
	}

	public void setEficeKm(String eficeKm) {
		this.eficeKm = eficeKm;
	}

	public String getEficencaDite() {
		return eficencaDite;
	}

	public void setEficencaDite(String eficencaDite) {
		this.eficencaDite = eficencaDite;
	}

	public String getClazzi() {
		return clazzi;
	}

	public void setClazzi(String clazzi) {
		this.clazzi = clazzi;
	}

	public List<Rental> getListRentals() {
		return listRentals;
	}

	public void setListRentals(List<Rental> listRentals) {
		this.listRentals = listRentals;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getLicenseCar() {
		return licenseCar;
	}

	public void setLicenseCar(String licenseCar) {
		this.licenseCar = licenseCar;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getSumTeTjera() {
		return sumTeTjera;
	}

	public void setSumTeTjera(double sumTeTjera) {
		this.sumTeTjera = sumTeTjera;
	}

	public int getNrDiteve() {
		return nrDiteve;
	}

	public void setNrDiteve(int nrDiteve) {
		this.nrDiteve = nrDiteve;
	}

	public int getSumKm() {
		return sumKm;
	}

	public void setSumKm(int sumKm) {
		this.sumKm = sumKm;
	}

	public double getSumInvestime() {
		return sumInvestime;
	}

	public void setSumInvestime(double sumInvestime) {
		this.sumInvestime = sumInvestime;
	}

	public int getTempKm() {
		return tempKm;
	}

	public void setTempKm(int tempKm) {
		this.tempKm = tempKm;
	}

	public Date getStartime() {
		return startime;
	}

	public void setStartime(Date startime) {
		this.startime = startime;
	}
}
