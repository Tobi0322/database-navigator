package com.dci.intellij.dbn.editor.data.record.ui;

import com.dci.intellij.dbn.common.ui.DBNDialog;
import com.dci.intellij.dbn.editor.data.ui.table.model.DatasetEditorModelRow;
import org.jetbrains.annotations.Nullable;

import javax.swing.Action;
import javax.swing.JComponent;

public class DatasetRecordEditorDialog extends DBNDialog {
    private DatasetRecordEditorForm editorForm;

    public DatasetRecordEditorDialog(DatasetEditorModelRow row) {
        super(row.getModel().getDataset().getProject(), row.getModel().isEditable() ? "Edit Record" : "View Record", true);
        setModal(true);
        setResizable(true);
        editorForm = new DatasetRecordEditorForm(row);
        getCancelAction().putValue(Action.NAME, "Close");
        init();
    }

    protected String getDimensionServiceKey() {
        return "DBNavigator.RecordEditor";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return editorForm.getPreferredFocusComponent();
    }

    protected final Action[] createActions() {
        return new Action[]{
                getCancelAction(),
                getHelpAction()
        };
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();
    }

    @Nullable
    protected JComponent createCenterPanel() {
        return editorForm.getComponent();
    }

    @Override
    protected void dispose() {
        super.dispose();
        editorForm.dispose();
    }
}
