library utils;

import 'dart:io';
import 'package:meta/meta.dart';

import "package:path/path.dart" show dirname, join;

Future<List<String>> getInputForDay(int day) async {
  final path = join(dirname(Platform.script.path), 'inputs/day$day.txt');
  return (await new File(path).readAsLines()).toList();
}

const ANSI_BOLD_YELLOW = '\x1B[1;93m';
const ANSI_RESET = '\x1B[0m';

void printResult(String part, dynamic result) {
  print('Part $part result is $ANSI_BOLD_YELLOW$result$ANSI_RESET');
}

class AdventOutput {
  static const int width = 42;
  int day;
  int Function(List<String>) part1;
  int Function(List<String>) part2;
  List<String> Function(List<String>) pipe;

  AdventOutput(
      {@required this.day,
      @required this.part1,
      @required this.part2,
      this.pipe}) {
    run();
  }

  void run() async {
    final middle = ((width - 8) / 2).floor();
    final separators = List.generate(middle, (_) => '-').join();

    final title = 'DAY ' + (day > 10 ? '' : '0');

    print('$separators $title$day $separators');
    
    var input = (await getInputForDay(day)).toList();
    if (pipe != null) {
      input = pipe(input);
    }
    final result1 = await Future.microtask(() => part1(input));
    print('Part 1 :'.padRight(width - result1.toString().length) +
        '$ANSI_BOLD_YELLOW$result1$ANSI_RESET');
    final result2 = await Future.microtask(() => part2(input));
    print('Part 2 :'.padRight(width - result2.toString().length) +
        '$ANSI_BOLD_YELLOW$result2$ANSI_RESET');
  }
}
