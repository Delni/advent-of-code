import 'package:AdventOfCode2023/utils.dart';

extension MathList on Iterable {
  num sum() => this.fold<num>(0, (p, e) => p + e);
}

void main() async {
  final List<String> input = await getInputForDay(1);
  print("--- PART 0NE --- ");
  print(input.map(findFirstAndLastDigit).sum());
  print("--- PART TWO --- ");
  print(
    input
        .map((e) => findFirstAndLastDigit(replaceFirstAndLastSpelledNumbers(e)))
        .sum(),
  );
}

int findFirstAndLastDigit(String message) {
  // print(message);
  final digitsOnly = message.split("").map((letter) => int.tryParse(letter));
  final int firstDigit = digitsOnly.firstWhere((element) => element != null)!;
  final int lastDigit = digitsOnly.lastWhere((element) => element != null)!;
  return firstDigit * 10 + lastDigit;
}

String replaceFirstSpelledNumber(String message) {
  final numbers = RegExp(r'one|two|three|four|five|six|seven|eight|nine');
  final matches = numbers.allMatches(message);
  if (matches.isEmpty) {
    return message;
  }
  return message.replaceRange(
    matches.first.start,
    matches.first.end,
    wordToDigit(message.substring(matches.first.start, matches.first.end)),
  );
}

String replaceLastSpelledNumber(String message) {
  final reversedMessage = message.split('').reversed.join('');
  final numbers = RegExp('one|two|three|four|five|six|seven|eight|nine'
      .split('')
      .reversed
      .join(''));
  final matches = numbers.allMatches(reversedMessage);
  if (matches.isEmpty) {
    return message;
  }

  final actualStart = message.length - matches.first.end;
  final actualEnd = message.length - matches.first.start;

  return message.replaceRange(
    actualStart,
    actualEnd,
    wordToDigit(message.substring(actualStart, actualEnd)),
  );
}

String replaceFirstAndLastSpelledNumbers(String message) =>
    replaceLastSpelledNumber(replaceFirstSpelledNumber(message));

String wordToDigit(String word) => word
    .replaceAll('one', '1')
    .replaceAll('two', '2')
    .replaceAll('three', '3')
    .replaceAll('four', '4')
    .replaceAll('five', '5')
    .replaceAll('six', '6')
    .replaceAll('seven', '7')
    .replaceAll('eight', '8')
    .replaceAll('nine', '9');
