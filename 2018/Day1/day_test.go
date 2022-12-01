package day1

import (
	"testing"

	"delni.me/2018/utils"
	"github.com/stretchr/testify/assert"
)


func TestFrequencyDrift(t *testing.T) {
	frequency_sequence_drifts := []int{-1, 1}
	assert.Equal(t, 0, ComputeFrequency(frequency_sequence_drifts))
}

func TestFrequencyDriftWithExemple(t *testing.T) {
	sample1 := []int{+1, +1, +1}
	expect1 := 3

	sample2 := []int{+1, +1, -2}
	expect2 := 0

	sample3 := []int{-1, -2, -3}
	expect3 := -6

	assert.Equal(t, expect1, ComputeFrequency(sample1))
	assert.Equal(t, expect2, ComputeFrequency(sample2))
	assert.Equal(t, expect3, ComputeFrequency(sample3))
}

func TestDay1Part1(t *testing.T) {
	input := utils.ReadInputAsInt("./input.txt")
	assert.Equal(t, 520, ComputeFrequency(input))
}

func TestFrequencyRecordWithTwoChanges(t *testing.T) {
	frequency_sequence_drift := []int{-1, 1}
	assert.Equal(t, 0, FrequencyReachedTwice(frequency_sequence_drift))

}

func TestFrequencyRecordWithFiveChanges(t *testing.T) {
	frequency_sequence_drift := []int{+3, +3, +4, -2, -4}
	assert.Equal(t, 10, FrequencyReachedTwice(frequency_sequence_drift))
}

func TestDay1Part2(t *testing.T) {
	input := utils.ReadInputAsInt("./input.txt")
	assert.Equal(t, 394, FrequencyReachedTwice(input))
}