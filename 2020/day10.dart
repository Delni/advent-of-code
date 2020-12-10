import 'dart:math';

import 'utils.dart';

void main() {
  TypedOutput<int>(
      day: 10,
      part1: (entries) {
        Map<int, int> differences = {
          3: 1
        }; // Always add a diff of 3 for the last adapter
        entries.sort();
        int lastValue = 0;
        for (var i = 0; i < entries.length; i++) {
          final diff = entries[i] - lastValue;
          differences.update(diff, (value) => value + 1, ifAbsent: () => diff);
          lastValue = entries[i];
        }
        return differences[1] * differences[3];
      },
      part2: (entries) {
        entries..add(0)..sort();
        var canBeRemoved = 1;
        var i = 0;
        while (i < entries.length) {
          var j = i;
          while (j != entries.length - 1 && entries[j+1] - entries[j] == 1) {
            j++;
          }
          if( i != j) {
            final nb_min = max(0, (j - i - 3));
            canBeRemoved *= pow(2, j - i -1) - nb_min;
          }

          i = j+1;
        }
        return canBeRemoved;
      },
      pipe: (input) => input.map(int.parse).toList());
}
