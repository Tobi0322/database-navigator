package com.dci.intellij.dbn.editor.data.state.sorting.action;

import com.dci.intellij.dbn.common.Icons;
import com.dci.intellij.dbn.editor.data.state.sorting.ui.DatasetSortingForm;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;


public class AddSortingColumnAction extends DumbAwareAction {
    private DatasetSortingForm sortingForm;

    public AddSortingColumnAction(DatasetSortingForm sortingForm) {
        super("Add sorting column ", null, Icons.DATASET_FILTER_CONDITION_NEW);
        this.sortingForm = sortingForm;
    }

    public void actionPerformed(AnActionEvent e) {
        sortingForm.addSortingColumn(null);
    }
}
