package com.github.adamyork.intellij_redis_viewer.state;

import com.github.adamyork.intellij_redis_viewer.model.RedisViewerEntry;

@SuppressWarnings("WeakerAccess")
public class RedisViewerStateEntry {

    public String name;
    public String host;

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public String getHost() {
        return host;
    }

    public static RedisViewerStateEntry from(final RedisViewerEntry entry) {
        final RedisViewerStateEntry instance = new RedisViewerStateEntry();
        instance.name = entry.getName();
        instance.host = entry.getHost();
        return instance;
    }

}
