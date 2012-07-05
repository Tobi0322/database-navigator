package com.dci.intellij.dbn.common;

import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.RowIcon;
import gnu.trove.THashMap;

import javax.swing.*;
import java.util.Map;

public class Icons {
    private static final Map<String, Icon> REGISTERED_ICONS = new THashMap<String, Icon>();

    public static final Icon COMMON_INFO = load("/img/common/Info.png");
    public static final Icon COMMON_INFO_DISABLED = load("/img/common/InfoDisabled.png");
    public static final Icon COMMON_WARNING = load("/img/common/Warning.png");
    public static final Icon COMMON_RIGHT = load("/img/common/SplitRight.png");
    public static final Icon COMMON_UP = load("/img/common/SplitUp.png");


    public static final Icon ACTION_COPY = load("/img/action/Copy.png");
    public static final Icon ACTION_SORT = load("/img/action/SortAlpha.png");
    public static final Icon ACTION_SORT_ALPHA = load("/img/action/SortAlphabetically.png");
    public static final Icon ACTION_SORT_ASC = load("/img/action/SortAscending.png");
    public static final Icon ACTION_SORT_DESC = load("/img/action/SortDescending.png");
    public static final Icon ACTION_ADD = load("/img/action/Add.png");
    public static final Icon ACTION_ADD_SPECIAL = load("/img/action/AddSpecial.png");
    public static final Icon ACTION_REMOVE = load("/img/action/Remove.png");
    public static final Icon ACTION_MOVE_UP = load("/img/action/MoveUp.png");
    public static final Icon ACTION_MOVE_DOWN = load("/img/action/MoveDown.png");
    public static final Icon ACTION_EDIT = load("/img/action/EditSource.png");
    public static final Icon ACTION_SETTINGS = load("/img/action/Properties.png");
    public static final Icon ACTION_COLLAPSE_ALL = load("/img/action/CollapseAll.png");
    public static final Icon ACTION_EXPAND_ALL = load("/img/action/ExpandAll.png");
    public static final Icon ACTION_GROUP = load("/img/action/Group.png");
    public static final Icon ACTION_DELETE = load("/img/action/Delete.png");
    public static final Icon ACTION_CLOSE = ACTION_DELETE;
    public static final Icon ACTION_UP_DOWN = load("/img/action/UpDown.png");
    public static final Icon ACTION_REFRESH = load("/img/action/Synchronize.png");
    public static final Icon ACTION_FIND = load("/img/action/Find.png");
    public static final Icon ACTION_WRAP_TEXT = load("/img/action/WrapText.png");
    public static final Icon ACTION_RERUN = load("/img/action/Rerun.png");
    public static final Icon ACTION_PIN = load("/img/action/Pin.png");


    public static final Icon DATABASE_NAVIGATOR = load("/img/project/DatabaseNavigator.png");
    public static final Icon DATABASE_MODULE = load("/img/project/DatabaseModule.png");
    public static final Icon DATABASE_MODULE_SMALL_OPEN = load("/img/project/DatabaseModuleOpen.png");
    public static final Icon DATABASE_MODULE_SMALL_CLOSED = load("/img/project/DatabaseModuleClosed.png");

    public static final Icon WINDOW_DATABASE_BROWSER = load("/img/window/DatabaseBrowser.png");
    public static final Icon WINDOW_EXECUTION_CONSOLE = load("/img/window/ExecutionConsole.png");

    public static final Icon FILE_SQL_CONSOLE = load("/img/file/SQLConsole.png");
    public static final Icon FILE_SQL = load("/img/file/SQLFile.png");
    public static final Icon FILE_PLSQL = load("/img/file/PLSQLFile.png");
    public static final Icon FILE_BLOCK_PLSQL = load("FILE_BLOCK_PLSQL", "/img/PLSQLBlock.png");
    public static final Icon FILE_BLOCK_PSQL = load("FILE_BLOCK_PSQL", "/img/file/PSQLBlock.png");
    public static final Icon FILE_BLOCK_SQL = load("FILE_BLOCK_SQL", "/img/file/SQLBlock.png");


    public static final Icon DIALOG_INFO     = load("/img/dialog/Information.png");
    public static final Icon DIALOG_WARNING  = load("/img/dialog/Warning.png");
    public static final Icon DIALOG_ERROR    = load("/img/dialog/Error.png");
    public static final Icon DIALOG_QUESTION = load("/img/dialog/Dialog.png");


