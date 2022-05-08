/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.shared.service;

import java.util.List;

/**
 * The Interface ServiceResultsMethods.
 * 
 * @author Luis Espinosa
 *
 * @param <T> the generic type
 */
public interface ServiceResultsMethods<T> {
	
	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	List<T> getResults();
	

}
