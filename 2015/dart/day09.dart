import 'dart:math';

import '../../tools/dart/utils.dart';
import 'day06.dart';

void main() {
  TypedOutput<LocationPair>(
    day: 9,
    part1: (entries) => SantaRoutes.fromLocations(entries).shortest,
    part2: (entries) => SantaRoutes.fromLocations(entries).longest,
    pipe: (entries) => entries.map(LocationPair.from).toList(),
  );
}

extension AccumulatorForSanta on Iterable<LocationPair> {
  SantaRoutes asRoutes() {
    final santaRoutes = SantaRoutes();
    forEach((route) {
      santaRoutes.routes.putIfAbsent(route.from, () => {});
      santaRoutes.routes.putIfAbsent(route.to, () => {});
      santaRoutes.routes[route.from]![route.to] = route.distance;
      santaRoutes.routes[route.to]![route.from] = route.distance;
    });
    return santaRoutes;
  }
}

class LocationPair {
  late final String from;
  late final String to;
  late final int distance;

  LocationPair.from(String input) {
    final regexp = RegExp(r'(?<from>\w+) to (?<to>\w+) = (?<distance>\d+)');
    if (!regexp.hasMatch(input)) {
      throw ArgumentError('I don\'t know what to do with $input');
    }

    final match = regexp.firstMatch(input)!;
    from = match.namedGroup('from')!;
    to = match.namedGroup('to')!;
    distance = int.parse(match.namedGroup("distance")!);
  }
}

class SantaRoutes {
  final Map<String, Map<String, int>> routes = {};

  SantaRoutes();

  Iterable<String> get cities => routes.keys;

  int get shortest {
    return travelingSantaProblem(routes).reduce(min);
  }

  int get longest {
    return travelingSantaProblem(routes).reduce(max);
  }

  SantaRoutes.fromLocations(Iterable<LocationPair> locations) {
    locations.forEach((location) {
      routes.putIfAbsent(location.from, () => {});
      routes.putIfAbsent(location.to, () => {});
      routes[location.from]![location.to] = location.distance;
      routes[location.to]![location.from] = location.distance;
    });
  }
}

const int maxInteger =  0x7FFFFFFFFFFFFFFF;
// Held-Karp Algorithm
Iterable<int> travelingSantaProblem(Map<String, Map<String, int>> ways) {
  final cities = ways.keys.toList(); // All nodes name in the graph
  final allPaths = generatePermutations(cities);
  return allPaths
  .map((path) => path.indexed.toList())
  .map((path) {
    return path
      .where((city) => city.$1 < path.length -1 )
      .map((city) => ways[city.$2]![path[city.$1 + 1]]!)
      .reduce(plus);
  });
}

List<List<T>> generatePermutations<T>(List<T> items) {
  if (items.length <= 1) return [items];

  return items.map((item) {
    List<T> remaining = List.from(items)..remove(item);
     return generatePermutations(remaining).map((perm) => [item, ...perm]);
  }).expand((i) => i).toList();
}
