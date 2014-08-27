package org.poligraph.model.bean;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fullName;
	private String shortName;
	private int price;
	private int quantity;
	private String link;
	private String category;
	
	public Product() {
		
	}
	
	public Product(String fullName, int price, int quantity) {
		setFullName(fullName);
		this.price = price;
		this.quantity = quantity;
	}
	
	private String getShortValue(String str) {
		String[] array = str.split("/");
		array = array[array.length - 1].split("#");
		return array[array.length - 1].replace("#", ":")
				.substring(0, array[array.length - 1].length());
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		setShortName(fullName);
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String fullName) {
		this.shortName = getShortValue(fullName);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = getShortValue(category);;
	}

	@Override
	public String toString() {
		return "Product [fullName=" + fullName + ", shortName=" + shortName
				+ ", price=" + price + ", quantity=" + quantity + ", link="
				+ link + ", category=" + category + "]";
	}

	
}
