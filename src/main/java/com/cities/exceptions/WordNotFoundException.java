package com.cities.exceptions;

public class WordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WordNotFoundException(Long id) {
		super("Could not find word with id: " + id);
	}
}
