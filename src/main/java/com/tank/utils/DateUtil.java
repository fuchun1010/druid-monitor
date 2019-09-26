package com.tank.utils;

import com.tank.constants.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @author tank198435163.com
 */
@Component
@Comment(desc = "日期类工具")
public class DateUtil {

  @Comment(desc = "")
  public Optional<String> toDateStr(@NotNull final LocalDateTime dateTime) {
    return Optional.ofNullable(this.dateFormatter.format(dateTime));
  }

  @Comment(desc = "")
  public Optional<String> toDateTimeStr(@NotNull final LocalDateTime dateTime) {
    return Optional.ofNullable(this.dateTimeFormatter.format(dateTime));
  }

  @Comment(desc = "")
  public Optional<LocalDateTime> toDate(@NotNull final String dateStr) {

    if (dateStr.indexOf(timeSeparator) != -1) {
      throw new IllegalArgumentException("don't allow time exists in dateStr");
    }
    LocalDateTime temp = LocalDateTime.parse(String.format("%s 00:00:00", dateStr), this.dateTimeFormatter);
    return Optional.ofNullable(temp);
  }

  @Comment
  public Optional<LocalDateTime> toDateTime(@NotNull final String dateStr) {
    if (dateStr.indexOf(timeSeparator) == -1) {
      throw new IllegalArgumentException("should exists time in dateStr");
    }
    LocalDateTime temp = LocalDateTime.parse(dateStr, this.dateTimeFormatter);
    return Optional.ofNullable(temp);
  }

  @Comment
  public Optional<String> toDateTimeStr(@NotNull final Long millions) {
    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millions), ZoneId.systemDefault());
    return this.toDateTimeStr(localDateTime);
  }

  @Autowired
  @Qualifier("dateFormatter")
  private DateTimeFormatter dateFormatter;

  @Autowired
  @Qualifier("dateTimeFormatter")
  private DateTimeFormatter dateTimeFormatter;

  private final String timeSeparator = ":";

}
