package com.example.fxex.message.process;

import com.example.fxex.message.entity.InstrumentEntity;
import com.example.fxex.message.parse.dto.PriceMessageRow;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MarginProcessor {

  public static final int SCALE = 4;
  private final MarginProvider marginProvider;

  public InstrumentEntity processAndMapToEntity(PriceMessageRow messageRow) {
    BigDecimal bidWithCommision =
        messageRow
            .getBid()
            .multiply(marginProvider.getBidMarginPercentage())
            .setScale(SCALE, RoundingMode.HALF_UP);

    BigDecimal askWithCommision =
        messageRow
            .getAsk()
            .multiply(marginProvider.getAskMarginPercentage())
            .setScale(SCALE, RoundingMode.HALF_UP);

    return new InstrumentEntity(
        messageRow.getInstrumentName(),
        bidWithCommision,
        askWithCommision,
        messageRow.getTimestamp());
  }
}
