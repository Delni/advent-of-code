import { getInputFrom, isNotIndex, sum } from './utils';

describe('utils', () => {
	describe('getInputFrom', () => {
		it('should read txt files', () => {
			expect(getInputFrom('test')).toBeDefined()
		})
	});

	describe('isNotIndex', () => {
		it('should return true of false', () => {
			const given = isNotIndex(1)
			expect(given(1, 1)).toBeFalsy()
			expect(given(1, 2)).toBeTruthy()
		})
	});

	describe('sum', () => {
		it('should return the sum', () => {
			expect(sum(2,3)).toBe(5)
		})
	})
})