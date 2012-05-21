package com.dci.intellij.dbn.common.editor;

import com.dci.intellij.dbn.common.thread.SimpleLaterInvocator;
import com.dci.intellij.dbn.common.util.DocumentUtil;
import com.dci.intellij.dbn.common.util.EditorUtil;
import com.dci.intellij.dbn.vfs.DatabaseEditableObjectFile;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class BasicTextEditorProvider implements FileEditorProvider, ApplicationComponent, DumbAware {
    public static final Key<String> DBN_FILE_EDITOR_PROVIDER = Key.create("DBN_FILE_EDITOR_PROVIDER");

    @NotNull
    public FileEditorState readState(@NotNull Element sourceElement, @NotNull final Project project, @NotNull final VirtualFile virtualFile) {
        BasicTextEditorState editorState = new BasicTextEditorState();
        Document document = DocumentUtil.getDocument(virtualFile);
        editorState.readState(sourceElement, project, document);
        return editorState;
    }

    public void writeState(@NotNull FileEditorState state, @NotNull Project project, @NotNull Element targetElement) {
        if (state instanceof BasicTextEditorState) {
            BasicTextEditorState editorState = (BasicTextEditorState) state;
            editorState.writeState(targetElement, project);
        }
    }

    protected void updateTabIcon(final DatabaseEditableObjectFile databaseFile, final BasicTextEditor textEditor, final Icon icon) {
        new SimpleLaterInvocator() {
            public void run() {
                EditorUtil.setEditorIcon(databaseFile, textEditor, icon);
            }
        }.start();
    }

    /*********************************************************
     *                ApplicationComponent                   *
     *********************************************************/
    public void initComponent() {
    }

    public void disposeComponent() {

    }
}
