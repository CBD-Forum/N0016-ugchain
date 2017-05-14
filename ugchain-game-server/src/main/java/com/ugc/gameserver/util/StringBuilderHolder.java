package com.ugc.gameserver.util;

/**
 * Created by yuanshichao on 2016/11/17.
 */
public class StringBuilderHolder {

    private final StringBuilder sb;

    public StringBuilderHolder(int capacity) {
        sb = new StringBuilder(capacity);
    }

    public StringBuilder resetAndGet() {
        sb.setLength(0);
        return sb;
    }
}
