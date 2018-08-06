package com.usaparel.view;

public class PackInfo {
    private int status_id;
    private int packingID;
    private int packingQuantity;
    private int shipID;
    private int returnQuantity;
    
    public PackInfo() {
        super();
    }
    public PackInfo(int newShipID,int newPackingID,int newStatus, int newPackingQty,int newReturnQuantity){
        this.status_id = newStatus;
        this.packingID = newPackingID;
        this.packingQuantity = newPackingQty;
        this.shipID = newShipID;
        this.returnQuantity = newReturnQuantity;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setReturnQuantity(int returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public int getReturnQuantity() {
        return returnQuantity;
    }

    public void setPackingID(int packingID) {
        this.packingID = packingID;
    }

    public int getPackingID(){
        return this.packingID;
    }

    public void setPackingQuantity(int packingQuantity) {
        this.packingQuantity = packingQuantity;
    }

    public int getPackingQuantity() {
        return packingQuantity;
    }


    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    public int getShipID() {
        return shipID;
    }

    public String toString(){
        return "pack_id:"+getPackingID()+", packing_quantity:"+getPackingQuantity()+"- ,status:"+getStatus_id()+"- returnQuantity:"+getReturnQuantity()+"- ,ship_id"+getShipID();
    }
}
