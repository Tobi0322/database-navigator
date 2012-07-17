package com.dci.intellij.dbn.connection.config.action;

import com.dci.intellij.dbn.common.Icons;
import com.dci.intellij.dbn.connection.ConnectionBundle;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.ui.ListUtil;

import javax.swing.JList;

public class MoveConnectionUpAction extends DumbAwareAction {
    private JList list;
    private ConnectionBundle connectionBundle;

    public MoveConnectionUpAction(JList list, ConnectionBundle connectionBundle) {
        super("Move selection up", null, Icons.ACTION_MOVE_UP);
        this.list = list;
        this.connectionBundle = connectionBundle;
    }

    public void actionPerformed(AnActionEvent e) {
        connectionBundle.setModified(true);
        ListUtil.moveSelectedItemsUp(list);
    }

    public void update(AnActionEvent e) {
        int length = list.getSelectedValues().length;
        boolean enabled = length > 0 && list.getMinSelectionIndex() > 0;
        e.getPresentation().setEnabled(enabled);
    }
}
