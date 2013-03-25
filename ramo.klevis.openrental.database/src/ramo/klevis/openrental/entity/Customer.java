package ramo.klevis.openrental.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ramo.klevis.openrental.utils.CustomerStatus;

/**
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name = "customers")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cardexp;
	private String debt = "";
	private String debtdescription = "";

	@Column(name = "nrPikeve")
	private int nrPikeve;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private CustomerStatus status;
	private String cardnr;

	private String cccvv;

	private String ccname;

	private String cctype;

	private String companyname;

	private int custcat;

	private String custname;

	private String dr2fname;

	private String dr2lastname;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dr2licexpdate;

	private String dr2licnr;

	private String draddress;

	@Temporal(TemporalType.TIMESTAMP)
	private Date drbdate;

	private String drcity;

	private String drcountry;

	private String dremail;

	private String drfirstname;

	private String drhphone;

	private String drladdress;

	private String drlastname;

	@Temporal(TemporalType.TIMESTAMP)
	private Date drlexpdate;

	private String drliccat;

	private String drlicnr;

	private String drlphone;

	private String drmphone;

	@Id
	private String drpasportid;

	private String drplace;

	private String drstate;

	private String drzip;

	private int gurantee;

	private String inscomp;

	private String insfax;

	private String insphone;

	private String inspol;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inspolexp;

	private int onlineid;

	private String pafirstname = "";

	private String palastname = "";

	@Lob()
	private byte[] picture;

	@Lob()
	private byte[] picture2;

	@Lob()
	private byte[] picture3;

	@Lob()
	private String remarks;

	private int vipid;

	private String workphone;

	public Customer() {
	}

	public String getCardexp() {
		return this.cardexp;
	}

	public void setCardexp(String cardexp) {
		this.cardexp = cardexp;
	}

	public String getCardnr() {
		return this.cardnr;
	}

	public void setCardnr(String cardnr) {
		this.cardnr = cardnr;
	}

	public String getCccvv() {
		return this.cccvv;
	}

	public void setCccvv(String cccvv) {
		this.cccvv = cccvv;
	}

	public String getCcname() {
		return this.ccname;
	}

	public void setCcname(String ccname) {
		this.ccname = ccname;
	}

	public String getCctype() {
		return this.cctype;
	}

	public void setCctype(String cctype) {
		this.cctype = cctype;
	}

	public String getCompanyname() {
		return this.companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public int getCustcat() {
		return this.custcat;
	}

	public void setCustcat(int custcat) {
		this.custcat = custcat;
	}

	public String getCustname() {
		return this.custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getDr2fname() {
		return this.dr2fname;
	}

	public void setDr2fname(String dr2fname) {
		this.dr2fname = dr2fname;
	}

	public String getDr2lastname() {
		return this.dr2lastname;
	}

	public void setDr2lastname(String dr2lastname) {
		this.dr2lastname = dr2lastname;
	}

	public Date getDr2licexpdate() {
		return this.dr2licexpdate;
	}

	public void setDr2licexpdate(Date dr2licexpdate) {
		this.dr2licexpdate = dr2licexpdate;
	}

	public String getDr2licnr() {
		return this.dr2licnr;
	}

	public void setDr2licnr(String dr2licnr) {
		this.dr2licnr = dr2licnr;
	}

	public String getDraddress() {
		return this.draddress;
	}

	public void setDraddress(String draddress) {
		this.draddress = draddress;
	}

	public Date getDrbdate() {
		return this.drbdate;
	}

	public void setDrbdate(Date drbdate) {
		this.drbdate = drbdate;
	}

	public String getDrcity() {
		return this.drcity;
	}

	public void setDrcity(String drcity) {
		this.drcity = drcity;
	}

	public String getDrcountry() {
		return this.drcountry;
	}

	public void setDrcountry(String drcountry) {
		this.drcountry = drcountry;
	}

	public String getDremail() {
		return this.dremail;
	}

	public void setDremail(String dremail) {
		this.dremail = dremail;
	}

	public String getDrfirstname() {
		return this.drfirstname;
	}

	public void setDrfirstname(String drfirstname) {
		this.drfirstname = drfirstname;
	}

	public String getDrhphone() {
		return this.drhphone;
	}

	public void setDrhphone(String drhphone) {
		this.drhphone = drhphone;
	}

	public String getDrladdress() {
		return this.drladdress;
	}

	public void setDrladdress(String drladdress) {
		this.drladdress = drladdress;
	}

	public String getDrlastname() {
		return this.drlastname;
	}

	public void setDrlastname(String drlastname) {
		this.drlastname = drlastname;
	}

	public Date getDrlexpdate() {
		return this.drlexpdate;
	}

	public void setDrlexpdate(Date drlexpdate) {
		this.drlexpdate = drlexpdate;
	}

	public String getDrliccat() {
		return this.drliccat;
	}

	public void setDrliccat(String drliccat) {
		this.drliccat = drliccat;
	}

	public String getDrlicnr() {
		return this.drlicnr;
	}

	public void setDrlicnr(String drlicnr) {
		this.drlicnr = drlicnr;
	}

	public String getDrlphone() {
		return this.drlphone;
	}

	public void setDrlphone(String drlphone) {
		this.drlphone = drlphone;
	}

	public String getDrmphone() {
		return this.drmphone;
	}

	public void setDrmphone(String drmphone) {
		this.drmphone = drmphone;
	}

	public String getDrpasportid() {
		return this.drpasportid;
	}

	public void setDrpasportid(String drpasportid) {
		this.drpasportid = drpasportid;
	}

	public String getDrplace() {
		return this.drplace;
	}

	public void setDrplace(String drplace) {
		this.drplace = drplace;
	}

	public String getDrstate() {
		return this.drstate;
	}

	public void setDrstate(String drstate) {
		this.drstate = drstate;
	}

	public String getDrzip() {
		return this.drzip;
	}

	public void setDrzip(String drzip) {
		this.drzip = drzip;
	}

	public int getGurantee() {
		return this.gurantee;
	}

	public void setGurantee(int gurantee) {
		this.gurantee = gurantee;
	}

	public String getInscomp() {
		return this.inscomp;
	}

	public void setInscomp(String inscomp) {
		this.inscomp = inscomp;
	}

	public String getInsfax() {
		return this.insfax;
	}

	public void setInsfax(String insfax) {
		this.insfax = insfax;
	}

	public String getInsphone() {
		return this.insphone;
	}

	public void setInsphone(String insphone) {
		this.insphone = insphone;
	}

	public String getInspol() {
		return this.inspol;
	}

	public void setInspol(String inspol) {
		this.inspol = inspol;
	}

	public Date getInspolexp() {
		return this.inspolexp;
	}

	public void setInspolexp(Date inspolexp) {
		this.inspolexp = inspolexp;
	}

	public int getOnlineid() {
		return this.onlineid;
	}

	public void setOnlineid(int onlineid) {
		this.onlineid = onlineid;
	}

	public String getPafirstname() {
		return this.pafirstname;
	}

	public void setPafirstname(String pafirstname) {
		this.pafirstname = pafirstname;
	}

	public String getPalastname() {
		return this.palastname;
	}

	public void setPalastname(String palastname) {
		this.palastname = palastname;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public byte[] getPicture2() {
		return this.picture2;
	}

	public void setPicture2(byte[] picture2) {
		this.picture2 = picture2;
	}

	public byte[] getPicture3() {
		return this.picture3;
	}

	public void setPicture3(byte[] picture3) {
		this.picture3 = picture3;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getVipid() {
		return this.vipid;
	}

	public void setVipid(int vipid) {
		this.vipid = vipid;
	}

	public String getWorkphone() {
		return this.workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	public String getDebt() {
		return debt;
	}

	public void setDebt(String debt) {
		this.debt = debt;
	}

	public String getDebtdescription() {
		return debtdescription;
	}

	public void setDebtdescription(String debtdescription) {
		this.debtdescription = debtdescription;
	}

	public int getNrPikeve() {
		return nrPikeve;
	}

	public void setNrPikeve(int nrPikeve) {
		this.nrPikeve = nrPikeve;
	}

}