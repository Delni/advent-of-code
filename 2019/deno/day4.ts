import { prettyPrint } from './utils.ts';


const input = '125730-579381';

const part1 = (entry: string): number => {
	const [min, max] = entry.split('-').map(it => parseInt(it));
	let valid = 0;
	for (let index = min; index < max; index++) {
		const element = index.toString();
		if (element.length !== new Set(element.split('')).size && element === element.split('').sort().join('')) {
			valid++
		}

	}

	return valid;
};

const part2 = (entry: string): number => {
	const [min, max] = entry.split('-').map(it => parseInt(it));
	let valid = 0;
	for (let index = min; index < max; index++) {
		const element = index.toString().split('');
		const uniqNumbers = new Set(element)
		const hasDuplicates = element.length !== uniqNumbers.size
		const isIncreasing = element.join('') === element.sort().join('')
		const hasDuplicatesNotRepeatedMoreThanTwice = [...uniqNumbers].some(number => element.filter(it => it === number).length === 2)
		if (hasDuplicates && isIncreasing && hasDuplicatesNotRepeatedMoreThanTwice) {
			valid++
		}

	}

	return valid;
};


prettyPrint('04', [{
	prefix: 'Part 1',
	result: part1(input)
}, {
	prefix: 'Part 2',
	result: part2(input)
}])