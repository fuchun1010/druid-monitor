package com.tank.message;

import com.tank.constants.OrderTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tank198435163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PreSaleOrder extends Order {
  
  @Override
  protected OrderTypeEnum currentOrderTypeEnum() {
    return OrderTypeEnum.PreSaleOrder;
  }
}
