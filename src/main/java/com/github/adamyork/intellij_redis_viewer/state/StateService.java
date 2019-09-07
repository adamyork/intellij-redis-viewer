package com.github.adamyork.intellij_redis_viewer.state;

import com.github.adamyork.intellij_redis_viewer.model.RedisViewerEntry;
import com.github.adamyork.intellij_redis_viewer.model.RedisViewerListModel;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@State(name = "RedisViewerState", storages = {@Storage("RedisViewer.xml")})
public class StateService implements PersistentStateComponent<RedisViewerState> {

    private RedisViewerState redisViewerState;

    @Nullable
    @Override
    public RedisViewerState getState() {
        return redisViewerState;
    }

    @Override
    public void loadState(@NotNull final RedisViewerState state) {
        redisViewerState = state;
    }

    public Void setRedisViewerState(final RedisViewerState redisViewerState) {
        this.redisViewerState = redisViewerState;
        return null;
    }

    public RedisViewerListModel stateToListModel() {
        return Optional.of(getState() != null)
                .filter(bool -> bool)
                .map(bool -> {
                    final List<RedisViewerStateEntry> entries = getState()
                            .getEntries()
                            .orElse(new ArrayList<>());
                    final Vector<RedisViewerEntry> collect = IntStream.range(0, entries.size())
                            .sequential()
                            .mapToObj(index -> RedisViewerEntry.from(entries.get(index)))
                            .collect(Collectors.toCollection(Vector::new));
                    return new RedisViewerListModel(collect);
                })
                .orElse(new RedisViewerListModel());
    }
}
