package com.yj.reservation.common.util;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

/**
 * @author shkstart
 * @create 2019-03-20 11:20
 */
public class RandomGenerator {

//  private static final RandomStringGenerator generatorA_Z = new RandomStringGenerator.Builder()
//      .withinRange('a', 'z').build();

  private static final RandomStringGenerator generator_Alphabetic = new RandomStringGenerator.Builder().withinRange('A', 'z').filteredBy(CharacterPredicates.LETTERS).build();
  private static final RandomStringGenerator generator_Numeric = new RandomStringGenerator.Builder().withinRange('0', '9').build();
  private static final RandomStringGenerator generator_Alphanumeric = new RandomStringGenerator.Builder().withinRange('A', 'z').filteredBy(CharacterPredicates.ASCII_ALPHA_NUMERALS).build();
  private static final RandomStringGenerator generator_Ascii = new RandomStringGenerator.Builder().withinRange('!', '~').build();

//同样我们还能实现自己的过滤器 这使用强大的lamda表达式，我们只要‘3’到‘9’的数字字符和字母（注意引号）
//CharacterPredicate filter2 = c -> c >= '3' && c<= '9';
//  RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'Z').filteredBy(filter2,LETTERS).build();
//  String randomLetters = generator.generate(20);

  public static String randomAlphabetic(int count) {
    return generator_Alphabetic.generate(count);
  }


  public static String randomNumeric(int count) {
    return generator_Numeric.generate(count);
  }

  public static String randomAlphanumeric(int count) {
    return generator_Alphanumeric.generate(count);
  }

  //可见的字符
  public static String randomAscii(int count) {
    return generator_Ascii.generate(count);
  }

  public static void main(String[] args) {
    String str = "123";
    LoggerUtil.debug("hello");
  }

}
