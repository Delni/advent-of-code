import '../../tools/dart/utils.dart';

void main() async {
  TypedOutput<String>(
    day: 7,
    part1: (entries) => Circuit.from(entries).resolve('a'),
    part2: (entries) => Circuit.from(entries).put('a')['in']!('b').resolve('a'),
  );
}

class Circuit {
  Map<String, String> wires = {};
  Map<String, int> loadedWires = {};

  Circuit.from(List<String> entries) {
    entries.forEach((entry) {
      final [value, key] = entry.split(' -> ');
      wires.putIfAbsent(key, () => value);
    });
  }

  Map<String, Circuit Function(String)> put(String resolvedKey) {
    final resolvedValue = resolve(resolvedKey);
    final circuit = this;
    return {
      'in': (String overridenKey) {
        loadedWires = {};
        loadedWires[overridenKey] = resolvedValue;
        return circuit;
      }
    };
  }

  int resolve(String key) {
    if (loadedWires.containsKey(key)) {
      return loadedWires[key]!;
    }
    final simpleInstruction = RegExp(r'\w+');
    final verbInstruction =
        RegExp(r'(?<left>\w+) (?<verb>[A-Z]+) (?<right>\w+)');
    final notInstruction = RegExp(r'NOT (?<right>\w+)');
    if (!wires.containsKey(key)) {
      throw ArgumentError('Key $key not found in wires');
    }
    final input = wires[key]!;

    final verbMap = {
      'AND': (int a, int b) => a & b,
      'OR': (int a, int b) => a | b,
      'LSHIFT': (int a, int b) => a << b,
      'RSHIFT': (int a, int b) => a >> b,
    };

    final int result;
    if (verbInstruction.hasMatch(input)) {
      final match = verbInstruction.firstMatch(input)!;
      result = _resolveOperand(
        match,
        verbMap[match.namedGroup('verb')!]!,
      );
    } else if (notInstruction.hasMatch(input)) {
      result = ~_simpleResolve(
        notInstruction.firstMatch(input)!.namedGroup('right')!,
      );
    } else if (simpleInstruction.hasMatch(input)) {
      result = _simpleResolve(input);
    } else {
      result = 0;
    }
    var masked = result & 0xFFFF; // Masking for 16BIT
    loadedWires[key] = masked;
    return masked;
  }

  int _simpleResolve(String input) {
    return int.tryParse(input) ?? resolve(input);
  }

  int _resolveOperand(RegExpMatch match, int Function(int, int) callback) =>
      callback(
        _simpleResolve(match.namedGroup('left')!),
        _simpleResolve(match.namedGroup('right')!),
      );
}