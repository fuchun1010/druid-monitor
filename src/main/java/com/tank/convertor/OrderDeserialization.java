package com.tank.convertor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.tank.message.GroupOrder;
import com.tank.message.NormalOrder;
import com.tank.message.Order;

import java.io.IOException;
import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class OrderDeserialization extends StdDeserializer<Order> {

  public OrderDeserialization() {
    this(Order.class);
  }

  public OrderDeserialization(Class<?> vc) {
    super(vc);
  }

  @Override
  public Order deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

    Order order = null;

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
    order = isGroupOrder ? new GroupOrder() : new NormalOrder();

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
      GroupOrder groupOrder = ((GroupOrder) order);
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
