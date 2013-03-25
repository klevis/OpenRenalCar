package ramo.klevis.openrental.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * The persistent class for the ROLE database table.
 * 
 */
@Entity
@Table(name = "role")
public class Role {
	private static final long serialVersionUID = 1L;

	@Id
	@Enumerated(EnumType.STRING)
	@Column(name = "ID", length = 30)
	private RoleEnum roleEnum;

	@Column(name = "TAG", length = 100)
	private String tag;

	public Role() {
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tag;
	}

	public RoleEnum getRoleEnum() {
		return roleEnum;
	}

	public void setRoleEnum(RoleEnum roleEnum) {
		this.roleEnum = roleEnum;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Role)) {
			return false;
		}
		Role castOther = (Role) other;
		return (this.roleEnum == castOther.getRoleEnum());

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.getRoleEnum().hashCode();

		return hash;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}