package net.kzn.shoppingbackend.dao;

import java.util.List;

import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

public interface UserDAO {
	//add an user
	boolean addUser(User user);
	User getByEmail(String email);
	
	//add an address
	boolean addAddress(Address address);
	Address getBillingAddress(User user);
	List<Address> listShippingAddresses(User user);
	
	//update a cart
	boolean updateCart(Cart card);
	
	

}
