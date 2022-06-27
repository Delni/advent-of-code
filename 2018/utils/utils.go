package utils

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"time"
)

func AoCRunner[K int|string](day string, input []K, Part1 func([]K) int, Part2 func([]K) int) {
	now := time.Now()
	fmt.Printf("-------- DAY %s --------\n", day)
	fmt.Printf("Part One: %14v\n", Part1(input))
	fmt.Printf("Part Two: %14v\n", Part2(input))
	fmt.Printf("Ran in: %16v\n", time.Since(now))
	fmt.Println("------------------------")
}

func ReadInputAsInt(fname string) []int {
	file, err := os.Open(fname)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	var input []int

	scan := bufio.NewScanner(file)
	for scan.Scan() {
		i, err := strconv.Atoi(scan.Text())
		if err != nil {
			log.Fatal(err)
		}
		input = append(input, i)
	}

	return input
}

func ReadInputAsString(fname string) []string {
	file, err := os.Open(fname)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	var input []string

	scan := bufio.NewScanner(file)
	for scan.Scan() {
		i := scan.Text()
		input = append(input, i)
	}

	return input
}