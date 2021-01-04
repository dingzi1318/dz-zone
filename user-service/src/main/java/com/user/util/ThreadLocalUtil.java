package com.user.util;

import java.util.List;

public final class ThreadLocalUtil {

    private static ThreadLocal<List<Long>> userIdThreadLocalHolder = new ThreadLocal();

    public static ThreadLocal<List<Long>> getUserIdThreadLocalHolder() {

        return userIdThreadLocalHolder;
    }

}
