import 'utils.dart';

main() {
  // print(WaitingArea(rows: sample, longview: true).occupiedSeats);
  AdventOutput(
    day: 11,
    part1: (entries) => WaitingArea(rows: entries, longview: false).occupiedSeats,
    part2: (entries) => WaitingArea(rows: entries, longview: true).occupiedSeats,
  );
}

class WaitingArea {
  List<String> rows;
  List<String> previousState = [];

  WaitingArea({this.rows, bool longview}) {
    if (longview) {
      runLongview();
    } else {
      run();
    }
  }

  int get occupiedSeats => rows.map((e) => e.split('').where(isOccupied).length).fold(0, sum);
  bool get isStable => previousState.join('') == rows.join('');

  run() {
    while (previousState.join('') != rows.join('')) {
      previousState = List.from(rows);
      for (var i = 0; i < previousState.length; i++) {
        final row = previousState[i].split('');
        for (var j = 0; j < row.length; j++) {
          final seat = row[j];
          final offset = 1;
          final surroundings = [
            Surrounding(dir: 'TL', coords: [i - offset, j - offset]),
            Surrounding(dir: 'T', coords: [i - offset, j]),
            Surrounding(dir: 'TR', coords: [i - offset, j + offset]),
            Surrounding(dir: 'L', coords: [i, j - offset]),
            Surrounding(dir: 'R', coords: [i, j + offset]),
            Surrounding(dir: 'BL', coords: [i + offset, j - offset]),
            Surrounding(dir: 'B', coords: [i + offset, j]),
            Surrounding(dir: 'BR', coords: [i + offset, j + offset]),
          ].where(firstInBounds(0, previousState.length)).where(lastInBounds(0, row.length));
          if (seat == 'L') {
            row[j] = surroundings.any(isOccupiedByCoord(previousState)) ? row[j] : '#';
          } else if (seat == '#') {
            row[j] =
                surroundings.where(isOccupiedByCoord(previousState)).length >= 4 ? 'L' : row[j];
          }
          rows[i] = row.join();
        }
      }
    }
  }

  runLongview() {
    while (previousState.join('') != rows.join('')) {
      previousState = List.from(rows);
      for (var i = 0; i < previousState.length; i++) {
        final row = previousState[i].split('');
        for (var j = 0; j < row.length; j++) {
          final seat = row[j];
          var surroundings;
          var offset = 1;
          Map<String, String> canSee = {};
          do {
            surroundings = [
              Surrounding(dir: 'TL', coords: [i - offset, j - offset]),
              Surrounding(dir: 'T', coords: [i - offset, j]),
              Surrounding(dir: 'TR', coords: [i - offset, j + offset]),
              Surrounding(dir: 'L', coords: [i, j - offset]),
              Surrounding(dir: 'R', coords: [i, j + offset]),
              Surrounding(dir: 'BL', coords: [i + offset, j - offset]),
              Surrounding(dir: 'B', coords: [i + offset, j]),
              Surrounding(dir: 'BR', coords: [i + offset, j + offset]),
            ].where(firstInBounds(0, previousState.length)).where(lastInBounds(0, row.length))
              ..forEach((e) {
                var local_seat = previousState[e.coords.first][e.coords.last];
                if (local_seat != '.') {
                  canSee.putIfAbsent(e.dir, () => previousState[e.coords.first][e.coords.last]);
                }
              });
            offset++;
          } while (surroundings.length > 0);

          if (seat == 'L') {
            row[j] = canSee.values.any(isOccupied) ? row[j] : '#';
          } else if (seat == '#') {
            row[j] = canSee.values.where(isOccupied).length >= 5 ? 'L' : row[j];
          }
          rows[i] = row.join();
        }
      }
    }
  }
}

class Surrounding {
  String dir;
  List<int> coords;
  Surrounding({this.dir, this.coords});
}

bool Function(Surrounding element) firstInBounds(int min, int max) =>
    (element) => element.coords.first >= min && element.coords.first < max;
bool Function(Surrounding element) lastInBounds(int min, int max) =>
    (element) => element.coords.last >= min && element.coords.last < max;
bool isOccupied(seat) => seat == '#';
bool Function(Surrounding element) isOccupiedByCoord(List<String> state) =>
    (element) => isOccupied(state[element.coords.first][element.coords.last]);
int sum(previousValue, element) => previousValue + element;
