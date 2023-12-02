library utils;

import 'dart:io';

import "package:path/path.dart" show dirname, join;

Future<List<String>> getInputForDay(int day) async {
  final path = join(dirname(Platform.script.path), '../inputs/day$day.txt');
  return (await new File(path).readAsLines()).toList();
}


extension MathList on Iterable {
  num sum() => this.fold<num>(0, (p, e) => p + e);
}
