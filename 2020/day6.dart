import 'utils.dart';

void main() async {
  final input = (await getInputForDay(6)).toList().join('\n').split('\n\n');
  print('--------- DAY 06 ---------');
  printResult('1', day6_1(input));
  printResult('2', day6_2(input));
}

int day6_1(List<String> entries) =>
    entries.map((e) => e.split('\n')).map(anyoneSaidYes).reduce(sum);

int day6_2(List<String> entries) =>
    entries.map((e) => e.split('\n')).map(everyoneSaidYes).reduce(sum);

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
