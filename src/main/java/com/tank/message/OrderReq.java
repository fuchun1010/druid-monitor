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
  public abstract OrderTypeEnum currentOrderTypeEnum();

  private Integer fullPrice;

  private Integer payment;

  private Integer orderStatus;

  private Integer entry;

  private Long orderNo;

  private Long customerId;

  private Long addressId;

  private String payNo;

  private String payWay;

  private String createTime;

  private String updateTime;

  private String dispatchTime;

  private String storeCode;

}
