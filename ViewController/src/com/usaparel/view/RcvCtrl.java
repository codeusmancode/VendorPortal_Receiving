package com.usaparel.view;

import java.sql.Date;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.AttributeBinding;
import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class RcvCtrl {

    private RichInputText itBarcode;
    private ArrayList<PackInfo> packetsList;
    private Map map;
    public RcvCtrl() {
        if (map == null) {
            map = new HashMap<Integer,Integer>();
        }
        map.put(1,1);
        map.put(2,2);
        map.put(3,4);
        map.put(4,5);
    }

    public void newBarcode(ValueChangeEvent valueChangeEvent) {
        DCIteratorBinding iterator = null;
        
        int packID = Integer.parseInt(valueChangeEvent.getNewValue().toString());
        //TOBE: add sanity check for pack id
        
        //System.out.println(packID);
        BindingContainer bc = getBindings();
        DCBindingContainer dbc = (DCBindingContainer)bc;
        //GET THE ATTRIBUTE(BIND VARIABLE) BINDING AND OPERATION BINDINGS FROM RESPECTIVE 
        //BINDING CONTAINERS
        AttributeBinding ab =  (AttributeBinding)bc.get("bvPackingID");
        OperationBinding op = dbc.getOperationBinding("ExecuteWithParams");
        //ASSIGN PACK ID TO BIND VARIABLE BY USING setInputValue method
        ab.setInputValue(packID);
        //and execute the operation that will filter out the desired pack
        op.execute();
        
        //get the VOPacksAll iterator
        iterator = dbc.findIteratorBinding("VOPacksAll1Iterator");
        
        //iterate through VOPacksAll- there is just one record in the VO as packID is primary key
        //we could have use just iterator to retrive the only one row
        //but this is safe i guess...
//        if (iterator.getAllRowsInRange().length>=0){
//            showMessage("Pack isn't shipped yet.");
//            return ;
//        }
//        
        Row row = iterator.getCurrentRow();
        int poHeaderID = Integer.parseInt(row.getAttribute("PoHeaderId").toString());
        int poNumber = Integer.parseInt(row.getAttribute("Segment1").toString());
        String typeLookupCode = row.getAttribute("TypeLookupCode").toString();
        Timestamp poCreationDate = (Timestamp)row.getAttribute("CreationDate");
        int orgID = Integer.parseInt(row.getAttribute("OrgId").toString());
        int poLineID = Integer.parseInt(row.getAttribute("PoLineId").toString());
        int itemID = Integer.parseInt(row.getAttribute("ItemId").toString());
        String itemDescription = row.getAttribute("ItemDescription").toString();
        int shipID = Integer.parseInt(row.getAttribute("ShipId").toString());
        int shipQty = Integer.parseInt(row.getAttribute("ShipQty").toString());
        int packingID =Integer.parseInt(row.getAttribute("PackingId").toString());   
        int packingQty = Integer.parseInt(row.getAttribute("PackingQty").toString());
        int logon = Integer.parseInt(ADFContext.getCurrent().getSessionScope().get("userid").toString());
       
       /**
        * CALL APPLICATION MODULE METHOD USING METHOD BINDING THAT RETURNS TOTAL ROWS AGAINST A PACKING ID
        */
        OperationBinding checkDuplicateMethod = dbc.getOperationBinding("getScannedPacks");
        checkDuplicateMethod.getParamsMap().put("packingID", packingID);
        checkDuplicateMethod.execute();
        Object result = checkDuplicateMethod.getResult();
        int count = 0;
        if (result == null){
            System.out.println("result is null");
        }else{
            count = Integer.parseInt(result.toString());
        }
        
        //the pack has already been scanned.return from here.
        if (count >0 )
            return;//WE WON'T EXECUTE THE BELOW CODE IF COUNT IS GREATER THEN 0
        
        //create a new row in VOScannedPacks
        OperationBinding opCreate = dbc.getOperationBinding("CreateInsert");
        opCreate.execute();
        
        
        //AND GET THE CURRENT ROWS I.E NEWLY CREATED ROW
        iterator = dbc.findIteratorBinding("VOCustScannedPacks1Iterator");        
        Row r = iterator.getCurrentRow();
       
        r.setAttribute("PoHeaderId", poHeaderID);
        r.setAttribute("Segment1", poNumber);
        r.setAttribute("TypeLookupCode", typeLookupCode);
        r.setAttribute("CreationDate", poCreationDate);
        r.setAttribute("OrgId", orgID);
        r.setAttribute("PoLineId", poLineID);
        r.setAttribute("ItemId", itemID);
        r.setAttribute("ItemDescription", itemDescription);
        r.setAttribute("ShipId", shipID);
        r.setAttribute("ShipQty", shipQty);
         r.setAttribute("PackingId", packingID); 
        r.setAttribute("PackingQty", packingQty);
        //r.setAttribute("ScannedDate",new Date(System.currentTimeMillis()));
//        r.setAttribute("CreatedDate", new Date(System.currentTimeMillis()));
//        r.setAttribute("UpdatedDate", new Date(System.currentTimeMillis()));
//        r.setAttribute("UpdatedBy", logon);
//        r.setAttribute("CreatedBy",logon );
        

        
        /**
         * UPDATE INITIAL STATUS OF PACK (AT VENROD PORTAL TABLE) TO SCANNED
         */
        OperationBinding setInitialStatusMethod = dbc.getOperationBinding("initialStatus");
        setInitialStatusMethod.getParamsMap().put("pid", packingID);
        setInitialStatusMethod.getParamsMap().put("s", 4);//4 means SCANNED
        setInitialStatusMethod.execute();

        
        
    }

    

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    private void writeJavaScriptToClient(String script) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = null;
        erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        erks.addScript(fctx, script);
    }


    public void setItBarcode(RichInputText itBarcode) {
        this.itBarcode = itBarcode;
    }

    public RichInputText getItBarcode() {
        return itBarcode;
    }

    public void statusChanged(ValueChangeEvent valueChangeEvent) {
        
      
        ArrayList<PackInfo> list =   (ArrayList<PackInfo>) ADFContext.getCurrent().getSessionScope().get("packs");
        
        if (list == null || list.size()<=0){
            System.out.println("list is empty");
            list = new ArrayList<PackInfo>();
        }
   
        DCBindingContainer bc = (DCBindingContainer)getBindings();
        DCIteratorBinding it =  bc.findIteratorBinding("VOCustScannedPacks1Iterator");
        Row curr = it.getCurrentRow();
        int packingID = Integer.parseInt(curr.getAttribute("PackingId").toString());
        int packingQty = Integer.parseInt(curr.getAttribute("PackingQty").toString());
        int status = Integer.parseInt(valueChangeEvent.getNewValue().toString());
        
        int shipID = Integer.parseInt(curr.getAttribute("ShipId").toString());
        
        PackInfo pi = new PackInfo(shipID,packingID,status,packingQty,0);
        
        
        //remove current packing from the list if it is there and add packing with fresh status
        
        for (Iterator<PackInfo> itr =  list.iterator();itr.hasNext();) {
            int packID = itr.next().getPackingID();
            if (packID == packingID){
                System.out.println("removing: "+packID);
                itr.remove();
            }
        }
        
        
        if (Integer.parseInt(map.get(pi.getStatus_id()).toString()) != 5){//only add scanned lines other then partial return becaue that list is being populated at returnValueEntered method
        System.out.println("adding "+pi);
            list.add(pi);    
        }
        
        ADFContext.getCurrent().getSessionScope().put("packs", list);
        
    }

    public String commitAction() {
       
        ArrayList<PackInfo> list =   (ArrayList<PackInfo>) ADFContext.getCurrent().getSessionScope().get("packs");
        ArrayList<PackInfo> partialReturnlist =   (ArrayList<PackInfo>) ADFContext.getCurrent().getSessionScope().get("partialReturnedPacks");
         if (list != null){
             /**
              * Adjust Shipment quantity and pack status one by one
              */
              for (PackInfo pi: list){
                  
                  adjust(pi);
                  System.out.println(pi+" ADJUSTED.");
              }
             
             list.clear();
             ADFContext.getCurrent().getSessionScope().put("packs", list);
         }
         
        if (partialReturnlist != null){
            
             for (PackInfo pi: partialReturnlist){
                 
                 adjust(pi);
                 System.out.println(pi+" ADJUSTED.");
             }
            
            partialReturnlist.clear();
            ADFContext.getCurrent().getSessionScope().put("packs", partialReturnlist);
            
        }
        
        
      
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Commit");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
       return null;
    }

    public String adjust(PackInfo p) {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("adjustQuantity");
        Map params = operationBinding.getParamsMap();
        params.put("newShipID", p.getShipID());
        params.put("newPackingID", p.getPackingID());
        params.put("newStatus", p.getStatus_id());
        params.put("newPackingQty", p.getPackingQuantity());
        params.put("newQuantity", p.getReturnQuantity());
        
        if (p.getStatus_id()==0){
            return null;
        }
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }
    
    
    private void showMessage(String message){
        FacesMessage Message = new FacesMessage(message);   
        Message.setSeverity(FacesMessage.SEVERITY_ERROR);   
        FacesContext fc = FacesContext.getCurrentInstance();   
        fc.addMessage(null, Message);    
        
    }

    public void returnValueEntered(ValueChangeEvent valueChangeEvent) {
        ArrayList<PackInfo> list =   (ArrayList<PackInfo>) ADFContext.getCurrent().getSessionScope().get("partialReturnedPacks");
        
        if (list == null || list.size()<=0){
            System.out.println("partial returned packs are empty.");
            list = new ArrayList<PackInfo>();
        }
        
        DCBindingContainer bc = (DCBindingContainer)getBindings();
        DCIteratorBinding it =  bc.findIteratorBinding("VOCustScannedPacks1Iterator");
        Row curr = it.getCurrentRow();
        int packingID = Integer.parseInt(curr.getAttribute("PackingId").toString());
        int packingQty = Integer.parseInt(curr.getAttribute("PackingQty").toString());
        int returnValue = Integer.parseInt(valueChangeEvent.getNewValue().toString());
        int shipID = Integer.parseInt(curr.getAttribute("ShipId").toString());
        
        
        //remove current packing from the list if it is there and add packing with fresh status
        
        for (Iterator<PackInfo> itr =  list.iterator();itr.hasNext();) {
            int packID = itr.next().getPackingID();
            if (packID == packingID){
                System.out.println("removing: "+packID);
                itr.remove();
            }
        }
   
        PackInfo pi = new PackInfo(shipID,packingID,5,packingQty,returnValue);    
        if (returnValue>0){
            list.add(pi);    
        }
        
        
        
        ADFContext.getCurrent().getSessionScope().put("partialReturnedPacks", list);
        

    }
    
    
    public String callCommit(){
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Commit");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("errorsssssssss");
            return null;
        }
        return null;
    }
}
