package com.tank.controller;

import com.tank.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tank198435163.com
 */
@RestController
@RequestMapping(value = "/monitor/v1")
public class Index {

  @GetMapping(value = "/storesCounter")
  public ResponseEntity<Map<String, Integer>> storesCounter() {

    Map<String, Integer> body = new HashMap<>(initialCapacity);

    body.putIfAbsent("cnt", storeService.totalStores());

    return ResponseEntity.ok(body);
  }

  @Autowired
  private StoreService storeService;

  private int initialCapacity = 32;

}
