package com.tank.message;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tank.constants.OrderTypeEnum;
import com.tank.convertor.OrderDeserialization;
import lombok.Data;

import java.util.Objects;

/**
 * @author tank198435163.com
 */
@Data
@JsonDeserialize(using = OrderDeserialization.class)
public class Order {

  Integer fullPrice;

  Integer payment;

  Integer orderStatus;

  Integer entry;

  Long orderNo;

  Long customerId;

  Long addressId;

  String payNo;

  String payWay;

  String createTime;

  String updateTime;

  String dispatchTime;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Order)) {
      return false;
    }
    Order order = (Order) o;
    return getOrderNo().equals(order.getOrderNo());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getOrderNo());
  }

  /**
   * identify order type
   *
   * @return
   */
  protected OrderTypeEnum currentOrderTypeEnum() {
    return OrderTypeEnum.NormalOrder;
  }
}
