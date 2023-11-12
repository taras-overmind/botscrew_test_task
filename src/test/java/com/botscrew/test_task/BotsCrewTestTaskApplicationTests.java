package com.botscrew.test_task;

import com.botscrew.test_task.repository.LectorRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BotsCrewTestTaskApplicationTests {
	@Autowired
	LectorRepository lectorRepository;

	@Test
	@Transactional
	void contextLoads() {
	}

}
