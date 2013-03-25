package ramo.klevis.openrental.entity;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.eclipse.swt.widgets.Display;

import ramo.klevis.openrental.utils.CarImageHolder;

/**
 * The persistent class for the cars database table.
 * 
 */
@Entity
@Table(name = "cars")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Transient
	private ramo.klevis.openrental.utils.CarImageHolder carImageHolder;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cars")
	private List<Investime> investimet;

	@Column(name = "1kmdrate")
	private int limKm1;
	@Column(name = "2kmdrate")
	private int limKm2;
	@Column(name = "3kmdrate")
	private int limKm3;
	@Column(name = "4kmdrate")
	private int limKm4;
	@Column(name = "5kmdrate")
	private int limKm5;

	@Transient
	private String payForDay;

	@Column(name = "choosen")
	private String choosen;
	@Transient
	private Dokumentacioni dokumentacioni;

	@Column(name = "2drate")
	private BigDecimal _drate2;

	@Column(name = "vflimitkm")
	private int vFLimitKm;

	@Column(name = "vfllastkm")
	private int vFLastKm;

	@Transient
	private int nrditeve;
	@Column(name = "3drate")
	private BigDecimal _drate3;

	@Column(name = "4drate")
	private BigDecimal _drate4;

	@Column(name = "5drate")
	private BigDecimal _drate5;

	@Column(name = "6drate")
	private BigDecimal _drate6;

	@Temporal(TemporalType.TIMESTAMP)
	private Date acquired;

	private boolean active;

	private int avb;

	private String bodystyle;

	@Column(name = "class", insertable = false, updatable = false)
	private int class_;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "class", referencedColumnName = "id")
	private Class classes;

	private String color;

	private BigDecimal deposit;

	private String description;

	private BigDecimal discrate;

	private String engine;

	@Temporal(TemporalType.TIMESTAMP)
	private Date firstdate;

	private int freekm;

	private String fuel;

	public int getLimKm1() {
		return limKm1;
	}

	public void setLimKm1(int limKm1) {
		this.limKm1 = limKm1;
	}

	public int getLimKm2() {
		return limKm2;
	}

	public void setLimKm2(int limKm2) {
		this.limKm2 = limKm2;
	}

	public int getLimKm3() {
		return limKm3;
	}

	public void setLimKm3(int limKm3) {
		this.limKm3 = limKm3;
	}

	public int getLimKm4() {
		return limKm4;
	}

	public void setLimKm4(int limKm4) {
		this.limKm4 = limKm4;
	}

	public int getLimKm5() {
		return limKm5;
	}

	public void setLimKm5(int limKm5) {
		this.limKm5 = limKm5;
	}

	private BigDecimal fuelamt;

	private String fueltype;

	private double hrate;

	private BigDecimal insamt;

	private String inscomp;

	@Temporal(TemporalType.TIMESTAMP)
	private Date insexpdate;

	private BigDecimal inspamt;

	private String inspol;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastservdate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastupd;

	private String license;

	private String make;

	private int maxload;

	private int milage;

	private int milagelastserv;

	private String model;

	private BigDecimal mrate;

	private BigDecimal mxrate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date nextinspdate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date nextmotdate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date nextservdate;

	private String num;

	private String outitem;

	@Lob()
	private byte[] pic;

	@Column(name = "extension")
	private String extent;

	private String picloc;

	private BigDecimal purchasePrice;

	private String qbclass;

	private String qbclass1;

	private String qbclass10;

	private String qbclass11;

	private String qbclass12;

	private String qbclass13;

	private String qbclass14;

	private String qbclass15;

	private String qbclass16;

	private String qbclass2;

	private String qbclass3;

	private String qbclass4;

	private String qbclass5;

	private String qbclass6;

	private String qbclass7;

	private String qbclass8;

	private String qbitem;

	private String qbitem1;

	private String qbitem10;

	private String qbitem11;

	private String qbitem12;

	private String qbitem13;

	private String qbitem14;

	private String qbitem15;

	private String qbitem16;

	private String qbitem2;

	private String qbitem3;

	private String qbitem4;

	private String qbitem5;

	private String qbitem6;

	private String qbitem7;

	private String qbitem8;

	private int qbtype;

	private double rate;

	private BigDecimal ratekm;

	@Temporal(TemporalType.TIMESTAMP)
	private Date reldate;

	private String remarks;

	private String rentid;

	private BigDecimal saleprice;

	private BigDecimal servamt;

	private int servint;

	private int servmile;

	private int status;

	private BigDecimal taxamt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date taxexpdate;

	private String trans;

	private String vehbr;

	private int vehloc;

	private String vin;

	private BigDecimal wrate;

	private BigDecimal wxrate;

	private int xkm;

	private int year;

	public Car() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal get_drate2() {
		return this._drate2;
	}

	public void set_drate2(BigDecimal _drate) {
		this._drate2 = _drate;
	}

	public BigDecimal get_drate3() {
		return this._drate3;
	}

	public void set_drate3(BigDecimal _drate) {
		this._drate3 = _drate;
	}

	public BigDecimal get_drate4() {
		return this._drate4;
	}

	public void set_drate4(BigDecimal _drate) {
		this._drate4 = _drate;
	}

	public BigDecimal get_drate5() {
		return this._drate5;
	}

	public void set_drate5(BigDecimal _drate) {
		this._drate5 = _drate;
	}

	public BigDecimal get_drate6() {
		return this._drate6;
	}

	public void set_drate6(BigDecimal _drate) {
		this._drate6 = _drate;
	}

	public Date getAcquired() {
		return this.acquired;
	}

	public void setAcquired(Date acquired) {
		this.acquired = acquired;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getAvb() {
		return this.avb;
	}

	public void setAvb(int avb) {
		this.avb = avb;
	}

	public String getBodystyle() {
		return this.bodystyle;
	}

	public void setBodystyle(String bodystyle) {
		this.bodystyle = bodystyle;
	}

	public int getClass_() {
		return this.class_;
	}

	public void setClass_(int class_) {
		this.class_ = class_;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getDeposit() {
		return this.deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDiscrate() {
		return this.discrate;
	}

	public void setDiscrate(BigDecimal discrate) {
		this.discrate = discrate;
	}

	public String getEngine() {
		return this.engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public Date getFirstdate() {
		return this.firstdate;
	}

	public void setFirstdate(Date firstdate) {
		this.firstdate = firstdate;
	}

	public int getFreekm() {
		return this.freekm;
	}

	public void setFreekm(int freekm) {
		this.freekm = freekm;
	}

	public String getFuel() {
		return this.fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public BigDecimal getFuelamt() {
		return this.fuelamt;
	}

	public void setFuelamt(BigDecimal fuelamt) {
		this.fuelamt = fuelamt;
	}

	public String getFueltype() {
		return this.fueltype;
	}

	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}

	public double getHrate() {
		return this.hrate;
	}

	public void setHrate(double hrate) {
		this.hrate = hrate;
	}

	public BigDecimal getInsamt() {
		return this.insamt;
	}

	public void setInsamt(BigDecimal insamt) {
		this.insamt = insamt;
	}

	public String getInscomp() {
		return this.inscomp;
	}

	public void setInscomp(String inscomp) {
		this.inscomp = inscomp;
	}

	public Date getInsexpdate() {
		return this.insexpdate;
	}

	public void setInsexpdate(Date insexpdate) {
		this.insexpdate = insexpdate;
	}

	public BigDecimal getInspamt() {
		return this.inspamt;
	}

	public void setInspamt(BigDecimal inspamt) {
		this.inspamt = inspamt;
	}

	public String getInspol() {
		return this.inspol;
	}

	public void setInspol(String inspol) {
		this.inspol = inspol;
	}

	public Date getLastservdate() {
		return this.lastservdate;
	}

	public void setLastservdate(Date lastservdate) {
		this.lastservdate = lastservdate;
	}

	public Date getLastupd() {
		return this.lastupd;
	}

	public void setLastupd(Date lastupd) {
		this.lastupd = lastupd;
	}

	public String getLicense() {
		return this.license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public int getMaxload() {
		return this.maxload;
	}

	public void setMaxload(int maxload) {
		this.maxload = maxload;
	}

	public int getMilage() {
		return this.milage;
	}

	public void setMilage(int milage) {
		this.milage = milage;
	}

	public int getMilagelastserv() {
		return this.milagelastserv;
	}

	public void setMilagelastserv(int milagelastserv) {
		this.milagelastserv = milagelastserv;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getMrate() {
		return this.mrate;
	}

	public void setMrate(BigDecimal mrate) {
		this.mrate = mrate;
	}

	public BigDecimal getMxrate() {
		return this.mxrate;
	}

	public void setMxrate(BigDecimal mxrate) {
		this.mxrate = mxrate;
	}

	public Date getNextinspdate() {
		return this.nextinspdate;
	}

	public void setNextinspdate(Date nextinspdate) {
		this.nextinspdate = nextinspdate;
	}

	public Date getNextmotdate() {
		return this.nextmotdate;
	}

	public void setNextmotdate(Date nextmotdate) {
		this.nextmotdate = nextmotdate;
	}

	public Date getNextservdate() {
		return this.nextservdate;
	}

	public void setNextservdate(Date nextservdate) {
		this.nextservdate = nextservdate;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getOutitem() {
		return this.outitem;
	}

	public void setOutitem(String outitem) {
		this.outitem = outitem;
	}

	public byte[] getPic() {
		return this.pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public String getPicloc() {
		return this.picloc;
	}

	public void setPicloc(String picloc) {
		this.picloc = picloc;
	}

	public BigDecimal getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getQbclass() {
		return this.qbclass;
	}

	public void setQbclass(String qbclass) {
		this.qbclass = qbclass;
	}

	public String getQbclass1() {
		return this.qbclass1;
	}

	public void setQbclass1(String qbclass1) {
		this.qbclass1 = qbclass1;
	}

	public String getQbclass10() {
		return this.qbclass10;
	}

	public void setQbclass10(String qbclass10) {
		this.qbclass10 = qbclass10;
	}

	public String getQbclass11() {
		return this.qbclass11;
	}

	public void setQbclass11(String qbclass11) {
		this.qbclass11 = qbclass11;
	}

	public String getQbclass12() {
		return this.qbclass12;
	}

	public void setQbclass12(String qbclass12) {
		this.qbclass12 = qbclass12;
	}

	public String getQbclass13() {
		return this.qbclass13;
	}

	public void setQbclass13(String qbclass13) {
		this.qbclass13 = qbclass13;
	}

	public String getQbclass14() {
		return this.qbclass14;
	}

	public void setQbclass14(String qbclass14) {
		this.qbclass14 = qbclass14;
	}

	public String getQbclass15() {
		return this.qbclass15;
	}

	public void setQbclass15(String qbclass15) {
		this.qbclass15 = qbclass15;
	}

	public String getQbclass16() {
		return this.qbclass16;
	}

	public void setQbclass16(String qbclass16) {
		this.qbclass16 = qbclass16;
	}

	public String getQbclass2() {
		return this.qbclass2;
	}

	public void setQbclass2(String qbclass2) {
		this.qbclass2 = qbclass2;
	}

	public String getQbclass3() {
		return this.qbclass3;
	}

	public void setQbclass3(String qbclass3) {
		this.qbclass3 = qbclass3;
	}

	public String getQbclass4() {
		return this.qbclass4;
	}

	public void setQbclass4(String qbclass4) {
		this.qbclass4 = qbclass4;
	}

	public String getQbclass5() {
		return this.qbclass5;
	}

	public void setQbclass5(String qbclass5) {
		this.qbclass5 = qbclass5;
	}

	public String getQbclass6() {
		return this.qbclass6;
	}

	public void setQbclass6(String qbclass6) {
		this.qbclass6 = qbclass6;
	}

	public String getQbclass7() {
		return this.qbclass7;
	}

	public void setQbclass7(String qbclass7) {
		this.qbclass7 = qbclass7;
	}

	public String getQbclass8() {
		return this.qbclass8;
	}

	public void setQbclass8(String qbclass8) {
		this.qbclass8 = qbclass8;
	}

	public String getQbitem() {
		return this.qbitem;
	}

	public void setQbitem(String qbitem) {
		this.qbitem = qbitem;
	}

	public String getQbitem1() {
		return this.qbitem1;
	}

	public void setQbitem1(String qbitem1) {
		this.qbitem1 = qbitem1;
	}

	public String getQbitem10() {
		return this.qbitem10;
	}

	public void setQbitem10(String qbitem10) {
		this.qbitem10 = qbitem10;
	}

	public String getQbitem11() {
		return this.qbitem11;
	}

	public void setQbitem11(String qbitem11) {
		this.qbitem11 = qbitem11;
	}

	public String getQbitem12() {
		return this.qbitem12;
	}

	public void setQbitem12(String qbitem12) {
		this.qbitem12 = qbitem12;
	}

	public String getQbitem13() {
		return this.qbitem13;
	}

	public void setQbitem13(String qbitem13) {
		this.qbitem13 = qbitem13;
	}

	public String getQbitem14() {
		return this.qbitem14;
	}

	public void setQbitem14(String qbitem14) {
		this.qbitem14 = qbitem14;
	}

	public String getQbitem15() {
		return this.qbitem15;
	}

	public void setQbitem15(String qbitem15) {
		this.qbitem15 = qbitem15;
	}

	public String getQbitem16() {
		return this.qbitem16;
	}

	public void setQbitem16(String qbitem16) {
		this.qbitem16 = qbitem16;
	}

	public String getQbitem2() {
		return this.qbitem2;
	}

	public void setQbitem2(String qbitem2) {
		this.qbitem2 = qbitem2;
	}

	public String getQbitem3() {
		return this.qbitem3;
	}

	public void setQbitem3(String qbitem3) {
		this.qbitem3 = qbitem3;
	}

	public String getQbitem4() {
		return this.qbitem4;
	}

	public void setQbitem4(String qbitem4) {
		this.qbitem4 = qbitem4;
	}

	public String getQbitem5() {
		return this.qbitem5;
	}

	public void setQbitem5(String qbitem5) {
		this.qbitem5 = qbitem5;
	}

	public String getQbitem6() {
		return this.qbitem6;
	}

	public void setQbitem6(String qbitem6) {
		this.qbitem6 = qbitem6;
	}

	public String getQbitem7() {
		return this.qbitem7;
	}

	public void setQbitem7(String qbitem7) {
		this.qbitem7 = qbitem7;
	}

	public String getQbitem8() {
		return this.qbitem8;
	}

	public void setQbitem8(String qbitem8) {
		this.qbitem8 = qbitem8;
	}

	public int getQbtype() {
		return this.qbtype;
	}

	public void setQbtype(int qbtype) {
		this.qbtype = qbtype;
	}

	public double getRate() {
		return this.rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public BigDecimal getRatekm() {
		return this.ratekm;
	}

	public void setRatekm(BigDecimal ratekm) {
		this.ratekm = ratekm;
	}

	public Date getReldate() {
		return this.reldate;
	}

	public void setReldate(Date reldate) {
		this.reldate = reldate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRentid() {
		return this.rentid;
	}

	public void setRentid(String rentid) {
		this.rentid = rentid;
	}

	public BigDecimal getSaleprice() {
		return this.saleprice;
	}

	public void setSaleprice(BigDecimal saleprice) {
		this.saleprice = saleprice;
	}

	public BigDecimal getServamt() {
		return this.servamt;
	}

	public void setServamt(BigDecimal servamt) {
		this.servamt = servamt;
	}

	public int getServint() {
		return this.servint;
	}

	public void setServint(int servint) {
		this.servint = servint;
	}

	public int getServmile() {
		return this.servmile;
	}

	public void setServmile(int servmile) {
		this.servmile = servmile;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BigDecimal getTaxamt() {
		return this.taxamt;
	}

	public void setTaxamt(BigDecimal taxamt) {
		this.taxamt = taxamt;
	}

	public Date getTaxexpdate() {
		return this.taxexpdate;
	}

	public void setTaxexpdate(Date taxexpdate) {
		this.taxexpdate = taxexpdate;
	}

	public String getTrans() {
		return this.trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	public String getVehbr() {
		return this.vehbr;
	}

	public void setVehbr(String vehbr) {
		this.vehbr = vehbr;
	}

	public int getVehloc() {
		return this.vehloc;
	}

	public void setVehloc(int vehloc) {
		this.vehloc = vehloc;
	}

	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public BigDecimal getWrate() {
		return this.wrate;
	}

	public void setWrate(BigDecimal wrate) {
		this.wrate = wrate;
	}

	public BigDecimal getWxrate() {
		return this.wxrate;
	}

	public void setWxrate(BigDecimal wxrate) {
		this.wxrate = wxrate;
	}

	public int getXkm() {
		return this.xkm;
	}

	public void setXkm(int xkm) {
		this.xkm = xkm;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getNrditeve() {
		return nrditeve;
	}

	public void setNrditeve(int nrDiteve) {
		this.nrditeve = nrDiteve;
	}

	public Class getClasses() {
		return classes;
	}

	public void setClasses(Class classes) {
		this.classes = classes;
	}

	public Dokumentacioni getDokumentacioni() {
		return dokumentacioni;
	}

	public void setDokumentacioni(Dokumentacioni dokumentacioni) {
		this.dokumentacioni = dokumentacioni;
	}

	public int getvFLastKm() {
		return vFLastKm;
	}

	public void setvFLastKm(int vFLastKm) {
		this.vFLastKm = vFLastKm;
	}

	public int getvFLimitKm() {
		return vFLimitKm;
	}

	public void setvFLimitKm(int vFLimitKm) {
		this.vFLimitKm = vFLimitKm;
	}

	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	public String getPayForDay() {
		return payForDay;
	}

	public void setPayForDay(String payFormDay) {
		this.payForDay = payFormDay;
	}

	public List<Investime> getInvestimet() {
		if (investimet == null) {
			investimet = new ArrayList<Investime>();
		}
		return investimet;
	}

	public void setInvestimet(List<Investime> investimet) {
		this.investimet = investimet;
	}

	public CarImageHolder getCarImageHolder() {

		if (this.getPic() != null) {

			if (this.getPic().length > 0) {

				carImageHolder = new CarImageHolder(Display.getCurrent()
						.getActiveShell(), 0);
				InputStream in = new ByteArrayInputStream(this.getPic());
				try {
					BufferedImage bImageFromConvert = ImageIO.read(in);
					String extent = this.getExtent();
					String string = "c:/prov/image." + extent;
					if (extent != null) {
						ImageIO.write(bImageFromConvert, extent, new File(
								string));

						carImageHolder.addImage(string);
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		return carImageHolder;
	}

	public void setCarImageHolder(CarImageHolder carImageHolder) {
		this.carImageHolder = carImageHolder;
	}

	public String getChoosen() {
		return choosen;
	}

	public void setChoosen(String choosen) {
		this.choosen = choosen;
	}

}