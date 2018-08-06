package com.usaparel.model.EO;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.adf.share.ADFContext;

import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Dec 20 14:12:34 PKT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CustScannedHeaderImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        DeliveryChallan,
        ScanHeaderId,
        CreatedBy,
        UpdateDate,
        VersionNumber,
        CreatedDate,
        UpdatedBy,
        ScanComplete,
        CustScannedLines;
        private static AttributesEnum[] vals = null;
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
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int UPDATEDATE = AttributesEnum.UpdateDate.index();
    public static final int VERSIONNUMBER = AttributesEnum.VersionNumber.index();
    public static final int CREATEDDATE = AttributesEnum.CreatedDate.index();
    public static final int UPDATEDBY = AttributesEnum.UpdatedBy.index();
    public static final int SCANCOMPLETE = AttributesEnum.ScanComplete.index();
    public static final int CUSTSCANNEDLINES = AttributesEnum.CustScannedLines.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CustScannedHeaderImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.usaparel.model.EO.CustScannedHeader");
    }

    /**
     * Gets the attribute value for DeliveryChallan, using the alias name DeliveryChallan.
     * @return the value of DeliveryChallan
     */
    public BigDecimal getDeliveryChallan() {
        return (BigDecimal) getAttributeInternal(DELIVERYCHALLAN);
    }

    /**
     * Sets <code>value</code> as the attribute value for DeliveryChallan.
     * @param value value to set the DeliveryChallan
     */
    public void setDeliveryChallan(BigDecimal value) {
        setAttributeInternal(DELIVERYCHALLAN, value);
    }

    /**
     * Gets the attribute value for ScanHeaderId, using the alias name ScanHeaderId.
     * @return the value of ScanHeaderId
     */
    public BigDecimal getScanHeaderId() {
        return (BigDecimal) getAttributeInternal(SCANHEADERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ScanHeaderId.
     * @param value value to set the ScanHeaderId
     */
    public void setScanHeaderId(BigDecimal value) {
        setAttributeInternal(SCANHEADERID, value);
    }

    /**
     * Gets the attribute value for CreatedBy, using the alias name CreatedBy.
     * @return the value of CreatedBy
     */
    public BigDecimal getCreatedBy() {
        return (BigDecimal) getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreatedBy.
     * @param value value to set the CreatedBy
     */
    public void setCreatedBy(BigDecimal value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for UpdateDate, using the alias name UpdateDate.
     * @return the value of UpdateDate
     */
    public Timestamp getUpdateDate() {
        return (Timestamp) getAttributeInternal(UPDATEDATE);
    }

    /**
     * Gets the attribute value for VersionNumber, using the alias name VersionNumber.
     * @return the value of VersionNumber
     */
    public BigDecimal getVersionNumber() {
        return (BigDecimal) getAttributeInternal(VERSIONNUMBER);
    }

    /**
     * Gets the attribute value for CreatedDate, using the alias name CreatedDate.
     * @return the value of CreatedDate
     */
    public Timestamp getCreatedDate() {
        return (Timestamp) getAttributeInternal(CREATEDDATE);
    }

    /**
     * Gets the attribute value for UpdatedBy, using the alias name UpdatedBy.
     * @return the value of UpdatedBy
     */
    public BigDecimal getUpdatedBy() {
        return (BigDecimal) getAttributeInternal(UPDATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for UpdatedBy.
     * @param value value to set the UpdatedBy
     */
    public void setUpdatedBy(BigDecimal value) {
        setAttributeInternal(UPDATEDBY, value);
    }

    /**
     * Gets the attribute value for ScanComplete, using the alias name ScanComplete.
     * @return the value of ScanComplete
     */
    public String getScanComplete() {
        return (String) getAttributeInternal(SCANCOMPLETE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ScanComplete.
     * @param value value to set the ScanComplete
     */
    public void setScanComplete(String value) {
        setAttributeInternal(SCANCOMPLETE, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getCustScannedLines() {
        return (RowIterator) getAttributeInternal(CUSTSCANNEDLINES);
    }


    /**
     * @param scanHeaderId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(BigDecimal scanHeaderId) {
        return new Key(new Object[] { scanHeaderId });
    }

    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        switch (operation){
        case DML_UPDATE:
            setUpdatedBy(new BigDecimal(ADFContext.getCurrent().getSessionScope().get("userid").toString()));            
            break;
        case DML_INSERT:
            setUpdatedBy(new BigDecimal(ADFContext.getCurrent().getSessionScope().get("userid").toString()));            
            setCreatedBy(new BigDecimal(ADFContext.getCurrent().getSessionScope().get("userid").toString()));            
            break;
        }
        super.doDML(operation, e);
    }
}
