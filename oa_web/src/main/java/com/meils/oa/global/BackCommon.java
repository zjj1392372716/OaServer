package com.meils.oa.global;

import java.util.List;

public class BackCommon<T> {


    private T com;

    public BackCommon(T t) {
        this.com = t;
    }

    public T getCom() {
        return com;
    }

    public void setCom(T com) {
        this.com = com;
    }
}
