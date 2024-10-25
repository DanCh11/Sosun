package de.service.filereader;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReaderContext {

  @Autowired
  private ReadStrategy strategy;

  public Object readFile(File file) throws Exception {
    if (strategy == null) {
      throw new IllegalStateException("Read stategy not set");
    }
    return strategy.read(file);
  }

}
