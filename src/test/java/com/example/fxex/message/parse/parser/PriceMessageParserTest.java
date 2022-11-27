package com.example.fxex.message.parse.parser;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.fxex.message.parse.dto.PriceMessageRow;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.Test;

class PriceMessageParserTest {
  private final PriceMessageParser sut = new PriceMessageParser();

  @Test
  void testParse() {
    List<PriceMessageRow> rows = sut.parse("106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001");

    assertThat(rows)
        .hasSize(1)
        .first()
        .isEqualTo(
            new PriceMessageRow(
                "106",
                "EUR/USD",
                new BigDecimal("1.1000"),
                new BigDecimal("1.2000"),
                LocalDateTime.of(2020, Month.JUNE, 1, 12, 1, 1, 1000000)));
  }

  @Test
  void testParseMultipleRows() {
    List<PriceMessageRow> rows = sut.parse("""
    106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001
    107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002
    """);

    assertThat(rows)
        .hasSize(2);

    assertThat(rows.get(0))
        .isEqualTo(
            new PriceMessageRow(
                "106",
                "EUR/USD",
                new BigDecimal("1.1000"),
                new BigDecimal("1.2000"),
                LocalDateTime.of(2020, Month.JUNE, 1, 12, 1, 1, 1000000)));

    assertThat(rows.get(1))
        .isEqualTo(
            new PriceMessageRow(
                "107",
                "EUR/JPY",
                new BigDecimal("119.60"),
                new BigDecimal("119.90"),
                LocalDateTime.of(2020, Month.JUNE, 1, 12, 1, 2, 2000000)));
  }
}
