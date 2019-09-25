package com.tank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StoreService {

  public int totalStores() {
    List<Integer> counter = jdbcTemplate.query("select count(*) as cnt from tab_stores_order where 1 = 0", (rs, index) -> rs.getInt("cnt"));

    Optional<Integer> optCounter = Optional.ofNullable(counter).flatMap(c -> {
      int firstIndex = 0;
      Integer rs = c.isEmpty() ? DEFAULT_INTEGER_VALUE : c.get(firstIndex);
      return Optional.ofNullable(rs);
    });

    return optCounter.orElse(DEFAULT_INTEGER_VALUE);
  }


  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  private int DEFAULT_INTEGER_VALUE = 0;
}
