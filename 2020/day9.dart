import 'utils.dart';

main() {
  TypedOutput<int>(
      day: 9,
      part1: (entries) => firstError(entries),
      part2: (codes) {
        final errorCode = firstError(codes);
        return rangeToSum(errorCode, codes);
      },
      pipe: (List<String> input) => input.map(int.parse).toList());
}

int firstError(List<int> codes) {
  for (var i = 25; i < codes.length; i++) {
    final preamble = codes.sublist(i - 25, i);
    final anySumsToCode = preamble
        .any((element) => preamble.any((code) => code + element == codes[i]));
    if (!anySumsToCode) {
      return codes[i];
    }
  }
  return 0;
}

int rangeToSum(int target, List<int> codes) {
  List<int> range = [];
  for (var i = 0; i < codes.length; i++) {
    var sum = 0;
    for (var j = i; j < codes.length - 1; j++) {
      sum += codes[j];

      if (sum == target) {
        range = codes.sublist(i, j)..sort();
        return range.first + range.last;
      } else if (sum > target) {
        break;
      }
    }
  }
  return 0;
}
