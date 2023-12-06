import 'dart:math';

import 'package:AdventOfCode2023/utils.dart';

void main() async {
  final List<String> input = (await getInputForDay(5)).join("\n").split("\n\n");

  print("--- PART 0NE ---");
  print(partOne(input));
  print("--- PART TWO ---");
  // print(winScratchCards(input, initialCards: input));
}

int partOne(List<String> input) {
  final seeds = input.first
      .split(":")
      .last
      .split(" ")
      .where((element) => element.isNotEmpty)
      .map(int.parse);
  var mappedResult = [...seeds];
  input
      .sublist(1)
      .map((e) => e.split("\n"))
      .map(mapToRanges)
      .forEach((indications) {
    mappedResult = pipe(mappedResult, indications);
  });
  return mappedResult.reduce(min);
}

class RangeIndication {
  final int source;
  final int destination;
  final int range;

  const RangeIndication({
    required this.source,
    required this.destination,
    required this.range,
  });

  factory RangeIndication.fromString(String indication) {
    final match = RegExp(r"^(?<destination>\d+) (?<source>\d+) (?<range>\d+)$")
        .firstMatch(indication)!;
    return RangeIndication(
      source: int.parse(match.namedGroup('source')!),
      destination: int.parse(match.namedGroup('destination')!),
      range: int.parse(match.namedGroup('range')!),
    );
  }

  bool contains(int value) => value >= source && value <= source + range;
  int pipe(int value) => destination + (value - source);

  @override
  bool operator ==(Object other) =>
      other is RangeIndication &&
      other.source == source &&
      other.destination == destination &&
      other.range == range;
}

List<RangeIndication> mapToRanges(List<String> map) =>
    map.where((element) => !element.contains(":")).map((rangeIndication) {
      final match =
          RegExp(r"^(?<destination>\d+) (?<source>\d+) (?<range>\d+)$")
              .firstMatch(rangeIndication)!;
      return RangeIndication(
        source: int.parse(match.namedGroup('source')!),
        destination: int.parse(match.namedGroup('destination')!),
        range: int.parse(match.namedGroup('range')!),
      );
    }).toList();

List<int> pipe(List<int> source, List<RangeIndication> indications) => source
    .map((element) =>
        indications
            .where((indication) => indication.contains(element))
            .firstOrNull
            ?.pipe(element) ??
        element)
    .toList();
