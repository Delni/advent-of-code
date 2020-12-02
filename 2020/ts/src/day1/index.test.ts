import { day1_1, day1_2, findFirstCoupleThatSumsTo, findFirstTrioThatSumsTo } from '.'

describe('Advent of Code 2020', () => {
	describe('Day 1', () => {
		describe('findFirstCoupleThatSumsTo', () => {
			it('should return two numbers', () => {
				const testCase = findFirstCoupleThatSumsTo(10)
				const given = [1,2,3,4,5,6,7,8,9]
				expect(testCase(given)).toHaveLength(2)
			})

			it('should find unordered', () => {
				const testCase = findFirstCoupleThatSumsTo(10)
				const given = [12,3,4,5,6,7,8,9]
				expect(testCase(given)).toEqual([3, 7])
			})
		})

		describe('findFirstTrioThatSumsTo', () => {
			it('should return three numbers', () => {
				const testCase = findFirstTrioThatSumsTo(10)
				const given = [1,2,3,4,5,6,7,8,9]
				expect(testCase(given)).toHaveLength(3)
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