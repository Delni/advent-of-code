package day5

import (
	"regexp"
)

func findReactingUnits(unit1 rune, unit2 rune) bool {
	return unit1-32 == unit2 || unit1+32 == unit2
}

func makeChainReaction(polymer string) int {
	result := ""
	for i := 0; i < len(polymer); i++ {
		if i+1 == len(polymer) || !findReactingUnits(rune(polymer[i]), rune(polymer[i+1])) {
			result += string(polymer[i])
		} else {
			i++
		}
	}

	if result == polymer {
		return len(polymer)
	} else {
		return makeChainReaction(result)
	}
}

func Part1(input []string) int {
	return makeChainReaction(input[0])
}

func removeUnitFromPolymer(polymer string, unit string) string {
	return regexp.MustCompile("(?i)"+unit).ReplaceAllString(polymer, "")
}

func findShortestPolymerLength(polymer string) int {
	result := len(polymer)
	for i := 0; i < 26; i++ {
		letter := string(rune('A' + i))
		reducedPolymer := removeUnitFromPolymer(polymer, letter)
		reactedPolymerLength := makeChainReaction(reducedPolymer)
		if reactedPolymerLength < result {
			result = reactedPolymerLength
		}
	}
	return result
}
