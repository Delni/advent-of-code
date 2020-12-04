import { getInputFrom, prettyPrint, sum, toInt } from './utils.ts';

const input = await getInputFrom('day1');

const part1 = (entries: string[]): number => entries
	.map(toInt)
	.map(fuelForMass)
	.reduce(sum, 0);

const part2 = (entries: string[]): number => entries
	.map(toInt)
	.map(realFuelForMass)
	.reduce(sum, 0);

const fuelForMass = (mass : number): number => Math.floor(mass/3) - 2;

const realFuelForMass = (mass : number): number => {
	const additionalFuel = fuelForMass(mass)
	if(additionalFuel < 0) {
		return 0;
	} 
	return additionalFuel + realFuelForMass(additionalFuel)
}

prettyPrint('01', [{
	prefix: 'Part 1',
	result: part1(input)
},{
	prefix: 'Part 2',
	result: part2(input)
}])