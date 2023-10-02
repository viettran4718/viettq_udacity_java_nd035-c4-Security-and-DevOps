package com.example.demo.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "security")
@Data
public class SecurityProperties {

	private JWTProperties jwt;
	private String tokenPrefix;
	private List<String> allowedPublicApis;

}
