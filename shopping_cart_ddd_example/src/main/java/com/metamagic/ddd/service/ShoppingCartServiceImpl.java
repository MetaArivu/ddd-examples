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
package com.metamagic.ddd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ddd.dto.AddItems;
import com.metamagic.ddd.entity.ShoppingCart;
import com.metamagic.ddd.repo.ShoppingCartRepository;

/**
 * Domain Service which specifically focus on domain logic of shopping cart
 * @author ketan gote
 *
 */

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Autowired
	private ShoppingCartRepository shoppingCartRepo;
	
	/**
	 * Creates the shopping cart
	 * @param cart {@link ShoppingCart}
	 */	
	@Override
	public void createCart(ShoppingCart cart) {
		 shoppingCartRepo.save(cart);
	}
	
	/**
	 * Adds item to shopping cart 
	 * @param addItems {@link AddItems}
	 * @throws Exception
	 */
	@Override
	public void addItem(AddItems addItems) throws Exception {
		ShoppingCart cart = shoppingCartRepo.fetchShoppingCart(addItems.getCartId());
		cart.addLineItem(addItems.getItemId(), addItems.getItemName(), addItems.getPrice(), addItems.getQuantity());
		shoppingCartRepo.save(cart);
	}
	 
	/**
	 * Removes item for given cart
	 * @param cartId {@link String}
	 * @param itemId {@link String}
	 * @throws Exception
	 */	
	@Override
	public void removeItem(String cartId, String itemId) throws Exception {
		ShoppingCart cart = shoppingCartRepo.fetchShoppingCart(cartId);
		cart.removeItem(itemId);
		shoppingCartRepo.save(cart);
	}
	
}
