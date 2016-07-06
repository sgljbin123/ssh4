package com.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * UtMemu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ut_menu")
public class UtMenu implements java.io.Serializable {

	// Fields

	private Integer id;
	private UtMenu utMemu;
	private String text;
	private String icon;
	private String url;
	private Set<UtMenu> utMemus = new HashSet<UtMenu>(0);

	// Constructors

	/** default constructor */
	public UtMenu() {
	}

	/** full constructor */
	public UtMenu(UtMenu utMemu, String text, String icon, String url,
			Set<UtMenu> utMemus) {
		this.utMemu = utMemu;
		this.text = text;
		this.icon = icon;
		this.url = url;
		this.utMemus = utMemus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public UtMenu getUtMemu() {
		return this.utMemu;
	}

	public void setUtMemu(UtMenu utMemu) {
		this.utMemu = utMemu;
	}

	@Column(name = "text", length = 100)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "icon", length = 100)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "url", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utMemu")
	public Set<UtMenu> getUtMemus() {
		return this.utMemus;
	}

	public void setUtMemus(Set<UtMenu> utMemus) {
		this.utMemus = utMemus;
	}

}