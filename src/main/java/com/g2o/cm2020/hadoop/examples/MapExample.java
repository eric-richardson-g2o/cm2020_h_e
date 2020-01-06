package com.g2o.cm2020.hadoop.examples;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapExample {
  public static void main(String[] args) throws Exception {
    numbers();
    words();
    lineLengths();
  }

  /**
   * Example showing how to use the map function on a list of numbers
   */
  private static void numbers() {
    Stream<Integer> numbers = Stream.of(-1, 0, 1, 2, 3, 4, 5);
    List<Integer> mappedNumbers = numbers.map(x -> x + 1).collect(Collectors.toList());
    System.out.println(mappedNumbers);
    // [0, 1, 2, 3, 4, 5, 6]
  }

  /**
   * Example showing how to use the map function on a list of words
   */
  private static void words() {
    Stream<String> words = Stream.of("Hello", "World", "!", "Having", "a", "good", "day", "?");
    List<String> mappedWords = words.map(x -> x + 1).collect(Collectors.toList());
    System.out.println(mappedWords);
    //[Hello1, World1, !1, Having1, a1, good1, day1, ?1]
  }

  private static void lineLengths() throws Exception {
    String fileName = "magnacarta.txt";
    URI uri = MapExample.class.getClassLoader().getResource(fileName).toURI();
    Stream<String> lines = Files.lines(Paths.get(uri));
    List<Integer> lineLengths = lines.map(String::length).collect(Collectors.toList());
    System.out.println(lineLengths);
  }

}
