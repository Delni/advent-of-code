package main

import "testing"

func TestHasCharacterTimesTrue(t *testing.T) {
	result := HasCharacterTimes("bababc", 2)
	if result != true {
		t.Errorf("Expected %v, but got %v", true, result)
	}
}

func TestHasCharacterTimesFalse(t *testing.T) {
	result := HasCharacterTimes("abcdef", 2)
	if result != false {
		t.Errorf("Expected %v, but got %v", false, result)
	}
}

func TestDay1Part1(t *testing.T) {
	// Given
	input := []string{
		"abcdef",
		"bababc",
		"abbcde",
		"abcccd",
		"aabcdd",
		"abcdee",
		"ababab"}
	// When
	result := Checksum(input)
	// Expect
	expected := 12

	if result != expected {
		t.Errorf("Expected %v, but got %v", expected, result)
	}
}

func TestDay1Part2(t *testing.T) {
	// Given
	input := []string{
		"abcde",
		"fghij",
		"klmno",
		"pqrst",
		"fguij",
		"axcye",
		"wvxyz",
	}
	// When
	result := CommonLetters(input)
	// Expect
	expected := "fgij"

	if result != expected {
		t.Errorf("Expected %v, but got %v", expected, result)
	}
}
