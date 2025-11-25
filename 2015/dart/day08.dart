import '../../tools/dart/utils.dart';
import 'day06.dart';

void main() async {
  TypedOutput<String>(
    day: 8,
    part1: (entries) => entries
      .map((it) => it.length - it.inMemoryLength)
      .reduce(plus),
    part2: (entries) => entries
      .map((it) => it.encoded.length - it.length)
      .reduce(plus),
  );
}

extension Counter on String {
  int get inMemoryLength {
    return replaceAll(r'\\', r'\')
        .replaceAll(r'\"', "#")
        .replaceAll(RegExp(r'(\\x([a-f]|\d){2})'), "#")
        .replaceAll(r'"', '')
        .length;
  }

  String get encoded {
    return '"' + replaceAll(r'\', r'\\').replaceAll(r'"', r'\"') + '"';
  }
}
