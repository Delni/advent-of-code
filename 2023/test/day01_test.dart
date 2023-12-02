import 'package:AdventOfCode2023/day01.dart';
import 'package:test/test.dart';

void main() {
  test("find first and last digit of string", () {
    expect(findFirstAndLastDigit("1abc2"), equals(12));
    expect(findFirstAndLastDigit("pqr3stu8vwx"), 38);
    expect(findFirstAndLastDigit("a1b2c3d4e5f"), 15);
    expect(findFirstAndLastDigit("treb7uchet"), 77);
  });

  test("find first and last digit of string with numbers", () {
    expect(findFirstAndLastDigit(replaceFirstAndLastSpelledNumbers("two1nine")), equals(29));
    expect(findFirstAndLastDigit(replaceFirstAndLastSpelledNumbers("eightwothree")), equals(83));
    expect(findFirstAndLastDigit(replaceFirstAndLastSpelledNumbers("abcone2threexyz")), equals(13));
    expect(findFirstAndLastDigit(replaceFirstAndLastSpelledNumbers("xtwone3four")), equals(24));
    expect(findFirstAndLastDigit(replaceFirstAndLastSpelledNumbers("4nineeightseven2")), equals(42));
    expect(findFirstAndLastDigit(replaceFirstAndLastSpelledNumbers("zoneight234")), equals(14));
    expect(findFirstAndLastDigit(replaceFirstAndLastSpelledNumbers("7pqrstsixteen")), equals(76));
    expect(findFirstAndLastDigit(replaceFirstAndLastSpelledNumbers("2fourseven1oneights")), equals(28));
  });

  test("day01part2", () {
    var input = [
      "two1nine",
      "eightwothree",
      "abcone2threexyz",
      "xtwone3four",
      "4nineeightseven2",
      "zoneight234",
      "7pqrstsixteen",
    ];
    var actual = input
        .map((e) => findFirstAndLastDigit(replaceFirstAndLastSpelledNumbers(e)))
        .fold<int>(0, (p, e) => p + e);
    expect(actual, equals(281));
  });
}
