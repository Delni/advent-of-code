package main

import (
	"delni.me/2018/day1"
	"delni.me/2018/utils"
)

func main() {
	utils.AoCRunner(
		"01", 
		utils.ReadInputAsInt("./Day1/input.txt"), 
		day1.ComputeFrequency, 
		day1.FrequencyReachedTwice,
	)
}