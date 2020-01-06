package com.g2o.cm2020.hadoop.examples;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

public class ReduceExample {
  public static void main(String[] args) throws Exception {
    words();
    numbers();
    totalLength();
  }

  /**
   * String concatenation is not commutative, associative and distributitive so this is a non-deterministic reduce on a distributed system
   * <p>The order being non-deterministic is simulated by using a set rather than an array for the words.</p>
   */
  private static void words() {
    Stream<String> words = new HashSet<String>(Arrays.asList("Hello", "World", "!", "Having", "a", "good", "day", "?")).stream();
    Stream<String> wordList = Stream.of("Hello", "World", "!", "Having", "a", "good", "day", "?");
    BinaryOperator<String> reducer = (a, b) -> a + " " + b;
    String reducedWords = words.reduce("",reducer );
    System.out.println("words as set: '" + reducedWords + "'");
    String reducedWordList = wordList.reduce("", reducer);
    System.out.println("words as list: '" + reducedWordList + "'");
  }

  private static void numbers() {
    Stream<Integer> numbers = Stream.of(-1, 0, 1, 2, 3, 4, 5);
    Function<Integer, Stream<Integer>> makeStream = i -> Stream.of(i - 1, i, i + 1);
    Stream<Integer> flatMappedNumbers = numbers.flatMap(makeStream);
    BinaryOperator<Integer> reducer=(a, b) -> a + b;
    Integer reduced = flatMappedNumbers.reduce(0, reducer);
    System.out.println(reduced);
    // 42
  }

  private static void totalLength() throws Exception {
    String fileName = "magnacarta.txt";
    URI uri = MapExample.class.getClassLoader().getResource(fileName).toURI();
    Stream<String> lines = Files.lines(Paths.get(uri));
    Stream<Integer> lineLengths = lines.map(String::length);
    BinaryOperator<Integer> reducer=(a, b) -> a + b;
    Integer totalLength = lineLengths.reduce(0, reducer);
    System.out.println(totalLength);
    //24687
  }
}
