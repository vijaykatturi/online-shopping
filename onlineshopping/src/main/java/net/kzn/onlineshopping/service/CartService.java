package net.kzn.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kzn.onlineshopping.model.UserModel;
import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;
import net.kzn.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ProductDAO productDAO;
	
	//returns the cart of the user who has logged in	
	private Cart getCart() {
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	//returns the entire cart lines
	public List<CartLine> getCartLine(){
		
		return cartLineDAO.list(this.getCart().getId());
		
	}

	 public String manageCartLine(int cartLineId, int count) {
		//fetch the cart line
		 CartLine cartLine = cartLineDAO.get(cartLineId);
		 if(cartLine == null) {
			 return "result=error";
		 }
		 else {
			 
			 Product product = cartLine.getProduct();
			 double oldTotal = cartLine.getTotal();
			
			 //cheking if the product quantity is available
			 if(product.getQuantity() < count) {
				 return "result=unavailable";
			 }
			 
			 cartLine.setProductCount(count);
			 cartLine.setBuyingPrice(product.getUnitPrice());
			 
			 cartLine.setTotal(product.getUnitPrice() * count);
			 
			 cartLineDAO.update(cartLine);
			 
			 Cart cart = this.getCart();
			 cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());			 
			 cartLineDAO.updateCart(cart);
			 
			 return "result=updated";
		 }
		 
	 }

	public String deleteCartLine(int cartLineId) {
		//fetch the cartline
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine == null) {
			return "result=error";
		}
		else {
			//update the cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);
			
			//remove the cartline
			cartLineDAO.delete(cartLine);
			
			return "result=deleted";
			
		}
		
	}

	public String addCartLine(int productId) {
		
		String response = null;
		
		Cart cart = this.getCart();//gets the cart(ie,currently logged in user's cart) from the session
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if(cartLine == null) { //i.e, this product is not there in the cartline
			//add a new cartline
			cartLine = new CartLine();
			
			//fetch the product
			Product product = productDAO.get(productId);
			
			cartLine.setCartId(cart.getId());			
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
		}
		else{//i.e,this  product is already there in the cartline
			
			//check if the cartline has been reached the maximum count
			if(cartLine.getProductCount() < 3) {
				response = this.manageCartLine(cartLine.getId(),cartLine.getProductCount() + 1);
			}
			else {
				response = "result=maximum";
			}
			
		}
		
		return response;		
	}	

}
