package com.tank.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import com.tank.constants.OrderTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tank198435163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupOrderReq extends OrderReq {

  @JsonIgnore
  public boolean isEnough() {
    return this.capacity > 1 && this.participants.size() == capacity - 1;
  }

  public void addParticipant(@NotNull final Long customerId) {
    this.participants.add(customerId);
  }

  public List<Long> listParticipants() {
    return this.participants.stream().collect(Collectors.toList());
  }

  @Override
  public OrderTypeEnum currentOrderTypeEnum() {
    return OrderTypeEnum.GroupOrder;
  }

  private Set<Long> participants = Sets.newHashSet();

  private Integer capacity = 1;
}
