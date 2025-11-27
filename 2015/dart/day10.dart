import '../../tools/dart/utils.dart';

void main() {
  TypedOutput<String>(
    day: 10,
    part1: (entries) => lookAndSay(entries.first, 40).length,
    part2: (entries) => lookAndSay(entries.first, 50).length,
  );
}

String lookAndSay(String input, [int applyTimes = 1]) {
  StringBuffer result = StringBuffer();
  int index = 0;
  final characters = input.split("");
  while (index < input.length) {
    final char = input[index];
    final length = characters.skip(index).takeWhile((it) => it == char).length;
    index += length;
    result.write("$length$char");
  }
  if(applyTimes > 1) {
    return lookAndSay(result.toString(), applyTimes-1);
  }
  return result.toString();
}