    public static final Icon METHOD_EXECUTION_RUN     = load("/img/ExecuteMethod.png");
    public static final Icon METHOD_EXECUTION_DEBUG   = load("/img/DebugMehod.png");
    public static final Icon METHOD_EXECUTION_RERUN   = load("/img/RerunMethodExecution.png");
    public static final Icon METHOD_EXECUTION_DIALOG  = load("/img/ExecuteMethodDialog.png");
    public static final Icon METHOD_EXECUTION_HISTORY = load("/img/MethodExecutionHistory.png");
    public static final Icon METHOD_LOOKUP            = load("/img/MethodLookup.png");


    public static final Icon STMT_EXECUTION_RUN     = load("/img/ExecuteStatement.png");
    public static final Icon STMT_EXECUTION_RERUN   = ACTION_RERUN;
    public static final Icon STMT_EXECUTION_RESUME  = load("/actions/resume.png");
    public static final Icon STMT_EXECUTION_REFRESH = load("/actions/sync.png");
    public static final Icon STMT_EXECUTION_ERROR   = load("/debugger/db_obsolete.png");

    public static final Icon STMT_EXEC_RESULTSET        = load("/img/ExecutionResultSet.png");
    public static final Icon STMT_EXEC_RESULTSET_RERUN  = load("/img/ExecutionResultSetRerun.png");
    public static final Icon STMT_EXEC_RESULTSET_ORPHAN = load("/img/ExecutionResultSetOrphan.png");

    public static final Icon EXEC_RESULT_RERUN            = load("/actions/sync.png");
    public static final Icon EXEC_RESULT_OPEN_EXEC_DIALOG = load("/img/ExecuteMethodDialog.png");
    public static final Icon EXEC_RESULT_RESUME           = load("/actions/resume.png");
    public static final Icon EXEC_RESULT_STOP             = load("/actions/suspend.png");
    public static final Icon EXEC_RESULT_CLOSE            = load("/actions/cancel.png");
    public static final Icon EXEC_RESULT_VIEW_STATEMENT   = load("/graph/printPreview.png");
    public static final Icon EXEC_RESULT_VIEW_RECORD      = load("/img/RecordViewer.png");
    public static final Icon EXEC_RESULT_OPTIONS          = load("/actions/properties.png");
    public static final Icon EXEC_RESULT_MESSAGES         = load("/general/toolWindowMessages.png");
    public static final Icon EXEC_CONFIG                  = load("/img/DBProgram.png");

    public static final Icon NAVIGATION_GO_TO_SPEC       = load("/img/GoToSpec.png");
    public static final Icon NAVIGATION_GO_TO_BODY       = load("/img/GoToBody.png");

    public static final Icon BROWSER_BACK = load("/actions/back.png");
    public static final Icon BROWSER_NEXT = load("/actions/forward.png");
    public static final Icon BROWSER_AUTOSCROLL_TO_EDITOR = load("/general/autoscrollToSource.png");
    public static final Icon BROWSER_AUTOSCROLL_FROM_EDITOR = load("/general/autoscrollFromSource.png");
    public static final Icon BROWSER_OBJECT_PROPERTIES = load("/img/ObjectProperties.png");


    public static final Icon DATA_EDITOR_ROW_INSERT = load("/img/InsertRow.png");
    public static final Icon DATA_EDITOR_ROW_NEW = load("/img/NewRow.png");
    public static final Icon DATA_EDITOR_ROW_MODIFIED = load("/img/ModifiedRow.png");
    public static final Icon DATA_EDITOR_ROW_DELETED = load("/img/DeletedRow.png");

