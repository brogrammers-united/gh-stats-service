package org.bgu.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author William Gentry
 */
public class GhWeeklyContributionStats {

	private final String startOfWeek;

	private final int additions;

	private final int deletions;

	private final int commits;

	public GhWeeklyContributionStats(JsonNode node) {
		this.startOfWeek = new SimpleDateFormat("MMMMM dd yyyy").format(new Date(node.get("w").asLong() * 1000L));
		this.additions = node.get("a").asInt();
		this.deletions = node.get("d").asInt();
		this.commits = node.get("c").asInt();
	}

	public String getStartOfWeek() {
		return startOfWeek;
	}


	public int getAdditions() {
		return additions;
	}


	public int getDeletions() {
		return deletions;
	}


	public int getCommits() {
		return commits;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GhWeeklyContributionStats that = (GhWeeklyContributionStats) o;
		return startOfWeek == that.startOfWeek &&
				additions == that.additions &&
				deletions == that.deletions &&
				commits == that.commits;
	}

	@Override
	public int hashCode() {
		return Objects.hash(startOfWeek, additions, deletions, commits);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", GhWeeklyContributionStats.class.getSimpleName() + "[", "]")
				.add("startOfWeek=" + startOfWeek)
				.add("additions=" + additions)
				.add("deletions=" + deletions)
				.add("commits=" + commits)
				.toString();
	}

	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyy MM dd").format(new Date(1537056000L)));
	}
}
