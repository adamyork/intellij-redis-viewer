package com.github.adamyork.intellij_redis_viewer;

import com.github.adamyork.intellij_redis_viewer.dao.ConnectionManager;
import com.github.adamyork.intellij_redis_viewer.model.RedisViewerEntry;
import com.github.adamyork.intellij_redis_viewer.model.RedisViewerListModel;
import com.github.adamyork.intellij_redis_viewer.state.RedisViewerState;
import com.github.adamyork.intellij_redis_viewer.state.StateService;
import com.github.adamyork.intellij_redis_viewer.view.RedisViewSplitter;
import com.github.adamyork.intellij_redis_viewer.view.RedisViewerActionToolbarFactory;
import com.intellij.ide.AppLifecycleListener;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBList;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.table.JBTable;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.messages.MessageHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class RedisToolWindowFactory implements ToolWindowFactory {

    private final StateService stateService;
    private ConnectionManager connectionManager;

    public RedisToolWindowFactory() {
        stateService = ServiceManager.getService(StateService.class);
    }

    @Override
    public void createToolWindowContent(@NotNull final Project project, @NotNull final ToolWindow toolWindow) {
        final RedisViewerListModel model = stateService.stateToListModel();
        final JBTable keysTable = new JBTable();
        connectionManager = new ConnectionManager(model, keysTable);
        final JBList<RedisViewerEntry> testList = new JBList<>(model);
        testList.setCellRenderer(new RedisViewerListCellRenderer());
        final ActionToolbar toolBar = RedisViewerActionToolbarFactory.getInstance()
                .createActionToolbar(testList, model);
        final RedisViewSplitter splitter = new RedisViewSplitter(toolBar, testList, keysTable);
        final ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        final Content content = contentFactory.createContent(splitter, "", false);
        toolWindow.getContentManager().addContent(content);
        final MessageBusConnection connection = project.getMessageBus().connect();
        connection.setDefaultHandler(getMessageHandler(model));
        connection.subscribe(AppLifecycleListener.TOPIC);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private MessageHandler getMessageHandler(final RedisViewerListModel model) {
        return (event, params) -> {
            Optional.of(event.getName().equals("appClosing"))
                    .filter(bool -> bool)
                    .map(bool -> stateService.setRedisViewerState(RedisViewerState.from(model)))
                    .orElse(null);
        };
    }


}
