package com.usaparel.view;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCErrorHandlerImpl;

import java.sql.SQLException;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCErrorHandlerImpl;

import oracle.jbo.JboException;


public class ErrorHandler extends DCErrorHandlerImpl {

    public ErrorHandler() {

        this(true);

    }


    public ErrorHandler(boolean setToThrow) {

        super(setToThrow);

    }


    @Override

    public void reportException(DCBindingContainer dCBindingContainer,

        Exception exception) {

        super.reportException(dCBindingContainer, exception);

    }


    public String getDisplayMessage(BindingContext ctx, Exception ex) {

        String message = "";

        if (ex instanceof oracle.jbo.TooManyObjectsException) {
            message = "Pack was already scanned";
        }

        return message;

    }

}
