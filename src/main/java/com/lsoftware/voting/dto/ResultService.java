/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.dto;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;


import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import com.lsoftware.voting.model.Vote;
import com.lsoftware.voting.shared.service.ServiceResultsMethods;

/**
 * The Class ResultService.
 * 
 * @author Luis Espinosa
 */
@Service
public class ResultService implements ServiceResultsMethods<ResultDTO> {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ResultService.class);
	
	/** The Constant TOTAL. */
	private static final String TOTAL = "total";

	/** The mongo template. */
	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	@Override
	public List<ResultDTO> getResults() {
		LOG.info("method: getResults");
		Aggregation agg = newAggregation(
				match(Criteria.where("_id").exists(true)),
				group("candidateId").count().as(TOTAL),
				project(TOTAL).and("candidateId").previousOperation(),
				sort(Sort.Direction.DESC, TOTAL)
			);

			AggregationResults<ResultDTO> groupResults 
				= mongoTemplate.aggregate(agg, Vote.class, ResultDTO.class);
			List<ResultDTO> result = groupResults.getMappedResults();
			
			return result;
	}

}
