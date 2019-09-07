package com.github.adamyork.intellij_redis_viewer.state;

import com.github.adamyork.intellij_redis_viewer.model.RedisViewerEntry;
import com.github.adamyork.intellij_redis_viewer.model.RedisViewerListModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public class RedisViewerState {

    public List<RedisViewerStateEntry> entries;

    @SuppressWarnings("unused")
    public RedisViewerState() {
    }

    public RedisViewerState(final List<RedisViewerStateEntry> names) {
        this.entries = names;
    }

    public Optional<List<RedisViewerStateEntry>> getEntries() {
        return Optional.ofNullable(entries);
    }

    public static RedisViewerState from(final RedisViewerListModel model) {
        final List<RedisViewerEntry> entriesFromModel = new ArrayList<>(Collections.list(model.elements()));
        final List<RedisViewerStateEntry> stateEntriesFromList = entriesFromModel.stream()
                .map(RedisViewerStateEntry::from)
                .collect(Collectors.toList());
        return new RedisViewerState(stateEntriesFromList);
    }
}
