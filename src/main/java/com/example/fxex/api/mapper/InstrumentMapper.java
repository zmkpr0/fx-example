package com.example.fxex.api.mapper;

import com.example.fxex.api.dto.InstrumentDataDto;
import com.example.fxex.message.entity.InstrumentEntity;

public class InstrumentMapper {
  public InstrumentDataDto map(InstrumentEntity entity) {
    return new InstrumentDataDto(
        entity.getInstrumentName(), entity.getBid(), entity.getAsk(), entity.getTimestamp());
  }
}
