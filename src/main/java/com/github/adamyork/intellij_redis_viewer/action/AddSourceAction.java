package com.github.adamyork.intellij_redis_viewer.action;

import com.github.adamyork.intellij_redis_viewer.RedisViewerPopupFactory;
import com.github.adamyork.intellij_redis_viewer.model.RedisViewerEntry;
import com.github.adamyork.intellij_redis_viewer.model.RedisViewerListModel;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionListener;

@SuppressWarnings("WeakerAccess")
public class AddSourceAction extends AnAction {

    private final RedisViewerListModel model;

    public AddSourceAction(final RedisViewerListModel model) {
        super(IconLoader.getIcon("/icons/addActionIcon.png"));
        this.model = model;
    }

    @Override
    public void actionPerformed(@NotNull final AnActionEvent addSourceActionEvent) {
        final RedisViewerPopupFactory.RedisViewerPopupWrapper addSourceActionPopup =
                RedisViewerPopupFactory.getInstance().createAddSourcePopup();
        addSourceActionPopup.getPopup().showInBestPositionFor(addSourceActionEvent.getDataContext());
        addSourceActionPopup.getAddButton()
                .addActionListener(getAddSourceFromPopupActionListener(addSourceActionPopup));
    }

    public ActionListener getAddSourceFromPopupActionListener(final RedisViewerPopupFactory.RedisViewerPopupWrapper wrapper) {
        return addSourceFromPopupActionEvent -> {
            wrapper.getPopup().cancel();
            model.add(model.getSize(), new RedisViewerEntry.Builder(wrapper.getNameTextField().getText(),
                    wrapper.getConnectionStringTextField().getText())
                    .build());
        };
    }
}
