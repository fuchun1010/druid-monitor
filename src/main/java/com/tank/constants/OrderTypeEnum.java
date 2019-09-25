package com.tank.constants;

/**
 * @author tank198435163.com
 */
@Comment(desc = "订单类型描述")
public enum OrderTypeEnum {

  GroupOrder(1, "拼团单"),
  NormalOrder(2, "常规单"),
  PreSaleOrder(3, "预售单");

  OrderTypeEnum(int orderType, String desc) {
    this.orderType = orderType;
    this.desc = desc;
  }

  public int orderType;

  public String desc;
}
