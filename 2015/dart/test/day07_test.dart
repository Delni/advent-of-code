import 'package:test/test.dart';

import '../day07.dart';

void main() {
  final instructions = [
    '123 -> x',
    '456 -> y',
    'x AND y -> d',
    'x OR y -> e',
    'x LSHIFT 2 -> f',
    'y RSHIFT 2 -> g',
    'NOT x -> h',
    'NOT y -> i',
  ];

  final expected = {
    'd': 72,
    'e': 507,
    'f': 492,
    'g': 114,
    'h': 65412,
    'i': 65079,
    'x': 123,
    'y': 456,
  };

  test("should parse circuit as inputs/outputs", () {
    expect(
      Circuit.from(instructions).wires,
      equals({
        'd': 'x AND y',
        'e': 'x OR y',
        'f': 'x LSHIFT 2',
        'g': 'y RSHIFT 2',
        'h': 'NOT x',
        'i': 'NOT y',
        'x': '123',
        'y': '456',
      }),
    );
  });

  test("should raise error for inexisting key", () {
    expect(() => Circuit.from(instructions).resolve('a'), throwsArgumentError);
  });

  test("should return if key is int", () {
    expect(Circuit.from(['123 -> a']).resolve('a'), equals(123));
  });

  test("should resolve recursively if key is int", () {
    expect(Circuit.from(['b -> a', '123 -> b']).resolve('a'), equals(123));
    expect(Circuit.from(['ab -> a', '123 -> ab']).resolve('a'), equals(123));
  });

  group("should resolve verb", () {
    test("AND", () {
      expect(
        Circuit.from([
          'c AND b -> a',
          '20 -> b',
          '23 -> c',
        ]).resolve('a'),
        equals(20),
      );
    });

    test("OR", () {
      expect(
        Circuit.from([
          'c OR b -> a',
          '20 -> b',
          '23 -> c',
        ]).resolve('a'),
        equals(23),
      );
    });

    test("RSHIFT", () {
      expect(
        Circuit.from([
          'c RSHIFT b -> a',
          '2 -> b',
          '230 -> c',
        ]).resolve('a'),
        equals(57),
      );
    });

    test("LSHIFT", () {
      expect(
        Circuit.from([
          'c LSHIFT b -> a',
          '1 -> b',
          '230 -> c',
        ]).resolve('a'),
        equals(460),
      );
    });

    test("NOT", () {
      expect(
        Circuit.from([
          'NOT b -> a',
          '20 -> b',
        ]).resolve('a'),
        equals(65515),
      );
    });
  });

  test("should resolve as expected", () {
    final circuit = Circuit.from(instructions);
    expected.entries.forEach((entry) {
      expect(
        circuit.resolve(entry.key),
        equals(entry.value),
        reason: "Error for ${entry.key}",
      );
    });
  });
}
