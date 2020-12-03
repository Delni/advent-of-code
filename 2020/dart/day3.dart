import 'utils.dart';

void main() async {
  final input = (await getInputForDay(3)).toList();
  print('--------- DAY 03 ---------');
  printResult('1', day3_1(input));
  printResult('2', day3_2(input));
}

int day3_1(List<String> entries) =>
    checkForTrees(Slope(right: 3, down: 1), entries);

int day3_2(List<String> entries) => [
      Slope(right: 1, down: 1),
      Slope(right: 3, down: 1),
      Slope(right: 5, down: 1),
      Slope(right: 7, down: 1),
      Slope(right: 1, down: 2),
    ]
        .map((slope) => checkForTrees(slope, entries))
        .reduce((value, element) => value * element);

int checkForTrees(Slope slope, List<String> entries) {
  int result = 0;
  int right = 0;
  for (var i = 0; i < entries.length - slope.down; i+=slope.down) {
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

  Slope({this.right, this.down});
}
