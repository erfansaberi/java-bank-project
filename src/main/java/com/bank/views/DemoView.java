package com.bank.views;

import com.bank.models.DemoModel;

public class DemoView {
    public static String demo() {
        DemoModel model = new DemoModel();
        return model.hello();
    }
}
