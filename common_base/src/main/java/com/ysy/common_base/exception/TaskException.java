package com.ysy.common_base.exception;

/**
 * Created by xianglanzuo on 2018/1/2.
 */

public class TaskException extends RuntimeException {

    private final int mErrorCode;

    private final String mMessage;

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public TaskException(int errorCode, String message) {
        mErrorCode = errorCode;
        mMessage = message;
    }

    @Override
    public String toString() {
        return "TaskException{" +
                "mErrorCode=" + mErrorCode +
                ", mMessage='" + mMessage + '\'' +
                '}';
    }
}
