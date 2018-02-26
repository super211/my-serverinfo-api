package com.boot;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boot.model.Serverinfo;
import com.boot.repository.ServerinfoRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServerinfoRepositoryIntegrationTest {

	@Autowired
	private ServerinfoRepository serverinfoRepository;

	@Test
	public void testFindAll() {
		List<Serverinfo> wrecks = serverinfoRepository.findAll();
		assertThat(wrecks.size(), is(greaterThanOrEqualTo(0)));
	}
	
}
