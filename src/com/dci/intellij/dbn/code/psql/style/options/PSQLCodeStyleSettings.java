package com.dci.intellij.dbn.code.psql.style.options;

import com.dci.intellij.dbn.code.common.style.options.CodeStyleCaseSettings;
import com.dci.intellij.dbn.code.common.style.options.CodeStyleCustomSettings;
import com.dci.intellij.dbn.code.common.style.options.CodeStyleFormattingSettings;
import com.dci.intellij.dbn.code.common.style.options.ProjectCodeStyleSettings;
import com.dci.intellij.dbn.code.psql.style.options.ui.PSQLCodeStyleSettingsEditorForm;
import com.dci.intellij.dbn.common.Icons;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PSQLCodeStyleSettings extends CodeStyleCustomSettings<PSQLCodeStyleSettingsEditorForm>{

    public static PSQLCodeStyleSettings getInstance(Project project) {
        return ProjectCodeStyleSettings.getInstance(project).getPSQLCodeStyleSettings();    
    }

    @Nullable
    public Icon getIcon() {
        return Icons.PLSQL_FILE;
    }

    public String getDisplayName() {
        return "PL/SQL";
    }

    protected CodeStyleCaseSettings createCaseSettings() {
        return new PSQLCodeStyleCaseSettings();
    }

    protected CodeStyleFormattingSettings createAttributeSettings() {
        return new PSQLCodeStyleFormattingSettings();
    }

    /*********************************************************
    *                     Configuration                     *
    *********************************************************/
    public PSQLCodeStyleSettingsEditorForm createConfigurationEditor() {
        return new PSQLCodeStyleSettingsEditorForm(this);
    }


}