package org.bgu;

import org.bgu.config.properties.WebClientProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GhStatsServiceApplicationTests {

	@Autowired
	private WebClientProperties webClientProperties;

	@Test
	public void contextLoads() {
		System.err.println(webClientProperties.getUrl());
	}

}
