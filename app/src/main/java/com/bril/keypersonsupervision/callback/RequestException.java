package com.bril.keypersonsupervision.callback;

import com.bril.keypersonsupervision.bean.BaseBean;
import com.google.gson.Gson;

public class RequestException extends IllegalStateException {
    private BaseBean errorBean;

    public RequestException(String s) {
        super(s);
        errorBean = new Gson().fromJson(s, BaseBean.class);
    }

    public BaseBean getErrorBean() {
        return errorBean;
    }
}
