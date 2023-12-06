package day5

import (
	"testing"

	"delni.me/2018/utils"
	"github.com/stretchr/testify/assert"
)

var input string = "dabAcCaCBAcCcaDA"
var expectedOutput string = "dabCBAcaDA"

func TestFindReactingUnits(t *testing.T) {
	// When
	result := findReactingUnits('a', 'A')
	// Expect
	assert.Equal(t, true, result)

	// When
	result = findReactingUnits('a', 'a')
	// Expect
	assert.Equal(t, false, result)

	// When
	result = findReactingUnits('A', 'a')
	// Expect
	assert.Equal(t, true, result)
}

func TestMakeChainReaction(t *testing.T) {
	result := makeChainReaction("aAb")
	assert.Equal(t, len("b"), result)
}

func TestMake2ChainReaction(t *testing.T) {
	result := makeChainReaction("aABb")
	assert.Equal(t, len(""), result)
}

func TestMakeFullChainReaction(t *testing.T) {
	result := makeChainReaction(input)
	assert.Equal(t, len(expectedOutput), result)
}

func TestDay5Part1(t *testing.T) {
	result := makeChainReaction(utils.ReadInputAsString("./input.txt")[0])
	assert.Equal(t, 9704, result)
}

func TestRemoveUnitFromPolymer(t *testing.T) {
	chain := "aAbB"
	assert.Equal(t, "bB", removeUnitFromPolymer(chain, "a"))
}

func TestFindShortestPolymerLength(t *testing.T) {
	chain := "dabAcCaCBAcCcaDA"
	shortestLength := findShortestPolymerLength(chain)
	assert.Equal(t, 4, shortestLength)
}

func TestDay5Part2(t *testing.T) {
	result := findShortestPolymerLength(utils.ReadInputAsString("./input.txt")[0])
	assert.Equal(t, 6942, result)
}
