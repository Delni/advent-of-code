import 'utils.dart';

main() {
  AdventOutput(
    day: 12,
    part1: (entries) => Boat().toPosition(entries).distanceToOrigin,
    part2: (entries) => Boat().toWaypoint(entries).distanceToOrigin,
  );
}

class Boat {
  final _cardinals = ['E', 'N', 'W', 'S'];
  String facing = 'E';
  Point position = Point(0, 0);
  Point waypoint = Point(-1, 10);

  Boat();

  Boat toPosition(List<String> sequence) {
    sequence.forEach(moveTo);
    return this;
  }

  Boat toWaypoint(List<String> sequence) {
    sequence.forEach(moveToWaypoint);
    return this;
  }

  void moveTo(String move) {
    String direction = move[0];
    int amount = int.parse(move.substring(1));
    if (direction == 'F') {
      direction = facing;
    } else if (['R', 'L'].contains(direction)) {
      facing = _cardinals[
          (_cardinals.indexOf(facing) + (amount / 90).floor() * (direction == 'R' ? -1 : 1)) %
              _cardinals.length];
    }

    switch (direction) {
      case 'N':
        position.x -= amount;
        break;
      case 'E':
        position.y += amount;
        break;
      case 'S':
        position.x += amount;
        break;
      case 'W':
        position.y -= amount;
        break;
      default:
    }
  }

  void moveToWaypoint(String move) {
    String direction = move[0];
    int amount = int.parse(move.substring(1));
    if (direction == 'F') {
      position.x += (waypoint.x * amount);
      position.y += (waypoint.y * amount);
    } else if (['R', 'L'].contains(direction)) {
      for (var i = 1; i <= amount / 90; i++) {
        if (direction == 'R') {
          waypoint = Point(waypoint.y, -waypoint.x);
        } else {
          waypoint = Point(-waypoint.y, waypoint.x);
        }
      }
    }

    switch (direction) {
      case 'N':
        waypoint.x -= amount;
        break;
      case 'E':
        waypoint.y += amount;
        break;
      case 'S':
        waypoint.x += amount;
        break;
      case 'W':
        waypoint.y -= amount;
        break;
      default:
    }
  }

  int get distanceToOrigin => manhantanDistance(position, Point.ZERO);
}

class Point {
  int x;
  int y;
  Point(this.x, this.y);

  static final Point ZERO = Point(0, 0);
}

int manhantanDistance(Point p1, Point p2) => (p2.x - p1.x).abs() + (p2.y - p1.y).abs();
