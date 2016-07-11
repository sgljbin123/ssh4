package com.drivenModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.model.UtMenu;

public class MenuModel {

	private Integer id;
	private Integer pid;
	private String ptext;
	private String text;
	private String icon;
	private String url;
	private Set<UtMenu> children = new HashSet<UtMenu>(0);
	public String getPtext() {
		return ptext;
	}
	public void setPtext(String ptext) {
		this.ptext = ptext;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Set<UtMenu> getChildren() {
		return children;
	}
	public void setChildren(Set<UtMenu> children) {
		this.children = children;
	}

	
	
}
