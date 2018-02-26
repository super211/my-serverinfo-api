package com.boot;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.boot.controller.HomeController;
import com.boot.model.Homeview;

public class AppTest {

	@Test
    public void testApp() {
		HomeController hc = new HomeController();
		Homeview result = hc.home();
        assertEquals( result.getPageTitle() , "Server Info, home page!" );
	}
}
