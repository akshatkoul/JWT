package com.wings.may24.security;

public class SecurityConstants {
    public static final String AUTH_HEADER = "Authorization";
    public static final String LOGIN_URL = "/login";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ROLE_PREFIX = "ROLE_";
    public enum ALLOWED_ROLES {
        ADMIN,
        USER
    }

    public enum ALLOWED_PERMISSION{
        ADD_PERMISSION,
        UPDATE_PERMISSION,
        DELETE_PERMISSION
    }
}
