package com.example.fxex.message.process;

import com.example.fxex.message.entity.InstrumentEntity;
import com.example.fxex.message.parse.dto.PriceMessageRow;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MarginProcessorTest {

  private final MarginProvider testMarginProvider =
      new MarginProvider() {
        @Override
        public BigDecimal getAskMarginPercentage() {
          return new BigDecimal("1.02");
        }

        @Override
        public BigDecimal getBidMarginPercentage() {
          return new BigDecimal("0.98");
        }
      };
  private final MarginProcessor sut = new MarginProcessor(testMarginProvider);

  @Test
  void testApplyMargin() {
    // given
    var message =
        new PriceMessageRow(
            "106",
            "EUR/USD",
            new BigDecimal("1.1000"),
            new BigDecimal("1.2000"),
            LocalDateTime.of(2020, Month.JUNE, 1, 12, 1, 1, 1000000));

    // when
    var entity = sut.processAndMapToEntity(message);

    // then
    Assertions.assertThat(entity)
        .isEqualTo(
            new InstrumentEntity(
                "EUR/USD",
                new BigDecimal("1.0780"),
                new BigDecimal("1.2240"),
                LocalDateTime.of(2020, Month.JUNE, 1, 12, 1, 1, 1000000)));
  }
}
