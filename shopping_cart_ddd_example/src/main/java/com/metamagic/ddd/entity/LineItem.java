package com.metamagic.ddd.entity;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.metamagic.ddd.exception.InvalidDataException;

@PersistenceCapable(table = "lineitems",detachable = "true")
public class LineItem {

	@Persistent(column = "lineitemid", customValueStrategy="uuid")
	private String lineitemid;
	
	@Persistent(column="cartId")
	private ShoppingCart shoppingCart;
	
	@Persistent(column = "itemid")
	private String itemId;
	
	@Persistent(column = "itemname")
	private String itemName;
	
	@Persistent(column = "price")
	private Double price;
	
	@Persistent(column = "quantity")
	private Integer quantity;

	@Persistent(column = "subtotal")
	private Double subTotal;
	
	@Persistent(column = "activestatus")	
	private boolean activeStatus;
	
	public LineItem(String itemId, String itemName, Double price, Integer quantity, ShoppingCart shoppingCart) throws InvalidDataException{
		this.setItemId(itemId);
		this.setItemName(itemName);
		this.setPrice(price);
		this.setQuantity(quantity);
		
		this.shoppingCart = shoppingCart;
		this.subTotal = price * quantity;
		this.activeStatus = true;
	}

	private void setItemId(String itemId) throws InvalidDataException{
		if(itemId == null)
			throw new InvalidDataException("Invalid itemId");
		
		this.itemId = itemId;
	}
	
	private void setItemName(String itemName) throws InvalidDataException{
		if(itemName == null)
			throw new InvalidDataException("Invalid itemName");
		
		this.itemName = itemName;
	}
	
	private void setPrice(Double price) throws InvalidDataException{
		if(price == null)
			throw new InvalidDataException("Invalid price");
		
		this.price = price;
	}
	
	private void setQuantity(Integer quantity) throws InvalidDataException{
		if(quantity == null)
			throw new InvalidDataException("Invalid quantity");
		
		this.quantity = quantity;
	}
	
	
	public String getLineitemid() {
		return lineitemid;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public String getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getSubTotal(){
		return subTotal;
	}
	
	public void deactivate(){
		this.activeStatus = false;
	}

	@Override
	public boolean equals(Object lineItem) {
		if(lineItem instanceof LineItem){
			return itemId.equals(((LineItem) lineItem).getItemId());
		}else{
			return false;
		}
		
	}
	
	@Override
	public int hashCode() {
		return itemId.hashCode();
	}

	@Override
	public String toString() {
		return "{ itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + ", quantity=" + quantity
				+ ", subTotal=" + subTotal+" }";
	}
	
	
	
}
