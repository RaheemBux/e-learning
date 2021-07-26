package com.test.studentv.security;

import com.test.studentv.entity.UserEntity;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserEntity user) {
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                null,
                null,
                null,

                user.getPassword(),
                null,
                true,
                null
        );
    }

}
