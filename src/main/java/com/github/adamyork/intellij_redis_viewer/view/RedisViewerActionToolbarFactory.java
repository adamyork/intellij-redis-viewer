package com.github.adamyork.intellij_redis_viewer.view;

import com.github.adamyork.intellij_redis_viewer.action.AddSourceAction;
import com.github.adamyork.intellij_redis_viewer.action.ConnectAction;
import com.github.adamyork.intellij_redis_viewer.action.DisconnectAction;
import com.github.adamyork.intellij_redis_viewer.action.RemoveSourceAction;
import com.github.adamyork.intellij_redis_viewer.model.RedisViewerEntry;
import com.github.adamyork.intellij_redis_viewer.model.RedisViewerListModel;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.ui.components.JBList;

import java.util.Optional;

public class RedisViewerActionToolbarFactory {

    private RedisViewerActionToolbarFactory() {
    }

    public ActionToolbar createActionToolbar(final JBList<RedisViewerEntry> testList,
                                             final RedisViewerListModel model) {
        final DefaultActionGroup actionGroup = new DefaultActionGroup();
        final ConnectAction connectAction = new ConnectAction(testList, model);
        actionGroup.add(new AddSourceAction(model));
        actionGroup.add(new RemoveSourceAction(testList, model));
        actionGroup.add(connectAction);
        actionGroup.add(new DisconnectAction(testList, model));
        return ActionManager.getInstance()
                .createActionToolbar("RedisViewer", actionGroup, true);
    }

    private static RedisViewerActionToolbarFactory instance;

    public static RedisViewerActionToolbarFactory getInstance() {
        return Optional.ofNullable(instance)
                .map(inst -> instance)
                .orElseGet(() -> {
                    instance = new RedisViewerActionToolbarFactory();
                    return instance;
                });
    }

}
