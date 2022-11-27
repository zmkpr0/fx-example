package com.example.fxex.message.parse.parser;

import com.opencsv.bean.processor.StringProcessor;

public class TrimStringsProcessor implements StringProcessor {
  @Override
  public String processString(String value) {
    if (value == null) {
      return null;
    }

    return value.trim();
  }

  @Override
  public void setParameterString(String value) {}
}
