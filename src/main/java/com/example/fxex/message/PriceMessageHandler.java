package com.example.fxex.message;

import com.example.fxex.message.adapters.PersistenceAdapter;
import com.example.fxex.message.entity.InstrumentEntity;
import com.example.fxex.message.parse.dto.PriceMessageRow;
import com.example.fxex.message.parse.parser.PriceMessageParser;
import com.example.fxex.message.process.ConstantMarginProviderImpl;
import com.example.fxex.message.process.MarginProcessor;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceMessageHandler {
  private final PriceMessageParser parser = new PriceMessageParser();
  private final MarginProcessor marginProcessor =
      new MarginProcessor(new ConstantMarginProviderImpl());
  private final PersistenceAdapter persistenceAdapter = new PersistenceAdapter();

  public void onMessage(String message) {
    List<PriceMessageRow> rows = parser.parse(message);
    Map<String, InstrumentEntity> entitiesById =
        rows.stream()
            .map(marginProcessor::processAndMapToEntity)
            .collect(
                Collectors.toMap(
                    InstrumentEntity::getInstrumentName,
                    Function.identity(),
                    (a, b) -> a.getTimestamp().isAfter(b.getTimestamp()) ? a : b));

    persistenceAdapter.storeAll(entitiesById);
  }
}
