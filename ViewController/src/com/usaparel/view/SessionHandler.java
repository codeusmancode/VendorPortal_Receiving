package com.usaparel.view;

import oracle.adf.share.ADFContext;

public class SessionHandler {
    public SessionHandler() {
    }

    @SuppressWarnings("unchecked")
    public void saveSessionValues(String userid,String username) {
        ADFContext.getCurrent().getSessionScope().put("userid", userid);
        ADFContext.getCurrent().getSessionScope().put("username", username);
    }
}
