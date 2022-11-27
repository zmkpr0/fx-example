package com.example.fxex.message.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Value;

@Value
public class InstrumentEntity {
  String instrumentName;
  BigDecimal bid;
  BigDecimal ask;
  LocalDateTime timestamp;
}
