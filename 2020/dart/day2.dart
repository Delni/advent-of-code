import 'utils.dart';

void main() async {
  final input = (await getInputForDay(2)).toList();

  print('Day 2.1 result is ${day2_1(input)}');
  print('Day 2.2 result is ${day2_2(input)}');
}

int day2_1(List<String> entries) => entries
    .map((entry) {
      final splittedEntry = entry.split(': ');
      return OTCPPolicy
          .fromString(splittedEntry.first)
          .validate(splittedEntry.last);
    })
    .where((ok) => ok)
    .length;

int day2_2(List<String> entries) => 1;

class OTCPPolicy {
  int min;
  int max;
  String character;

  OTCPPolicy({this.min, this.max, this.character});

  bool validate(String password) {
    int found = password
      .split('')
      .where((element) => element == character)
      .length;
    return found >= min && found <= max;
  }

  @override
  String toString() =>
      "OTCP Policy :" + " have $character between $min and $max times.";

  factory OTCPPolicy.fromString(String entry) {
    final character = entry.split(' ').last;
    final minMax = entry.split(' ').first.split('-').map(int.parse);
    return OTCPPolicy(
        min: minMax.first, max: minMax.last, character: character);
  }
}
