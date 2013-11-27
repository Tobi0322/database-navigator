package com.dci.intellij.dbn.execution.method.result.ui;

import com.dci.intellij.dbn.common.ui.tree.DBNTree;
import com.dci.intellij.dbn.common.util.TextAttributesUtil;
import com.dci.intellij.dbn.data.editor.color.DataGridTextAttributesKeys;
import com.dci.intellij.dbn.data.type.DBDataType;
import com.dci.intellij.dbn.execution.method.ArgumentValue;
import com.dci.intellij.dbn.object.DBArgument;
import com.dci.intellij.dbn.object.DBMethod;
import com.dci.intellij.dbn.object.DBTypeAttribute;
import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.util.ui.UIUtil;

import javax.swing.JTree;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.List;

public class ArgumentValuesTree extends DBNTree{
    private MethodExecutionResultForm parentForm;

    public ArgumentValuesTree(MethodExecutionResultForm parentForm, List<ArgumentValue> inputArgumentValues, List<ArgumentValue> outputArgumentValues) {
        super(new ArgumentValuesTreeModel(parentForm.getMethod(), inputArgumentValues, outputArgumentValues));
        this.parentForm = parentForm;
        setCellRenderer(new CellRenderer());
        Color bgColor = TextAttributesUtil.getSimpleTextAttributes(DataGridTextAttributesKeys.PLAIN_DATA).getBgColor();
        setBackground(bgColor == null ? UIUtil.getTableBackground() : bgColor);

        addMouseListener(mouseAdapter);
    }

    private final MouseAdapter mouseAdapter = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                ArgumentValuesTreeNode treeNode = (ArgumentValuesTreeNode) getLastSelectedPathComponent();
                if (treeNode != null) {
                    Object userValue = treeNode.getUserValue();
                    if (userValue instanceof ArgumentValue) {
                        ArgumentValue argumentValue = (ArgumentValue) userValue;
                        DBArgument argument = argumentValue.getArgument();
                        if (argument.isOutput()) {
                            Object value = argumentValue.getValue();
                            if (value instanceof ResultSet) {
                                parentForm.selectCursorOutput(argument);
                            }
                        }
                    }
                }
            }
        }
    };


    class CellRenderer extends ColoredTreeCellRenderer {
        @Override
        public void customizeCellRenderer(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            ArgumentValuesTreeNode treeNode = (ArgumentValuesTreeNode) value;
            Object userValue = treeNode.getUserValue();
            if (userValue instanceof DBMethod) {
                DBMethod method = (DBMethod) userValue;
                setIcon(method.getIcon());
                append(method.getName(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
            }

            if (userValue instanceof String) {
                append((String) userValue, treeNode.isLeaf() ?
                        SimpleTextAttributes.REGULAR_ATTRIBUTES :
                        SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES);
            }

            if (userValue instanceof DBArgument) {
                DBArgument argument = (DBArgument) userValue;
                setIcon(argument.getIcon());
                append(argument.getName(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
            }

            if (userValue instanceof ArgumentValue) {
                ArgumentValue argumentValue = (ArgumentValue) userValue;
                DBArgument argument = argumentValue.getArgument();
                DBTypeAttribute attribute = argumentValue.getAttribute();
                Object originalValue = argumentValue.getValue();
                String displayValue = originalValue instanceof ResultSet ? "" : "" + originalValue;

                if (attribute == null) {
                    setIcon(argument.getIcon());
                    append(argument.getName(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
                    append(" = ", SimpleTextAttributes.REGULAR_ATTRIBUTES);
                    DBDataType dataType = argument.getDataType();
                    if (dataType != null) {
                        append("{" + dataType.getName().toLowerCase() + "} " , SimpleTextAttributes.GRAY_ATTRIBUTES);
                    }

                    append(displayValue, SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES);
                } else {
                    setIcon(attribute.getIcon());
                    append(attribute.getName(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
                    append(" = ", SimpleTextAttributes.REGULAR_ATTRIBUTES);
                    DBDataType dataType = attribute.getDataType();
                    if (dataType != null) {
                        append("{" + dataType.getName() + "}" , SimpleTextAttributes.GRAY_ATTRIBUTES);
                    }
                    append(displayValue, SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES);
                }
            }

        }
    };
}
