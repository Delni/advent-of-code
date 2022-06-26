package main

import "testing"


func TestDay1Part1(t *testing.T) {
	// Given
	input := []int{+1, -2, +3, +1} 
	// When
	result := FrequencyAnalyzer(input)
	// Expect
	expected := 3

	if result != expected {
		t.Errorf("Expected %v, but got %v", expected, result)
	}
}

func TestDay1Part2(t *testing.T) {
	// Given
	input := []int{+1, -2, +3, +1} 
	// When
	result := FrequencyFinder(input)
	// Expect
	expected := 2

	if result != expected {
		t.Errorf("Expected %v, but got %v", expected, result)
	}
}