package com.seitenbau.testing.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Mark a test as ignore. Either use TestRecorderRule, or better use
 * the {@link com.seitenbau.testing.runner.SBTestRunner}
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestIgnore
{
    String[] value();

    boolean fixed = false;
}
