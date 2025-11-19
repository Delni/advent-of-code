import '../../tools/dart/utils.dart';

void main() async {
  AdventOutput(
      day: 2,
      part1: (entries) => entries
          .map((e) => e.split(': '))
          .map((entry) =>
              OTCPPolicy.fromString(entry.first).validateNumber(entry.last))
          .where((ok) => ok)
          .length,
      part2: (entries) => entries
          .map((e) => e.split(': '))
          .map((entry) =>
              OTCPPolicy.fromString(entry.first).validatePosition(entry.last))
          .where((ok) => ok)
          .length);
}

class OTCPPolicy {
  int lowConstraint;
  int highConstraint;
  String character;

  OTCPPolicy({
    required this.lowConstraint,
    required this.highConstraint,
    required this.character,
  });

  bool validateNumber(String password) {
    int found =
        password.split('').where((element) => element == character).length;
    return found >= lowConstraint && found <= highConstraint;
  }

  bool validatePosition(String password) {
    bool isInFirstPosition = password[lowConstraint - 1] == character;
    bool isInSecondPostion = password[highConstraint - 1] == character;
    return isInFirstPosition != isInSecondPostion;
  }

  factory OTCPPolicy.fromString(String entry) {
    final character = entry.split(' ').last;
    final constraints = entry.split(' ').first.split('-').map(int.parse);
    return OTCPPolicy(
        lowConstraint: constraints.first,
        highConstraint: constraints.last,
        character: character);
  }
}
