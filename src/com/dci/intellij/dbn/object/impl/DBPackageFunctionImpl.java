package com.dci.intellij.dbn.object.impl;

import com.dci.intellij.dbn.editor.DBContentType;
import com.dci.intellij.dbn.object.DBPackage;
import com.dci.intellij.dbn.object.DBPackageFunction;
import com.dci.intellij.dbn.object.DBProgram;
import com.dci.intellij.dbn.object.common.DBObjectType;
import com.dci.intellij.dbn.object.common.property.DBObjectProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBPackageFunctionImpl extends DBFunctionImpl implements DBPackageFunction {
    private DBPackage packagee;
    private int overload;

    public DBPackageFunctionImpl(DBPackage packagee, ResultSet resultSet) throws SQLException {
        super(packagee, resultSet);
        this.packagee = packagee;
        overload = resultSet.getInt("OVERLOAD");
    }

    @Override
    public void updateStatuses(ResultSet resultSet) throws SQLException {}

    @Override
    public void updateProperties() {
        getProperties().set(DBObjectProperty.NAVIGABLE);
    }

    public DBPackage getPackage() {
        packagee = (DBPackage) packagee.getUndisposedElement();
        return packagee;
    }

    @Override
    public DBProgram getProgram() {
        return getPackage();
    }

    public int getOverload() {
        return overload;
    }

    public boolean isEmbedded() {
        return true;
    }

    @Override
    public String getPresentableTextDetails() {
        return getOverload() > 0 ? " - " + getOverload() : "";
    }

    @Override
    public DBObjectType getObjectType() {
        return DBObjectType.PACKAGE_FUNCTION;
    }

    public void executeUpdateDDL(DBContentType contentType, String oldCode, String newCode) throws SQLException {}

    @Override
    public void dispose() {
        super.dispose();
        //packagee = null;
    }
}
