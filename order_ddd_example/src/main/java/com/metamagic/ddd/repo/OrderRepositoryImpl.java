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

import com.metamagic.ddd.entity.Order;

/**
 * Repository for handling order implements {@link OrderRepository}
 * @author ketangote
 *
 */

@Repository
public class OrderRepositoryImpl implements OrderRepository{

	@Autowired
	PersistenceManagerFactory pmf;

	private PersistenceManager pm() {
		return pmf.getPersistenceManager();
	}

	/**
	 * Saves order
	 */
	@Override
	public void saveOrder(Order order) {
		PersistenceManager pm = pm();
		pm.setDetachAllOnCommit(true);
		pm.makePersistent(order);
		pm.close();
	}
	
	/**
	 * Fetch order based on order id
	 */
	@Override
	public Order findByOrderId(String orderId) throws Exception {
		PersistenceManager pm = pm();
		try
		{
			Query query = (pm.newQuery(Order.class));
			query.setFilter("this.orderId==:orderId");
			
			@SuppressWarnings("unchecked")
			Collection<Order> carts = (Collection<Order>) query.execute(orderId, true);
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
