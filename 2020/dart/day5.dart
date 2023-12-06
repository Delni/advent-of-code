import 'utils.dart';

void main() async {
  AdventOutput(
    day: 5,
    part1: day5_1,
    part2: day5_2,
  );
}

int day5_1(List<String> entries) {
  return (entries
          .map((b) => Place.fromBoardingPass(b))
          .map((place) => place.ID)
          .toList()
            ..sort())
      .last;
}

int day5_2(List<String> entries) {
  var places = entries
      .map((b) => Place.fromBoardingPass(b))
      .map((place) => place.ID)
      .toList();
  places.sort();
  for (var i = 0; i < places.length; i++) {
    if (i > 0 && places[i] == places[i - 1] + 2) {
      return places[i] - 1;
    }
  }
  return places.last;
}

class Place {
  static final int rows = 127;
  static final int cols = 007;
  int row;
  int col;

  Place({required this.row, required this.col});

  @override
  String toString() => "Place $ID ($row - $col)";

  factory Place.fromBoardingPass(String boardingPass) {
    var minRow = 0, minCol = 0;
    var maxRow = rows, maxCol = cols;
    boardingPass.split('').forEach((element) {
      switch (element) {
        case 'F':
          maxRow = ((maxRow + minRow) * 0.5).floor();
          break;
        case 'B':
          minRow += ((maxRow - minRow) * 0.5).round();
          break;
        case 'L':
          maxCol = ((maxCol + minCol) * 0.5).floor();
          break;
        case 'R':
          minCol += ((maxCol - minCol) * 0.5).round();
          break;
        default:
          break;
      }
    });
    return Place(row: maxRow, col: maxCol);
  }

  int get ID => 8 * row + col;
}
