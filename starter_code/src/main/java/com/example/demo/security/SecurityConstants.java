package com.example.demo.security;

public class SecurityConstants {
    public static final String SECRET = "qYCp8K071Eo4BTAs8xQFR3YL_nVmhKJQhiBWErR-MVzl7EoH";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String[] PUBLIC_URL = {"/api/user/create", "/h2-ui/**"};
}
