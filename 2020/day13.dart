import 'utils.dart';

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
      return buses.first.ID * buses.first.closestDepartureTo(departure);
    },
    part2: (entries) => 1,
  );
  // final departure = 939;
  final input = ['7', '13', 'x', 'x', '59', 'x', '31', '19'];
  final buses = input.map((e) => Bus(e)).toList();
  var t = 1;
  while (true) {
    for (var i = 0; i < buses.length; i++) {
      final element = buses[i];
      if (element.ID != null && element.closestDepartureTo(t) != t + i) {
        break;
      }
      print(t);
    }
    t++;
  }
  // print(t);
}

class Bus {
  int ID;

  Bus(String id) {
    if (id != 'x') {
      this.ID = int.parse(id);
    }
  }

  int closestDepartureTo(int departure) {
    return ID - departure % ID;
  }

  @override
  String toString() => "Bus ($ID)";
}

Comparator<Bus> Function(int) byNextDepartureFrom = (int departure) =>
    (Bus b1, Bus b2) => b1
        .closestDepartureTo(departure)
        .compareTo(b2.closestDepartureTo(departure));
