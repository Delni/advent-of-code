import '../../tools/dart/utils.dart';

main() {
  AdventOutput(
    day: 13,
    part1: (entries) {
      final departure = int.parse(entries.first);
      final buses = entries.last
          .split(',')
          .map((e) => Bus(e))
          .where((element) => element.ID != null)
          .toList()
            ..sort(byNextDepartureFrom(departure));
      return buses.first.ID! * buses.first.closestDepartureTo(departure);
    },
    part2: (entries) {
      final buses = entries.last.split(',').map((e) => Bus(e)).toList();
      final rem = [];
      for (var i = 0; i < buses.length; i++) {
        final element = buses[i];
        if (element.ID != null) {
          rem.add(i == 0 ? 0 : (element.ID! - i % element.ID!));
        }
      }
      return chineseRemainder(
        rem.map((e) => BigInt.from(e)).toList(),
        buses
            .where(Bus.isNotNull)
            .map(Bus.toBigInt)
            .toList(),
      ).toInt();
    },
  );
}

class Bus {
  int? ID;

  Bus(String id) {
    if (id != 'x') {
      this.ID = int.parse(id);
    }
  }

  int closestDepartureTo(int departure) {
    return ID! - departure % ID!;
  }

  static bool isNotNull(Bus b) => b.ID != null;
  static BigInt toBigInt(Bus b) => BigInt.from(b.ID!);

  @override
  String toString() => "Bus ($ID)";
}

Comparator<Bus> Function(int) byNextDepartureFrom = (int departure) =>
    (Bus b1, Bus b2) => b1
        .closestDepartureTo(departure)
        .compareTo(b2.closestDepartureTo(departure));

BigInt chineseRemainder(List<BigInt> remainders, List<BigInt> modules) {
  final product = modules.reduce((value, element) => value * element);

  BigInt sum = BigInt.zero;

  for (var i = 0; i < modules.length; i++) {
    BigInt mod = modules[i];
    final p = BigInt.from((product / mod).floor());
    sum += (remainders[i] * modularMultiplicativeInverse(p, mod) * p);
  }

  return sum;
}

BigInt modularMultiplicativeInverse(BigInt a, BigInt modulus) {
  BigInt b = a % modulus;
  for (var hypothesis = BigInt.one;
      hypothesis < modulus;
      hypothesis + BigInt.one) {
    if ((b * hypothesis) % modulus == BigInt.one) {
      return hypothesis;
    }
  }
  return BigInt.one;
}
