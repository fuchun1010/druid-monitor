package com.tank.convertor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.tank.constants.Comment;
import com.tank.message.GroupOrderReq;
import com.tank.message.NormalOrderReq;
import com.tank.message.OrderReq;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * @author tank198435163.com
 */
@Component
@Comment(desc = "订单反序列化")
public class OrderDeserialization extends StdDeserializer<OrderReq> {

  public OrderDeserialization() {
    this(OrderReq.class);
  }

  public OrderDeserialization(Class<?> vc) {
    super(vc);
  }

  @Override
  public OrderReq deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

    OrderReq order = null;

    JsonNode root = jsonParser.getCodec().readTree(jsonParser);

    Integer fullPrice = root.get("fullPrice").intValue();
    Integer payment = root.get("payment").intValue();
    Integer orderStatus = root.get("orderStatus").intValue();
    Integer entry = root.get("entry").intValue();

    Long addressId = root.get("addressId").longValue();
    Long customerId = root.get("customerId").longValue();
    Long orderNo = root.get("orderNo").longValue();

    String payNo = root.get("payNo").textValue();
    String payWay = root.get("payWay").textValue();
    String dispatchTime = root.get("dispatchTime").textValue();

    boolean isGroupOrder = Objects.nonNull(root.get("capacity")) && Objects.nonNull(root.get("participants")) && root.get("participants").isArray();
    order = isGroupOrder ? new GroupOrderReq() : new NormalOrderReq();

    order.setFullPrice(fullPrice);
    order.setAddressId(addressId);
    order.setCustomerId(customerId);
    order.setPayNo(payNo);
    order.setDispatchTime(dispatchTime);
    order.setOrderNo(orderNo);
    order.setPayWay(payWay);
    order.setOrderStatus(orderStatus);
    order.setEntry(entry);
    order.setPayment(payment);

    if (isGroupOrder) {
      int capacity = root.get("capacity").intValue();
      GroupOrderReq groupOrder = ((GroupOrderReq) order);
      groupOrder.setCapacity(capacity);
      JsonNode arr = root.get("participants");
      for (JsonNode node : arr) {
        groupOrder.addParticipant(node.longValue());
      }
      return groupOrder;
    }


    return order;
  }

}
