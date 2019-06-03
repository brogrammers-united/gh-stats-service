package org.bgu.web;

import org.bgu.config.annotation.CurrentUser;
import org.bgu.model.GhRepositoryContributionStatsEntry;
import org.bgu.model.interfaces.BguUserDetails;
import org.bgu.service.AsyncGhStatsService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author William Gentry
 */
@RestController
public class AsyncGhStatsController {

	private final AsyncGhStatsService asyncGhStatsService;

	public AsyncGhStatsController(AsyncGhStatsService asyncGhStatsService) {
		this.asyncGhStatsService = asyncGhStatsService;
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Mono<List<GhRepositoryContributionStatsEntry>> getRepositoryContributorStatistics(@CurrentUser BguUserDetails details, @RequestParam("owner") String owner, @RequestParam("repository") String repository) {
		return asyncGhStatsService.getRepositoryStatistics(details, owner, repository);
	}
}
