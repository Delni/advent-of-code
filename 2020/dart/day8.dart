import '../../tools/dart/utils.dart';

main() {
  AdventOutput(
    day: 8,
    part1: (entries) => HandHeldConsole().run(instructions: entries),
    part2: (entries) => HandHeldConsole().bootSequence(instructions: entries),
  );
}

class HandHeldConsole {
  Set<int> _visited = {};
  int _accumulator = 0;
  int _pointer = 0;

  HandHeldConsole();

  void acc(int value) {
    _accumulator += value;
  }

  void jmp(int value) {
    _pointer += value;
  }

  void nop(int value) {}

  void reset() {
    _pointer = 0;
    _accumulator = 0;
    _visited = {};
  }

  int run({required List<String> instructions}) {
    if (_visited.contains(_pointer) || _pointer >= instructions.length) {
      return _accumulator;
    } else {
      _visited.add(_pointer);
      final instruction = instructions[_pointer].split(' ');
      switch (instruction[0]) {
        case 'acc':
          acc(int.parse(instruction[1]));
          _pointer++;
          break;
        case 'jmp':
          jmp(int.parse(instruction[1]));
          break;
        case 'nop':
          nop(int.parse(instruction[1]));
          _pointer++;
          break;
        default:
          break;
      }
    }
    return run(instructions: instructions);
  }

  int bootSequence({required List<String> instructions}) {
    for (var i = 0; i < instructions.length; i++) {
      reset();
      final instruction = instructions[i];
      late List<String> alteredInstructions;
      if (instruction.contains('jmp')) {
        alteredInstructions = List.from(instructions.whereType<String>())
          ..replaceRange(i, i+1, [instruction.replaceAll('jmp', 'nop')]);
      } else if (instruction.contains('nop')) {
        alteredInstructions = List.from(instructions.whereType<String>())
          ..replaceRange(i, i+1, [instruction.replaceAll('nop', 'jmp')]);
      }
      run(instructions: alteredInstructions);
      if (_pointer >= instructions.length) {
        return _accumulator;
      }
    }
    return _accumulator;
  }
}
