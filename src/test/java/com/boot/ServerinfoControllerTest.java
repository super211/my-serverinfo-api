package com.boot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.boot.controller.ServerinfoController;
import com.boot.model.Serverinfo;
import com.boot.repository.ServerinfoRepository;

public class ServerinfoControllerTest {
	@InjectMocks
	private ServerinfoController sc;

	@Mock
	private ServerinfoRepository serverinfoRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testServerinfoGet() {
    	Serverinfo sw = new Serverinfo();
    	sw.setId(1l);
		when(serverinfoRepository.findOne(1l)).thenReturn(sw);

		Serverinfo wreck = sc.get(1L);

		verify(serverinfoRepository).findOne(1l);		

//		assertEquals(1l, wreck.getId().longValue());	
		assertThat(wreck.getId(), is(1l));
	}

}
