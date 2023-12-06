import 'package:AdventOfCode2023/utils.dart';

void main() async {
  final List<String> input = await getInputForDay(3);

  print("--- PART 0NE ---");
  print(findEngineParts(input).sum());
}

List<int> findEngineParts(List<String> input) {
  final numRegex = RegExp(r'(\d+)');
  final parts = <int>[];
  for (var i = 0; i < input.length; i++) {
    final matches = numRegex.allMatches(input[i]);
    if (matches.isEmpty) {
      continue;
    }

    matches.forEach((element) {
      final value = int.parse(input[i].substring(element.start, element.end));
      final hasSymbolBefore =
          element.start > 0 && input[i][element.start - 1] != ".";
      final hasSymbolAfter =
          element.end < input[i].length - 1 && input[i][element.end] != ".";

      final hasSymbolAbove = range(element.start - 1, element.end + 1).any((x) {
        final isInLine = x > 0 && x < input[i].length;
        return i >= 1 && isInLine && input[i - 1][x] != ".";
      });

      final hasSymbolBelow = range(element.start - 1, element.end + 1).any((x) {
        final isInLine = x > 0 && x < input[i].length;
        return i < input.length - 1 && isInLine && input[i + 1][x] != ".";
      });

      if (hasSymbolAbove ||
          hasSymbolBelow ||
          hasSymbolBefore ||
          hasSymbolAfter) {
        parts.add(value);
      }
    });
  }
  return parts;
}

List<int> range(int from, int to) =>
    List.generate(to - from, (index) => index + from);
