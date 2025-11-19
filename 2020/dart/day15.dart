import '../../tools/dart/utils.dart';

main() {
  TypedOutput<int>(
    day: 15,
    part1: spokenNumberAt(2020),
    part2: spokenNumberAt(10),
    pipe: (input) => input.map(int.parse).toList(),
  );
}

int Function(List<int>) spokenNumberAt(int target) {
  return (entries) {
    Map<int, SpokenNumber> spokenNumbers = {};
    for (var i = 0; i < entries.length; i++) {
      spokenNumbers.putIfAbsent(
        entries[i],
        () => SpokenNumber(lastTime: i + 1),
      );
    }
    var nextNumber = entries.last;
    for (var i = entries.length + 1; i < target; i++) {
      final element = spokenNumbers[nextNumber]!;
      nextNumber = element.secondLastTime != null
          ? element.lastTime - element.secondLastTime!
          : 0;
      spokenNumbers.update(
        nextNumber,
        (value) => SpokenNumber(
          lastTime: i,
          secondLastTime: value.lastTime,
        ),
        ifAbsent: () => SpokenNumber(lastTime: i),
      );
    }
    return nextNumber;
  };
}

class SpokenNumber {
  int lastTime;
  int? secondLastTime;
  SpokenNumber({required this.lastTime, this.secondLastTime});
}

/**
 fun solutionWithNbTurn(input: String, nbTurn: Int): Int {
    val initialNumbers = input.split(",").map { it.toInt() }

    val spokenNumbers = initialNumbers
        .mapIndexed { index, i -> i to SpokenNumber(index + 1, null) }
        .toMap().toMutableMap()

    var numberToSay = initialNumbers.last()

    for (turnNumber in initialNumbers.size + 1..nbTurn) {
        numberToSay = spokenNumbers[numberToSay]?.let {
            it.secondLastTimeSaid?.let { secondLastTimeSaid -> it.lastTimeSaid - secondLastTimeSaid }
        } ?: 0

        spokenNumbers[numberToSay] = spokenNumbers[numberToSay]?.let {
            it.copy(lastTimeSaid = turnNumber, secondLastTimeSaid = it.lastTimeSaid)
        } ?: SpokenNumber(turnNumber, null)
    }

    return numberToSay
}

data class SpokenNumber(val lastTimeSaid: Int, val secondLastTimeSaid: Int?)
 */
