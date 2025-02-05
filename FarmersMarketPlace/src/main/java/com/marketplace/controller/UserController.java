package com.marketplace.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.marketplace.pojos.Authentication;
import com.marketplace.pojos.Cart;
import com.marketplace.pojos.CartItem;
import com.marketplace.pojos.OrderDetails;
import com.marketplace.pojos.User;
import com.marketplace.service.EmailSenderService;
import com.marketplace.service.IUserService;
import com.marketplace.service.PdfExportService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService u_service;
	
	@Autowired
	private PdfExportService pdfService;

	@Autowired
	private EmailSenderService mailService;

	
	List<CartItem> items = null;
	Cart mycart = new Cart();
	User user = new User();

	@PostMapping("/register")
	public ResponseEntity<?> RegisterNewUser(@RequestBody User user) {
		u_service.Register(user);
		return new ResponseEntity<String>("Registration Successful..!!", HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> LoginUser(@RequestBody Authentication userID) {
		String email = userID.getEmail();
		String password = userID.getPassword();
		System.out.println(email + "   " + password);
		User u = null;
		try {
			u = u_service.Authenticate(email, password);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

		user = u;

		items = new ArrayList<CartItem>();
		return new ResponseEntity<User>(u, HttpStatus.OK);

	}

	@PostMapping("/addtocart/{productid}")
	public ResponseEntity<?> addToCart(@PathVariable int productid, @RequestBody Map<String, Integer> form) {
	    System.out.println("Product ID: " + productid);
	    Integer qty = form.get("qty");  // Extract quantity from request body.
	    if (qty == null || qty <= 0) {
	        return new ResponseEntity<>("Invalid quantity", HttpStatus.BAD_REQUEST);
	    }
	    CartItem product = u_service.AddToCart(productid, qty);
	    if (product == null) {
	        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	    }
	    items.add(product);
	    return new ResponseEntity<>(items, HttpStatus.OK);
	}


	@GetMapping("/checkout")
	public ResponseEntity<?> CheckOut() {
		System.out.println("checkout");
		double grandtotal = 0.0;

		for (CartItem item : items) {
			grandtotal += item.getAmount();
		}
		mycart.setItems(items);
		mycart.setGrandTotal(grandtotal);
		return new ResponseEntity<List<CartItem>>(items, HttpStatus.OK);
	}

	@PostMapping("/removefromcart/{productid}") // "productid" here is index of list
	public ResponseEntity<?> removeItem(@PathVariable int productid) {
		System.out.println("Removing item");
		items.remove(productid);
		return new ResponseEntity<List<CartItem>>(items, HttpStatus.OK);
	}

	@PostMapping("/placeorder")
	public ResponseEntity<?> PlaceOrder() {
	    try {
	        // Assuming `mycart` and `user` are coming from the session or request context
	        if (mycart == null || user == null) {
	            throw new IllegalArgumentException("Order details are missing.");
	        }

	        // Proceed with placing the order
	        u_service.PlaceOrder(mycart, user);

	        // Generate PDF
	        boolean pdfGenerated = pdfService.export(items);
	        if (!pdfGenerated) {
	            throw new IOException("Failed to generate PDF.");
	        }

	        // Send confirmation email with attachment
	        boolean emailSent = mailService.sendEmailWithAttachment(user.getEmail(),
	                "Please check the attached PDF for details. Have a good day!", 
	                "Your order is placed.", "receipt.pdf");

	        if (!emailSent) {
	            throw new MessagingException("Failed to send email.");
	        }

	        // Clear items and return success response
	        items.clear();
	        return new ResponseEntity<>("Order placed successfully. Receipt sent to your email.", HttpStatus.OK);

	    } catch (IllegalArgumentException | IOException | MessagingException ex) {
	        // Log the error and return error response
	        ex.printStackTrace();
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    } catch (Exception ex) {
	        // Handle unexpected errors
	        ex.printStackTrace();
	        return new ResponseEntity<>("An unexpected error occurred. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	@PostMapping("/orders") // "productid" here is index of list
	public ResponseEntity<?> Orders(@RequestParam int userId) {
		System.out.println("inside orders" + userId);
		List<OrderDetails> orders = u_service.getOrder(userId);
		return new ResponseEntity<List<OrderDetails>>(orders, HttpStatus.OK);
	}
}
