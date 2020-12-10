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
        entries.sort();
        final max = entries.last;
        return entries.map((adapter) {
          if (adapter == max) {
            return 0;
          }
          final possibilities = entries
              .where((element) =>
                  [adapter + 1, adapter + 2, adapter + 3].contains(element))
              .length;
          if (possibilities == 1) {
            return 0;
          }
          return possibilities;
        }).fold(1, (previousValue, element) => previousValue * element);
      },
      pipe: (input) => input.map(int.parse).toList());
}

double combinaison(int n, int k) => f(n) / (f(n - k) * f(k));

int f(int i) => (i == 0) ? 1 : i * f(i - 1);

// For each adapter
// How many next exist ?
// then multiply it
