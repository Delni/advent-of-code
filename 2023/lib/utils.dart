library utils;

import 'dart:io';

import "package:path/path.dart" show dirname, join;

Future<List<String>> getInputForDay(int day) async {
  final path = join(dirname(Platform.script.path), '../inputs/day$day.txt');
  return (await new File(path).readAsLines()).toList();
}