package com.dci.intellij.dbn.object;

import com.dci.intellij.dbn.object.common.DBSchemaObject;

import java.util.List;

public interface DBIndex extends DBSchemaObject {
    boolean isUnique();
    DBTable getTable();

    List<DBColumn> getColumns();
}
