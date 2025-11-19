import '../../tools/dart/utils.dart';

void main() async {
  AdventOutput(
    day: 6, 
    part1: (entries) => entries.map((e) => e.split('\n')).map(anyoneSaidYes).reduce(sum),
    part2: (entries) => entries.map((e) => e.split('\n')).map(everyoneSaidYes).reduce(sum),
    pipe: (input) => input.join('\n').split('\n\n')
  );
}

int sum(int a, int b) => a + b;

int anyoneSaidYes(List<String> answers) {
  var answerSet = new Set.from(answers.first.split(''));
  answers.forEach((element) {
    answerSet.addAll(element.split(''));
  });
  return answerSet.length;
}

int everyoneSaidYes(List<String> answers) => 'abcdefghijklmnopqrstuvwxyz'
    .split('')
    .where((answer) => answers.every((element) => element.contains(answer)))
    .length;
