package ramo.klevis.national.beans;

import java.util.Date;

import ramo.klevis.openrental.entity.Location;

public class BeanAvaibleCar {

	private Date fromDate;
	private Date toDate;

	private Location location;

	private ramo.klevis.openrental.entity.Class clazz;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ramo.klevis.openrental.entity.Class getClazz() {
		return clazz;
	}

	public void setClazz(ramo.klevis.openrental.entity.Class clazz) {
		this.clazz = clazz;
	}

}
