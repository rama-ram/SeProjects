package com.test.common;

public class ConfigException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9170694332607380021L;

	ConfigException(String propName) {
		System.out.println("pre-requesite config not set: " + propName);

	}
}
