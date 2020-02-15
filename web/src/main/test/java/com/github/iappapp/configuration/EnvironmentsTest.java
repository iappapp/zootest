package com.github.iappapp.configuration;

import com.github.iappapp.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;

@Slf4j
public class EnvironmentsTest extends BaseTest {
    @Autowired
    private Environments environment;

    @Autowired
    private DbConfig dbConfig;

    @Autowired
    private Environment env;


    @Test
    public void getHome() {
        String userHome = environment.getHome();
        log.info("getHome userHome={}", userHome);
        String osVersion = environment.getOsVersion();
        log.info("osVersion osVersion={}", osVersion);
    }

    @Test
    @Repeat(10)
    @Timed(millis = 100L)
    public void testDbConfig() {
        log.info("testDbConfig dbConfig={}", dbConfig);
    }

    @Test
    @IfProfileValue(name = "os.version", value = "10.14.6")    // Test pass
    // @IfProfileValue(name = "os.version", value = "10.14.5") Test ignored.
    public void testIfProfileValue() {
        String osVersion = env.getProperty("os.version");
        log.info("testIfProfileValue osVersion={}", osVersion);
        String[] activeProfile = env.getActiveProfiles();
        log.info("activeProfile activeProfile={}", activeProfile);
    }
}
