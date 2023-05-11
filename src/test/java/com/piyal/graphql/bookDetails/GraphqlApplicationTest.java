package com.piyal.graphql.bookDetails;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GraphqlApplicationTest {
	
	@Test
	void contextLoads() {
		GraphqlApplication.main(new String[]{});
		assertTrue(true); // to ensure the test passes
	}
}