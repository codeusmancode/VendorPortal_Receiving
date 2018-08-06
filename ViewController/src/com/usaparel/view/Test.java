package com.usaparel.view;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.BindingContainer;
import oracle.binding.AttributeBinding;
import oracle.binding.OperationBinding;

import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

public class Test {
    public Test() {
    }

    public String b2_action() {
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bc = bctx.getCurrentBindingsEntry();
        DCBindingContainer dbc =(DCBindingContainer) bctx.getCurrentBindingsEntry();
        DCIteratorBinding iterator = dbc.findIteratorBinding("VOPacksAll1Iterator");
        
        
        
        
        AttributeBinding ab = (AttributeBinding) bc.get("bvPackingID");

        ab.setInputValue(1925);

        OperationBinding operationBinding = bc.getOperationBinding("ExecuteWithParams");

        Object result = operationBinding.execute();
        
        ViewObject vo = iterator.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);
        while (rsi.hasNext()){
            String item = rsi.next().getAttribute("ItemDescription").toString();
            System.out.println(item);
        }
        
        
        return null;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String b3_action() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Commit");

        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String b1_action() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("ExecuteWithParams");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public void valueChanged(ValueChangeEvent valueChangeEvent) {
        int pid = Integer.parseInt(valueChangeEvent.getNewValue().toString());
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bc = bctx.getCurrentBindingsEntry();
        DCBindingContainer dbc =(DCBindingContainer) bctx.getCurrentBindingsEntry();
        DCIteratorBinding iterator = dbc.findIteratorBinding("VOPacksAll1Iterator");
        
        
        
        
        AttributeBinding ab = (AttributeBinding) bc.get("bvPackingID");

        ab.setInputValue(pid);

        OperationBinding operationBinding = bc.getOperationBinding("ExecuteWithParams");

        Object result = operationBinding.execute();
        
        ViewObject vo = iterator.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);
        while (rsi.hasNext()){
            String item = rsi.next().getAttribute("ItemDescription").toString();
            System.out.println(item);
        }
        
    }
}
