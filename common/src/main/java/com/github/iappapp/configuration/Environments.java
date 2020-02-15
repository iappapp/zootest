package com.github.iappapp.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
public class Environments {
    @Value("${user.home}")
    private String home;

    @Value("${os.version}")
    private String osVersion;
}
