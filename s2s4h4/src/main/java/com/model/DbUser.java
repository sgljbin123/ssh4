package com.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * DbUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "db_user", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class DbUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8803514565478727616L;
	/**
	 * 
	 */
	
	private int id;
	private String name;
	private String password;
	private Date createtime;
	private Date modifytime;

	// Constructors

	/** default constructor */
	public DbUser() {
	}

	/** minimal constructor */
	public DbUser(String name, String password) {
		this.name = name;
		this.password = password;
	}

	/** full constructor */
	public DbUser(String name, String password, Date createtime, Date modifytime) {
		this.name = name;
		this.password = password;
		this.createtime = createtime;
		this.modifytime = modifytime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public Integer getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NAME", unique = true, nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PASSWORD", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME", length = 7)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFYTIME", length = 7)
	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

}