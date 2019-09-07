package com.github.adamyork.intellij_redis_viewer.view;

import com.github.adamyork.intellij_redis_viewer.model.RedisViewerEntry;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.components.JBList;
import com.intellij.ui.table.JBTable;

public class RedisViewSplitter extends JBSplitter {

    public RedisViewSplitter(final ActionToolbar toolBar,
                             final JBList<RedisViewerEntry> testList,
                             final JBTable keysTable) {
        super(true);
        final SimpleToolWindowPanel panelTop = new SimpleToolWindowPanel(true, false);
        panelTop.setToolbar(toolBar.getComponent());
        panelTop.add(ScrollPaneFactory.createScrollPane(testList));
        setFirstComponent(panelTop);
        final SimpleToolWindowPanel panelBottom = new SimpleToolWindowPanel(true, false);
        panelBottom.add(ScrollPaneFactory.createScrollPane(keysTable));
        setFirstComponent(panelTop);
        setSecondComponent(panelBottom);
    }

}
