package com.dci.intellij.dbn.connection.action;

import com.dci.intellij.dbn.common.Icons;
import com.dci.intellij.dbn.connection.ConnectionHandler;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.DumbAwareAction;

public class OpenSQLConsoleAction extends DumbAwareAction {
    private ConnectionHandler connectionHandler;

    public OpenSQLConsoleAction(ConnectionHandler connectionHandler) {
        super("Open SQL console", null, Icons.FILE_SQL_CONSOLE);
        this.connectionHandler = connectionHandler;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(connectionHandler.getProject());
        fileEditorManager.openFile(connectionHandler.getSQLConsoleFile(), true);
    }
}
