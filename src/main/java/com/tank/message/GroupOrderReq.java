package com.tank.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import com.tank.constants.Comment;
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
@Comment(desc = "平团订单")
@EqualsAndHashCode(callSuper = true)
public class GroupOrderReq extends OrderReq {

  @JsonIgnore
  @Comment(desc = "是否凑够订单")
  public boolean isEnough() {
    return this.capacity > 1 && this.participants.size() == capacity - 1;
  }

  @Comment(desc = "添加一个凑单客户")
  public void addParticipant(@NotNull final Long customerId) {
    if (!this.isEnough()) {
      this.participants.add(customerId);
    }
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
