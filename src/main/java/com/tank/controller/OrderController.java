package com.tank.controller;

import com.google.common.collect.Maps;
import com.tank.constants.Comment;
import com.tank.message.OrderReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

import static com.tank.constants.UrlPrefix.URL_PREFIX;

/**
 * @author tank198435163.com
 */
@Comment(desc = "订单域")
@RestController
@RequestMapping(value = URL_PREFIX)
public class OrderController {

  @PostMapping(value = "/createOrder")
  public ResponseEntity<Map<String, Object>> createOrder(@RequestBody @NotNull final OrderReq order) {
    Map<String, Object> body = Maps.newHashMap();
    body.putIfAbsent("type", order.currentOrderTypeEnum().desc);
    return ResponseEntity.ok(body);
  }

  @GetMapping(value = "/show")
  public ResponseEntity<Map<String, String>> show() {
    Map<String, String> body = Maps.newHashMap();
    body.putIfAbsent("target", System.getProperty("DruidMonitor.home"));
    return ResponseEntity.ok(body);
  }

}
