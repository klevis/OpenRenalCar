package ramo.klevis.openrental.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the classes database table.
 * 
 */
@Entity
@Table(name = "classes")
public class Class implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "2dr")
	private double _dr2;

	@Column(name = "3dr")
	private double _dr3;

	@Column(name = "4dr")
	private double _dr4;

	@Column(name = "5dr")
	private double _dr5;

	@Column(name = "6dr")
	private double _dr6;

	private String bodystyle;

	@Column(name = "class")
	private String class_;

	private String descr;

	private BigDecimal mrate;

	private BigDecimal mxrate;

	private BigDecimal rate;

	private BigDecimal wrate;

	private BigDecimal wxrate;

	public Class() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double get_dr2() {
		return this._dr2;
	}

	public void set_dr2(double _dr) {
		this._dr2 = _dr;
	}

	public double get_dr3() {
		return this._dr3;
	}

	public void set_dr3(double _dr) {
		this._dr3 = _dr;
	}

	public double get_dr4() {
		return this._dr4;
	}

	public void set_dr4(double _dr) {
		this._dr4 = _dr;
	}

	public double get_dr5() {
		return this._dr5;
	}

	public void set_dr5(double _dr) {
		this._dr5 = _dr;
	}

	public double get_dr6() {
		return this._dr6;
	}

	public void set_dr6(double _dr) {
		this._dr6 = _dr;
	}

	public String getBodystyle() {
		return this.bodystyle;
	}

	public void setBodystyle(String bodystyle) {
		this.bodystyle = bodystyle;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
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

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
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

}