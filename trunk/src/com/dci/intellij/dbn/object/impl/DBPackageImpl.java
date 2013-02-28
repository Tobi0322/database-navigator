package com.dci.intellij.dbn.object.impl;

import com.dci.intellij.dbn.browser.DatabaseBrowserUtils;
import com.dci.intellij.dbn.browser.model.BrowserTreeNode;
import com.dci.intellij.dbn.browser.ui.HtmlToolTipBuilder;
import com.dci.intellij.dbn.common.Icons;
import com.dci.intellij.dbn.common.content.DynamicContent;
import com.dci.intellij.dbn.common.content.loader.DynamicContentLoader;
import com.dci.intellij.dbn.common.content.loader.DynamicContentResultSetLoader;
import com.dci.intellij.dbn.common.content.loader.DynamicSubcontentLoader;
import com.dci.intellij.dbn.database.DatabaseMetadataInterface;
import com.dci.intellij.dbn.ddl.DDLFileManager;
import com.dci.intellij.dbn.ddl.DDLFileType;
import com.dci.intellij.dbn.ddl.DDLFileTypeId;
import com.dci.intellij.dbn.editor.DBContentType;
import com.dci.intellij.dbn.object.DBPackage;
import com.dci.intellij.dbn.object.DBPackageFunction;
import com.dci.intellij.dbn.object.DBPackageProcedure;
import com.dci.intellij.dbn.object.DBPackageType;
import com.dci.intellij.dbn.object.DBSchema;
import com.dci.intellij.dbn.object.common.DBObject;
import com.dci.intellij.dbn.object.common.DBObjectType;
import com.dci.intellij.dbn.object.common.list.DBObjectList;
import com.dci.intellij.dbn.object.common.list.DBObjectListContainer;
import com.dci.intellij.dbn.object.common.loader.DBObjectTimestampLoader;
import com.dci.intellij.dbn.object.common.loader.DBSourceCodeLoader;
import com.dci.intellij.dbn.object.common.status.DBObjectStatus;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBPackageImpl extends DBProgramImpl implements DBPackage {
    protected DBObjectList<DBPackageType> types;
    public DBPackageImpl(DBSchema schema, ResultSet resultSet) throws SQLException {
        super(schema, DBContentType.CODE_SPEC_AND_BODY, resultSet);
        name = resultSet.getString("PACKAGE_NAME");
        initLists();
    }

    protected void initLists() {
        DBObjectListContainer container = getChildObjects();
        functions = container.createSubcontentObjectList(DBObjectType.PACKAGE_FUNCTION, this, FUNCTIONS_LOADER, getSchema(), false);
        procedures = container.createSubcontentObjectList(DBObjectType.PACKAGE_PROCEDURE, this, PROCEDURES_LOADER, getSchema(), false);
        types = container.createSubcontentObjectList(DBObjectType.PACKAGE_TYPE, this, TYPES_LOADER, getSchema(), true);
    }

    public List getTypes() {
        return types.getObjects();
    }

    public DBPackageType getType(String name) {
        return types.getObject(name);
    }

    public DBObjectType getObjectType() {
        return DBObjectType.PACKAGE;
    }

    public Icon getIcon() {
        if (getStatus().is(DBObjectStatus.VALID)) {
            if (getStatus().is(DBObjectStatus.DEBUG))  {
                return Icons.DBO_PACKAGE_DEBUG;
            } else {
                return Icons.DBO_PACKAGE;
            }
        } else {
            return Icons.DBO_PACKAGE_ERR;
        }
    }

    public Icon getOriginalIcon() {
        return Icons.DBO_PACKAGE;
    }

    public void buildToolTip(HtmlToolTipBuilder ttb) {
        ttb.append(true, getObjectType().getName(), true);
        ttb.createEmptyRow();
        super.buildToolTip(ttb);
    }

    /*********************************************************
     *                     TreeElement                       *
     *********************************************************/
    @NotNull
    public List<BrowserTreeNode> buildAllPossibleTreeChildren() {
        return DatabaseBrowserUtils.createList(procedures, functions, types);
    }

    /*********************************************************
     *                         Loaders                       *
     *********************************************************/

    private static final DynamicContentLoader<DBPackageFunction> FUNCTIONS_ALTERNATIVE_LOADER = new DynamicContentResultSetLoader<DBPackageFunction>() {
        public ResultSet createResultSet(DynamicContent<DBPackageFunction> dynamicContent, Connection connection) throws SQLException {
            DatabaseMetadataInterface metadataInterface = dynamicContent.getConnectionHandler().getInterfaceProvider().getMetadataInterface();
            DBPackage packagee = (DBPackage) dynamicContent.getParent();
            return metadataInterface.loadPackageFunctions(packagee.getSchema().getName(), packagee.getName(), connection);
        }

        public DBPackageFunction createElement(DynamicContent<DBPackageFunction> dynamicContent, ResultSet resultSet, LoaderCache loaderCache) throws SQLException {
            DBPackageImpl packagee = (DBPackageImpl) dynamicContent.getParent();
            return new DBPackageFunctionImpl(packagee, resultSet);
        }
    };

    private static final DynamicSubcontentLoader FUNCTIONS_LOADER = new DynamicSubcontentLoader<DBPackageFunction>(true) {
        public DynamicContentLoader<DBPackageFunction> getAlternativeLoader() {
            return FUNCTIONS_ALTERNATIVE_LOADER;
        }

        public boolean match(DBPackageFunction function, DynamicContent dynamicContent) {
            DBPackage packagee = (DBPackage) dynamicContent.getParent();
            return function.getPackage() == packagee;
        }
    };

    private static final DynamicContentLoader<DBPackageProcedure> PROCEDURES_ALTERNATIVE_LOADER = new DynamicContentResultSetLoader<DBPackageProcedure>() {
        public ResultSet createResultSet(DynamicContent<DBPackageProcedure> dynamicContent, Connection connection) throws SQLException {
            DatabaseMetadataInterface metadataInterface = dynamicContent.getConnectionHandler().getInterfaceProvider().getMetadataInterface();
            DBPackage packagee = (DBPackage) dynamicContent.getParent();
            return metadataInterface.loadPackageProcedures(packagee.getSchema().getName(), packagee.getName(), connection);
        }

        public DBPackageProcedure createElement(DynamicContent<DBPackageProcedure> dynamicContent, ResultSet resultSet, LoaderCache loaderCache) throws SQLException {
            DBPackageImpl packagee = (DBPackageImpl) dynamicContent.getParent();
            return new DBPackageProcedureImpl(packagee, resultSet);
        }
    };

    private static final DynamicSubcontentLoader PROCEDURES_LOADER = new DynamicSubcontentLoader<DBPackageProcedure>(true) {
        public DynamicContentLoader<DBPackageProcedure> getAlternativeLoader() {
            return PROCEDURES_ALTERNATIVE_LOADER;
        }

        public boolean match(DBPackageProcedure procedure, DynamicContent dynamicContent) {
            DBPackage packagee = (DBPackage) dynamicContent.getParent();
            return procedure.getPackage() == packagee;
        }
    };

    private static final DynamicContentLoader<DBPackageType> TYPES_ALTERNATIVE_LOADER = new DynamicContentResultSetLoader<DBPackageType>() {
        public ResultSet createResultSet(DynamicContent<DBPackageType> dynamicContent, Connection connection) throws SQLException {
            DatabaseMetadataInterface metadataInterface = dynamicContent.getConnectionHandler().getInterfaceProvider().getMetadataInterface();
            DBPackage packagee = (DBPackage) dynamicContent.getParent();
            return metadataInterface.loadPackageTypes(packagee.getSchema().getName(), packagee.getName(), connection);
        }

        public DBPackageType createElement(DynamicContent<DBPackageType> dynamicContent, ResultSet resultSet, LoaderCache loaderCache) throws SQLException {
            DBPackageImpl packagee = (DBPackageImpl) dynamicContent.getParent();
            return new DBPackageTypeImpl(packagee, resultSet);
        }
    };

    private static final DynamicSubcontentLoader TYPES_LOADER = new DynamicSubcontentLoader<DBPackageType>(true) {
        public DynamicContentLoader<DBPackageType> getAlternativeLoader() {
            return TYPES_ALTERNATIVE_LOADER;
        }

        public boolean match(DBPackageType type, DynamicContent dynamicContent) {
            DBPackage packagee = (DBPackage) dynamicContent.getParent();
            return type.getPackage() == packagee;
        }
    };

    @Override
    public void reload() {
        super.reload();
        types.reload(true);
    }

    private class SpecSourceCodeLoader extends DBSourceCodeLoader {
        protected SpecSourceCodeLoader(DBObject object) {
            super(object, false);
        }

        public ResultSet loadSourceCode(Connection connection) throws SQLException {
            DatabaseMetadataInterface metadataInterface = getConnectionHandler().getInterfaceProvider().getMetadataInterface();
            return metadataInterface.loadObjectSourceCode(
                   getSchema().getName(), getName(), "PACKAGE", connection);
        }
    }

    private class BodySourceCodeLoader extends DBSourceCodeLoader {
        protected BodySourceCodeLoader(DBObject object) {
            super(object, true);
        }

        public ResultSet loadSourceCode(Connection connection) throws SQLException {
            DatabaseMetadataInterface metadataInterface = getConnectionHandler().getInterfaceProvider().getMetadataInterface();
            return metadataInterface.loadObjectSourceCode(getSchema().getName(), getName(), "PACKAGE BODY", connection);
        }
    }

    private static DBObjectTimestampLoader SPEC_TIMESTAMP_LOADER = new DBObjectTimestampLoader("PACKAGE") {};
    private static DBObjectTimestampLoader BODY_TIMESTAMP_LOADER = new DBObjectTimestampLoader("PACKAGE BODY") {};

   /*********************************************************
     *                   DBEditableObject                    *
     *********************************************************/
    public String loadCodeFromDatabase(DBContentType contentType) throws SQLException {
       DBSourceCodeLoader loader =
               contentType == DBContentType.CODE_SPEC ? new SpecSourceCodeLoader(this) :
               contentType == DBContentType.CODE_BODY ? new BodySourceCodeLoader(this) : null;

       return loader == null ? null : loader.load();

    }

    public String getCodeParseRootId(DBContentType contentType) {
        return contentType == DBContentType.CODE_SPEC ? "package_spec" :
               contentType == DBContentType.CODE_BODY ? "package_body" : null;
    }

    public DDLFileType getDDLFileType(DBContentType contentType) {
        DDLFileManager ddlFileManager = DDLFileManager.getInstance(getProject());
        return contentType == DBContentType.CODE_SPEC ? ddlFileManager.getDDLFileType(DDLFileTypeId.PACKAGE_SPEC) :
               contentType == DBContentType.CODE_BODY ? ddlFileManager.getDDLFileType(DDLFileTypeId.PACKAGE_BODY) :
               ddlFileManager.getDDLFileType(DDLFileTypeId.PACKAGE);
    }


    public DDLFileType[] getDDLFileTypes() {
        DDLFileManager ddlFileManager = DDLFileManager.getInstance(getProject());
        return new DDLFileType[]{
                ddlFileManager.getDDLFileType(DDLFileTypeId.PACKAGE),
                ddlFileManager.getDDLFileType(DDLFileTypeId.PACKAGE_SPEC),
                ddlFileManager.getDDLFileType(DDLFileTypeId.PACKAGE_BODY)};
    }

    public DBObjectTimestampLoader getTimestampLoader(DBContentType contentType) {
        return contentType == DBContentType.CODE_SPEC ? SPEC_TIMESTAMP_LOADER :
               contentType == DBContentType.CODE_BODY ? BODY_TIMESTAMP_LOADER : null;
    }
}
