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
package com.metamagic.ddd.repo;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metamagic.ddd.entity.ShoppingCart;

/**
 * Repository for handling {@link ShoppingCart} implements {@link ShoppingCartRepositoryImpl}
 * @author ketan gote
 *
 */

@Repository
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository{

	@Autowired
	PersistenceManagerFactory pmf;

	private PersistenceManager pm() {
		return pmf.getPersistenceManager();
	}
	
	/**
	 * Persist shopping cart to DB
	 * @param products
	 */	
	public void save(ShoppingCart products) {
		PersistenceManager pm = pm();
		pm.setDetachAllOnCommit(true);
		pm.makePersistent(products);
		pm.close();
	}
	
	/**
	 * Fetch shopping cart based on cartid
	 * @param cartId {@link String}
	 * @return {@link ShoppingCart}
	 * @throws Exception
	 */
	@Override
	public ShoppingCart fetchShoppingCart(String cartId) throws Exception {
		PersistenceManager pm = pm();
		try
		{
			Query query = (pm.newQuery(ShoppingCart.class));
			query.setFilter("this.cartId==:cartId");
			@SuppressWarnings("unchecked")
			Collection<ShoppingCart> carts = (Collection<ShoppingCart>) query.execute(cartId, true);
			if(!carts.isEmpty()){
				return pm.detachCopy(carts.iterator().next());	
			}else{
				throw new Exception("Unable to retrive data");
			}
		}
		catch (Exception e)
		{
			throw new Exception("Unable to retrive data");
		}
		
	}
}
