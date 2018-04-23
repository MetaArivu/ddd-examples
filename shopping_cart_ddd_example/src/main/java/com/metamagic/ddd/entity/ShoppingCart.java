/**

 * Copyright (c) 2018 Ketan Gote
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.

*/
package com.metamagic.ddd.entity;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.metamagic.ddd.customjackson.ShoppingCartDeserializer;
import com.metamagic.ddd.exception.InvalidDataException;

/**
 * 
 * @author ketan gote
 * Aggregate entity for shopping cart, it holds user  {@link LineItem} added aswell. This entity has one to many relationship with {@link LineItem}
 */
@JsonDeserialize(using = ShoppingCartDeserializer.class)
@PersistenceCapable(table="shoppingcart", detachable="true")
public class ShoppingCart {

	@PrimaryKey
	@Persistent(column = "cartid", customValueStrategy = "uuid")
	private String cartId;

	@Persistent(column = "userid")
	private String userid;

	@Persistent(column = "status")
	private String status;
		
	@Persistent(mappedBy = "shoppingCart", defaultFetchGroup = "true")
	private Set<LineItem> lineItems;

	/**
	 *
	 * @param userId {@link String}
	 * @throws InvalidDataException
	 * 
	 */
	public ShoppingCart(String userId) throws InvalidDataException{
		this.setUserId(userId);
		this.open();
		this.initCart();
	}
	
	/**
	 * 
	 * @param userId {@link String}
	 * @param cartLineItems {@link Set<LineItem>}
	 * @throws InvalidDataException
	 */
	public ShoppingCart(String userId, Set<LineItem> cartLineItems) throws InvalidDataException{
		this(userId);
		this.lineItems = cartLineItems;
	}
	
	
	/**
	 * Added line item to user cart
	 * @param itemId
	 * @param itemName
	 * @param price
	 * @param quantity
	 * @throws InvalidDataException
	 */
	public void addLineItem(String itemId, String itemName, Double price, Integer quantity) throws InvalidDataException{
		LineItem lineItem = new LineItem(itemId, itemName, price, quantity, this);
		this.lineItems.add(lineItem);
	}
	
	/**
	 * Removes line item from user cart
	 * @param itemId
	 */
	public void removeItem(String itemId){
		for (LineItem lineItem : lineItems) {
			if(lineItem.getItemId().equals(itemId)){
				lineItem.deactivate();
			}
		}
		
	}
	
	/**
	 * Sets userid if it is valid otherwise throws exception
	 * @param userId {@link String}
	 * @throws InvalidDataException
	 */
	private void setUserId(String userId) throws InvalidDataException{
		if(userId == null || userId.length() == 0)
			throw new InvalidDataException("Invalid User");
		
		this.userid = userId;
	}

	/**
	 * Initialize the empty cart
	 */
	private void initCart(){
		this.lineItems = new HashSet<LineItem>();
	}
	
	/**
	 * Maps cart status as open
	 */
	public void open(){
		this.status = "OPEN";
	}
	
	/**
	 * Maps cart status as close
	 */	
	public void close(){
		this.status = "CLOSE";
	}
	
	/**
	 * 
	 * @return cartid {@link String}
	 */
	public String getCartId() {
		return cartId;
	}

	/**
	 * 
	 * @return userid {@link String}
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * 
	 * @return cart status {@link String}
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @return cart lineitems {@link Set<LineItem>}
	 */
	public Set<LineItem> getLineItems() {
		return lineItems;
	}
	
	

	@Override
	public boolean equals(Object shoppingCart) {
		if(shoppingCart instanceof ShoppingCart){
			return this.cartId.equals(((ShoppingCart)shoppingCart).getCartId());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.cartId.hashCode();
	}

	@Override
	public String toString() {
		return "ShoppingCart [cartId=" + cartId + ", userid=" + userid + ", status=" + status + ", lineItems="
				+ lineItems + "]";
	}
	
	
	
	
	
}
