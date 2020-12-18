import 'utils.dart';

void main() {
  AdventOutput(
    day: 17,
    part1: (entries) {
      List<Cube> grid = [];
      for (var i = 0; i < entries.length; i++) {
        final line = entries[i].split('');
        for (var j = 0; j < line.length; j++) {
          grid.add(Cube(
            i,
            j,
            0,
            state: line[i] == '#',
          ));
        }
      }

      for (var i = 0; i < 6; i++) {
        grid.forEach((cube) {
          final activeNeighbors = cube.neighbors
              .map((e) =>
                  grid.firstWhere(
                      (element) => element.isAtTheSameSpotThan(e)) ??
                  e)
              .where((element) => element.state)
              .length;
          cube.state = activeNeighbors == 4;
        });
      }
      return 1;
    },
    part2: (entries) => 1,
  );

  print(Cube(1, 1, 1));
}

class Cube {
  int x;
  int y;
  int z;
  bool state;

  Cube(this.x, this.y, this.z, {this.state = false});

  List<Cube> get neighbors => [
        Cube(x - 1, y - 1, z - 1),
        Cube(x, y - 1, z - 1),
        Cube(x + 1, y - 1, z - 1),
        Cube(x - 1, y, z - 1),
        Cube(x, y, z - 1),
        Cube(x + 1, y, z - 1),
        Cube(x - 1, y + 1, z - 1),
        Cube(x, y + 1, z - 1),
        Cube(x + 1, y + 1, z - 1),
        Cube(x - 1, y - 1, z),
        Cube(x, y - 1, z),
        Cube(x + 1, y - 1, z),
        Cube(x - 1, y, z),
        Cube(x, y, z),
        Cube(x + 1, y, z),
        Cube(x - 1, y + 1, z),
        Cube(x, y + 1, z),
        Cube(x + 1, y + 1, z),
        Cube(x - 1, y - 1, z + 1),
        Cube(x, y - 1, z + 1),
        Cube(x + 1, y - 1, z + 1),
        Cube(x - 1, y, z + 1),
        Cube(x, y, z + 1),
        Cube(x + 1, y, z + 1),
        Cube(x - 1, y + 1, z + 1),
        Cube(x, y + 1, z + 1),
        Cube(x + 1, y + 1, z + 1),
      ];

  bool isAtTheSameSpotThan(Cube other) =>
      other.x == x && other.y == y && other.z == z;

  @override
  String toString() {
    return "Cube {$x,$y,$z} - $state";
  }
}
