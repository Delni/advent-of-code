import 'utils.dart';

void main() async {
  final input = (await getInputForDay(4)).toList().join('\n').split('\n\n');
  print('--------- DAY 04 ---------');
  printResult('1', day4_1(input));
  printResult('2', day4_2(input));
}

int day4_1(List<String> entries) => entries
    .map((entry) => Passport.fromString(entry))
    .where((passport) => passport.requiredFieldsArePresent())
    .length;

int day4_2(List<String> entries) => entries
    .map((entry) => Passport.fromString(entry))
    .where((passport) => passport.isValid())
    .length;

class Passport {
  Map<String, String> fields;

  Passport(this.fields);

  bool requiredFieldsArePresent() {
    return FIELDS
        .where((element) => element != 'cid')
        .every((key) => fields.containsKey(key));
  }

  bool isValid() {
    return requiredFieldsArePresent() &&
        fields.entries.every((field) {
          Fields fieldKey = fieldFormString(field.key);
          switch (fieldKey) {
            case Fields.byr:
              try {
                final year = int.parse(field.value);
                return year >= 1920 && year <= 2002;
              } catch (e) {
                return false;
              }
              break;
            case Fields.iyr:
              try {
                final year = int.parse(field.value);
                return year >= 2010 && year <= 2020;
              } catch (e) {
                return false;
              }
              break;
            case Fields.eyr:
              try {
                final year = int.parse(field.value);
                return year >= 2020 && year <= 2030;
              } catch (e) {
                return false;
              }
              break;
            case Fields.hgt:
              final matches =
                  RegExp(r'(\d{1,3})(\w{2})').firstMatch(field.value);
              if (matches == null) return false;
              final size = int.parse(matches.group(1));
              final unit = matches.group(2);
              return unit == 'cm'
                  ? (size >= 150 && size <= 193)
                  : (size >= 59 && size <= 76);
            case Fields.hcl:
              return RegExp(r'#(\d|[a-f]){6}').hasMatch(field.value);
            case Fields.ecl:
              return ['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth']
                  .contains(field.value);
            case Fields.pid:
              return RegExp(r'\d{9}').hasMatch(field.value);
            case Fields.cid:
              return true;
            default:
              return false;
          }
        });
  }

  factory Passport.fromString(String data) =>
      Passport.fromList(data.split(RegExp('\n| ', multiLine: true)));

  factory Passport.fromList(List<String> rawFields) {
    return Passport(rawFields
        .map((it) => it.split(':'))
        .map((e) => MapEntry(e[0], e[1]))
        .fold({}, (previousValue, element) {
      previousValue.addEntries([element]);
      return previousValue;
    }));
  }
}

/**
 * 
    byr (Birth Year)
    iyr (Issue Year)
    eyr (Expiration Year)
    hgt (Height)
    hcl (Hair Color)
    ecl (Eye Color)
    pid (Passport ID)
    cid (Country ID)

 */
final List<String> FIELDS =
    Fields.values.map((e) => e.toString().split('.')[1]).toList();

enum Fields { byr, iyr, eyr, hgt, hcl, ecl, pid, cid }

Fields fieldFormString(String str) =>
    Fields.values.firstWhere((e) => e.toString() == 'Fields.' + str);
