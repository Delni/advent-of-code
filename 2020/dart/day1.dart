import 'utils.dart';

void main() async {
  const target = 2020;
  TypedOutput<int>(
      day: 1,
      part1: (entries) => findCoupleThatSumsTo(entries, target)
          .first
          .reduce((value, element) => value * element),
      part2: (entries) => findTrioThatSumsTo(entries, target)
          .first
          .reduce((value, element) => value * element),
      pipe: (input) => input.map(int.parse).toList());
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
