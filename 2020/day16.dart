import 'utils.dart';

void main() {
  AdventOutput(
    day: 16,
    part1: (entries) {
      final fields =
          entries.first.split('\n').map((e) => Field.from(e)).toList();
      final nearbyTickets = entries.last.split('\n').toList()..removeAt(0);
      return nearbyTickets
          .map((e) => e.split(',').map((e) => int.parse(e)).toList())
          .map(
            (element) => fields
                .firstWhere((field) => field.invalidCode(element) != null)
                ?.invalidCode(element),
          )
          .where((e) => e != null)
          .fold(0, (total, element) => total + element);
    },
    part2: (entries) => 1,
    pipe: (input) => input.join('\n').split('\n\n'),
  );
}

class Ticket {
  List<int> fields;
}

class Field {
  String name;
  List<int> values;

  Field({this.name, this.values});

  int invalidCode(List<int> codes) {
    final invalid = codes.where((element) => !values.contains(element));
    return invalid.length != 0 ? invalid.first : null;
  }

  factory Field.from(String input) {
    String name = input.split(':').first;
    RegExp r = RegExp(r'^.*: (\d{1,4})-(\d{1,4}) or (\d{1,4})-(\d{1,4})$');
    final matches = r
        .allMatches(input)
        .first
        .groups([1, 2, 3, 4])
        .map((e) => int.parse(e))
        .toList();

    List<int> values = []
      ..addAll(List.generate(
        matches[1] - matches[0] + 1,
        (index) => index + matches[0],
      ))
      ..addAll(List.generate(
        matches[3] - matches[2] + 1,
        (index) => index + matches[2],
      ));
    return Field(name: name, values: values);
  }
}
