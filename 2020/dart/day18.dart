import '../../tools/dart/utils.dart';

void main() {
  AdventOutput(
    day: 18,
    part1: (entries) => entries
        .map(elvesMath)
        .fold(0, (previousValue, element) => previousValue + element),
    part2: (entries) => 1,
  );
  // print(elvesMath('2 * 3 + (4 * 5)'));
  // print(elvesMath('5 + (8 * 3 + 9 + 3 * 4 * 3)'));
}

int elvesMath(String input) {
  String result = "$input";
  RegExp group = RegExp(r'\(([\d\+\*\s]+)\)');
  Iterable<RegExpMatch> matches;
  while ((matches = group.allMatches(result)).length > 0) {
    matches.forEach((match) {
      result = result.replaceFirst(
          match.group(0)!, elfMath(match.group(1)!).toString());
    });
  }

  return elfMath(result);
}

int elfMath(String input) {
  String result = "$input";
  Iterable<RegExpMatch> matches;
  RegExp unitOperation = RegExp(r'(\d+) ([\+\*]) (\d+)+');
  while ((matches = unitOperation.allMatches(result)).length > 0) {
    matches.forEach((match) {
      final firstOperand = int.parse(match.group(1)!);
      final secondOperand = int.parse(match.group(3)!);
      final calculus = match.group(2) == '+'
          ? firstOperand + secondOperand
          : firstOperand * secondOperand;
      result = result.replaceFirst(match.group(0)!, '$calculus');
    });
  }
  return int.parse(result);
}