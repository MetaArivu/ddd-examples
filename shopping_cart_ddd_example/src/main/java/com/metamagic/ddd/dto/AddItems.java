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

package com.metamagic.ddd.dto;

/**
 * 
 * @author ketan gote
 * Additem is DTO which is used in additem Rest endpoint.
 */
public class AddItems {

	private String cartId;
	
	private String itemId;
	
	private String itemName;
	
	private Double price;
	
	private Integer quantity;
	
	/**
	 * 
	 * @param cartId {@link String}
	 * @param itemId {@link String}
	 * @param itemName {@link String}
	 * @param price {@link String}
	 * @param quantity {@link String}
	 * 
	 */
	public AddItems(String cartId, String itemId, String itemName, Double price, Integer quantity){
		this.cartId = cartId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}

	/**
	 * 
	 * @return cartId {@link String}
	 * 
	 */
	public String getCartId() {
		return cartId;
	}

	/**
	 * 
	 * @return cartId {@link String}
	 * 
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * 
	 * @return itemName {@link String}
	 * 
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 
	 * @return price {@link Double}
	 * 
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 
	 * @return quantity {@link Integer}
	 * 
	 */
	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AddItems){
			return this.itemId.equals(((AddItems)obj).getItemId());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.itemId.hashCode();
	}
	
	@Override
	public String toString() {
		return "AddItems [cartId=" + cartId + ", itemId=" + itemId + ", itemName=" + itemName + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	
	
}
