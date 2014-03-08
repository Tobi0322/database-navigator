package com.dci.intellij.dbn.editor.data.ui.table.renderer;

import com.dci.intellij.dbn.data.editor.color.DataGridTextAttributes;
import com.dci.intellij.dbn.data.ui.table.basic.BasicTable;
import com.dci.intellij.dbn.data.ui.table.basic.BasicTableCellRenderer;
import com.dci.intellij.dbn.editor.data.model.DatasetEditorColumnInfo;
import com.dci.intellij.dbn.editor.data.model.DatasetEditorModelCell;
import com.dci.intellij.dbn.editor.data.model.DatasetEditorModelRow;
import com.dci.intellij.dbn.editor.data.ui.table.DatasetEditorTable;
import com.dci.intellij.dbn.object.DBColumn;
import com.intellij.openapi.project.Project;
import com.intellij.ui.SimpleTextAttributes;

import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class DatasetEditorTableCellRenderer extends BasicTableCellRenderer {
    private static final Border CELL_ERROR_BORDER = new LineBorder(Color.RED, 1);

    public DatasetEditorTableCellRenderer(Project project) {
        super(project);
    }

    protected void customizeCellRenderer(JTable table, Object value, boolean isSelected, boolean hasFocus, int rowIndex, int columnIndex) {
        DataGridTextAttributes configTextAttributes = ((BasicTable) table).getConfigTextAttributes();

        DatasetEditorModelCell cell = (DatasetEditorModelCell) value;
        DatasetEditorTable datasetEditorTable = (DatasetEditorTable) table;



        if (cell != null && !cell.isDisposed()) {
            DatasetEditorModelRow row = cell.getRow();
            DatasetEditorColumnInfo columnInfo = cell.getColumnInfo();
            boolean isLoading = datasetEditorTable.isLoading();
            boolean isInserting = datasetEditorTable.isInserting();

            boolean isDeletedRow = row.isDeleted();
            boolean isInsertRow = row.isInsert();
            boolean isCaretRow = table.getCellSelectionEnabled() && table.getSelectedRow() == rowIndex && table.getSelectedRowCount() == 1;


            //DataModelCell cellAtMouseLocation = datasetEditorTable.getCellAtMouseLocation();

            DBColumn column = columnInfo.getColumn();
            if (cell.getUserValue() != null) {
                SimpleTextAttributes textAttributes =
                        isSelected ? configTextAttributes.getSelection() :
                        isLoading || !datasetEditorTable.getDataset().getConnectionHandler().isConnected() ? configTextAttributes.getLoadingData() :
                        isInserting && !isInsertRow ? configTextAttributes.getReadonlyData() :
                        isDeletedRow ? configTextAttributes.getDeletedData() :
                        isPrimaryKey(column) ? (isCaretRow ? configTextAttributes.getPrimaryKeyAtCaretRow() : configTextAttributes.getPrimaryKey()) :
                        isForeignKey(column) ? (isCaretRow ? configTextAttributes.getForeignKeyAtCaretRow() : configTextAttributes.getForeignKey()) :
                        cell.isModified() ? configTextAttributes.getModifiedData() :
                        cell.isLobValue() ? configTextAttributes.getReadonlyData() :
                                            configTextAttributes.getPlainData();

                writeUserValue(cell, textAttributes, configTextAttributes);

                
            } /*else {
                    append("[null]", SimpleTextAttributes.GRAYED_ATTRIBUTES);
                }*/

            //updateBorder(cell, datasetEditorTable);
            


            if (!isSelected) {
                if (isLoading || !datasetEditorTable.getDataset().getConnectionHandler().isConnected()) {
                    setBackground(configTextAttributes.getLoadingData().getBgColor());
                } else {

                    if (isCaretRow) {
                        setBackground(configTextAttributes.getCaretRowBgColor());

                    } else if (isDeletedRow) {
                        setBackground(configTextAttributes.getDeletedData().getBgColor());

                    } else if (cell.hasError()) {
                        setBorder(CELL_ERROR_BORDER);
                        setBackground(configTextAttributes.getErrorData().getBgColor());

                    } else if (isInserting && !isInsertRow) {
                        setBackground(configTextAttributes.getReadonlyData().getBgColor());

                    } else if (isInsertRow) {
                        setBackground(configTextAttributes.getPlainData().getBgColor());

                    } else if (isPrimaryKey(column)) {
                        setBackground(configTextAttributes.getPrimaryKey().getBgColor());

                    } else if (isForeignKey(column)) {
                        setBackground(configTextAttributes.getForeignKey().getBgColor());

                    } else {
                        setBackground(configTextAttributes.getPlainData().getBgColor());
                    }
                }
            }
        }
    }

    private boolean isForeignKey(DBColumn column) {
        return column != null && column.isForeignKey();
    }

    private boolean isPrimaryKey(DBColumn column) {
        return column != null && column.isPrimaryKey();
    }
}
                                                                