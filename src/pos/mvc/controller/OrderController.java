/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.mvc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import pos.mvc.db.DBConnection;
import pos.mvc.model.OrderDetailModel;
import pos.mvc.model.OrderModel;

/**
 *
 * @author User
 */
public class OrderController {

    public String placeOrder(OrderModel orderModel, ArrayList<OrderDetailModel> orderDetailModels) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            String orderQuery = "INSERT INTO orders VALUES(?,?,?)";
            PreparedStatement statementForOder = connection.prepareStatement(orderQuery);
            statementForOder.setString(1, orderModel.getOrderId());
            statementForOder.setString(2, orderModel.getOrdeDate());
            statementForOder.setString(3, orderModel.getCustId());

            if (statementForOder.executeUpdate() > 0) {

                boolean isOrderDetailSaved = true;
                String orderDetailQuery = "INSERT INTO orderdetail VALUES(?,?,?,?)";

                for (OrderDetailModel orderDetailModel : orderDetailModels) {
                    PreparedStatement statementForOderDetail = connection.prepareStatement(orderDetailQuery);
                    statementForOderDetail.setString(1, orderModel.getOrderId());
                    statementForOderDetail.setString(2, orderDetailModel.getItemCode());
                    statementForOderDetail.setInt(3, orderDetailModel.getOrderQty());
                    statementForOderDetail.setDouble(4, orderDetailModel.getDiscount());

                    if (!(statementForOderDetail.executeUpdate() > 0)) {
                        isOrderDetailSaved = false;
                    }

                }
                if (isOrderDetailSaved) {
                    boolean isItemUpdated = true;
                    String itemQuery = "UPDATE item SET QtyOnHand = QtyOnHand - ? WHERE ItemCode = ?";
                    for (OrderDetailModel orderDetailModel : orderDetailModels) {
                        PreparedStatement statementForItem = connection.prepareStatement(itemQuery);
                        statementForItem.setInt(1, orderDetailModel.getOrderQty());
                        statementForItem.setString(2, orderDetailModel.getItemCode());
                        if (!(statementForItem.executeUpdate() >= 0)) {
                            isItemUpdated = false;
                        }

                    }
                    if (isItemUpdated) {
                        connection.commit();
                        return "Success";
                    } else {
                        connection.rollback();
                        return " Item Update Error";
                    }
                } else {
                    connection.rollback();
                    return " Order Detail Save Error";
                }

            } else {
                connection.rollback();
                return " Order Save Error";
            }

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return e.getMessage();
        } finally{
            connection.setAutoCommit(true);
        }

    }

}
