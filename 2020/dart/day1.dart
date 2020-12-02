import 'dart:io';

import "package:path/path.dart" show dirname, join;

void main() async {
  final path = join(dirname(Platform.script.path), '../inputs/day1.txt');
  List<int> input =
      (await new File(path).readAsLines()).map(int.parse).toList();

  print('Day 1.1 result is ${day1_1(input)}');
  print('Day 1.2 result is ${day1_2(input)}');
}

int day1_1(List<int> entries) {
  return findCoupleThatSumsTo(entries, 2020)
      .first
      .reduce((value, element) => value * element);
}

int day1_2(List<int> entries) {
  return findTrioThatSumsTo(entries, 2020)
      .first
      .reduce((value, element) => value * element);
}

List<List<int>> findCoupleThatSumsTo(List<int> entries, int target) {
  final double middle = target / 2;

  var greaterGroup = entries.where((int element) => element >= middle).toList();
  var lesserGroup = entries.where((int element) => element < middle).toList();

  List<List<int>> result = [];

  greaterGroup.forEach((element) {
    List<int> addable =
        lesserGroup.where((second) => second + element == target).toList();
    if (addable.isNotEmpty) {
      result.add([element, addable.first]);
    }
  });

  return result;
}

List<List<int>> findTrioThatSumsTo(List<int> entries, int target) {
  List<List<int>> result = [];

  entries.forEach((element) {
    final couples = findCoupleThatSumsTo(entries, target - element);
    if (couples.isNotEmpty) {
      List<int> intermediateResult = [element, ...couples.first];
      result.add(intermediateResult);
    }
  });

  return result;
}
