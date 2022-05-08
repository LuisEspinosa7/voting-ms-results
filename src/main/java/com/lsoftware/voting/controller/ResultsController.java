/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lsoftware.voting.dto.ResultDTO;
import com.lsoftware.voting.dto.ResultService;
import com.lsoftware.voting.shared.api.ApiCustomResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * The Class ResultsController.
 * 
 * @author Luis Espinosa
 */
@RestController
@RequestMapping("/api/v1/results")
public class ResultsController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ResultsController.class);

	/** The vote service. */
	private ResultService resultService;
	
	/**
	 * Instantiates a new vote controller.
	 *
	 * @param resultService the result service
	 */
	public ResultsController(ResultService resultService) {
		this.resultService = resultService;
	}
	
	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	@Operation(summary = "Ask for the results")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Ask for the results", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiCustomResponse.class)) }) })
	@GetMapping
	public ResponseEntity<ApiCustomResponse> getResults() {
		LOG.info("method: getResults");

		List<ResultDTO> results = resultService.getResults();
		ApiCustomResponse response = new ApiCustomResponse.ApiResponseBuilder(200).message("Results obtained")
				.data(results).build();

		return ResponseEntity.ok(response);
	}
	
}
