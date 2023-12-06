import 'package:AdventOfCode2023/day05.dart';
import 'package:test/test.dart';

void main() {
  const testInput = [
    "seeds: 79 14 55 13",
    "",
    "seed-to-soil map:",
    "50 98 2",
    "52 50 48",
    "",
    "soil-to-fertilizer map:",
    "0 15 37",
    "37 52 2",
    "39 0 15",
    "",
    "fertilizer-to-water map:",
    "49 53 8",
    "0 11 42",
    "42 0 7",
    "57 7 4",
    "",
    "water-to-light map:",
    "88 18 7",
    "18 25 70",
    "",
    "light-to-temperature map:",
    "45 77 23",
    "81 45 19",
    "68 64 13",
    "",
    "temperature-to-humidity map:",
    "0 69 1",
    "1 0 69",
    "",
    "humidity-to-location map:",
    "60 56 37",
    "56 93 4",
  ];

  test("should parse map", () {
    const map = [
      "soil-to-fertilizer map:",
      "0 15 37",
      "37 52 2",
      "39 0 15",
    ];
    expect(
      mapToRanges(map).first,
      equals(RangeIndication(source: 15, destination: 0, range: 37)),
    );
  });

  test("should map seeds to soil", () {
    const seeds = [79, 14, 55, 13];
    const seedToSoilMap = [
      RangeIndication(destination: 50, source: 98, range: 2),
      RangeIndication(destination: 52, source: 50, range: 48),
    ];

    final actual = pipe(seeds, seedToSoilMap);
    expect(actual, equals([81, 14, 57, 13]));
  });
}
