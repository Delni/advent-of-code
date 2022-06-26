package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"strconv"
	"strings"
)


func main() {
	content, err := ioutil.ReadFile("inputs/day1.txt")

     if err != nil {
          log.Fatal(err)
     }

	 rawInput := strings.Split(string(content), "\n")

	 var input []int
	 for _,line := range rawInput {
		number, err := strconv.Atoi(line)
		if err != nil {
			log.Fatal(err)
	   }
	   input = append(input, number)
	 }

	 fmt.Println("------ DAY 01 ------")
	 fmt.Printf("Part One: %10v\n", FrequencyAnalyzer(input))
	 fmt.Printf("Part One: %10v\n", FrequencyFinder(input))
	 fmt.Println("--------------------")
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