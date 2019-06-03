package org.bgu.service;

import org.bgu.model.GhRepositoryContributionStatsEntry;
import org.bgu.model.interfaces.BguUserDetails;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author William Gentry
 */
@Service
public class AsyncGhStatsService {

	private final WebClient webClient;

	public AsyncGhStatsService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
	}

	public Mono<List<GhRepositoryContributionStatsEntry>> getRepositoryStatistics(BguUserDetails userDetails, String owner, String repository) {
		return webClient.get()
					.uri("/{owner}/{repository}/stats/contributors", owner, repository)
					.header(HttpHeaders.AUTHORIZATION, "token " + userDetails.getGithubOAuthToken())
					.exchange()
					.flatMap(clientResponse -> clientResponse.bodyToMono(new ParameterizedTypeReference<List<GhRepositoryContributionStatsEntry>>() {}));
	}
}
