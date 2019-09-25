package com.tank.message;

import com.tank.constants.OrderTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tank198435163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NormalOrder extends Order {


  @Override
  public OrderTypeEnum currentOrderTypeEnum() {
    return OrderTypeEnum.NormalOrder;
  }
}
