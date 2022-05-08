/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class ResultDTO.
 * 
 * @author Luis Espinosa
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4825378463877146220L;
	
	/** The candidate id. */
	private int candidateId;
	
	/** The total. */
	private long total;
	
}
