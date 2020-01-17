package com.ysy.common_base.base;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;


import java.io.Serializable;


public class TResponse<Data> extends BaseObservable implements Serializable {


    @Bindable
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
        notifyPropertyChanged(BR.data);
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
