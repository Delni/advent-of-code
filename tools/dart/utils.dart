library utils;

import 'dart:io';

import "package:path/path.dart" show dirname, join;

Future<List<String>> getInputForDay(int day) async {
  var path = join(dirname(Platform.script.path), '../resources/day${day.toString().padLeft(2, '0')}.txt');
  return (await new File(path).readAsLines()).toList();
}

const ANSI_BOLD_YELLOW = '\x1B[1;93m';
const ANSI_RESET = '\x1B[0m';

void printResult(String part, dynamic result) {
  print('Part $part result is $ANSI_BOLD_YELLOW$result$ANSI_RESET');
}

class TypedOutput<T> {
  static const int width = 42;
  int day;
  int Function(List<T>) part1;
  int Function(List<T>) part2;
  List<T> Function(List<String>)? pipe;

  TypedOutput({
    required this.day,
    required this.part1,
    required this.part2,
    this.pipe,
  }) {
    run();
  }

  void run() async {
    final middle = ((width - 8) / 2).floor();
    final separators = List.generate(middle, (_) => '-').join();

    final title = 'DAY ' + (day > 10 ? '' : '0');

    print('$separators $title$day $separators');

    List<T> input;
    if (pipe != null) {
      input = pipe!((await getInputForDay(day)).toList());
    } else {
      input = (await getInputForDay(day)).toList() as List<T>;
    }
    var start = new DateTime.now();
    final result1 = await Future.microtask(() => part1(List.from(input)));
    var elapsed = new DateTime.now().difference(start).inMilliseconds;
    print('Part 1 (${elapsed}ms):'.padRight(width - result1.toString().length) +
        '$ANSI_BOLD_YELLOW$result1$ANSI_RESET');
    start = new DateTime.now();
    final result2 = await Future.microtask(() => part2(List.from(input)));
     elapsed = new DateTime.now().difference(start).inMilliseconds;
    print('Part 2 (${elapsed}ms):'.padRight(width - result2.toString().length) +
        '$ANSI_BOLD_YELLOW$result2$ANSI_RESET');
    print('-' * width);
  }
}

class AdventOutput extends TypedOutput<String> {
  AdventOutput({
    required int day,
    required int Function(List<String>) part1,
    required int Function(List<String>) part2,
    List<String> Function(List<String>)? pipe,
  }) : super(day: day, part1: part1, part2: part2, pipe: pipe);
}
