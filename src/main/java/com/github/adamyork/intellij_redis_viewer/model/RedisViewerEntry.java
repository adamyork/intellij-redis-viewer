package com.github.adamyork.intellij_redis_viewer.model;

import com.github.adamyork.intellij_redis_viewer.state.RedisViewerStateEntry;

@SuppressWarnings("WeakerAccess")
public class RedisViewerEntry {

    private final String name;
    private final String host;
    private final boolean connected;

    private RedisViewerEntry(final Builder builder) {
        name = builder.name;
        host = builder.host;
        connected = builder.connected;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public boolean isConnected() {
        return connected;
    }

    public static class Builder {

        private final String name;
        private final String host;
        private boolean connected;

        public Builder(final String name,
                       final String host) {
            this.name = name;
            this.host = host;
            this.connected = false;
        }

        public Builder connected(final boolean connected) {
            this.connected = connected;
            return this;
        }

        public RedisViewerEntry build() {
            return new RedisViewerEntry(this);
        }

    }

    public static RedisViewerEntry empty() {
        return new RedisViewerEntry.Builder("empty", "empty").build();
    }

    public static RedisViewerEntry from(final RedisViewerStateEntry stateEntry) {
        return new Builder(stateEntry.name, stateEntry.host).build();
    }

    @Override
    public String toString() {
        return name;
    }
}
