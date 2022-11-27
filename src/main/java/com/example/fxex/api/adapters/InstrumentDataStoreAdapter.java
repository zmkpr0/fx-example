package com.example.fxex.api.adapters;

import com.example.fxex.api.providers.ApiConcurrentHashmapStoreProvider;
import com.example.fxex.message.entity.InstrumentEntity;
import com.example.fxex.persistence.ConcurrentHashmapStore;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InstrumentDataStoreAdapter {
  private final ConcurrentHashmapStore store = ApiConcurrentHashmapStoreProvider.getStore();

  public InstrumentEntity get(String id) {
    return store.retrieve(id);
  }
}
