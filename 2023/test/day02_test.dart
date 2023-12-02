import 'package:AdventOfCode2023/day02.dart';
import 'package:test/test.dart';

void main() {
  test("should parse game", () {
    final game = Game.fromString(
      "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
    );
    expect(game.id, equals(1));
    expect(game.sets.length, equals(3));
    expect(game.sets.first.red, equals(4));
    expect(game.sets.first.blue, equals(3));
    expect(game.sets.first.green, isNull);
  });

  test("should assert game validity", () {
    final constraints = GameConstraints(red: 12, green: 13, blue: 14);
    expect(
        Game.fromString(
          "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
        ).isValidAgainst(constraints),
        isTrue);
    expect(
        Game.fromString(
          "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
        ).isValidAgainst(constraints),
        isTrue);
    expect(
        Game.fromString(
          "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
        ).isValidAgainst(constraints),
        isFalse);
    expect(
        Game.fromString(
          "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
        ).isValidAgainst(constraints),
        isFalse);
    expect(
        Game.fromString(
          "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        ).isValidAgainst(constraints),
        isTrue);
  });

  test("find the fewest cubes needed", () {
    final gameSet = Game.fromString(
      "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
    ).fewestCubesSet;

    expect(gameSet.red, equals(4));
    expect(gameSet.green, equals(2));
    expect(gameSet.blue, equals(6));
    expect(gameSet.power, equals(48));
  });
}
