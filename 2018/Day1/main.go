package main

import (
	"delni.me/2018/utils"
)

func main() {
	input := utils.ReadInputAsInt("Day1/input.txt")

	utils.AoCRunner("01", input, FrequencyAnalyzer, FrequencyFinder)
}

func FrequencyAnalyzer(changes []int) int {
	frequency := 0
	for _, delta := range changes {
		frequency += delta

	}
	return frequency
}

func FrequencyFinder(changes []int) int {
	var current_frenquency int
	var found_frequencies []int
	for again := true; again; again = current_frenquency != -1 {

		for _, delta := range changes {
			current_frenquency += delta
			if isElementIn(found_frequencies, current_frenquency) {
				return current_frenquency
			} else {
				found_frequencies = append(found_frequencies, current_frenquency)
			}
		}
	}
	return 0
}

func isElementIn(array []int, value int) bool {
	for _, v := range array {
		if v == value {
			return true
		}
	}
	return false
}