package com.tank.utils;

import com.tank.constants.Empty;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static com.tank.constants.Empty.EMPTY_STR;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DateUtilTest {

  @Test
  public void testInstance() {
    Assert.assertTrue(Objects.nonNull(this.dateUtil));
  }

  @Test
  public void toDateStr() {
    String dateStr = this.dateUtil.toDateStr(LocalDateTime.now()).orElse(EMPTY_STR);
    Assert.assertTrue(!EMPTY_STR.equalsIgnoreCase(dateStr));
  }

  @Test
  public void toDateTimeStr() {
    String dateStr = this.dateUtil.toDateTimeStr(LocalDateTime.now()).orElse(EMPTY_STR);
    Assert.assertTrue(!EMPTY_STR.equalsIgnoreCase(dateStr));
  }

  @Test
  public void toDate() {
    int day = this.dateUtil.toDate("2019-11-12").flatMap(date -> Optional.ofNullable(date.getDayOfYear())).orElse(Empty.EMPTY_INTEGER);
    Assert.assertTrue(day != Empty.EMPTY_INTEGER);
  }

  @Test
  public void toDateTime() {
    int day = this.dateUtil.toDateTime("2019-11-12 11:12:32").flatMap(date -> Optional.ofNullable(date.getDayOfYear())).orElse(Empty.EMPTY_INTEGER);
    Assert.assertTrue(day != Empty.EMPTY_INTEGER);
  }

  @Autowired
  private DateUtil dateUtil;
}