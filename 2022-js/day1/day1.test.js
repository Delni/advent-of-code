const { expect } = require('chai')
const {
  toInventories,
  toTotal,
  mostCaloriesForElves,
  findTop3Total,
  runPart1,
  runPart2,
} = require('./index')

describe('Day 1', () => {
  const testInput = [
    '1000',
    '2000',
    '3000',
    '',
    '4000',
    '',
    '5000',
    '6000',
    '',
    '7000',
    '8000',
    '9000',
    '',
    '10000',
  ]
  it('should parse input', () => {
    const expected = toInventories(testInput)
    expect(expected).to.have.length(5)
  })

  it('should reduce to total', () => {
    const given = toInventories(testInput)
    const expected = toTotal(given)
    expect(expected).to.deep.equal([6000, 4000, 11000, 24000, 10000])
  })

  it('should find max from input', () => {
    const mostCalories = mostCaloriesForElves(testInput)
    expect(mostCalories).to.equal(24000)
  })

  it('should find top 3 total from input', () => {
    const expected = findTop3Total(testInput)
    expect(expected).to.equal(45000)
  })

  describe('With real input', () => {
    it('part 1 should be 72602', () => {
      expect(runPart1()).to.equal(72602)
    })

    it('part 2 should be 207410', () => {
      expect(runPart2()).to.equal(207410)
    })
  })
})
