package com.example.fxex.message.process;

import java.math.BigDecimal;

public interface MarginProvider {
  BigDecimal getAskMarginPercentage();

  BigDecimal getBidMarginPercentage();
}
