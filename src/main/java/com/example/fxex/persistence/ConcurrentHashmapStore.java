package com.example.fxex.persistence;

import com.example.fxex.message.entity.InstrumentEntity;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashmapStore {
  private final ConcurrentHashMap<String, InstrumentEntity> map = new ConcurrentHashMap<>();

  public void storeAll(Map<String, InstrumentEntity> entitiesById) {
    entitiesById.forEach(
        (key, value) ->
            map.merge(
                key,
                value,
                (oldValue, newValue) ->
                    oldValue.getTimestamp().isAfter(newValue.getTimestamp())
                        ? oldValue
                        : newValue));
  }

  public InstrumentEntity retrieve(String id) {
    return map.get(id);
  }
}
