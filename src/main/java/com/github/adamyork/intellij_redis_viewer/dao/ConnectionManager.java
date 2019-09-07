package com.github.adamyork.intellij_redis_viewer.dao;

import com.github.adamyork.intellij_redis_viewer.model.RedisViewerListModel;
import com.intellij.ui.table.JBTable;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;
import java.util.Optional;
import java.util.stream.IntStream;

@SuppressWarnings("WeakerAccess")
public class ConnectionManager {

    private final JBTable keysTable;

    public ConnectionManager(final RedisViewerListModel model,
                             final JBTable keysTable) {
        model.addListDataListener(getDataListener());
        this.keysTable = keysTable;
    }

    public ListDataListener getDataListener() {
        return new ListDataListener() {
            @Override
            public void intervalAdded(final ListDataEvent addedEvent) {
                System.out.println("connect item addedEvent");
                keysTable.removeAll();
            }

            @Override
            public void intervalRemoved(final ListDataEvent removedEvent) {
                System.out.println("connect item removedEvent");
                keysTable.removeAll();
            }

            @Override
            public void contentsChanged(final ListDataEvent changeEvent) {
                System.out.println("connect item changeEvent ");
                final RedisViewerListModel casted = (RedisViewerListModel) changeEvent.getSource();
                final boolean isConnectionEvent = IntStream.range(0, casted.getSize() - 1)
                        .mapToObj(index -> casted.getElementAt(index).isConnected())
                        .anyMatch(bool -> bool);
                Optional.of(isConnectionEvent)
                        .filter(bool -> bool)
                        .map(bool -> {
                            final DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{
                                    {"some", Math.random()}, {"any", Math.random()}, {"even", Math.random()},
                                    {"text", Math.random()}, {"and", Math.random()}, {"text", Math.random()}},
                                    new Object[]{"Key", "Ttl"});
                            keysTable.setModel(tableModel);
                            return true;
                        })
                        .orElseGet(() -> {
                            final DefaultTableModel tableModelCasted = ((DefaultTableModel) keysTable.getModel());
                            return removeAllRowsFromTableModel(tableModelCasted);
                        });
            }
        };
    }

    private boolean removeAllRowsFromTableModel(final DefaultTableModel defaultTableModel) {
        IntStream.range(0, defaultTableModel.getRowCount())
                .forEach(index -> defaultTableModel.removeRow(0));
        return false;
    }
}
