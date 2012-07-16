package com.dci.intellij.dbn.editor.data.record.ui;

import com.dci.intellij.dbn.common.locale.Formatter;
import com.dci.intellij.dbn.common.locale.options.RegionalSettings;
import com.dci.intellij.dbn.common.ui.UIForm;
import com.dci.intellij.dbn.common.ui.UIFormImpl;
import com.dci.intellij.dbn.data.editor.ui.BasicDataEditorComponent;
import com.dci.intellij.dbn.data.editor.ui.DataEditorComponent;
import com.dci.intellij.dbn.data.editor.ui.ListPopupValuesProvider;
import com.dci.intellij.dbn.data.editor.ui.TextFieldWithPopup;
import com.dci.intellij.dbn.data.editor.ui.TextFieldWithTextEditor;
import com.dci.intellij.dbn.data.type.BasicDataType;
import com.dci.intellij.dbn.data.type.DBDataType;
import com.dci.intellij.dbn.data.type.DBNativeDataType;
import com.dci.intellij.dbn.data.type.DataTypeDefinition;
import com.dci.intellij.dbn.data.value.LazyLoadedValue;
import com.dci.intellij.dbn.editor.data.options.DataEditorSettings;
import com.dci.intellij.dbn.editor.data.options.DataEditorValueListPopupSettings;
import com.dci.intellij.dbn.editor.data.ui.table.model.DatasetEditorColumnInfo;
import com.dci.intellij.dbn.editor.data.ui.table.model.DatasetEditorModelCell;
import com.dci.intellij.dbn.editor.data.ui.table.model.DatasetEditorModelRow;
import com.dci.intellij.dbn.object.DBColumn;
import com.intellij.openapi.project.Project;
import com.intellij.ui.DocumentAdapter;
import com.intellij.util.ui.UIUtil;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.List;

public class DatasetRecordEditorColumnForm extends UIFormImpl implements UIForm {
    private JLabel columnLabel;
    private JPanel valueFieldPanel;
    private JLabel dataTypeLabel;
    private JPanel mainPanel;

    private DatasetRecordEditorForm parentForm;
    private DatasetEditorModelCell cell;
    private DataEditorComponent editorComponent;

    private RegionalSettings regionalSettings;

    public DatasetRecordEditorColumnForm(DatasetRecordEditorForm parentForm, DatasetEditorModelCell cell) {
        this.parentForm = parentForm;
        final DatasetEditorColumnInfo columnInfo = cell.getColumnInfo();
        DBColumn column = columnInfo.getColumn();
        DBDataType dataType = column.getDataType();
        Project project = column.getProject();
        regionalSettings = RegionalSettings.getInstance(project);

        columnLabel.setIcon(column.getIcon());
        columnLabel.setText(column.getName());
        dataTypeLabel.setText(dataType.getQualifiedName());

        DBNativeDataType nativeDataType = dataType.getNativeDataType();
        DataTypeDefinition dataTypeDefinition = nativeDataType.getDataTypeDefinition();
        BasicDataType basicDataType = dataTypeDefinition.getBasicDataType();

        DataEditorSettings dataEditorSettings = DataEditorSettings.getInstance(project);

        long dataLength = dataType.getLength();

        if (basicDataType.is(BasicDataType.DATE_TIME, BasicDataType.LITERAL)) {
            TextFieldWithPopup textFieldWithPopup = new TextFieldWithPopup(project);

            textFieldWithPopup.setPreferredSize(new Dimension(200, -1));
            JTextField valueTextField = textFieldWithPopup.getTextField();
            valueTextField.getDocument().addDocumentListener(documentListener);
            valueTextField.addKeyListener(keyAdapter);
            valueTextField.addFocusListener(focusListener);

            if (cell.getRow().getModel().isEditable()) {
                if (basicDataType == BasicDataType.DATE_TIME) {
                    textFieldWithPopup.createCalendarPopup(false);
                }

                if (basicDataType == BasicDataType.LITERAL) {
                    if (dataLength > 20 && !column.isPrimaryKey() && !column.isForeignKey())
                    textFieldWithPopup.createTextAreaPopup(false);
                    DataEditorValueListPopupSettings valueListPopupSettings = dataEditorSettings.getValueListPopupSettings();

                    if (column.isForeignKey() || (dataLength <= valueListPopupSettings.getDataLengthThreshold() &&
                            (!column.isSinglePrimaryKey() || valueListPopupSettings.isActiveForPrimaryKeyColumns()))) {
                        ListPopupValuesProvider valuesProvider = new ListPopupValuesProvider() {
                            public List<String> getValues() {
                                return columnInfo.getPossibleValues();
                            }
                        };
                        textFieldWithPopup.createValuesListPopup(valuesProvider, false);
                    }
                }
            }
            editorComponent = textFieldWithPopup;
        } else if (basicDataType.is(BasicDataType.BLOB, BasicDataType.CLOB)) {
            editorComponent = new TextFieldWithTextEditor(project);
        } else {
            editorComponent = new BasicDataEditorComponent();
        }


        valueFieldPanel.add((Component) editorComponent, BorderLayout.CENTER);
        setCell(cell);
    }

