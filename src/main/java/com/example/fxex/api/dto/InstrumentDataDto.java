package com.example.fxex.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Value;

@Value
public class InstrumentDataDto {
  String instrumentName;
  BigDecimal bid;
  BigDecimal ask;
  LocalDateTime timestamp;
}
