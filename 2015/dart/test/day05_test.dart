import 'package:test/test.dart';

import '../day05.dart';


void main() {
  test("find 3 vowels", () {
    expect(hasAtLeastThreeVowels('ugknbfddgicrmopn'), true);
    expect(hasAtLeastThreeVowels('aaa'), true);
    expect(hasAtLeastThreeVowels('dvszwmarrgswjxmb'), false);

  });

  test("find repeated letters", () {
    expect(hasAtLeastOneDoubleLetter("abcd"), false);
    expect(hasAtLeastOneDoubleLetter("aacd"), true);
    expect(hasAtLeastOneDoubleLetter("abcdd"), true);
  });

  test("contains no naughty substring", () {
    expect(hasNoNaughtyString("ac"), true);
    expect(hasNoNaughtyString("abc"), false);
    expect(hasNoNaughtyString("cde"), false);
    expect(hasNoNaughtyString("pqr"), false);
    expect(hasNoNaughtyString("xy"), false);
  });

  test("contains a pair of any two letters that appears at least twice in the string without overlapping", () {
    expect(hasPairRepeated("xyxy"), true, reason: 'xyxy should be repeated');
    expect(hasPairRepeated("aaa"), false, reason: 'aaa should not be repeated');
    expect(hasPairRepeated("aabcdefgaa"), true, reason: 'aabcdefgaa has aa repeated');
  });

  test("contains at least one letter which repeats with exactly one letter between them", () {
    expect(hasLetterRepeatedWithOffset('xyx'), true);
    expect(hasLetterRepeatedWithOffset('abcdefeghi'), true);
    expect(hasLetterRepeatedWithOffset('aaa'), true);
    expect(hasLetterRepeatedWithOffset('aab'), false);
  });
}