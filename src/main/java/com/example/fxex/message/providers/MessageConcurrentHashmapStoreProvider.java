package com.example.fxex.message.providers;

import com.example.fxex.persistence.ConcurrentHashmapStore;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class is meant as a replacement for a DI framework. To allow for setting different store
 * implemetantions for integration testing
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageConcurrentHashmapStoreProvider {
  private static ConcurrentHashmapStore store;

  public static ConcurrentHashmapStore getStore() {
    return store;
  }

  public static void setStore(ConcurrentHashmapStore newStore) {
    store = newStore;
  }
}
