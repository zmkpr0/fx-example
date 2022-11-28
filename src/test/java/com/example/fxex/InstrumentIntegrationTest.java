package com.example.fxex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.fxex.api.InstrumentController;
import com.example.fxex.api.dto.InstrumentDataDto;
import com.example.fxex.api.providers.ApiConcurrentHashmapStoreProvider;
import com.example.fxex.message.PriceMessageHandler;
import com.example.fxex.message.providers.MessageConcurrentHashmapStoreProvider;
import com.example.fxex.persistence.ConcurrentHashmapStore;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InstrumentIntegrationTest {

  private final ConcurrentHashmapStore store = new ConcurrentHashmapStore();

  private PriceMessageHandler priceMessageHandler;

  private InstrumentController instrumentController;

  @BeforeEach
  void setup() {
    ApiConcurrentHashmapStoreProvider.setStore(store);
    MessageConcurrentHashmapStoreProvider.setStore(store);

    priceMessageHandler = new PriceMessageHandler();
    instrumentController = new InstrumentController();

    priceMessageHandler.onMessage("""
      106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001
      107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002
      """);

    priceMessageHandler.onMessage("""
      108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002
      109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100
      """);

    priceMessageHandler.onMessage("""
      110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110
      """);


  }

  @Test
  void testGbpUsd() {
    //when
    InstrumentDataDto instrumentDataDto = instrumentController.get("GBP/USD");
    System.out.println("GBP/USD price. Ask: " + instrumentDataDto.getAsk() + " Bid: " + instrumentDataDto.getBid());
    //then
    assertThat(instrumentDataDto.getInstrumentName()).isEqualTo("GBP/USD");
    assertThat(instrumentDataDto.getBid()).isEqualTo("1.2487");
    assertThat(instrumentDataDto.getAsk()).isEqualTo("1.2574");
    assertThat(instrumentDataDto.getTimestamp())
        .isEqualTo(LocalDateTime.of(2020, 6, 1, 12, 1, 2, toNanos(100)));
  }

  @Test
  void testOlderMessageArrivingAgain() {
    //when
    InstrumentDataDto instrumentDataDto = instrumentController.get("EUR/JPY");
    priceMessageHandler.onMessage("""
      106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001
      107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002
      """);

    //then
    assertThat(instrumentDataDto.getInstrumentName()).isEqualTo("EUR/JPY");
    assertThat(instrumentDataDto.getBid()).isEqualTo("119.4904");
    assertThat(instrumentDataDto.getAsk()).isEqualTo("120.0299");
    assertThat(instrumentDataDto.getTimestamp())
        .isEqualTo(LocalDateTime.of(2020, 6, 1, 12, 1, 2, toNanos(110)));
  }

  @Test
  void testOlderTimestampWithNewIdArrivingLater() {
    //when
    InstrumentDataDto instrumentDataDto = instrumentController.get("EUR/JPY");
    priceMessageHandler.onMessage("""
      130, EUR/JPY, 119.60,120.90,01-06-2020 12:01:01:002
      """);

    //then
    assertThat(instrumentDataDto.getInstrumentName()).isEqualTo("EUR/JPY");
    assertThat(instrumentDataDto.getBid()).isEqualTo("119.4904");
    assertThat(instrumentDataDto.getAsk()).isEqualTo("120.0299");
    assertThat(instrumentDataDto.getTimestamp())
        .isEqualTo(LocalDateTime.of(2020, 6, 1, 12, 1, 2, toNanos(110)));
  }

  @Test
  void testNoValue() {
    //when - then
    assertThatThrownBy(() -> {
      InstrumentDataDto instrumentDataDto = instrumentController.get("GBP/PLN");
    }).hasMessageContaining("No instrument");
  }

  private int toNanos(int millis) {
    return millis * 1000000;
  }
}