    public static final Icon DATA_EDITOR_DUPLICATE_RECORD = load("/img/DatasetEditorDuplicateRecord.png");
    public static final Icon DATA_EDITOR_INSERT_RECORD = load("/img/DatasetEditorInsertRecord.png");
    public static final Icon DATA_EDITOR_DELETE_RECORD = load("/img/DatasetEditorDeleteRecord.png");
    public static final Icon DATA_EDITOR_SWITCH_EDITABLE_STATUS = load("/img/DatasetEditorSwitchEditableStatus.png");
    public static final Icon DATA_EDITOR_FETCH_NEXT_RECORDS = load("/actions/resume.png");
    public static final Icon DATA_EDITOR_EDIT_RECORD = load("/img/EditDatasetRecord.png");
    public static final Icon DATA_EDITOR_NEXT_RECORD = load("/img/NextRecord.png");
    public static final Icon DATA_EDITOR_PREVIOUS_RECORD = load("/img/PreviousRecord.png");
    public static final Icon DATA_EDITOR_FIRST_RECORD = load("/img/FirstRecord.png");
    public static final Icon DATA_EDITOR_LAST_RECORD = load("/img/LastRecord.png");
    public static final Icon DATA_EDITOR_LOCK_EDITING = load("/img/LockEditing.png");

    public static final Icon DATA_EDITOR_RELOAD_DATA = load("/actions/sync.png");
    public static final Icon DATA_EDITOR_BROWSE =    load("/img/Browse.png");
    public static final Icon DATA_EDITOR_CALENDAR =    load("/img/Calendar.png");

    public static final Icon DATA_EXPORT =    load("/img/DataExport.png");
    public static final Icon DATA_IMPORT =    load("/img/DataImport.png");

    public static final Icon DATASET_FILTER =    load("/img/filter/DatasetFilter.png");
    public static final Icon DATASET_FILTER_NEW =    load("/img/filter/DatasetFilterNew.png");
    public static final Icon DATASET_FILTER_EDIT =    load("/img/filter/DatasetFilterEdit.png");
    public static final Icon DATASET_FILTER_BASIC =    load("/img/filter/DatasetFilterBasic.png");
    public static final Icon DATASET_FILTER_BASIC_ERR =    load("/img/filter/DatasetFilterBasicErr.png");
    public static final Icon DATASET_FILTER_BASIC_TEMP =    load("/img/filter/DatasetFilterBasicTemp.png");
    public static final Icon DATASET_FILTER_BASIC_TEMP_ERR =    load("/img/filter/DatasetFilterBasicTempErr.png");
    public static final Icon DATASET_FILTER_CUSTOM =    load("/img/filter/DatasetFilterCustom.png");
    public static final Icon DATASET_FILTER_CUSTOM_ERR =    load("/img/filter/DatasetFilterCustomErr.png");
    public static final Icon DATASET_FILTER_GLOBAL =    load("/img/filter/DatasetFilterGlobal.png");
    public static final Icon DATASET_FILTER_GLOBAL_ERR =    load("/img/filter/DatasetFilterGlobalErr.png");
    public static final Icon DATASET_FILTER_EMPTY =    load("/img/filter/DatasetFilterEmpty.png");

    public static final Icon DATASET_FILTER_CONDITION_ACTIVE =    load("/img/ActiveFilterCondition.png");
    public static final Icon DATASET_FILTER_CONDITION_INACTIVE =    load("/img/InactiveFilterCondition.png");
    public static final Icon DATASET_FILTER_CONDITION_REMOVE =    load("/img/RemoveFilterCondition.png");
    public static final Icon DATASET_FILTER_CONDITION_NEW =    load("/img/NewFilterCondition.png");


    public static final Icon CONDITION_JOIN_TYPE =    load("/img/JoinTypeSwitch.png");

    public static final Icon TEXT_CELL_EDIT_ACCEPT = load("/img/CellEditAccept.png");
    public static final Icon TEXT_CELL_EDIT_REVERT = load("/img/CellEditRevert.png");
    public static final Icon TEXT_CELL_EDIT_DELETE = load("/img/CellEditDelete.png");

    public static final Icon CALENDAR_CELL_EDIT_NEXT_MONTH = load("/img/CalendarNextMonth.png");
    public static final Icon CALENDAR_CELL_EDIT_NEXT_YEAR = load("/img/CalendarNextYear.png");
    public static final Icon CALENDAR_CELL_EDIT_PREVIOUS_MONTH = load("/img/CalendarPreviousMonth.png");
    public static final Icon CALENDAR_CELL_EDIT_PREVIOUS_YEAR = load("/img/CalendarPreviousYear.png");
    public static final Icon CALENDAR_CELL_EDIT_CLEAR_TIME = load("/img/CalendarResetTime.png");

