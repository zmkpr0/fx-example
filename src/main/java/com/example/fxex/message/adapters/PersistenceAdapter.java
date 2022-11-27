package com.example.fxex.message.adapters;

import com.example.fxex.message.entity.InstrumentEntity;
import com.example.fxex.message.providers.MessageConcurrentHashmapStoreProvider;
import com.example.fxex.persistence.ConcurrentHashmapStore;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersistenceAdapter {
  private final ConcurrentHashmapStore store = MessageConcurrentHashmapStoreProvider.getStore();

  public void storeAll(Map<String, InstrumentEntity> entitiesById) {
    store.storeAll(entitiesById);
  }
}
