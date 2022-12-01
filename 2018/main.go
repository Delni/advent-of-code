package main

import (
	"delni.me/2018/day1"
	"delni.me/2018/day2"
	"delni.me/2018/utils"
)

func main() {
	utils.AoCRunner(
		"01", 
		utils.ReadInputAsInt("./Day1/input.txt"), 
		day1.ComputeFrequency, 
		day1.FrequencyReachedTwice,
	)
	utils.AoCRunner(
		"02", 
		utils.ReadInputAsString("./Day2/input.txt"), 
		day2.ComputeChecksum, 
		day2.FindLettersOfTwoCommonBoxes,
	)
}