package entities;

import java.util.ArrayList;

public class Product {

	private String name;
	private Double price;
	private Integer quantity;
	private Double totalPrice;
	
	public Product(String name, Double price, Integer quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Product(String name, Double totalPrice) {
		this.name = name;
		this.totalPrice= totalPrice;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double total() {
		return price * quantity;
	}
	
	@Override
	public String toString() {
		return "Name ==> "
		     + this.name
			 + " Price ==> " 
			 + this.price
			 + " Quantity ==> "
			 + this.quantity;
	}
	
}
