/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.mvc.model;

/**
 *
 * @author User
 */
public class OrderModel {
    private String orderId;
    private String ordeDate;
    private String custId;

    public OrderModel() {
    }

    public OrderModel(String orderId, String ordeDate, String custId) {
        this.orderId = orderId;
        this.ordeDate = ordeDate;
        this.custId = custId;
    }

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the ordeDate
     */
    public String getOrdeDate() {
        return ordeDate;
    }

    /**
     * @param ordeDate the ordeDate to set
     */
    public void setOrdeDate(String ordeDate) {
        this.ordeDate = ordeDate;
    }

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    @Override
    public String toString() {
        return "OrderModel{" + "orderId=" + orderId + ", ordeDate=" + ordeDate + ", custId=" + custId + '}';
    }
    
    
}
