package org.bgu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.bgu.model.json.GhRepositoryContributionStatsEntryDeserializer;

import java.util.*;

/**
 * @author William Gentry
 */
@JsonDeserialize(using = GhRepositoryContributionStatsEntryDeserializer.class)
public class GhRepositoryContributionStatsEntry {

	private final GhUserContributionResource contributor;

	@JsonProperty("weekly_contribution_stats")
	private final List<GhWeeklyContributionStats> weeklyContributionStats = new ArrayList<>();

	public GhRepositoryContributionStatsEntry(JsonNode userInfoNode, int totalCommitsByContributor, JsonNode weeklyContributionArrayNode) {
		this.contributor = new GhUserContributionResource(userInfoNode, totalCommitsByContributor);
		Iterator<JsonNode> weeklyIterator = weeklyContributionArrayNode.iterator();
		weeklyIterator.forEachRemaining(node -> {
			if (!(node.get("a").asInt() == 0 && node.get("d").asInt() == 0 && node.get("c").asInt() == 0)) {
				weeklyContributionStats.add(new GhWeeklyContributionStats(node));
			}
		});
	}

	public GhUserContributionResource getContributor() {
		return contributor;
	}

	public List<GhWeeklyContributionStats> getWeeklyContributionStats() {
		return weeklyContributionStats;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GhRepositoryContributionStatsEntry that = (GhRepositoryContributionStatsEntry) o;
		return Objects.equals(contributor, that.contributor) &&
				Objects.equals(weeklyContributionStats, that.weeklyContributionStats);
	}

	@Override
	public int hashCode() {
		return Objects.hash(contributor, weeklyContributionStats);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", GhRepositoryContributionStatsEntry.class.getSimpleName() + "[", "]")
				.add("contributor=" + contributor)
				.add("weeklyContributionStats=" + weeklyContributionStats)
				.toString();
	}
}
