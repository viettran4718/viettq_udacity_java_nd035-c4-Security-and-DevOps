package com.example.demo.security;

import lombok.Data;

@Data
public class JWTProperties {
    private String header;
    private String secretKey;
    private long expiration;
}
