const { readFileSync } = require('fs')
const path = require('path')

/**
 * @param {string} root path to find input. Most likely `__dirname`
 * @param {string} splitter
 * @returns {string[]}
 */
module.exports.readInput = (root, splitter = '\n') =>
  readFileSync(path.join(root, 'input.txt'), {
    encoding: 'utf-8',
    flag: 'r',
  }).split(splitter)

/**
 * @param {number} a
 * @param {number} b
 * @returns {number}
 */
module.exports.sum = (a, b) => a + b

/**
 * @param {any} a
 * @param {any} b
 * @returns {number}
 * @example
 * array.sort(reversed)
 */
module.exports.reversed = (a, b) => b - a
