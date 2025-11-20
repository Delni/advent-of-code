import '../../tools/dart/utils.dart';

void main() async {
  TypedOutput<String>(
      day: 5,
      part1: (entries) => entries.where(isANiceString).length,
      part2: (entries) => entries.where(isAReallyNiceString).length,
      );
}

bool isANiceString(String word) {
  return hasAtLeastOneDoubleLetter(word) && hasAtLeastThreeVowels(word) && hasNoNaughtyString(word);
}

bool isAReallyNiceString(String word) => hasPairRepeated(word) && hasLetterRepeatedWithOffset(word);

bool hasAtLeastThreeVowels(String word) {
  return word.split('').where('aeiou'.contains).length >= 3;
}

bool hasAtLeastOneDoubleLetter(String word) {
  return word.split('').any((letter) => RegExp("($letter){2,}").hasMatch(word));
}

bool hasNoNaughtyString(String word) {
  return !['ab', 'cd', 'pq', 'xy'].any(word.contains);
}

bool hasPairRepeated(String word) {
  return word.split('').indexed.map((letter) {
    if(letter.$1 == word.length - 1) {
      return null;
    }
    return letter.$2 + word[letter.$1 + 1];
  })
  .any((pair) => pair != null && word.split(pair).length >= 3);
}

bool hasLetterRepeatedWithOffset(String word) {
  return word.split('').any((letter) => RegExp("($letter\\w$letter){1}").hasMatch(word));
}