import 'utils.dart';

void main() async {
  AdventOutput(
    day: 7,
    part1: (entries) {
      // Map<String, Bag> visited;
      List<Bag> bags = entries.map((e) => Bag.fromNLP(e)).toList();
      return getContainers('shiny gold', bags).length;
    },
    part2: (entries) {
      Map<String, Bag> bags = Map.fromIterable(entries.map((e) => Bag.fromNLP(e)), 
          key: (element) => (element as Bag).color, 
          value: (element) => element
      );
      return getNumberOfBags(bags, 'shiny gold');
    },
  );
}

Set<Bag> getContainers(String color, List<Bag> bags) {
  Set<Bag> parents = bags
      .where((bag) => bag.contains.any((element) => element.color == color))
      .toSet();
  Set<Bag> childrenOfParents =
      parents.expand((element) => getContainers(element.color, bags)).toSet();
  parents.addAll(childrenOfParents);
  return parents;
}

int getNumberOfBags(Map<String, Bag> bags, String color) {
  var numberOfChildren = bags[color].contains.fold(0, (total, element) => total + element.amount);
  return  numberOfChildren + bags[color].contains
    .fold(0, (total, element) => total + getNumberOfBags(bags, element.color) * element.amount);
}

class Bag {
  String color;
  List<Contain> contains;

  Bag();

  factory Bag.fromNLP(String nlp) {
    RegExp bagRegex = RegExp(r'^([\w+\s*]+) bags contain');
    RegExp contains = RegExp(r'((\d) ([\w+\s*]+) bag)+');
    return Bag()
      ..color = bagRegex.allMatches(nlp).first.group(1)
      ..contains = contains
          .allMatches(nlp)
          .map((match) =>
              Contain(color: match.group(3), amount: int.parse(match.group(2))))
          .toList();
  }
}

class Contain {
  String color;
  int amount;
  Contain({this.color, this.amount});
}
