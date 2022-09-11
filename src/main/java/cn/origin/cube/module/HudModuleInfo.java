package cn.origin.cube.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface HudModuleInfo {
    String name();

    String descriptions();

    Category category();

    int defaultKeyBind() default 0;

    boolean defaultEnable() default false;

    float x();

    float y();

    float width() default 0.0f;

    float height() default 0.0f;
}
