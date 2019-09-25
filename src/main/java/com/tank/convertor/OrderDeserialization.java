package com.tank.convertor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.tank.message.NormalOrder;
import com.tank.message.Order;

import java.io.IOException;

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
    JsonNode root = jsonParser.getCodec().readTree(jsonParser);
    return new NormalOrder();
  }
}
