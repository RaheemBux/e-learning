package com.test.studentv.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DevProfileCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String profile = conditionContext.getEnvironment().getProperty("spring.profiles.active");
        //System.out.println("Profile ==> "+profile);
        return profile.equalsIgnoreCase("dev");
    }
}
