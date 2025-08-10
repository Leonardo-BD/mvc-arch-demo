package com.arch.tests.mvc;

import com.arch.tests.mvc.config.FlywayDisableConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest
@Import(FlywayDisableConfig.class)
class MvcApplicationTests {

	@Test
	void contextLoads() {
	}

}
