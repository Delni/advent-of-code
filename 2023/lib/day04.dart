import 'dart:math';

import 'package:AdventOfCode2023/utils.dart';

void main() async {
  final List<String> input = await getInputForDay(4);

  print("--- PART 0NE ---");
  print(input.map(score).sum());
  print("--- PART TWO ---");
  print(winScratchCards(input, initialCards: input));
}

num score(String card) {
  final validNumbers = stripWinningNumbers(card);
  if (validNumbers.isEmpty) {
    return 0;
  }
  return pow(2, validNumbers.length - 1);
}

List<int> stripWinningNumbers(String card) {
  final winningNumbers = card
      .split(":")
      .last
      .split('|')
      .first
      .split(" ")
      .where((element) => element.isNotEmpty)
      .map(trimToInt);
  final drawnNumbers = card
      .split('|')
      .last
      .split(" ")
      .where((element) => element.isNotEmpty)
      .map(trimToInt);
  return drawnNumbers
      .where((element) => winningNumbers.contains(element))
      .toList();
}

int trimToInt(String number) => int.parse(number.trim());

int winScratchCards(List<String> cards, {required List<String> initialCards}) {
  int total = cards.length;
  cards.forEach((card) {
    final validNumbers = stripWinningNumbers(card);
    final cardId = int.parse(
        RegExp(r'Card\s+(?<id>\d+):').firstMatch(card)!.namedGroup('id')!);
    if (validNumbers.isNotEmpty) {
      total += winScratchCards(
        initialCards.sublist(cardId, validNumbers.length + cardId),
        initialCards: initialCards,
      );
    }
  });
  return total;
}
