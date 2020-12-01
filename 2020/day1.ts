
import { getInputFrom } from './utils';

const isNotIndex = (index: number) => (value: unknown, i: number) => i !== index
const isDefined = (ok?: unknown) => !!ok
const sum = (total: number, value: number) => total + value
const times = (total: number, value: number) => total * value


export const findFirstCoupleThatSumsTo = (num: number): (entries: number[]) => number[] => {
	return entries => {
		const result = entries.map((value: number, index: number, array: number[]) => {
			const second = array.filter(isNotIndex(index)).find((second) => second + value === num)
			if(second !== undefined) {
				return [value, second]
			}
		}).filter(value => value?.every(isDefined))[0]
	if(result?.every(isDefined)) {
		return result
	}
	throw 'No matching pair in entries'
}
}


export const day1_1 = (): number => {
	const entries = getInputFrom('day1').map((it: string) => parseInt(it))
	const sumTo2020 = findFirstCoupleThatSumsTo(2020)
	return (sumTo2020(entries) as number[]).reduce(times, 1)
}

console.log(`Day 1.1 results is : \t` , day1_1())