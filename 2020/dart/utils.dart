library utils;

import 'dart:io';

import "package:path/path.dart" show dirname, join;

Future<List<String>> getInputForDay(int day) async {
  final path = join(dirname(Platform.script.path), '../inputs/day$day.txt');
  return (await new File(path).readAsLines()).toList();
}

void printResult(String part, dynamic result) {
  const ANSI_BOLD_YELLOW = '\x1B[1;93m';
  const ANSI_RESET = '\x1B[0m';
  print('Part $part result is $ANSI_BOLD_YELLOW$result$ANSI_RESET.');
}
