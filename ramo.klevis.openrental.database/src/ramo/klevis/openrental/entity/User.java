package ramo.klevis.openrental.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	
	public User(Long nrOfRentals, String username) {
		super();
		this.setNrOfRentals(nrOfRentals);
		this.username = username;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seq;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLES_ID"))
	private List<Role> roles = new ArrayList<Role>();

	@Column(name = "DATA")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Transient
	private long nrOfRentals;

	private boolean betu;

	private boolean betv;

	private boolean ccnr;

	private boolean ched;

	private boolean chrates;

	private boolean chsd;

	private boolean cragents;

	private boolean delpaym;

	private boolean faktm;

	private boolean faktv;

	private int level;

	private boolean opzet;

	private String password;

	private boolean rap;

	private boolean updetup;

	private String userbr;

	private String userid;

	private String username;

	private boolean users;

	private boolean vehview;

	private boolean viewagents;

	public User() {
	}

	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public boolean getBetu() {
		return this.betu;
	}

	public void setBetu(boolean betu) {
		this.betu = betu;
	}

	public boolean getBetv() {
		return this.betv;
	}

	public void setBetv(boolean betv) {
		this.betv = betv;
	}

	public boolean getCcnr() {
		return this.ccnr;
	}

	public void setCcnr(boolean ccnr) {
		this.ccnr = ccnr;
	}

	public boolean getChed() {
		return this.ched;
	}

	public void setChed(boolean ched) {
		this.ched = ched;
	}

	public boolean getChrates() {
		return this.chrates;
	}

	public void setChrates(boolean chrates) {
		this.chrates = chrates;
	}

	public boolean getChsd() {
		return this.chsd;
	}

	public void setChsd(boolean chsd) {
		this.chsd = chsd;
	}

	public boolean getCragents() {
		return this.cragents;
	}

	public void setCragents(boolean cragents) {
		this.cragents = cragents;
	}

	public boolean getDelpaym() {
		return this.delpaym;
	}

	public void setDelpaym(boolean delpaym) {
		this.delpaym = delpaym;
	}

	public boolean getFaktm() {
		return this.faktm;
	}

	public void setFaktm(boolean faktm) {
		this.faktm = faktm;
	}

	public boolean getFaktv() {
		return this.faktv;
	}

	public void setFaktv(boolean faktv) {
		this.faktv = faktv;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean getOpzet() {
		return this.opzet;
	}

	public void setOpzet(boolean opzet) {
		this.opzet = opzet;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getRap() {
		return this.rap;
	}

	public void setRap(boolean rap) {
		this.rap = rap;
	}

	public boolean getUpdetup() {
		return this.updetup;
	}

	public void setUpdetup(boolean updetup) {
		this.updetup = updetup;
	}

	public String getUserbr() {
		return this.userbr;
	}

	public void setUserbr(String userbr) {
		this.userbr = userbr;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean getUsers() {
		return this.users;
	}

	public void setUsers(boolean users) {
		this.users = users;
	}

	public boolean getVehview() {
		return this.vehview;
	}

	public void setVehview(boolean vehview) {
		this.vehview = vehview;
	}

	public boolean getViewagents() {
		return this.viewagents;
	}

	public void setViewagents(boolean viewagents) {
		this.viewagents = viewagents;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getNrOfRentals() {
		return nrOfRentals;
	}

	public void setNrOfRentals(long nrOfRentals) {
		this.nrOfRentals = nrOfRentals;
	}

	

}