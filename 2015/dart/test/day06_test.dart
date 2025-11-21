import 'dart:math';

import 'package:test/test.dart';

import '../day06.dart';

void main() {
  test("should parse turn on instruction", () {
    expect(
      Instruction.parse("turn on 0,0 through 999,999"),
      equals(
        Instruction(
          action: Action.TURN_ON,
          topLeftCorner: Point(0, 0),
          bottomRightCorner: Point(999, 999),
        ),
      ),
    );
  });

  test("should parse turn off instruction", () {
    const instruction = "turn off 499,499 through 500,500";
    expect(
      Instruction.parse(instruction),
      equals(
        Instruction(
          action: Action.TURN_OFF,
          topLeftCorner: Point(499, 499),
          bottomRightCorner: Point(500, 500),
        ),
      ),
    );
  });

  test("should parse toggle instruction", () {
    const instruction = "toggle 0,0 through 999,0";
    expect(
      Instruction.parse(instruction),
      equals(
        Instruction(
          action: Action.TOGGLE,
          topLeftCorner: Point(0, 0),
          bottomRightCorner: Point(999, 0),
        ),
      ),
    );
  });

  test("part1", () {
    const entries = [
      "turn on 887,9 through 959,629",
    ];

    expect(part1(entries), equals(45333));
  });

  test("turn on should increase 0,0x0,0 by 1", () {
    final instruction = Instruction(action: Action.TURN_ON, topLeftCorner: Point(0,0), bottomRightCorner: Point(0,0),);
    final grid = new Map<Point, int>();
    instruction.updateBrightnessOf(grid);
    expect(grid.entries.fold(0, (total, it) => total + it.value ), equals(1));
  });

  test("turn off should not decrease 0,0x0,0 below 0 ", () {
    final instruction = Instruction(action: Action.TURN_OFF, topLeftCorner: Point(0,0), bottomRightCorner: Point(0,0),);
    final grid = new Map<Point, int>();
    instruction.updateBrightnessOf(grid);
    expect(grid.entries.fold(0, (total, it) => total + it.value ), equals(0));
  });

  test("toggle should increase 0,0x999,999 by 2000000", () {
    final instruction = Instruction(action: Action.TOGGLE, topLeftCorner: Point(0,0), bottomRightCorner: Point(999,999),);
    final grid = new Map<Point, int>();
    instruction.updateBrightnessOf(grid);
    expect(grid.entries.fold(0, (total, it) => total + it.value ), equals(2000000));
  });
}
