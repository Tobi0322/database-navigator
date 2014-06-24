package com.dci.intellij.dbn.object.impl;

import com.dci.intellij.dbn.browser.model.BrowserTreeNode;
import com.dci.intellij.dbn.browser.ui.HtmlToolTipBuilder;
import com.dci.intellij.dbn.common.Icons;
import com.dci.intellij.dbn.editor.DBContentType;
import com.dci.intellij.dbn.object.DBSchema;
import com.dci.intellij.dbn.object.DBSynonym;
import com.dci.intellij.dbn.object.common.DBObject;
import com.dci.intellij.dbn.object.common.DBObjectType;
import com.dci.intellij.dbn.object.common.DBSchemaObjectImpl;
import com.dci.intellij.dbn.object.common.list.DBObjectNavigationList;
import com.dci.intellij.dbn.object.common.list.DBObjectNavigationListImpl;
import com.dci.intellij.dbn.object.common.property.DBObjectProperties;
import com.dci.intellij.dbn.object.common.property.DBObjectProperty;
import com.dci.intellij.dbn.object.common.status.DBObjectStatus;
import com.dci.intellij.dbn.object.lookup.DBObjectRef;
import com.dci.intellij.dbn.object.properties.DBObjectPresentableProperty;
import com.dci.intellij.dbn.object.properties.PresentableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBSynonymImpl extends DBSchemaObjectImpl implements DBSynonym {
    private DBObjectRef<DBObject> underlyingObject;

    public DBSynonymImpl(DBSchema schema, ResultSet resultSet) throws SQLException {
        super(schema, DBContentType.NONE, resultSet);
    }

    @Override
    protected void initObject(ResultSet resultSet) throws SQLException {
        name = resultSet.getString("SYNONYM_NAME");
        underlyingObject = new DBObjectRef<DBObject>(getConnectionHandler());
        String schemaName = resultSet.getString("OBJECT_OWNER");
        String objectName = resultSet.getString("OBJECT_NAME");
        DBObjectType objectType = DBObjectType.getObjectType(resultSet.getString("OBJECT_TYPE"), DBObjectType.ANY);

        underlyingObject.append(DBObjectType.SCHEMA, schemaName);
        underlyingObject.append(objectType, objectName);
    }

    public void initStatus(ResultSet resultSet) throws SQLException {
        boolean valid = resultSet.getString("IS_VALID").equals("Y");
        getStatus().set(DBObjectStatus.VALID, valid);
    }

    @Override
    public void initProperties() {
        DBObjectProperties properties = getProperties();
        properties.set(DBObjectProperty.REFERENCEABLE);
        properties.set(DBObjectProperty.SCHEMA_OBJECT);
    }

    public DBObjectType getObjectType() {
        return DBObjectType.SYNONYM;
    }

    @Override
    public DBObject getDefaultNavigationObject() {
        return getUnderlyingObject();
    }

    public Icon getIcon() {
        if (getStatus().is(DBObjectStatus.VALID)) {
            return Icons.DBO_SYNONYM;
        } else {
            return Icons.DBO_SYNONYM_ERR;
        }
    }

    public Icon getOriginalIcon() {
        return Icons.DBO_SYNONYM;
    }

    @Nullable
    public DBObject getUnderlyingObject() {
        return underlyingObject.get();
    }

    public String getNavigationTooltipText() {
        DBObject parentObject = getParentObject();
        if (parentObject == null) {
            return "unknown " + getTypeName();
        } else {
            DBObject underlyingObject = getUnderlyingObject();
            if (underlyingObject == null) {
                return "unknown " + getTypeName() +
                        " (" + parentObject.getTypeName() + " " + parentObject.getName() + ")";
            } else {
                return getTypeName() + " of " + underlyingObject.getName() + " " + underlyingObject.getTypeName() +
                        " (" + parentObject.getTypeName() + " " + parentObject.getName() + ")";

            }

        }
    }

    protected List<DBObjectNavigationList> createNavigationLists() {
        List<DBObjectNavigationList> objectNavigationLists = super.createNavigationLists();

        objectNavigationLists.add(new DBObjectNavigationListImpl("Underlying " + getUnderlyingObject().getTypeName(), getUnderlyingObject()));

        return objectNavigationLists;
    }

    public void buildToolTip(HtmlToolTipBuilder ttb) {
        ttb.append(true, getUnderlyingObject().getObjectType().getName() + " ", true);
        ttb.append(false, getObjectType().getName(), true);
        ttb.createEmptyRow();
        super.buildToolTip(ttb);
    }

    @Override
    public List<PresentableProperty> getPresentableProperties() {
        List<PresentableProperty> properties = super.getPresentableProperties();
        DBObject underlyingObject = getUnderlyingObject();
        if (underlyingObject != null) {
            properties.add(0, new DBObjectPresentableProperty("Underlying object", underlyingObject, true));
        }
        return properties;
    }

    @Override
    public void dispose() {
        super.dispose();
        underlyingObject = null;
    }

    /*********************************************************
     *                     TreeElement                       *
     *********************************************************/

    public boolean isLeafTreeElement() {
        return true;
    }

    @NotNull
    public List<BrowserTreeNode> buildAllPossibleTreeChildren() {
        return BrowserTreeNode.EMPTY_LIST;
    }


}
