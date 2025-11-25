import 'package:test/test.dart';

import '../day08.dart';

void main() {
  const inputs = [
    '""',
    '"abc"',
    '"aaa\\"aaa"',
    '"\\x27"',
  ];

  test("string in memory", () {
    expect(inputs[0].inMemoryLength, 0);
    expect(inputs[1].inMemoryLength, 3);
    expect(inputs[2].inMemoryLength, 7);
    expect(inputs[3].inMemoryLength, 1);
  });

  test("should encode string", () {
    expect(inputs[0].encoded, r'"\"\""');
    expect(inputs[1].encoded, r'"\"abc\""');
    expect(inputs[2].encoded, r'"\"aaa\\\"aaa\""');
    expect(inputs[3].encoded, r'"\"\\x27\""');
  });
}
