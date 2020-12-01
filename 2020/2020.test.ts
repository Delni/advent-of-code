import { day1_1, day1_2, findFirstCoupleThatSumsTo } from './day1'

describe('Advent of Code 2020', () => {
	describe('Day 1', () => {
		describe('findEntriesThatSumsTo', () => {
			it('should return two numbers', () => {
				const testCase = findFirstCoupleThatSumsTo(10)
				const given = [1,2,3,4,5,6,7,8,9]
				expect(testCase(given)).toEqual([1, 9])
			})

			it('should find unordered', () => {
				const testCase = findFirstCoupleThatSumsTo(10)
				const given = [12,3,4,5,6,7,8,9]
				expect(testCase(given)).toEqual([3, 7])
			})
		})
		it('Part 1 should be equal to 381699', () => {
			expect(day1_1()).toBe(381699)
		})
		it('Part 2 should be equal to 111605670', () => {
			expect(day1_2()).toBe(111605670)
		})
	})
})