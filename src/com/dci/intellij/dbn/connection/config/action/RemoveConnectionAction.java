package com.dci.intellij.dbn.connection.config.action;

import com.dci.intellij.dbn.common.Icons;
import com.dci.intellij.dbn.connection.ConnectionManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.ui.ListUtil;

import javax.swing.JList;

public class RemoveConnectionAction extends DumbAwareAction {
    private ConnectionManager connectionManager;
    private JList list;

    public RemoveConnectionAction(JList list, ConnectionManager connectionManager) {
        super("Remove connection", null, Icons.ACTION_REMOVE);
        this.list = list;
        this.connectionManager = connectionManager;
    }

    public void actionPerformed(AnActionEvent e) {
        if (confirmDelete(list.getSelectedValues())) {
            connectionManager.setModified(true);
            ListUtil.removeSelectedItems(list);
/*
            int index = list.getMinSelectionIndex();
            connectionManager.deleteConnections(list.getSelectedValues());
            int size = list.getModel().getSize();
            if (size > 0) list.setSelectedIndex(Math.min(size - 1, index));
*/
        }
    }

    public boolean confirmDelete(Object[] connections) {
        /*SettingsBundle settings = manager.getSettingsBundle();
        boolean confirm = settings.getBoolean(SettingsBundle.KEY_CONFIRM_ACTION_DELETE, true);
        if (confirm) {
            String title = connections.length == 1 ? "Delete configuration" : "Delete configurations";
            String message = connections.length == 1 ?
                    "Are you sure you want to delete the cofiguration " + connections[0] + "?" :
                    "Are you sure you want to delete the selected configurations?";

            VcsShowConfirmationOption confirmationOption = new VcsShowConfirmationOptionImpl(null, null, null, null, null);
            boolean delete = ConfirmationDialog.requestForConfirmation(confirmationOption, manager.getProject(), message, title, CONNECTION_SETUP_QUESTION_DIALOG);
            if (VcsShowConfirmationOption.Value.DO_ACTION_SILENTLY == confirmationOption.getValue()) {
                settings.setBoolean(SettingsBundle.KEY_CONFIRM_ACTION_DELETE, false);
            }
            return delete;
        } */
        return true;
    }

    public void update(AnActionEvent e) {
        int length = list.getSelectedValues().length;
        Presentation presentation = e.getPresentation();
        presentation.setEnabled(length > 0);
        presentation.setText(length > 1 ? "Remove connections" : "Remove connection");
    }
}
