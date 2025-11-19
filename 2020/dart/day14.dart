import '../../tools/dart/utils.dart';

main() {
  AdventOutput(
    day: 14,
    part1: v1Decoder,
    part2: v2Decoder,
  );
}

int v1Decoder(List<String> entries) {
  List<String> mask = [];
  Map<String, int> memory = {};
  entries.forEach((entry) {
    if (entry.startsWith('mask')) {
      mask = entry.split('= ')[1].split('');
    } else {
      final address =
          entry.substring(entry.indexOf('[') + 1, entry.indexOf(']'));
      var binary = stringToUnsignedInt(entry.split('= ')[1]);

      for (var i = 0; i < binary.length; i++) {
        binary[i] = mask[i] == 'X' ? binary[i] : mask[i];
      }
      final maskedValue = int.parse(binary.join(''), radix: 2);
      memory.update(
        address,
        (_) => maskedValue,
        ifAbsent: () => maskedValue,
      );
    }
  });
  return memory.values.fold<int>(
    0,
    (previousValue, element) => previousValue + element,
  );
}

int v2Decoder(List<String>  entries) {
  List<String> mask = [];
  Map<String, int> memory = {};
  entries.forEach((entry) {
    if (entry.startsWith('mask')) {
      mask = entry.split('= ')[1].split('');
    } else {
      List<String> address = stringToUnsignedInt(entry.substring(
        entry.indexOf('[') + 1,
        entry.indexOf(']'),
      ));
      
      var value = int.parse(entry.split('= ')[1]);

      for (var i = 0; i < address.length; i++) {
        address[i] = mask[i] == '0' ? address[i] : mask[i];
      }

      addressesFromFloatingAddress(address).forEach((possibleAddress) { 
        final maskedAddress = int.parse(possibleAddress.join(''), radix: 2);
        memory.update(
          maskedAddress.toString(),
          (_) => value,
          ifAbsent: () => value,
        );
      });

    }
  });
  return memory.values.fold<int>(
    0,
    (previousValue, element) => previousValue + element,
  );
}

List<String> stringToUnsignedInt(String value) =>
    int.parse(value).toRadixString(2).padLeft(36, '0').split('');

List<List<String>> addressesFromFloatingAddress(List<String> floatingAddress) {
  if (floatingAddress.contains('X')) {
    final indexOfX = floatingAddress.indexOf('X');
    List<String> withZero = List.from(floatingAddress)
      ..replaceRange(indexOfX, indexOfX + 1, ['0']);
    List<String> withOne = List.from(floatingAddress)
      ..replaceRange(indexOfX, indexOfX + 1, ['1']);
    return []
      ..addAll(addressesFromFloatingAddress(withZero))
      ..addAll(addressesFromFloatingAddress(withOne));
  } else {
    return [floatingAddress];
  }
}