    public static final Icon EXEC_MESSAGES_INFO    = load("/compiler/information.png");
    public static final Icon EXEC_MESSAGES_WARNING = load("/compiler/warning.png");
    public static final Icon EXEC_MESSAGES_ERROR   = load("/compiler/error.png");

    public static final Icon CHECK   = load("/gutter/check.png");
    public static final Icon PROJECT = load("/nodes/project.png");
    public static final Icon FILE_CONNECTION_MAPPING = load("/img/FileConnection.png");
    public static final Icon FILE_SCHEMA_MAPPING = load("/img/FileSchema.png");

    public static final Icon CODE_EDITOR_SAVE = load("/img/SaveToDatabase.png");
    public static final Icon CODE_EDITOR_RESET = load("/actions/reset.png");
    public static final Icon CODE_EDITOR_RELOAD = load("/actions/sync.png");
    public static final Icon CODE_EDITOR_DIFF = load("/img/ShowDiff.png");
    public static final Icon CODE_EDITOR_DIFF_DB = load("/img/ShowDbDiff.png");
    public static final Icon CODE_EDITOR_DDL_FILE = load("/img/DDLFile.png");
    public static final Icon CODE_EDITOR_DDL_FILE_NEW = load("/img/DDLFileNew.png");
    public static final Icon CODE_EDITOR_DDL_FILE_UNBIND = load("/img/DDLFileUnbind.png");
    public static final Icon CODE_EDITOR_DDL_FILE_LINK = load("/img/DDLFileBind.png");
    public static final Icon CODE_EDITOR_SPEC = load("/img/CodeSpec.png");
    public static final Icon CODE_EDITOR_BODY = load("/img/CodeBody.png");

    public static final Icon OBEJCT_COMPILE     = load("/img/Compile.png");
    public static final Icon OBEJCT_COMPILE_DEBUG = load("/img/CompileDebug.png");
    //public static final Icon OBEJCT_COMPILE_KEEP = load("/img/CompileKeep.png");
    public static final Icon OBEJCT_COMPILE_ASK = load("/img/CompileAsk.png");
    public static final Icon OBEJCT_EDIT_SOURCE = load("/img/EditSource.png");
    public static final Icon OBEJCT_EDIT_DATA = load("/img/EditData.png");
    public static final Icon OBEJCT_VIEW_DATA = load("/img/ViewData.png");

    public static final Icon CONNECTION_COMMIT   = load("CONNECTION_COMMIT", "/img/ConnectionCommit.png");
    public static final Icon CONNECTION_ROLLBACK = load("CONNECTION_ROLLBACK", "/img/ConnectionRollback.png");

    public static final Icon COMMON_DIRECTION_IN = load("/img/common/DirectionIn.png");
    public static final Icon COMMON_DIRECTION_OUT = load("/img/common/DirectionOut.png");
    public static final Icon COMMON_DIRECTION_IN_OUT = load("/img/common/DirectionInOut.png");




    public static final Icon CONN_STATUS_INVALID      = load("/general/ideFatalError.png");
    public static final Icon CONN_STATUS_CONNECTED    = load("/actions/createFromUsage.png");
    public static final Icon CONN_STATUS_DISCONNECTED = load("/actions/createFromUsage_off.png");

    public static final Icon CONNECTION_VIRTUAL       = load("/img/connection/ConnectionVirtual1.png");
    public static final Icon CONNECTION_ACTIVE        = load("/img/connection/ConnectionActive1.png");
    public static final Icon CONNECTION_INACTIVE      = load("/img/connection/ConnectionInactive1.png");
    public static final Icon CONNECTION_DISABLED      = load("/img/connection/ConnectionDisabled.png");
    public static final Icon CONNECTION_NEW           = load("/img/connection/ConnectionNew.png");
    public static final Icon CONNECTION_INVALID       = load("/img/connection/ConnectionInvalid1.png");
    public static final Icon CONNECTIONS              = load("/img/connection/Connections1.png");


//    public static final Icon DBO_ARGUMENT_IN         = createRowIcon(DBO_ARGUMENT, COMMON_DIRECTION_IN);
//    public static final Icon DBO_ARGUMENT_OUT        = createRowIcon(DBO_ARGUMENT, COMMON_DIRECTION_OUT);
//    public static final Icon DBO_ARGUMENT_IN_OUT     = createRowIcon(DBO_ARGUMENT, COMMON_DIRECTION_IN_OUT);

