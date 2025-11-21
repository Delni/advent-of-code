import 'dart:math';

import '../../tools/dart/utils.dart';

void main() async {
  TypedOutput<String>(
    day: 6,
    part1: part1,
    part2: part2,
  );
}

int part1(List<String> entries) {
  final coordinates = Map<Point, bool>();
  entries.map(Instruction.parse).forEach((instruction) {
    instruction.applyTo(coordinates);
  });

  return coordinates.entries.where((entry) => entry.value).length;
}

int part2(List<String> entries) {
  final coordinates = Map<Point, int>();
  entries.map(Instruction.parse).forEach((instruction) {
    instruction.updateBrightnessOf(coordinates);
  });

  return coordinates.entries.map((it) => it.value).fold(0, (total, value) => total + value);
}

enum Action { TURN_ON, TURN_OFF, TOGGLE }

class Instruction {
  Point topLeftCorner;
  Point bottomRightCorner;
  Action action;

  Instruction({
    required this.action,
    required this.topLeftCorner,
    required this.bottomRightCorner,
  });

  @override
  bool operator ==(Object other) {
    return other is Instruction &&
        other.action == action &&
        other.bottomRightCorner == bottomRightCorner &&
        other.topLeftCorner == topLeftCorner;
  }

  @override
  String toString() =>
      "Instruction: ${action.name} from $topLeftCorner to $bottomRightCorner";

  void applyTo(Map<Point, bool> grid) {
    for (var x = topLeftCorner.x; x <= bottomRightCorner.x; x++) {
      for (var y = topLeftCorner.y; y <= bottomRightCorner.y; y++) {
        final point = Point(x, y);
        grid.putIfAbsent(point, () => false); // Put default value
        grid.update(Point(x, y), (value) {
          switch (action) {
            case Action.TURN_ON:
              return true;
            case Action.TURN_OFF:
              return false;
            case Action.TOGGLE:
              return !value;
          }
        });
      }
    }
  }

  void updateBrightnessOf(Map<Point, int> grid) {
    for (var x = topLeftCorner.x; x <= bottomRightCorner.x; x++) {
      for (var y = topLeftCorner.y; y <= bottomRightCorner.y; y++) {
        final point = Point(x, y);
        grid.putIfAbsent(point, () => 0); // Put default value
        grid.update(Point(x, y), (value) {
          switch (action) {
            case Action.TURN_ON:
              return value+1;
            case Action.TURN_OFF:
              return max(0, value-1);
            case Action.TOGGLE:
              return value+2;
          }
        });
      }
    }
  }

  static Instruction parse(String instruction) {
    final action = RegExp('(toggle|turn on|turn off)')
        .stringMatch(instruction)!
        .replaceAll(' ', '_')
        .toUpperCase();

    final bounds = RegExp(
            r'(?<topLeft>\d{1,3},\d{1,3}) through (?<bottomRight>\d{1,3},\d{1,3})')
        .firstMatch(instruction);

    final topLeft =
        bounds!.namedGroup('topLeft')!.split(',').map(int.parse).toList();
    final bottomRight =
        bounds.namedGroup('bottomRight')!.split(',').map(int.parse).toList();

    return Instruction(
      action: Action.values.firstWhere((it) => it.name == action),
      topLeftCorner: Point(topLeft[0], topLeft[1]),
      bottomRightCorner: Point(bottomRight[0], bottomRight[1]),
    );
  }
}
