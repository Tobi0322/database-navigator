package com.dci.intellij.dbn.vfs;

import com.dci.intellij.dbn.common.DevNullStreams;
import com.dci.intellij.dbn.editor.DBContentType;
import com.dci.intellij.dbn.object.DBDataset;

import java.io.IOException;
import java.io.OutputStream;

public class DatasetFile extends DatabaseContentFile {
    public DatasetFile(DatabaseEditableObjectFile databaseFile, DBContentType contentType) {
        super(databaseFile, contentType);
    }

    public DBDataset getObject() {
        return (DBDataset) super.getObject();
    }

    /*********************************************************
     *                     VirtualFile                       *
     *********************************************************/
    public OutputStream getOutputStream(Object requestor, long newModificationStamp, long newTimeStamp) throws IOException {
        return DevNullStreams.OUTPUT_STREAM;
    }

    public byte[] contentsToByteArray() throws IOException {
        return new byte[0];
    }

    public long getLength() {
        return 0;
    }
}
