package net.kzn.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.kzn.shoppingbackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		//check whether file has been selected
		if(product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			System.out.println("FOR NO IMAGE FILE");
			errors.rejectValue("file", null,"Please select an image file to upload");
			return;
		}
		if(!(product.getFile().getContentType().equals("image/jpeg")||
			 product.getFile().getContentType().equals("image/png")||
			 product.getFile().getContentType().equals("image/gif")
			)){
			errors.rejectValue("file", null, "Please use only image file for upload");
			return;
		}
				
	}

}
