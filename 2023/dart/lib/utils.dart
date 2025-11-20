library utils;

import 'dart:io';

import "package:path/path.dart" show dirname, join;

const resourcePath = "../../resources";

Future<List<String>> getInputForDay(int day) async {
  final path = join(dirname(Platform.script.path), resourcePath, 'day${day.toString().padLeft(2, '0')}.txt');
  return (await new File(path).readAsLines()).toList();
}

extension MathList on Iterable {
  num sum() => this.fold<num>(0, (p, e) => p + e);
}
