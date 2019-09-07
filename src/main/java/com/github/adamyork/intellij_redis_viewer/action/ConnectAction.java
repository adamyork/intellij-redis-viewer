package com.github.adamyork.intellij_redis_viewer.action;

import com.github.adamyork.intellij_redis_viewer.model.RedisViewerEntry;
import com.github.adamyork.intellij_redis_viewer.model.RedisViewerListModel;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

public class ConnectAction extends AnAction {

    private final JBList<RedisViewerEntry> list;
    private final RedisViewerListModel model;

    public ConnectAction(final JBList<RedisViewerEntry> list,
                         final RedisViewerListModel model) {
        super(IconLoader.getIcon("/icons/connectActionIcon.png"));
        this.list = list;
        this.model = model;
    }

    @Override
    public void update(@NotNull final AnActionEvent connectActionUpdateEvent) {
        final boolean aListItemIsSelected = !list.isSelectionEmpty();
        final boolean everySourceIsDisconnected = IntStream.range(0, model.getSize())
                .noneMatch(value -> model.getElementAt(value).isConnected());
        connectActionUpdateEvent.getPresentation()
                .setEnabled(aListItemIsSelected && everySourceIsDisconnected);
    }

    @Override
    public void actionPerformed(@NotNull final AnActionEvent connectActionPerformedEvent) {
        final RedisViewerEntry target = model.getElementAt(list.getSelectedIndex());
        final RedisViewerEntry selected = new RedisViewerEntry.Builder(target.getName(), target.getHost())
                .connected(true)
                .build();
        model.set(list.getSelectedIndex(), selected);
    }

}
