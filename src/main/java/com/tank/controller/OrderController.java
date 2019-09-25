package com.tank.controller;

import com.google.common.collect.Maps;
import com.tank.message.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Map;

import static com.tank.constants.UrlPrefix.URL_PREFIX;

/**
 * @author tank198435163.com
 */
@RestController
@RequestMapping(value = URL_PREFIX)
public class OrderController {

  @PostMapping(value = "/createOrder")
  public ResponseEntity<Map<String, Object>> createOrder(@RequestBody @NotNull final Order order) {
    Map<String, Object> body = Maps.newHashMap();
    body.putIfAbsent("type", order.currentOrderTypeEnum().desc);
    return ResponseEntity.ok(body);
  }
}
