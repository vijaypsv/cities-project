package com.cities.exceptions;

public class CityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CityNotFoundException(Long id) {
		super("Could not find city with id: " + id);
	}
}
