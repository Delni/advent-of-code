import { IntCodeComputer } from "./shared.ts";
import { getInputFrom, prettyPrint, toInt } from './utils.ts';

const input = await getInputFrom('day2', ',');

const part1 = (entries: string[]): number => new IntCodeComputer(entries.map(toInt))
	.run(12, 2)
	.get(0);

const part2 = (entries: string[]): number => {
	const target = 19690720
	let toggle = true;
	const computer = new IntCodeComputer(entries.map(toInt))
	for (let noun = 0; noun < 99; noun++) {
		for (let verb = 0; verb < 99; verb++) {
			if(computer.reset().run(noun, verb).output === target) {
				return computer.instruction
			}
		}
	}
	return 0;
}

prettyPrint('02', [{
	prefix: 'Part 1',
	result: part1(input)
},{
	prefix: 'Part 2',
	result: part2(input)
}])