package com.github.adamyork.intellij_redis_viewer.action;

import com.github.adamyork.intellij_redis_viewer.model.RedisViewerEntry;
import com.github.adamyork.intellij_redis_viewer.model.RedisViewerListModel;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class RemoveSourceAction extends AnAction {

    private final JBList<RedisViewerEntry> list;
    private final RedisViewerListModel model;

    public RemoveSourceAction(final JBList<RedisViewerEntry> list,
                              final RedisViewerListModel model) {
        super(IconLoader.getIcon("/icons/removeActionIcon.png"));
        this.model = model;
        this.list = list;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void actionPerformed(@NotNull final AnActionEvent anActionEvent) {
        Optional.of(list.getSelectedIndex() != -1)
                .filter(bool -> bool)
                .map(bool -> model.remove(list.getSelectedIndex()))
                .orElse(RedisViewerEntry.empty());
    }

}
