package main

import (
	"strings"

	"delni.me/2018/utils"
)

func main() {
	input := utils.ReadInputAsString("../Day2/input.txt")

	utils.AoCRunner("02", input, Checksum, Checksum)
}

func Checksum(input []string) int {
	var twice int
	var thrice int
	for _, word := range input {
		if HasCharacterTimes(word, 2) {
			twice++
		}
		if HasCharacterTimes(word, 3) {
			thrice++
		}
	}
	return twice * thrice
}

func HasCharacterTimes(word string, times int) bool {
	characters := strings.Split(word, "")
	for _, character := range characters {
		if len(strings.Split(word, character)) == times+1 {
			return true
		}
	}
	return false
}

func CommonLetters(input []string) string {
	for index, word := range input {
		for _, otherWord := range input[index:] {
			letters := append(strings.Split(word, ""), strings.Split(otherWord, "")...)
			// TODO : while twice, remove
			for i := 0; i < len(word); i++ {
				
			}
			// if len == 2 -> Gotcha ?
		}
	}
	return ""
}