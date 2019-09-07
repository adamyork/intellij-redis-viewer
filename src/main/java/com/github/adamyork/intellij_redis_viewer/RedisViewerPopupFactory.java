package com.github.adamyork.intellij_redis_viewer;

import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

@SuppressWarnings("WeakerAccess")
public class RedisViewerPopupFactory {

    private RedisViewerPopupFactory() {
    }

    public RedisViewerPopupWrapper createAddSourcePopup() {
        final RedisViewerPopupPanel panel = new RedisViewerPopupPanel();
        final JBPopup popup = JBPopupFactory.getInstance()
                .createComponentPopupBuilder(panel, panel)
                .setFocusable(true)
                .setRequestFocus(true)
                .setShowBorder(true)
                .setResizable(true)
                .setTitle("Add a data source")
                .setModalContext(false)
                .setShowBorder(true)
                .setMovable(true)
                .createPopup();
        return new RedisViewerPopupWrapper(popup,
                panel.getNameTextField(),
                panel.getConnectionStringTextField(),
                panel.getPasswordTextField(),
                panel.getAddButton());
    }

    public static class RedisViewerPopupWrapper {

        private final JBPopup popup;
        private final JTextField nameTextField;
        private final JTextField connectionStringTextField;
        private final JTextField passwordTextField;
        private final JButton addButton;

        private RedisViewerPopupWrapper(final JBPopup popup,
                                        final JTextField nameTextField,
                                        final JTextField connectionStringTextField,
                                        final JTextField passwordTextField,
                                        final JButton addButton) {
            this.popup = popup;
            this.nameTextField = nameTextField;
            this.connectionStringTextField = connectionStringTextField;
            this.passwordTextField = passwordTextField;
            this.addButton = addButton;
        }

        public JBPopup getPopup() {
            return popup;
        }

        public JTextField getNameTextField() {
            return nameTextField;
        }

        public JTextField getConnectionStringTextField() {
            return connectionStringTextField;
        }

        public JTextField getPasswordTextField() {
            return passwordTextField;
        }

        public JButton getAddButton() {
            return addButton;
        }
    }

    private static class RedisViewerPopupPanel extends JPanel {

        private final JTextField nameTextField;
        private final JTextField connectionStringTextField;
        private final JTextField passwordTextField;
        private final JButton addButton;

        private RedisViewerPopupPanel() {
            super();
            setPreferredSize(new Dimension(200, 200));
            setEnabled(true);
            final JLabel nameLabel = new JLabel("Name");
            nameTextField = new JTextField();
            nameTextField.setPreferredSize(new Dimension(200, 32));
            nameTextField.setEditable(true);
            nameTextField.setEnabled(true);
            final JLabel connectionStringLabel = new JLabel("Connection string");
            connectionStringTextField = new JTextField();
            connectionStringTextField.setPreferredSize(new Dimension(200, 32));
            connectionStringTextField.setEditable(true);
            final JLabel passwordLabel = new JLabel("Password (Optional)");
            passwordTextField = new JTextField();
            passwordTextField.setPreferredSize(new Dimension(200, 32));
            passwordTextField.setEditable(true);
            addButton = new JButton("Add");
            add(nameLabel);
            add(nameTextField);
            add(connectionStringLabel);
            add(connectionStringTextField);
            add(passwordLabel);
            add(passwordTextField);
            add(addButton);
        }

        public JTextField getNameTextField() {
            return nameTextField;
        }

        public JTextField getConnectionStringTextField() {
            return connectionStringTextField;
        }

        public JTextField getPasswordTextField() {
            return passwordTextField;
        }

        public JButton getAddButton() {
            return addButton;
        }
    }

    private static RedisViewerPopupFactory instance;

    public static RedisViewerPopupFactory getInstance() {
        return Optional.ofNullable(instance)
                .map(inst -> instance)
                .orElseGet(() -> {
                    instance = new RedisViewerPopupFactory();
                    return instance;
                });
    }
}
