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



package com.metamagic.ddd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ddd.dto.AddItems;
import com.metamagic.ddd.dto.ResponseBean;
import com.metamagic.ddd.entity.ShoppingCart;
import com.metamagic.ddd.service.ShoppingCartService;
import com.metamagic.ddd.service.ShoppingCartServiceImpl;

/**
 * 
 * @author Ketan
 * Rest API for accessing application.
 * @see createCart(..)
 * @see addItem(..)
 * @see removeItem(..)
 */
@RestController
@RequestMapping(value = "/shoppingcart")
public class ShoppingCartAPI {

	/**
	 * {@link ShoppingCartService}
	 */
	
	@Autowired 
	private ShoppingCartService shoppingCartService;
	
	/**
	 * 
	 * @param {@link {@link ShoppingCart}
	 * @return {@link ResponseEntity} with {@link ResponseBean} and {@link HttpStatus}
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> createCart(@RequestBody ShoppingCart cart)
	{
		
		try {
			shoppingCartService.createCart(cart);
			ResponseBean response = new ResponseBean(true, "Cart created successfully");
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		} catch (Exception e) {
			ResponseBean response = new ResponseBean(false, e.getMessage());
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		}
		
	}
	
	/**
	 * 
	 * @param {@link {@link AddItems}
	 * @return {@link ResponseEntity} with {@link ResponseBean} and {@link HttpStatus}
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/additem", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> addItem(@RequestBody AddItems addItem)
	{
	
		try {
			shoppingCartService.addItem(addItem);
			ResponseBean response = new ResponseBean(true, "Item added successfully");
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		} catch (Exception e) {
			ResponseBean response = new ResponseBean(false, e.getMessage());
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		}

	}
	
	
	/**
	 * 
	 * @param cartId {@link String}
	 * @param itemId {@link String}
	 * @return {@link ResponseEntity} with {@link ResponseBean} and {@link HttpStatus}
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeietm/{cartId}/{itemId}", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> removeItem(@PathVariable("cartId") String cartId, @PathVariable("itemId") String itemId) throws Exception
	{
		try {
			shoppingCartService.removeItem(cartId, itemId);
			ResponseBean response = new ResponseBean(true, "Item removed successfully");
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		} catch (Exception e) {
			ResponseBean response = new ResponseBean(false, e.getMessage());
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		}
	}

}
