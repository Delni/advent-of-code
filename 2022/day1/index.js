const { sum, reversed, readInput } = require('../utils')

const input = readInput(__dirname)

/**
 * @typedef {number[]} Inventory
 */

/**
 *
 * @param {string[]} lines
 * @returns {Inventory[]}
 */
function toInventories(lines) {
  const inventories = [[]]
  for (const line of lines) {
    if (line === '') {
      inventories.push([])
    } else {
      const lastInventory = inventories[inventories.length - 1]
      lastInventory.push(parseInt(line))
    }
  }
  return inventories
}

/**
 * @param {Inventory[]} inventories
 * @returns {[]number}
 */
function toTotal(inventories) {
  return inventories.map((inventory) => inventory.reduce(sum, 0))
}

/**
 * @param {string[]} input
 * @returns {number}
 */
function mostCaloriesForElves(input) {
  const totalInventories = toTotal(toInventories(input))
  return totalInventories.sort(reversed)[0]
}

/**
 * @param {string[]} input
 * @returns {number}
 */
function findTop3Total(input) {
  return toTotal(toInventories(input)).sort(reversed).slice(0, 3).reduce(sum, 0)
}

/**
 * @returns {number}
 */
function runPart1() {
  return mostCaloriesForElves(input)
}

/**
 * @returns {number}
 */
function runPart2() {
  return findTop3Total(input)
}

module.exports = {
  mostCaloriesForElves,
  findTop3Total,
  toInventories,
  toTotal,
  runPart1,
  runPart2,
}