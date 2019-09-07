package com.github.adamyork.intellij_redis_viewer.action;

import com.github.adamyork.intellij_redis_viewer.model.RedisViewerEntry;
import com.github.adamyork.intellij_redis_viewer.model.RedisViewerListModel;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

public class DisconnectAction extends AnAction {

    private final JBList<RedisViewerEntry> list;
    private final RedisViewerListModel model;

    public DisconnectAction(final JBList<RedisViewerEntry> list,
                            final RedisViewerListModel model) {
        super(IconLoader.getIcon("/icons/disconnectActionIcon.png"));
        this.list = list;
        this.model = model;
        this.getTemplatePresentation().setEnabled(false);
    }

    @Override
    public void update(@NotNull final AnActionEvent disconnectActionUpdateEvent) {
        final boolean aListItemIsSelected = !list.isSelectionEmpty();
        final boolean anySourceIsConnected = IntStream.range(0, model.getSize())
                .anyMatch(value -> model.getElementAt(value).isConnected());
        disconnectActionUpdateEvent.getPresentation()
                .setEnabled(aListItemIsSelected && anySourceIsConnected);
    }

    @Override
    public void actionPerformed(@NotNull final AnActionEvent disconnectActionPerformedEvent) {
        IntStream.range(0, model.getSize())
                .forEach(value -> {
                    final RedisViewerEntry target = model.getElementAt(value);
                    final RedisViewerEntry deselected = new RedisViewerEntry
                            .Builder(target.getName(), target.getHost())
                            .connected(false)
                            .build();
                    model.set(value, deselected);
                });
    }

}
