package com.example.fxex.api;

import com.example.fxex.api.dto.InstrumentDataDto;

public class InstrumentController {
  private final InstrumentRetrievalService instrumentRetrievalService =
      new InstrumentRetrievalService();

  public InstrumentDataDto get(String instrumentName) {
    return instrumentRetrievalService.get(instrumentName);
  }
}
