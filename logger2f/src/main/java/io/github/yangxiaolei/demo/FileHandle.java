package io.github.yangxiaolei.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yanglei on 2017/7/3.
 */

public class FileHandle {
    private File mFile;
    private PrintWriter mWriter;

    private boolean mIsOpen;

    public FileHandle(File file) {
        mFile = file;
    }

    public boolean isOpen() {
        return mIsOpen;
    }

    public void open() throws IOException {
        if (mIsOpen) return;
        if (!mFile.exists()) mFile.createNewFile();
        mWriter = new PrintWriter(new FileOutputStream(mFile, true));
        mIsOpen = true;
    }

    public void close() {
        if (!mIsOpen) return;
        if (mWriter != null) {
            mWriter.close();
        }
        mIsOpen = false;
    }

    public void append(String text) throws IllegalStateException {
        if (!isOpen()) throw new IllegalStateException("file is not open!");
        mWriter.println(text);
        mWriter.flush();
    }
}
