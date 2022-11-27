package com.example.fxex.api;

import com.example.fxex.api.adapters.InstrumentDataStoreAdapter;
import com.example.fxex.api.dto.InstrumentDataDto;
import com.example.fxex.api.exceptions.InstrumentNotFoundException;
import com.example.fxex.api.mapper.InstrumentMapper;
import com.example.fxex.message.entity.InstrumentEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InstrumentRetrievalService {
  private final InstrumentDataStoreAdapter storeAdapter = new InstrumentDataStoreAdapter();
  private final InstrumentMapper mapper = new InstrumentMapper();

  public InstrumentDataDto get(String instrumentName) {
    InstrumentEntity entity = storeAdapter.get(instrumentName);
    if (entity == null) {
      throw new InstrumentNotFoundException("No instrument with name " + instrumentName + " found");
    }
    return mapper.map(entity);
  }
}
