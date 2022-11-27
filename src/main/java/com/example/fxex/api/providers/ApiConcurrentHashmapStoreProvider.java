package com.example.fxex.api.providers;

import com.example.fxex.persistence.ConcurrentHashmapStore;

/**
 * This class is meant as a replacement for a DI framework. To allow for setting different store
 * implemetantions for integration testing
 */
public class ApiConcurrentHashmapStoreProvider {
  private static ConcurrentHashmapStore store;

  public static ConcurrentHashmapStore getStore() {
    return store;
  }

  public static void setStore(ConcurrentHashmapStore newStore) {
    store = newStore;
  }
}
