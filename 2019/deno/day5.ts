import { getInputFrom, prettyPrint } from './utils.ts';

const input = await getInputFrom('day05', ',');

const part1 = (input: string[]) => 1;
const part2 = (input: string[]) => 1;

prettyPrint('05', [{
  prefix: 'Part 1',
  result: part1(input)
}, {
  prefix: 'Part 2',
  result: part1(input)
}])
