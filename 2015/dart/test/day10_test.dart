import 'package:test/test.dart';

import '../day10.dart';

void main() {
  test("should translate repetition", () {
    expect(lookAndSay("1"), "11");
    expect(lookAndSay("11"), "21");
    expect(lookAndSay("111"), "31");
    expect(lookAndSay("1111"), "41");
  });


  test("should advance cursor", () {
    expect(lookAndSay("112"), "2112");
  });

  test("should run recursively", () {
    // 1:11
    // 2: 21
    // 3: 1211
    expect(lookAndSay("1", 3), "1211");
  });
}