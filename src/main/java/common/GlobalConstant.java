package common;

import java.time.Duration;

public class GlobalConstant {
    public static final String ADMIN_URL = "https://demo.guru99.com/test/login.html";
    public static final Duration IMPLICIT_TIMEOUT = Duration.ofSeconds(20);
    public static final Duration EXPLICIT_SHORT_TIMEOUT = Duration.ofSeconds(15);
    public static final Duration EXPLICIT_LONG_TIMEOUT = Duration.ofSeconds(30);
    public static final Duration POLLING_TIMEOUT = Duration.ofMillis(300);
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String ADMIN_USERNAME = "dung@test.com";
    public static final String ADMIN_PASSWORD = "admin";
}
