package com.github.adamyork.intellij_redis_viewer;

import com.github.adamyork.intellij_redis_viewer.model.RedisViewerEntry;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import sun.swing.DefaultLookup;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("WeakerAccess")
public class RedisViewerListCellRenderer extends JPanel
        implements ListCellRenderer<RedisViewerEntry>, Serializable {

    private static final Border SAFE_NO_FOCUS_BORDER = JBUI.Borders.empty(1);
    private static final Border DEFAULT_NO_FOCUS_BORDER = JBUI.Borders.empty(1);
    protected static Border noFocusBorder = DEFAULT_NO_FOCUS_BORDER;
    private final JLabel label;

    public RedisViewerListCellRenderer() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        label = new JLabel();
        add(label);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    @Override
    public Component getListCellRendererComponent(final JList<? extends RedisViewerEntry> list,
                                                  final RedisViewerEntry value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        //this.setComponentOrientation(list.getComponentOrientation());
        //this.setPreferredSize(list.getPreferredSize());
        label.setText(value.getName());
        //label.setHorizontalAlignment(SwingConstants.LEFT);
        // label.setHorizontalTextPosition(SwingConstants.LEFT);
        //label.setComponentOrientation(list.getComponentOrientation());

        setComponentOrientation(list.getComponentOrientation());

        Color bg = null;
        Color fg = null;

        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

            bg = DefaultLookup.getColor(this, ui, "List.dropCellBackground");
            fg = DefaultLookup.getColor(this, ui, "List.dropCellForeground");

            isSelected = true;
        }

        if (isSelected) {
            if (value.isConnected()) {
                setBackground(JBColor.GREEN);
            } else {
                setBackground(bg == null ? list.getSelectionBackground() : bg);
            }
            setForeground(fg == null ? list.getSelectionForeground() : fg);
        } else {
            if (value.isConnected()) {
                setBackground(JBColor.GREEN);
            } else {
                setBackground(list.getBackground());
            }
            setForeground(list.getForeground());
        }

        setEnabled(list.isEnabled());
        setFont(list.getFont());

        Border border = null;
        if (cellHasFocus) {
            if (isSelected) {
                border = DefaultLookup.getBorder(this, ui, "List.focusSelectedCellHighlightBorder");
            }
            if (border == null) {
                border = DefaultLookup.getBorder(this, ui, "List.focusCellHighlightBorder");
            }
        } else {
            border = getNoFocusBorder();
        }
        setBorder(border);

        return this;
    }

    private Border getNoFocusBorder() {
        Border border = DefaultLookup.getBorder(this, ui, "List.cellNoFocusBorder");
        if (System.getSecurityManager() != null) {
            if (border != null) return border;
            return SAFE_NO_FOCUS_BORDER;
        } else {
            if (border != null &&
                    (noFocusBorder == null ||
                            noFocusBorder == DEFAULT_NO_FOCUS_BORDER)) {
                return border;
            }
            return noFocusBorder;
        }
    }

}
