package com.tank.message;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tank.constants.Comment;
import com.tank.constants.OrderTypeEnum;
import com.tank.convertor.OrderDeserialization;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author tank198435163.com
 */
@Data
@JsonDeserialize(using = OrderDeserialization.class)
public abstract class OrderReq {

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof OrderReq)) {
      return false;
    }
    OrderReq order = (OrderReq) obj;
    return getOrderNo().equals(order.getOrderNo());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getOrderNo());
  }

  /**
   * identify every order type
   *
   * @return
   */
  @Comment(desc = "获取当前订单类型")
  public abstract OrderTypeEnum currentOrderTypeEnum();

  private Integer fullPrice;

  private Integer payment;

  private Integer orderStatus;

  @Comment(desc = "订单来源")
  private Integer entry;

  @Comment(desc = "配送方式")
  private Integer dispatchWay;

  private Long orderNo;

  private Long customerId;

  private Long addressId;

  private String payNo;

  private String payWay;

  private LocalDateTime createTime;

  private String updateTime;

  @Comment(desc = "配送时间")
  private String dispatchTime;

  private String storeCode;

}
