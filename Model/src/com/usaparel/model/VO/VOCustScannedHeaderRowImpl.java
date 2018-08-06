package com.usaparel.model.VO;

import com.usaparel.model.EO.CustScannedHeaderImpl;

import com.usaparel.model.VO.common.VOCustScannedHeaderRow;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Timestamp;

import java.sql.Types;

import oracle.jbo.RowIterator;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Dec 21 12:03:26 PKT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class VOCustScannedHeaderRowImpl extends ViewRowImpl implements VOCustScannedHeaderRow {


    public static final int ENTITY_CUSTSCANNEDHEADER = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        DeliveryChallan,
        ScanHeaderId,
        ChallanId,
        ChallanNumber,
        VehicleType,
        DriverContacts,
        ChallanDescription,
        MediumType,
        DeliveryDate,
        ChallanStatus,
        VehicleNumber,
        SupplierId,
        VersionNumber,
        CreatedBy,
        UpdatedBy,
        CreationDate,
        UpdateDate,
        MediumTypeName,
        VehicleTypeName,
        ScanOk,
        ScanComplete,
        VOCustScannedLines;
        private static AttributesEnum[] vals = null;
        ;
        private static final int firstIndex = 0;

        protected int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        protected static final int firstIndex() {
            return firstIndex;
        }

        protected static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        protected static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int DELIVERYCHALLAN = AttributesEnum.DeliveryChallan.index();
    public static final int SCANHEADERID = AttributesEnum.ScanHeaderId.index();
    public static final int CHALLANID = AttributesEnum.ChallanId.index();
    public static final int CHALLANNUMBER = AttributesEnum.ChallanNumber.index();
    public static final int VEHICLETYPE = AttributesEnum.VehicleType.index();
    public static final int DRIVERCONTACTS = AttributesEnum.DriverContacts.index();
    public static final int CHALLANDESCRIPTION = AttributesEnum.ChallanDescription.index();
    public static final int MEDIUMTYPE = AttributesEnum.MediumType.index();
    public static final int DELIVERYDATE = AttributesEnum.DeliveryDate.index();
    public static final int CHALLANSTATUS = AttributesEnum.ChallanStatus.index();
    public static final int VEHICLENUMBER = AttributesEnum.VehicleNumber.index();
    public static final int SUPPLIERID = AttributesEnum.SupplierId.index();
    public static final int VERSIONNUMBER = AttributesEnum.VersionNumber.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int UPDATEDBY = AttributesEnum.UpdatedBy.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int UPDATEDATE = AttributesEnum.UpdateDate.index();
    public static final int MEDIUMTYPENAME = AttributesEnum.MediumTypeName.index();
    public static final int VEHICLETYPENAME = AttributesEnum.VehicleTypeName.index();
    public static final int SCANOK = AttributesEnum.ScanOk.index();
    public static final int SCANCOMPLETE = AttributesEnum.ScanComplete.index();
    public static final int VOCUSTSCANNEDLINES = AttributesEnum.VOCustScannedLines.index();

    /**
     * This is the default constructor (do not remove).
     */
    public VOCustScannedHeaderRowImpl() {
    }

    /**
     * Gets CustScannedHeader entity object.
     * @return the CustScannedHeader
     */
    public CustScannedHeaderImpl getCustScannedHeader() {
        return (CustScannedHeaderImpl) getEntity(ENTITY_CUSTSCANNEDHEADER);
    }

    /**
     * Gets the attribute value for DELIVERY_CHALLAN using the alias name DeliveryChallan.
     * @return the DELIVERY_CHALLAN
     */
    public BigDecimal getDeliveryChallan() {
        return (BigDecimal) getAttributeInternal(DELIVERYCHALLAN);
    }

    /**
     * Sets <code>value</code> as attribute value for DELIVERY_CHALLAN using the alias name DeliveryChallan.
     * @param value value to set the DELIVERY_CHALLAN
     */
    public void setDeliveryChallan(BigDecimal value) {
        setAttributeInternal(DELIVERYCHALLAN, value);
    }

    /**
     * Gets the attribute value for SCAN_HEADER_ID using the alias name ScanHeaderId.
     * @return the SCAN_HEADER_ID
     */
    public BigDecimal getScanHeaderId() {
        return (BigDecimal) getAttributeInternal(SCANHEADERID);
    }

    /**
     * Sets <code>value</code> as attribute value for SCAN_HEADER_ID using the alias name ScanHeaderId.
     * @param value value to set the SCAN_HEADER_ID
     */
    public void setScanHeaderId(BigDecimal value) {
        setAttributeInternal(SCANHEADERID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ChallanId.
     * @return the ChallanId
     */
    public BigDecimal getChallanId() {
        return (BigDecimal) getAttributeInternal(CHALLANID);
    }

    /**
     * Gets the attribute value for the calculated attribute ChallanNumber.
     * @return the ChallanNumber
     */
    public String getChallanNumber() {
        return (String) getAttributeInternal(CHALLANNUMBER);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ChallanNumber.
     * @param value value to set the  ChallanNumber
     */
    public void setChallanNumber(String value) {
        setAttributeInternal(CHALLANNUMBER, value);
    }

    /**
     * Gets the attribute value for the calculated attribute VehicleType.
     * @return the VehicleType
     */
    public BigDecimal getVehicleType() {
        return (BigDecimal) getAttributeInternal(VEHICLETYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute VehicleType.
     * @param value value to set the  VehicleType
     */
    public void setVehicleType(BigDecimal value) {
        setAttributeInternal(VEHICLETYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DriverContacts.
     * @return the DriverContacts
     */
    public String getDriverContacts() {
        return (String) getAttributeInternal(DRIVERCONTACTS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DriverContacts.
     * @param value value to set the  DriverContacts
     */
    public void setDriverContacts(String value) {
        setAttributeInternal(DRIVERCONTACTS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ChallanDescription.
     * @return the ChallanDescription
     */
    public String getChallanDescription() {
        return (String) getAttributeInternal(CHALLANDESCRIPTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ChallanDescription.
     * @param value value to set the  ChallanDescription
     */
    public void setChallanDescription(String value) {
        setAttributeInternal(CHALLANDESCRIPTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MediumType.
     * @return the MediumType
     */
    public BigDecimal getMediumType() {
        return (BigDecimal) getAttributeInternal(MEDIUMTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MediumType.
     * @param value value to set the  MediumType
     */
    public void setMediumType(BigDecimal value) {
        setAttributeInternal(MEDIUMTYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DeliveryDate.
     * @return the DeliveryDate
     */
    public Timestamp getDeliveryDate() {
        return (Timestamp) getAttributeInternal(DELIVERYDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DeliveryDate.
     * @param value value to set the  DeliveryDate
     */
    public void setDeliveryDate(Timestamp value) {
        setAttributeInternal(DELIVERYDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ChallanStatus.
     * @return the ChallanStatus
     */
    public String getChallanStatus() {
        return (String) getAttributeInternal(CHALLANSTATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ChallanStatus.
     * @param value value to set the  ChallanStatus
     */
    public void setChallanStatus(String value) {
        setAttributeInternal(CHALLANSTATUS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute VehicleNumber.
     * @return the VehicleNumber
     */
    public String getVehicleNumber() {
        return (String) getAttributeInternal(VEHICLENUMBER);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute VehicleNumber.
     * @param value value to set the  VehicleNumber
     */
    public void setVehicleNumber(String value) {
        setAttributeInternal(VEHICLENUMBER, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SupplierId.
     * @return the SupplierId
     */
    public BigDecimal getSupplierId() {
        return (BigDecimal) getAttributeInternal(SUPPLIERID);
    }

    /**
     * Gets the attribute value for the calculated attribute MediumTypeName.
     * @return the MediumTypeName
     */
    public String getMediumTypeName() {
        return (String) getAttributeInternal(MEDIUMTYPENAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MediumTypeName.
     * @param value value to set the  MediumTypeName
     */
    public void setMediumTypeName(String value) {
        setAttributeInternal(MEDIUMTYPENAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute VehicleTypeName.
     * @return the VehicleTypeName
     */
    public String getVehicleTypeName() {
        return (String) getAttributeInternal(VEHICLETYPENAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute VehicleTypeName.
     * @param value value to set the  VehicleTypeName
     */
    public void setVehicleTypeName(String value) {
        setAttributeInternal(VEHICLETYPENAME, value);
    }


    /**
     * Gets the attribute value for the calculated attribute ScanOk.
     * @return the ScanOk
     */
    public String getScanOk() {
        return (String) getAttributeInternal(SCANOK);
    }

    /**
     * Gets the attribute value for SCAN_COMPLETE using the alias name ScanComplete.
     * @return the SCAN_COMPLETE
     */
    public String getScanComplete() {
        return (String) getAttributeInternal(SCANCOMPLETE);
    }

    /**
     * Sets <code>value</code> as attribute value for SCAN_COMPLETE using the alias name ScanComplete.
     * @param value value to set the SCAN_COMPLETE
     */
    public void setScanComplete(String value) {
        setAttributeInternal(SCANCOMPLETE, value);
    }

    /**
     * Gets the attribute value for VERSION_NUMBER using the alias name VersionNumber.
     * @return the VERSION_NUMBER
     */
    public BigDecimal getVersionNumber() {
        return (BigDecimal) getAttributeInternal(VERSIONNUMBER);
    }

    /**
     * Sets <code>value</code> as attribute value for VERSION_NUMBER using the alias name VersionNumber.
     * @param value value to set the VERSION_NUMBER
     */
    public void setVersionNumber(BigDecimal value) {
        setAttributeInternal(VERSIONNUMBER, value);
    }


    /**
     * Gets the attribute value for the calculated attribute CreatedBy.
     * @return the CreatedBy
     */
    public BigDecimal getCreatedBy() {
        return (BigDecimal) getAttributeInternal(CREATEDBY);
    }

    /**
     * Gets the attribute value for the calculated attribute UpdatedBy.
     * @return the UpdatedBy
     */
    public BigDecimal getUpdatedBy() {
        return (BigDecimal) getAttributeInternal(UPDATEDBY);
    }

    /**
     * Gets the attribute value for the calculated attribute CreationDate.
     * @return the CreationDate
     */
    public Timestamp getCreationDate() {
        return (Timestamp) getAttributeInternal(CREATIONDATE);
    }

    /**
     * Gets the attribute value for the calculated attribute UpdateDate.
     * @return the UpdateDate
     */
    public Timestamp getUpdateDate() {
        return (Timestamp) getAttributeInternal(UPDATEDATE);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link VOCustScannedLines.
     */
    public RowIterator getVOCustScannedLines() {
        return (RowIterator) getAttributeInternal(VOCUSTSCANNEDLINES);
    }
    
    public void scanComplete(String challan_id){
        System.out.println(challan_id+" dfsdfsdf");
        
        int returnStatus;
        CallableStatement cst = null;
        try {
            cst =this.getDBTransaction().createCallableStatement("begin " + "cust_complete_scan(?)" + "; end;", 0);
            
            cst.setObject(1, challan_id);
            
            cst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            if (cst != null) {
                try {
                    cst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        setScanComplete("Y");
        getDBTransaction().commit();
    }
}

