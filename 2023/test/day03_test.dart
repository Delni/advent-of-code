import 'package:AdventOfCode2023/day03.dart';
import 'package:test/test.dart';

void main() {
  test("should find all engines parts", () {
    final parts = findEngineParts([
      "467..114..",
      "...*......",
      "..35..633.",
      "......#...",
      "617*......",
      ".....+.58.",
      "..592.....",
      "......755.",
      "...\$.*....",
      ".664.598..",
    ]);

    expect(
        parts,
        equals(
          [467, 35, 633, 617, 592, 755, 664, 598],
        ));
  });

  // test("should find all gear ratios", () {
  //   final gears = findGearsRatio([
  //     "467..114..",
  //     "...*......",
  //     "..35..633.",
  //     "......#...",
  //     "617*......",
  //     ".....+.58.",
  //     "..592.....",
  //     "......755.",
  //     "...\$.*....",
  //     ".664.598..",
  //   ]);

  //   expect(
  //       gears,
  //       equals(
  //         [16345, 451490],
  //       ));
  // });
}
