package ramo.klevis.openrental.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;


/**
 * The persistent class for the dokumentacioni database table.
 * 
 */
@Entity
@Table(name = "dokumentacioni")
public class Dokumentacioni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idmakina;

	@Temporal(TemporalType.TIMESTAMP)
	private Date kasko;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "leje_nderkombetare")
	private Date lejeNderkombetare;

	@Temporal(TemporalType.TIMESTAMP)
	private Date sgs;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tpl;

	@Transient
	Label lblKasko ;

	@Transient
	Label lblSigThjeshte ;
	@Transient
	Label lblLejeNderkombetare ;
	@Transient
	Label lblKontrolTeknik ;

	public void setImage(String s, Label label) {

//		label.setImage(SWTResourceManager.getImage(FormDokumentacioni.class,
//				"/image/" + s));
	}

	public void controlDocumentacion(Dokumentacioni dokumentacioni) {

		Date kasko = dokumentacioni.getKasko();
		Date lejeNderkombetare = dokumentacioni.getLejeNderkombetare();
		Date sgs = dokumentacioni.getSgs();
		Date tpl = dokumentacioni.getTpl();
		Date date = new Date();

		if (kasko != null) {
			if (kasko.before(date)) {

				setImage("warning.png", lblKasko);
			} else {
				setImage("Symbol_OK.png", lblKasko);
			}
		} else {

			setImage("Warning-32.png", lblKasko);
		}

		if (lejeNderkombetare != null) {
			if (lejeNderkombetare.before(date)) {
				setImage("warning.png", lblLejeNderkombetare);
			} else {
				setImage("Symbol_OK.png", lblLejeNderkombetare);
			}
		} else {

			setImage("Warning-32.png", lblLejeNderkombetare);
		}

		if (sgs != null) {
			if (sgs.before(date)) {
				setImage("warning.png", lblKontrolTeknik);
			} else {
				setImage("Symbol_OK.png", lblKontrolTeknik);
			}

		} else {
			setImage("Warning-32.png", lblKontrolTeknik);
		}

		if (tpl != null) {
			if (tpl.before(date)) {
				setImage("warning.png", lblSigThjeshte);
			} else {
				setImage("Symbol_OK.png", lblSigThjeshte);
			}
		} else {

			setImage("Warning-32.png", lblSigThjeshte);
		}

	}

	// @Column(name="leje_qarkullimi")
	// private String lejeQarkullimi;

	public Label getLblKasko() {
		controlDocumentacion(this);
		return lblKasko;
	}

	public void setLblKasko(Label lblKasko) {
		this.lblKasko = lblKasko;
	}

	public Label getLblSigThjeshte() {
		controlDocumentacion(this);
		return lblSigThjeshte;
	}

	public void setLblSigThjeshte(Label lblSigThjeshte) {
		this.lblSigThjeshte = lblSigThjeshte;
	}

	public Label getLblLejeNderkombetare() {
		controlDocumentacion(this);
		return lblLejeNderkombetare;
	}

	public void setLblLejeNderkombetare(Label lblLejeNderkombetare) {
		this.lblLejeNderkombetare = lblLejeNderkombetare;
	}

	public Label getLblKontrolTeknik() {
		controlDocumentacion(this);
		return lblKontrolTeknik;
	}

	public void setLblKontrolTeknik(Label lblKontrolTeknik) {
		this.lblKontrolTeknik = lblKontrolTeknik;
	}

	public Dokumentacioni() {

	}

	public int getIdmakina() {
		return this.idmakina;
	}

	public void setIdmakina(int idmakina) {
		this.idmakina = idmakina;
	}

	public Date getKasko() {
		return this.kasko;
	}

	public void setKasko(Date kasko) {
		this.kasko = kasko;
	}

	public Date getLejeNderkombetare() {
		return this.lejeNderkombetare;
	}

	public void setLejeNderkombetare(Date lejeNderkombetare) {
		this.lejeNderkombetare = lejeNderkombetare;
	}

	public Date getSgs() {
		return this.sgs;
	}

	public void setSgs(Date sgs) {
		this.sgs = sgs;
	}

	public Date getTpl() {
		return this.tpl;
	}

	public void setTpl(Date tpl) {
		this.tpl = tpl;
	}

	// public String getLejeQarkullimi() {
	// return lejeQarkullimi;
	// }
	//
	// public void setLejeQarkullimi(String lejeQarkullimi) {
	// this.lejeQarkullimi = lejeQarkullimi;
	// }

}