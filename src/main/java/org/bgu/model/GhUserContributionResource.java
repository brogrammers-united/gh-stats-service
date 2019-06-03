package org.bgu.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author William Gentry
 */
public class GhUserContributionResource {

	private final String username;

	private final int contributions;

	public GhUserContributionResource(JsonNode userInfoNode, int contributions) {
		this.username = userInfoNode.get("login").asText();
		this.contributions = contributions;
	}

	public String getUsername() {
		return username;
	}

	public int getContributions() {
		return contributions;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GhUserContributionResource that = (GhUserContributionResource) o;
		return contributions == that.contributions &&
				Objects.equals(username, that.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, contributions);
	}
}
