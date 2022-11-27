package com.example.fxex.message.parse.dto;

import com.example.fxex.message.parse.parser.TrimStringsProcessor;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.processor.PreAssignmentProcessor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceMessageRow {
  @CsvBindByPosition(position = 0)
  @PreAssignmentProcessor(processor = TrimStringsProcessor.class)
  String id;

  @CsvBindByPosition(position = 1)
  @PreAssignmentProcessor(processor = TrimStringsProcessor.class)
  String instrumentName;

  @CsvBindByPosition(position = 2)
  BigDecimal bid;

  @CsvBindByPosition(position = 3)
  BigDecimal ask;

  @CsvBindByPosition(position = 4)
  @CsvDate("dd-MM-yyyy HH:mm:ss:SSS")
  LocalDateTime timestamp;
}
