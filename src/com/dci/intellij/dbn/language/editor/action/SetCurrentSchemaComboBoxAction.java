package com.dci.intellij.dbn.language.editor.action;

import com.dci.intellij.dbn.common.ui.DBNComboBoxAction;
import com.dci.intellij.dbn.common.util.ActionUtil;
import com.dci.intellij.dbn.common.util.NamingUtil;
import com.dci.intellij.dbn.connection.ConnectionHandler;
import com.dci.intellij.dbn.connection.mapping.FileConnectionMappingManager;
import com.dci.intellij.dbn.object.DBSchema;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;
import javax.swing.JComponent;

public class SetCurrentSchemaComboBoxAction extends DBNComboBoxAction {
    private static final String NAME = "Schema";
    private String place;

    @NotNull
    protected DefaultActionGroup createPopupActionGroup(JComponent component) {
        Project project = ActionUtil.getProject(component);
        ConnectionHandler activeConnection = FileConnectionMappingManager.getInstance(project).lookupActiveConnectionForEditor(place);
        DefaultActionGroup actionGroup = new DefaultActionGroup();
        if (activeConnection != null && !activeConnection.isDisposed()) {
            for (DBSchema schema : activeConnection.getObjectBundle().getSchemas()){
                actionGroup.add(new SetCurrentSchemaAction(schema));
            }
        }
        return actionGroup;
    }

    public synchronized void update(AnActionEvent e) {
        place = e.getPlace();
        Project project = ActionUtil.getProject(e);
        String text = NAME;

        Icon icon = null;
        boolean visible = false;
        boolean enabled = true;

        if (project != null) {
            FileConnectionMappingManager mappingManager = FileConnectionMappingManager.getInstance(project);
            ConnectionHandler activeConnection = mappingManager.lookupActiveConnectionForEditor(place);
            visible = activeConnection != null && !activeConnection.isVirtual();
            if (visible) {
                DBSchema schema = mappingManager.lookupCurrentSchemaForEditor(place);
                if (schema != null) {
                    text = NamingUtil.enhanceUnderscoresForDisplay(schema.getName());
                    icon = schema.getIcon();
                    enabled = true;
                }
            }
        }

        Presentation presentation = e.getPresentation();
        presentation.setText(text);
        presentation.setIcon(icon);
        presentation.setVisible(visible);
        presentation.setEnabled(enabled);
    }
 }