    public static final Icon DBO_ATTRIBUTE           = load("/img/object/Attribute.png");
    public static final Icon DBO_ATTRIBUTES          = load("/img/object/Attributes.png");
    public static final Icon DBO_ARGUMENT            = load("/img/object/Argument.png");
    public static final Icon DBO_ARGUMENTS           = load("/img/object/Arguments.png");
    public static final Icon DBO_ARGUMENT_IN         = load("/img/object/ArgumentIn.png");
    public static final Icon DBO_ARGUMENT_OUT        = load("/img/object/ArgumentOut.png");
    public static final Icon DBO_ARGUMENT_IN_OUT     = load("/img/object/ArgumentInOut.png");
    public static final Icon DBO_CLUSTER                = load("/img/object/Cluster.png");
    public static final Icon DBO_CLUSTERS               = load("/img/object/Clusters.png");
    public static final Icon DBO_COLUMN                 = load("/img/object/Column.png");
    public static final Icon DBO_COLUMN_PK              = load("/img/object/ColumnPk.png");
    public static final Icon DBO_COLUMN_FK              = load("/img/object/ColumnFk.png");
    public static final Icon DBO_COLUMN_PFK             = load("/img/object/ColumnPkFk.png");
    public static final Icon DBO_COLUMN_HIDDEN          = load("/img/object/ColumnHidden.png");
    public static final Icon DBO_COLUMNS                = load("/img/object/Columns.png");
    public static final Icon DBO_CONSTRAINT             = load("/img/object/Constraint.png");
    public static final Icon DBO_CONSTRAINT_DISABLED    = load("/img/object/ConstraintDisabled.png");
    public static final Icon DBO_CONSTRAINTS            = load("/img/object/Constraints.png");
    public static final Icon DBO_DATABASE_LINK          = load("/img/object/DatabaseLink.png");
    public static final Icon DBO_DATABASE_LINKS         = load("/img/object/DatabaseLinks.png");
    public static final Icon DBO_DIMENSION              = load("/img/object/Dimension.png");
    public static final Icon DBO_DIMENSIONS             = load("/img/object/Dimensions.png");
    public static final Icon DBO_FUNCTION               = load("/img/object/Function.png");
    public static final Icon DBO_FUNCTION_DEBUG         = load("/img/object/FunctionDebug.png");
    public static final Icon DBO_FUNCTION_ERR           = load("/img/object/FunctionErr.png");
    public static final Icon DBO_FUNCTIONS              = load("/img/object/Functions.png");
    public static final Icon DBO_INDEX                  = load("/img/object/Index.png");
    public static final Icon DBO_INDEX_DISABLED         = load("/img/object/IndexDisabled.png");
    public static final Icon DBO_INDEXES                = load("/img/object/Indexes.png");
    public static final Icon DBO_MATERIALIZED_VIEW      = load("/img/object/MaterializedView.png");
    public static final Icon DBO_MATERIALIZED_VIEWS     = load("/img/object/MaterializedViews.png");
    public static final Icon DBO_METHOD                 = load("/img/object/Method.png");
    public static final Icon DBO_METHODS                = load("/img/object/Methods.png");
    public static final Icon DBO_NESTED_TABLE           = load("/img/object/NestedTable.png");
    public static final Icon DBO_NESTED_TABLES          = load("/img/object/NestedTables.png");
    public static final Icon DBO_PACKAGE                = load("/img/object/Package.png");
    public static final Icon DBO_PACKAGE_ERR            = load("/img/object/PackageErr.png");
    public static final Icon DBO_PACKAGE_DEBUG          = load("/img/object/PackageDebug.png");
    public static final Icon DBO_PACKAGES               = load("/img/object/Packages.png");
    public static final Icon DBO_PACKAGE_SPEC           = load("DBO_PACKAGE_SPEC", "/img/object/PackageSpec.png");
    public static final Icon DBO_PACKAGE_BODY           = load("DBO_PACKAGE_BODY", "/img/object/PackageBody.png");
    public static final Icon DBO_PROCEDURE              = load("/img/object/Procedure.png");
    public static final Icon DBO_PROCEDURE_ERR          = load("/img/object/ProcedureErr.png");
    public static final Icon DBO_PROCEDURE_DEBUG        = load("/img/object/ProcedureDebug.png");
    public static final Icon DBO_PROCEDURES             = load("/img/object/Procedures.png");
    public static final Icon DBO_PRIVILEGE              = load("/img/object/Privilege.png");
    public static final Icon DBO_PRIVILEGES             = load("/img/object/Privileges.png");
    public static final Icon DBO_ROLE                   = load("/img/object/Role.png");
    public static final Icon DBO_ROLES                  = load("/img/object/Roles.png");
    public static final Icon DBO_SCHEMA                 = load("/img/object/Schema.png");
    public static final Icon DBO_SCHEMAS                = load("/img/object/Schemas.png");
    public static final Icon DBO_SYNONYM                = load("/img/object/Synonym.png");
    public static final Icon DBO_SYNONYM_ERR            = load("/img/object/SynonymErr.png");
    public static final Icon DBO_SYNONYMS               = load("/img/object/Synonyms.png");
    public static final Icon DBO_SEQUENCE               = load("/img/object/Sequence.png");
    public static final Icon DBO_SEQUENCES              = load("/img/object/Sequences.png");
    public static final Icon DBO_TMP_TABLE              = load("/img/object/TableTmp.png");
    public static final Icon DBO_TABLE                  = load("/img/object/Table.png");
    public static final Icon DBO_TABLES                 = load("/img/object/Tables.png");
    public static final Icon DBO_TRIGGER                = load("/img/object/Trigger.png");
    public static final Icon DBO_TRIGGER_ERR            = load("/img/object/TriggerErr.png");
    public static final Icon DBO_TRIGGER_DEBUG          = load("/img/object/TriggerDebug.png");
    public static final Icon DBO_TRIGGER_ERR_DISABLED   = load("/img/object/TriggerErrDisabled.png");
    public static final Icon DBO_TRIGGER_DISABLED       = load("/img/object/TriggerDisabled.png");
    public static final Icon DBO_TRIGGER_DISABLED_DEBUG = load("/img/object/TriggerDisabledDebug.png");
    public static final Icon DBO_TRIGGERS               = load("/img/object/Triggers.png");
    public static final Icon DBO_TYPE                   = load("/img/object/Type.png");
    public static final Icon DBO_TYPE_COLLECTION        = load("/img/object/TypeCollection.png");
    public static final Icon DBO_TYPE_COLLECTION_ERR    = load("/img/object/TypeCollectionErr.png");
    public static final Icon DBO_TYPE_ERR               = load("/img/object/TypeErr.png");
    public static final Icon DBO_TYPE_DEBUG             = load("/img/object/TypeDebug.png");
    public static final Icon DBO_TYPES                  = load("/img/object/Types.png");
    public static final Icon DBO_USER                   = load("/img/object/User.png");
    public static final Icon DBO_USER_EXPIRED           = load("/img/object/UserExpired.png");
    public static final Icon DBO_USER_LOCKED            = load("/img/object/UserLocked.png");
    public static final Icon DBO_USER_EXPIRED_LOCKED    = load("/img/object/UserExpiredLocked.png");
    public static final Icon DBO_USERS                  = load("/img/object/Users.png");
    public static final Icon DBO_VIEW                   = load("/img/object/View.png");
    public static final Icon DBO_VIEW_SYNONYM           = load("/img/object/ViewSynonym.png");
    public static final Icon DBO_VIEWS                  = load("/img/object/Views.png");
    public static final Icon DBO_VARIABLE               = load("/img/object/Variable.png");



    public static final Icon SPACE                        = load("/img/Space.png");
    public static final Icon TREE_BRANCH                  = load("/img/TreeBranch.png");
    public static final Icon SMALL_TREE_BRANCH            = load("/img/SmallTreeBranch.png");






    private static Icon load(String path) {
        return IconLoader.getIcon(path);
    }

    private static Icon load(String key, String path) {
        Icon icon = load(path);
        REGISTERED_ICONS.put(key, icon);
        return icon;
    }

    public static Icon getIcon(String key) {
        return REGISTERED_ICONS.get(key);
    }

    private static Icon createRowIcon(Icon left, Icon right) {
        RowIcon rowIcon = new RowIcon(2);
        rowIcon.setIcon(left, 0);
        rowIcon.setIcon(right, 1);
        return rowIcon;
    }

}