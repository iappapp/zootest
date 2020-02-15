package com.github.iappapp.controller;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.github.iappapp.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
public class BookControllerTest extends BaseTest {
    @Autowired
    private WebApplicationContext context;

    private WebClient webClient;

    private WebDriver webDriver;

    @Before
    public void setup()  {
        webClient = MockMvcWebClientBuilder.webAppContextSetup(context).build();
        webDriver = MockMvcHtmlUnitDriverBuilder.webAppContextSetup(context)
                .build();

    }


    @Test
    public void testLocationList() throws Exception {
        Page page = webClient.getPage(URL + "/location/list");
        log.info("url=/location/list page={}", page.getWebResponse().getContentAsString());
    }

    @Test
    public void testView() throws Exception {
        Page page = webClient.getPage(URL + "/book/hello");
        log.info("url=/book/hello page={}", page.getWebResponse().getContentAsString());
    }

}
