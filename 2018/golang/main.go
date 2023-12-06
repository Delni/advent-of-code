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
		utils.ReadInputAsInt("../resources/day01.txt"), 
		day1.ComputeFrequency, 
		day1.FrequencyReachedTwice,
	)
	utils.AoCRunner(
		"02", 
		utils.ReadInputAsString("../resources/day02.txt"), 
		day2.ComputeChecksum, 
		day2.FindLettersOfTwoCommonBoxes,
	)
	utils.AoCRunner(
		"03", 
		utils.ReadInputAsString("../resources/day03.txt"), 
		day3.CountCommonInches, 
		day3.FindUniqueNotOverlapping,
	)
	utils.AoCRunner(
		"04", 
		utils.ReadInputAsString("../resources/day04.txt"), 
		day4.Part1, 
		day4.Part2,
	)
	// utils.AoCRunner(
	// 	"05", 
	// 	utils.ReadInputAsString("../resources/day05.txt"), 
	// 	day5.Part1, 
	// 	day4.Part2,
	// )
}