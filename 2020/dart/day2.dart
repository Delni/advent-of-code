import 'utils.dart';

void main() async {
  final input = (await getInputForDay(2)).toList();

  print('Day 2.1 result is \x1B[1;93m${day2_1(input)}\x1B[0m.');
  print('Day 2.2 result is \x1B[1;93m${day2_2(input)}\x1B[0m.');
}

int day2_1(List<String> entries) => entries
    .map((entry) {
      final splittedEntry = entry.split(': ');
      return OTCPPolicy.fromString(splittedEntry.first)
          .validateNumber(splittedEntry.last);
    })
    .where((ok) => ok)
    .length;

int day2_2(List<String> entries) => entries
    .map((entry) {
      final splittedEntry = entry.split(': ');
      return OTCPPolicy.fromString(splittedEntry.first)
          .validatePosition(splittedEntry.last);
    })
    .where((ok) => ok)
    .length;

class OTCPPolicy {
  int lowConstraint;
  int highConstraint;
  String character;

  OTCPPolicy({this.lowConstraint, this.highConstraint, this.character});

  bool validateNumber(String password) {
    int found = password.split('')
        .where((element) => element == character)
        .length;
    return found >= lowConstraint && found <= highConstraint;
  }

  bool validatePosition(String password) {
    bool isInFirstPosition = password[lowConstraint - 1] == character;
    bool isInSecondPostion =password[highConstraint - 1] == character;
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