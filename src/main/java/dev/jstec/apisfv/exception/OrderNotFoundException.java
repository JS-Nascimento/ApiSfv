package dev.jstec.apisfv.exception;

public class OrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException() {
		super("Order no has exist!");
		
	}
	
	

}
