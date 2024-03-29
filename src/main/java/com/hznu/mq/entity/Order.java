package com.hznu.mq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LIN
 * @date 2023/8/7 14:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单编号
     */
    private Integer orderNumber;

    /**
     * 订单价格
     */
    private Double price;

    /**
     * 订单号创建时间
     */
    private Date createTime;

    /**
     * 订单描述
     */
    private String desc;
}
