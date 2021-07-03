package com.gouden.packet;

import java.io.Serializable;

public class InvokeResponse implements Serializable {

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