    public JPanel getComponent() {
        return mainPanel;
    }

    public void setCell(DatasetEditorModelCell cell) {
        if (this.cell != null) updateUserValue(false);
        this.cell = cell;

        DatasetEditorModelRow row = cell.getRow();
        boolean editable = !row.isDeleted() && row.getModel().isEditable();
        editorComponent.setEnabled(editable);
        editorComponent.setUserValueHolder(cell);

        Formatter formatter = regionalSettings.getFormatter();
        if (cell.getUserValue() instanceof String) {
            String userValue = (String) cell.getUserValue();
            if (userValue.indexOf('\n') > -1) {
                userValue = userValue.replace('\n', ' ');
                editorComponent.setEditable(false);
            } else {
                editorComponent.setEditable(editable);
            }
            editorComponent.setText(userValue);
        } else {
            editable = editable && !(cell.getUserValue() instanceof LazyLoadedValue);
            editorComponent.setEditable(editable);
            String formattedUserValue = formatter.formatObject(cell.getUserValue());
            editorComponent.setText(formattedUserValue);
        }
        JTextField valueTextField = editorComponent.getTextField();
        valueTextField.setBackground(UIUtil.getTextFieldBackground());
    }

    public DatasetEditorModelCell getCell() {
        return cell;
    }

    protected int[] getMetrics(int[] metrics) {
        return new int[] {
            (int) Math.max(metrics[0], columnLabel.getPreferredSize().getWidth()),
            (int) Math.max(metrics[1], valueFieldPanel.getPreferredSize().getWidth())};
    }

    protected void adjustMetrics(int[] metrics) {
        columnLabel.setPreferredSize(new Dimension(metrics[0], columnLabel.getHeight()));
        valueFieldPanel.setPreferredSize(new Dimension(metrics[1], valueFieldPanel.getHeight()));
    }

    public JComponent getEditorComponent() {
        return editorComponent.getTextField();
    }


    public Object getEditorValue() throws ParseException {
        DBDataType dataType = cell.getColumnInfo().getDataType();
        Class clazz = dataType.getTypeClass();
        String textValue = editorComponent.getText().trim();
        if (textValue.length() > 0) {
            Object value = getFormatter().parseObject(clazz, textValue);
            return dataType.getNativeDataType().getDataTypeDefinition().convert(value);
        } else {
            return null;
        }
    }

    private void updateUserValue(boolean highlightError) {
        if (editorComponent != null) {
            JTextField valueTextField = editorComponent.getTextField();
            if (valueTextField.isEditable())  {
                try {
                    Object value = getEditorValue();
                    editorComponent.getUserValueHolder().updateUserValue(value, false);
                    valueTextField.setForeground(Color.BLACK);
                } catch (ParseException e1) {
                    if (highlightError) {
                        valueTextField.setForeground(Color.RED);
                    }

                    //DBDataType dataType = cell.getColumnInfo().getDataType();
                    //MessageUtil.showErrorDialog("Can not convert " + valueTextField.getText() + " to " + dataType.getName());
                }
            }
        }
    }

    private Formatter getFormatter() {
        Project project = cell.getRow().getModel().getDataset().getProject();
        return Formatter.getInstance(project);
    }

    private Project getProject() {
        return null;
    }

    /*********************************************************
     *                     Listeners                         *
     *********************************************************/
    DocumentListener documentListener = new DocumentAdapter() {
        @Override
        protected void textChanged(DocumentEvent documentEvent) {
            JTextField valueTextField = editorComponent.getTextField();
            valueTextField.setForeground(Color.BLACK);
        }
    };

    KeyListener keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (!e.isConsumed()) {
                if (e.getKeyCode() == 38) {//UP
                    parentForm.focusPreviousColumnPanel(DatasetRecordEditorColumnForm.this);
                    e.consume();
                } else if (e.getKeyCode() == 40) { // DOWN
                    parentForm.focusNextColumnPanel(DatasetRecordEditorColumnForm.this);
                    e.consume();
                }
            }
        }
    };


    boolean isError = false;
    FocusListener focusListener = new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            if (e.getOppositeComponent() != null) {
                JTextField valueTextField = editorComponent.getTextField();
                DataEditorSettings settings = cell.getRow().getModel().getSettings();
                if (settings.getGeneralSettings().getSelectContentOnCellEdit().value()) {
                    valueTextField.selectAll();
                }

                Rectangle rectangle = new Rectangle(mainPanel.getLocation(), mainPanel.getSize());
                parentForm.getColumnsPanel().scrollRectToVisible(rectangle);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            updateUserValue(true);
        }
    };

    public void dispose() {
        super.dispose();
        regionalSettings = null;
        parentForm = null;
        cell = null;
        editorComponent = null;


    }
}
