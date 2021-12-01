import 'utils.dart';

void main() async {
  AdventOutput(
      day: 3,
      part1: (entries) => checkForTrees(Slope(right: 3, down: 1), entries),
      part2: (entries) => [
            Slope(right: 1, down: 1),
            Slope(right: 3, down: 1),
            Slope(right: 5, down: 1),
            Slope(right: 7, down: 1),
            Slope(right: 1, down: 2),
          ]
              .map((slope) => checkForTrees(slope, entries))
              .reduce((value, element) => value * element));
}

int checkForTrees(Slope slope, List<String> entries) {
  int result = 0;
  int right = 0;
  for (var i = 0; i < entries.length - slope.down; i += slope.down) {
    right = (right + slope.right) % entries[i].length;
    if (entries[i + slope.down][right] == '#') {
      result++;
    }
  }
  return result;
}

class Slope {
  int right;
  int down;

  Slope({required this.right, required this.down});
}
