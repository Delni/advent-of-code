package main

import (
	"delni.me/2018/day1"
	"delni.me/2018/day2"
	"delni.me/2018/day3"
	"delni.me/2018/day4"
	"delni.me/2018/utils"
)

func main() {
	utils.AoCRunner(
		"01", 
		utils.ReadInputAsInt("./day1/input.txt"), 
		day1.ComputeFrequency, 
		day1.FrequencyReachedTwice,
	)
	utils.AoCRunner(
		"02", 
		utils.ReadInputAsString("./day2/input.txt"), 
		day2.ComputeChecksum, 
		day2.FindLettersOfTwoCommonBoxes,
	)
	utils.AoCRunner(
		"03", 
		utils.ReadInputAsString("./day3/input.txt"), 
		day3.CountCommonInches, 
		day3.FindUniqueNotOverlapping,
	)
	utils.AoCRunner(
		"04", 
		utils.ReadInputAsString("./day4/input.txt"), 
		day4.Part1, 
		day4.Part2,
	)
	// utils.AoCRunner(
	// 	"05", 
	// 	utils.ReadInputAsString("./day5/input.txt"), 
	// 	day5.Part1, 
	// 	day4.Part2,
	// )
}