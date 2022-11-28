package com.example.fxex.message.process;

import java.math.BigDecimal;

public class ConstantMarginProviderImpl implements MarginProvider {
  private final BigDecimal bid = new BigDecimal("0.999");
  private final BigDecimal ask = new BigDecimal("1.001");

  @Override
  public BigDecimal getAskMarginPercentage() {
    return ask;
  }

  @Override
  public BigDecimal getBidMarginPercentage() {
    return bid;
  }
}
