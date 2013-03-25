package ramo.klevis.openrental.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ramo.klevis.openrental.utils.RentalStatus;

/**
 * The persistent class for the rentals database table.
 * 
 */
@Entity
@Table(name = "rentals")
public class Rental implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "agentpercent")
	private double agentpercent;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private RentalStatus rentalStatus;

	@Transient
	String statusstring;

	public String getStatusstring() {
		if (rentalStatus != null) {

			statusstring = rentalStatus.toString();
		}
		return statusstring;
	}

	public void setStatusstring(String statusstring) {
		this.statusstring = statusstring;
	}

	@Column(name = "rezervationPayed")
	private boolean rezervationPayed;

	@Column(name = "rezervationAmount")
	private int rezervationAmount;

	@Column(updatable = false, insertable = false, nullable = true)
	private String client2;
	private String curency;
	private String adjfax;

	@Column(name = "agentpayed")
	private boolean agentpayed;

	@Column(name = "inorout")
	private Boolean inorout;

	@Column(name = "curencyg")
	private String curencyg;
	private String adjname;

	private String adjphone;

	private String adjphoneext;

	// code im
	@Column(name = "xkarburant")
	private String xKarburant = "0";

	@Column(name = "xkm")
	private String xKm = "0";

	@Column(name = "xdamage")
	private String xDamage = "0";

	@Column(name = "xlate")
	private String xLate = "0";

	@Column(name = "xclean")
	private String xClean = "0";

	// code im

	@Column(updatable = false, insertable = false, nullable = true)
	private int agent;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "agent")
	private Agent agents;

	private String agentphone;

	private String aggrbr;

	private boolean alarm;

	private BigDecimal amount;

	private BigDecimal amountpaid;

	private String amsg;

	private boolean applbrate;

	private String arrairline;

	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivaldate;

	private int billto;

	private boolean cancelled;

	private boolean caption;

	private String cardexp;

	private String cardnr;

	private String cccvv;

	private String ccname;

	private String cctype;

	@Temporal(TemporalType.TIMESTAMP)
	private Date checkindate;

	private boolean chveh;

	private String claimnr;

	private String clientname;

	private int color;

	// codi im
	private String client;
	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "client2", referencedColumnName = "drpasportid", nullable = true)
	private Customer customer;
	// codi im

	@Lob()
	private String comments;

	private String companyname;

	private int confnr;

	private String contrnr;

	private BigDecimal currbal;

	private int custgrp;

	private boolean damrep;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateofloss;

	private String delby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date departdate;

	private BigDecimal deposit;

	private int deposittype;

	private String deptairline;

	private String deptflight;

	private String dr2addr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dr2bdate;

	private String dr2city;

	private String dr2country;

	private String dr2fname;

	private String dr2id;

	private String dr2lastname;

	private String dr2liccat;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dr2licexpdate;

	private String dr2licnr;

	private String dr2phone;

	private String dr2place;

	private String dr2state;

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

	@Lob()
	private String dropinfo;

	private int droploc;

	private String drpasportid;

	private String drplace;

	private String drstate;

	private String drzip;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endtime;

	private String flightnr;

	private int fuelin;

	private int fuellevel;

	private int gurantee;

	private int image;

	@Column(name = "fuelout")
	private int fuelout;

	@Temporal(TemporalType.TIMESTAMP)
	private Date insexpirationdate;

	private String inspolicy;

	private String insurancecomp;

	private String invnotes;

	private int invocc;

	private int invoice;

	private BigDecimal invoicetotal;

	private int kind;

	private int kmin;

	private int kmout;

	private int linkid;

	private int linkidseq;

	// codi im
	@Column(insertable = false, updatable = false, nullable = true)
	private int loc;
	//
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loc")
	private Location location;

	@Lob()
	private String notes;

	private String onlineref;

	private String pafirstname = "";

	private String palastname;

	private int paymtype;

	@Lob()
	private String picinfo;

	private int picloc;

	private String ponumber;

	private boolean renew;

	private int rentdays;

	private String repfac;

	private int res;

	private String reservationcode;

	private String riskamt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date starttime;

	private String subject;

	private String telnr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	private boolean upl;

	private String username;

	// codi im
	@Column(insertable = false, updatable = false)
	private int vehicle;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vehicle")
	private Car car;
	// codi im

	private int vipid;

	private String vouchercode;

	private String workphone;

	public Rental() {
	}

	public String getAdjfax() {
		return this.adjfax;
	}

	public void setAdjfax(String adjfax) {
		this.adjfax = adjfax;
	}

	public String getAdjname() {
		return this.adjname;
	}

	public void setAdjname(String adjname) {
		this.adjname = adjname;
	}

	public String getAdjphone() {
		return this.adjphone;
	}

	public void setAdjphone(String adjphone) {
		this.adjphone = adjphone;
	}

	public String getAdjphoneext() {
		return this.adjphoneext;
	}

	public void setAdjphoneext(String adjphoneext) {
		this.adjphoneext = adjphoneext;
	}

	public int getAgent() {
		return this.agent;
	}

	public void setAgent(int agent) {
		this.agent = agent;
	}

	public String getAgentphone() {
		return this.agentphone;
	}

	public void setAgentphone(String agentphone) {
		this.agentphone = agentphone;
	}

	public String getAggrbr() {
		return this.aggrbr;
	}

	public void setAggrbr(String aggrbr) {
		this.aggrbr = aggrbr;
	}

	// public boolean getAlarm() {
	// return this.alarm;
	// }
	//
	// public void setAlarm(boolean alarm) {
	// this.alarm = alarm;
	// }

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		if (amount == null) {
			amount = new BigDecimal(0);
		}
		this.amount = amount;
	}

	public BigDecimal getAmountpaid() {
		if (amountpaid == null) {
			amountpaid = new BigDecimal(0);
		}
		return this.amountpaid;
	}

	public void setAmountpaid(BigDecimal amountpaid) {
		this.amountpaid = amountpaid;
	}

	public String getAmsg() {
		return this.amsg;
	}

	public void setAmsg(String amsg) {
		this.amsg = amsg;
	}

	public boolean getApplbrate() {
		return this.applbrate;
	}

	public void setApplbrate(boolean applbrate) {
		this.applbrate = applbrate;
	}

	public String getArrairline() {
		return this.arrairline;
	}

	public void setArrairline(String arrairline) {
		this.arrairline = arrairline;
	}

	public Date getArrivaldate() {
		return this.arrivaldate;
	}

	public void setArrivaldate(Date arrivaldate) {
		this.arrivaldate = arrivaldate;
	}

	public int getBillto() {
		return this.billto;
	}

	public void setBillto(int billto) {
		this.billto = billto;
	}

	public boolean getCancelled() {
		return this.cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public boolean getCaption() {
		return this.caption;
	}

	public void setCaption(boolean caption) {
		this.caption = caption;
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

	public Date getCheckindate() {
		return this.checkindate;
	}

	public void setCheckindate(Date checkindate) {
		this.checkindate = checkindate;
	}

	public boolean getChveh() {
		return this.chveh;
	}

	public void setChveh(boolean chveh) {
		this.chveh = chveh;
	}

	public String getClaimnr() {
		return this.claimnr;
	}

	public void setClaimnr(String claimnr) {
		this.claimnr = claimnr;
	}

	public String getClient() {
		return this.client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClientname() {
		return this.clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public int getColor() {
		return this.color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCompanyname() {
		return this.companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public int getConfnr() {
		return this.confnr;
	}

	public void setConfnr(int confnr) {
		this.confnr = confnr;
	}

	public String getContrnr() {
		return this.contrnr;
	}

	public void setContrnr(String contrnr) {
		this.contrnr = contrnr;
	}

	public BigDecimal getCurrbal() {
		return this.currbal;
	}

	public void setCurrbal(BigDecimal currbal) {
		this.currbal = currbal;
	}

	public int getCustgrp() {
		return this.custgrp;
	}

	public void setCustgrp(int custgrp) {
		this.custgrp = custgrp;
	}

	public boolean getDamrep() {
		return this.damrep;
	}

	public void setDamrep(boolean damrep) {
		this.damrep = damrep;
	}

	public Date getDateofloss() {
		return this.dateofloss;
	}

	public void setDateofloss(Date dateofloss) {
		this.dateofloss = dateofloss;
	}

	public String getDelby() {
		return this.delby;
	}

	public void setDelby(String delby) {
		this.delby = delby;
	}

	public Date getDepartdate() {
		return this.departdate;
	}

	public void setDepartdate(Date departdate) {
		this.departdate = departdate;
	}

	public BigDecimal getDeposit() {
		return this.deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public int getDeposittype() {
		return this.deposittype;
	}

	public void setDeposittype(int deposittype) {
		this.deposittype = deposittype;
	}

	public String getDeptairline() {
		return this.deptairline;
	}

	public void setDeptairline(String deptairline) {
		this.deptairline = deptairline;
	}

	public String getDeptflight() {
		return this.deptflight;
	}

	public void setDeptflight(String deptflight) {
		this.deptflight = deptflight;
	}

	public String getDr2addr() {
		return this.dr2addr;
	}

	public void setDr2addr(String dr2addr) {
		this.dr2addr = dr2addr;
	}

	public Date getDr2bdate() {
		return this.dr2bdate;
	}

	public void setDr2bdate(Date dr2bdate) {
		this.dr2bdate = dr2bdate;
	}

	public String getDr2city() {
		return this.dr2city;
	}

	public void setDr2city(String dr2city) {
		this.dr2city = dr2city;
	}

	public String getDr2country() {
		return this.dr2country;
	}

	public void setDr2country(String dr2country) {
		this.dr2country = dr2country;
	}

	public String getDr2fname() {
		return this.dr2fname;
	}

	public void setDr2fname(String dr2fname) {
		this.dr2fname = dr2fname;
	}

	public String getDr2id() {
		return this.dr2id;
	}

	public void setDr2id(String dr2id) {
		this.dr2id = dr2id;
	}

	public String getDr2lastname() {
		return this.dr2lastname;
	}

	public void setDr2lastname(String dr2lastname) {
		this.dr2lastname = dr2lastname;
	}

	public String getDr2liccat() {
		return this.dr2liccat;
	}

	public void setDr2liccat(String dr2liccat) {
		this.dr2liccat = dr2liccat;
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

	public String getDr2phone() {
		return this.dr2phone;
	}

	public void setDr2phone(String dr2phone) {
		this.dr2phone = dr2phone;
	}

	public String getDr2place() {
		return this.dr2place;
	}

	public void setDr2place(String dr2place) {
		this.dr2place = dr2place;
	}

	public String getDr2state() {
		return this.dr2state;
	}

	public void setDr2state(String dr2state) {
		this.dr2state = dr2state;
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

	public String getDropinfo() {
		return this.dropinfo;
	}

	public void setDropinfo(String dropinfo) {
		this.dropinfo = dropinfo;
	}

	public int getDroploc() {
		return this.droploc;
	}

	public void setDroploc(int droploc) {
		this.droploc = droploc;
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

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getFlightnr() {
		return this.flightnr;
	}

	public void setFlightnr(String flightnr) {
		this.flightnr = flightnr;
	}

	public int getFuelin() {
		return this.fuelin;
	}

	public void setFuelin(int fuelin) {
		this.fuelin = fuelin;
	}

	public int getFuellevel() {
		return this.fuellevel;
	}

	public void setFuellevel(int fuellevel) {
		this.fuellevel = fuellevel;
	}

	public int getGurantee() {
		return this.gurantee;
	}

	public void setGurantee(int gurantee) {
		this.gurantee = gurantee;
	}

	public int getImage() {
		return this.image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public Date getInsexpirationdate() {
		return this.insexpirationdate;
	}

	public void setInsexpirationdate(Date insexpirationdate) {
		this.insexpirationdate = insexpirationdate;
	}

	public String getInspolicy() {
		return this.inspolicy;
	}

	public void setInspolicy(String inspolicy) {
		this.inspolicy = inspolicy;
	}

	public String getInsurancecomp() {
		return this.insurancecomp;
	}

	public void setInsurancecomp(String insurancecomp) {
		this.insurancecomp = insurancecomp;
	}

	public String getInvnotes() {
		return this.invnotes;
	}

	public void setInvnotes(String invnotes) {
		this.invnotes = invnotes;
	}

	public int getInvocc() {
		return this.invocc;
	}

	public void setInvocc(int invocc) {
		this.invocc = invocc;
	}

	public int getInvoice() {
		return this.invoice;
	}

	public void setInvoice(int invoice) {
		this.invoice = invoice;
	}

	public BigDecimal getInvoicetotal() {
		return this.invoicetotal;
	}

	public void setInvoicetotal(BigDecimal invoicetotal) {
		this.invoicetotal = invoicetotal;
	}

	public int getKind() {
		return this.kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getKmin() {
		return this.kmin;
	}

	public void setKmin(int kmin) {
		this.kmin = kmin;
	}

	public int getKmout() {
		return this.kmout;
	}

	public void setKmout(int kmout) {
		this.kmout = kmout;
	}

	public int getLinkid() {
		return this.linkid;
	}

	public void setLinkid(int linkid) {
		this.linkid = linkid;
	}

	public int getLinkidseq() {
		return this.linkidseq;
	}

	public void setLinkidseq(int linkidseq) {
		this.linkidseq = linkidseq;
	}

	public int getLoc() {
		return this.loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOnlineref() {
		return this.onlineref;
	}

	public void setOnlineref(String onlineref) {
		this.onlineref = onlineref;
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

	public int getPaymtype() {
		return this.paymtype;
	}

	public void setPaymtype(int paymtype) {
		this.paymtype = paymtype;
	}

	public String getPicinfo() {
		return this.picinfo;
	}

	public void setPicinfo(String picinfo) {
		this.picinfo = picinfo;
	}

	public int getPicloc() {
		return this.picloc;
	}

	public void setPicloc(int picloc) {
		this.picloc = picloc;
	}

	public String getPonumber() {
		return this.ponumber;
	}

	public void setPonumber(String ponumber) {
		this.ponumber = ponumber;
	}

	public boolean getRenew() {
		return this.renew;
	}

	public void setRenew(boolean renew) {
		this.renew = renew;
	}

	public int getRentdays() {
		return this.rentdays;
	}

	public void setRentdays(int rentdays) {
		this.rentdays = rentdays;
	}

	public String getRepfac() {
		return this.repfac;
	}

	public void setRepfac(String repfac) {
		this.repfac = repfac;
	}

	public int getRes() {
		return this.res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public String getReservationcode() {
		return this.reservationcode;
	}

	public void setReservationcode(String reservationcode) {
		this.reservationcode = reservationcode;
	}

	public String getRiskamt() {
		return this.riskamt;
	}

	public void setRiskamt(String riskamt) {
		this.riskamt = riskamt;
	}

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTelnr() {
		return this.telnr;
	}

	public void setTelnr(String telnr) {
		this.telnr = telnr;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public boolean getUpl() {
		return this.upl;
	}

	public void setUpl(boolean upl) {
		this.upl = upl;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(int vehicle) {
		this.vehicle = vehicle;
	}

	public int getVipid() {
		return this.vipid;
	}

	public void setVipid(int vipid) {
		this.vipid = vipid;
	}

	public String getVouchercode() {
		return this.vouchercode;
	}

	public void setVouchercode(String vouchercode) {
		this.vouchercode = vouchercode;
	}

	public String getWorkphone() {
		return this.workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public Customer getCustomer() {
		// if(customer==null){
		//
		// customer=new Customer();
		//
		// customer.setPafirstname(getPafirstname());
		// customer.setPalastname(getPalastname());
		// customer.setDrhphone(getDrhphone());
		// customer.setDrpasportid(drpasportid)
		//
		// }
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	// public Location getLocation() {
	// return location;
	// }
	//
	// public void setLocation(Location location) {
	// this.location = location;
	// }

	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}

	public boolean isAlarm() {
		return alarm;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setRentalStatus(RentalStatus rentalStatus) {
		this.rentalStatus = rentalStatus;
	}

	public RentalStatus getRentalStatus() {
		return rentalStatus;
	}

	public int getFuelout() {
		return fuelout;
	}

	public void setFuelout(int fuelout) {
		this.fuelout = fuelout;
	}

	public void setxKarburant(String xKarburant) {
		this.xKarburant = xKarburant;
	}

	public String getxKarburant() {
		return xKarburant;
	}

	public void setxKm(String xKm) {
		this.xKm = xKm;
	}

	public String getxKm() {
		return xKm;
	}

	public void setxDamage(String xDamage) {
		this.xDamage = xDamage;
	}

	public String getxDamage() {
		return xDamage;
	}

	public void setxLate(String xLate) {
		this.xLate = xLate;
	}

	public String getxLate() {
		return xLate;
	}

	public void setxClean(String xClean) {
		this.xClean = xClean;
	}

	public String getxClean() {
		return xClean;
	}

	public void setCurency(String curency) {
		this.curency = curency;
	}

	public String getCurency() {
		return curency;
	}

	public void setCurencyg(String curencyg) {
		this.curencyg = curencyg;
	}

	public String getCurencyg() {
		return curencyg;
	}

	public void setInorout(boolean inorout) {
		this.inorout = inorout;
	}

	public boolean isInorout() {
		if (inorout == null) {

			inorout = new Boolean(true);
		}
		return inorout;
	}

	public String getClient2() {
		return client2;
	}

	public void setClient2(String client2) {
		this.client2 = client2;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Boolean getAgentpayed() {
		return isAgentpayed();
	}

	public void setAgentpayed(Boolean agentpayed) {
		this.agentpayed = agentpayed;
	}

	public Agent getAgents() {
		return agents;
	}

	public void setAgents(Agent agents) {
		this.agents = agents;
	}

	public boolean isAgentpayed() {
		return agentpayed;
	}

	public void setAgentpayed(boolean agentpayed) {
		this.agentpayed = agentpayed;
	}

	public double getAgentpercent() {
		return agentpercent;
	}

	public void setAgentpercent(double agentpercent) {
		this.agentpercent = agentpercent;
	}

	public boolean isRezervationPayed() {
		return rezervationPayed;
	}

	public void setRezervationPayed(boolean rezervationPayed) {
		this.rezervationPayed = rezervationPayed;
	}

	public int getRezervationAmount() {
		return rezervationAmount;
	}

	public void setRezervationAmount(int rezervationAmount) {
		this.rezervationAmount = rezervationAmount;
	}

}