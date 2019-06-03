package org.bgu.model.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.bgu.model.GhRepositoryContributionStatsEntry;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author William Gentry
 */
public class GhRepositoryContributionStatsEntryDeserializer extends StdDeserializer<GhRepositoryContributionStatsEntry> {

	public GhRepositoryContributionStatsEntryDeserializer() {
		this(null);
	}

	public GhRepositoryContributionStatsEntryDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public GhRepositoryContributionStatsEntry deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode root = p.getCodec().readTree(p);
		if (root.isArray()) {
			System.err.println("Root JsonNode is an array");
			Iterator<JsonNode> rootNodeIterator = root.iterator();
			rootNodeIterator.forEachRemaining(System.err::println);
		} else {
			System.err.println("Root JsonNode is not an array");
			final int totalContributions = root.get("total").asInt();
			final JsonNode userInfoNode = root.path("author");
			final JsonNode weeklyContributionsNode = root.path("weeks");
			return new GhRepositoryContributionStatsEntry(userInfoNode, totalContributions, weeklyContributionsNode);
		}
		return null;
	}
}
