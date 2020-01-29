package net.kzn.shoppingbackend.dao;

import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

public interface UserDAO {
	boolean addUser(User user);
	boolean addAddress(Address address);
	boolean addCart(Cart card);

}
