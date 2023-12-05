import 'dart:math';

import 'package:AdventOfCode2023/day04.dart';
import 'package:AdventOfCode2023/utils.dart';
import 'package:test/test.dart';

void main() {
  test("should parse winning numbers on card", () {
    expect(
      stripWinningNumbers("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"),
      unorderedEquals([48, 83, 17, 86]),
    );
  });

  test("should find card Score", () {
    expect(
        score("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"), equals(8));
    expect(
        score("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19"), equals(2));
    expect(
        score("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1"), equals(2));
    expect(
        score("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83"), equals(1));
    expect(
        score("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36"), equals(0));
    expect(
        score("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"), equals(0));
  });

  test("should find total score", () {
    final input = [
      "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
      "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
      "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
      "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
      "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
      "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
    ];
    expect(
      input.map(score).sum(),
      equals(13),
    );
  });
}
