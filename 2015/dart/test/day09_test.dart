import 'package:test/test.dart';

import '../day09.dart';

void main() {
  final input = [
    "London to Dublin = 464",
    "London to Belfast = 518",
    "Dublin to Belfast = 141",
  ];

  test("parse one instruction", () {
    final pair = LocationPair.from("London to Dublin = 464");
    expect(pair.from, "London");
    expect(pair.to, "Dublin");
    expect(pair.distance, 464);
    // final routes = SantaRoutes.from([
    //   "London to Dublin = 464",
    //   "London to Belfast = 518",
    //   "Dublin to Belfast = 141",
    // ]);
    // expect(routes.cities, ["London", "Dublin", "Belfast"]);
  });

  test("should parse to santa's routes", () {
    final routes = input.map(LocationPair.from).asRoutes();
    expect(routes.cities, ["London", "Dublin", "Belfast"]);
  });

  test("should find shortest route possible", () {
    final routes = input.map(LocationPair.from).asRoutes();
    expect(routes.shortest, 605);
  });
}
