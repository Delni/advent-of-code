import 'dart:math';

import 'package:AdventOfCode2023/utils.dart';

void main() async {
  final List<String> input = await getInputForDay(2);
  print("--- PART 0NE --- ");
  final constraints = GameConstraints(red: 12, green: 13, blue: 14);
  print(
    input
        .map(Game.fromString)
        .where((element) => element.isValidAgainst(constraints))
        .map((e) => e.id)
        .sum(),
  );
  print("--- PART TWO --- ");
  print(
    input.map(Game.fromString).map((game) => game.fewestCubesSet.power).sum(),
  );
}

class GameConstraints {
  final int red;
  final int blue;
  final int green;

  GameConstraints({required this.red, required this.blue, required this.green});
}

class GameSet {
  final int? red;
  final int? blue;
  final int? green;

  GameSet({this.red, this.blue, this.green});

  factory GameSet.fromString(String gameSetString) => GameSet(
        red: int.tryParse(
          RegExp(r'(\d+) red').firstMatch(gameSetString)?.group(1) ?? "",
        ),
        blue: int.tryParse(
          RegExp(r'(\d+) blue').firstMatch(gameSetString)?.group(1) ?? "",
        ),
        green: int.tryParse(
          RegExp(r'(\d+) green').firstMatch(gameSetString)?.group(1) ?? "",
        ),
      );

  bool isValidAgainst(GameConstraints constraints) =>
      (red ?? 0) <= constraints.red &&
      (blue ?? 0) <= constraints.blue &&
      (green ?? 0) <= constraints.green;

  int get power => (red ?? 1) * (green ?? 1) * (blue ?? 1);
}

class Game {
  final int id;
  final List<GameSet> sets;

  Game({required this.id, required this.sets});

  factory Game.fromString(String gameString) => Game(
        id: int.parse(RegExp(r'^Game (?<id>\d+)')
            .firstMatch(gameString)!
            .namedGroup("id")!),
        sets: RegExp(r': (?<sets>.*)$')
            .firstMatch(gameString)!
            .namedGroup('sets')!
            .split(";")
            .map(GameSet.fromString)
            .toList(),
      );

  bool isValidAgainst(GameConstraints constraints) =>
      sets.every((element) => element.isValidAgainst(constraints));

  GameSet get fewestCubesSet => GameSet(
        red: sets.map((e) => e.red ?? 0).reduce(max),
        green: sets.map((e) => e.green ?? 0).reduce(max),
        blue: sets.map((e) => e.blue ?? 0).reduce(max),
      );
}
