package com.github.adamyork.intellij_redis_viewer.model;

import javax.swing.*;
import java.util.Enumeration;
import java.util.Vector;

public class RedisViewerListModel extends AbstractListModel<RedisViewerEntry> {

    private final Vector<RedisViewerEntry> delegate;

    public RedisViewerListModel() {
        delegate = new Vector<>();
    }

    public RedisViewerListModel(final Vector<RedisViewerEntry> vector) {
        delegate = vector;
    }

    @Override
    public int getSize() {
        return delegate.size();
    }

    @Override
    public RedisViewerEntry getElementAt(final int index) {
        return delegate.get(index);
    }

    public void add(final int index, final RedisViewerEntry element) {
        delegate.insertElementAt(element, index);
        fireIntervalAdded(this, index, index);
    }

    public Enumeration<RedisViewerEntry> elements() {
        return delegate.elements();
    }

    public RedisViewerEntry remove(final int index) {
        final RedisViewerEntry entry = delegate.elementAt(index);
        delegate.removeElementAt(index);
        fireIntervalRemoved(this, index, index);
        return entry;
    }

    public void set(int index, final RedisViewerEntry element) {
        delegate.setElementAt(element, index);
        fireContentsChanged(this, index, index);
    }

}
