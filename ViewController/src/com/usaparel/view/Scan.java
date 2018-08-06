package com.usaparel.view;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpSession;

import oracle.adf.model.AttributeBinding;
import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

public class Scan {
    private RichInputText itScanPack;

    public Scan() {
    }

    public void challanHeaderScan(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue()==null)
            return;
        
        if (valueChangeEvent.getNewValue().toString().equals("")){
            return;
        }
        
        int chid = Integer.parseInt(valueChangeEvent.getNewValue().toString());
        Map params = new HashMap();
        params.put("challan_id", chid);
        
        int alreadyExists = Integer.parseInt(executeOperationBinding("challanExists", params).toString());
        if (alreadyExists ==1){
            showMessage("This challan is already scanned");
            return;
        }
        
        
        //set bind variable 
        String bindVarEL = "#{bindings.ch_id.inputValue}";
        ADFUtil.setEL(bindVarEL,new oracle.jbo.domain.Number(Integer.parseInt(valueChangeEvent.getNewValue().toString())));
        
        
        //execute all challans vo 
        executeOperationBinding("ExecuteWithParams1",null);
        //executeOperationBinding("ExecuteWithParams",null);
        
        Row dcRow = (Row)ADFUtil.evaluateEL("#{bindings.VOChallansAll1Iterator.currentRow}");
        if (dcRow == null){
            showMessage("Wrong barcode");
            return;
        }
        ADFUtil.setEL("#{bindings.ChallanNumber.inputValue}",dcRow.getAttribute("ChallanNumber").toString() );
        ADFUtil.setEL("#{bindings.VehicleTypeName.inputValue}",(dcRow.getAttribute("VehicleTypeName")==null)?"":dcRow.getAttribute("VehicleTypeName").toString());
        ADFUtil.setEL("#{bindings.DriverContacts.inputValue}",(dcRow.getAttribute("DriverContacts")==null)?"": dcRow.getAttribute("DriverContacts").toString());
        ADFUtil.setEL("#{bindings.ChallanDescription.inputValue}",(dcRow.getAttribute("ChallanDescription")==null)?"" :dcRow.getAttribute("ChallanDescription").toString());
        ADFUtil.setEL("#{bindings.MediumTypeName.inputValue}",(dcRow.getAttribute("MediumTypeName")==null)?"0":dcRow.getAttribute("MediumTypeName").toString());
        ADFUtil.setEL("#{bindings.DeliveryDate.inputValue}",(dcRow.getAttribute("DeliveryDate")==null)?null: (Timestamp)dcRow.getAttribute("DeliveryDate"));
        ADFUtil.setEL("#{bindings.ChallanStatus.inputValue}",(dcRow.getAttribute("ChallanStatus")==null)?0: dcRow.getAttribute("ChallanStatus").toString());
        ADFUtil.setEL("#{bindings.VehicleNumber.inputValue}", (dcRow.getAttribute("VehicleNumber")==null)?"":dcRow.getAttribute("VehicleNumber").toString());
        
        
        
    }
    
    private Object executeOperationBinding(String operation, Map params){
        BindingContainer bc = getBindings();
        OperationBinding op = bc.getOperationBinding(operation);
        if (params != null)
            op.getParamsMap().putAll(params);
        
        return op.execute();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String commit() {
        
        
        
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Commit");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }
    private Row getCurrent(String it){
        DCIteratorBinding itr =  ((DCBindingContainer)getBindings()).findIteratorBinding(it);
        return itr.getCurrentRow();
    }
    private void showMessage(String message){
        FacesMessage Message = new FacesMessage(message);   
        Message.setSeverity(FacesMessage.SEVERITY_ERROR);   
        FacesContext fc = FacesContext.getCurrentInstance();   
        fc.addMessage(null, Message);    
        
    }
    
    public void newPackScanned(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue().toString().equals("")){
            return;
        }
        int pack_id = Integer.parseInt(valueChangeEvent.getNewValue().toString());
        Map params = new HashMap();
        params.put("packingID", pack_id);
        
        int alreadyExists = Integer.parseInt(executeOperationBinding("getScannedPacks", params).toString());
        if (alreadyExists ==1){
            showMessage("This Box is already scanned.");
            return;
        }
        
        Row challan = (Row)ADFUtil.evaluateEL("#{bindings.VOCustScannedHeader1Iterator.currentRow}");
        //(Row challan = getCurrent("VOCustScannedHeader1Iterator");
        System.out.println(challan.getAttribute("ChallanNumber")+" challan id");
        System.out.println(challan.getAttribute("DeliveryChallan")+" challan id");
        //find challan pack
        String bindVarChallanLinesEL = "#{bindings.cid.inputValue}";
        ADFUtil.setEL(bindVarChallanLinesEL,new oracle.jbo.domain.Number(Integer.parseInt(challan.getAttribute("DeliveryChallan").toString())));
        String bindVarPack = "#{bindings.pack_id.inputValue}";
        ADFUtil.setEL(bindVarPack,new oracle.jbo.domain.Number(pack_id));
        executeOperationBinding("ExecuteWithParams",null);
        
        Row pack = (Row)ADFUtil.evaluateEL("#{bindings.VOChallanLinesAll1Iterator.currentRow}");
        if (pack==null){
            showMessage("Wrong barcode");
            return;
        }
            
        DCBindingContainer dbc = (DCBindingContainer)getBindings();
        
        OperationBinding create = dbc.getOperationBinding("CreateInsert1");
        create.execute();
        Row newRow = (Row)ADFUtil.evaluateEL("#{bindings.VOCustScannedLines2Iterator.currentRow}");
        System.out.println((pack == null)+" ");
//        DCIteratorBinding iterator = dbc.findIteratorBinding("VOCustScannedLines2Iterator");
//        ViewObject vo =  iterator.getViewObject();
//        Row newRow = vo.createRow();
        newRow.setAttribute("PoHeaderId", (pack.getAttribute("PoHeaderId")==null)?"0":pack.getAttribute("PoHeaderId").toString());
        newRow.setAttribute("PoLineId", (pack.getAttribute("PoLineId")==null)?"0":pack.getAttribute("PoLineId").toString());
        newRow.setAttribute("ItemId", (pack.getAttribute("ItemId")==null)?"0":pack.getAttribute("ItemId").toString());
        newRow.setAttribute("PackingId", (pack.getAttribute("PackingId")==null)?"0":pack.getAttribute("PackingId").toString());
        newRow.setAttribute("PackingQty", (pack.getAttribute("PackingQty")==null)?"0":pack.getAttribute("PackingQty").toString());
        newRow.setAttribute("ScannedDate", new Date(System.currentTimeMillis()));
        newRow.setAttribute("Status", "SCANNED");
       
       //clear the field
       getItScanPack().resetValue();
       AdfFacesContext.getCurrentInstance().addPartialTarget(getItScanPack());
        
    }

    public void setItScanPack(RichInputText itScanPack) {
        this.itScanPack = itScanPack;
    }

    public RichInputText getItScanPack() {
        return itScanPack;
    }

    public String scanComplete() {
        commit();
        Row r = getCurrent("VOCustScannedLines2Iterator");
        if (r == null){
            showMessage("No Boxes Scanned...");
            return null;
        }
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("scanComplete");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public void logout() {
        setUserSession("userid", null);
        setUserSession("username", null);
    }
    
    private  void setUserSession(String key, String value) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ec = fctx.getExternalContext();
        HttpSession userSession = (HttpSession)ec.getSession(false);
        userSession.setAttribute(key, value);
    }
    
}


