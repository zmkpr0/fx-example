package com.example.fxex.message.parse.parser;

import com.example.fxex.message.parse.dto.PriceMessageRow;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.StringReader;
import java.util.List;

public class PriceMessageParser {
  public List<PriceMessageRow> parse(String message) {
    final var csvReader =
        new CsvToBeanBuilder<PriceMessageRow>(new StringReader(message))
            .withSeparator(',')
            .withType(PriceMessageRow.class)
            .build();
    return csvReader.parse();
  }
}
