package day2

import (
	"fmt"
	"testing"

	"delni.me/2018/utils"
	"github.com/stretchr/testify/assert"
)

func TestNumberOfLettersWithTwo(t *testing.T) {
	box_id := "abbcde"
	has_2_letters, has_3_letters := Have2Or3Letters(box_id)
	assert.Equal(t, true, has_2_letters)
	assert.Equal(t, false, has_3_letters)
}

func TestNumberOfLettersWithThree(t *testing.T) {
	box_id := "abbbcde"
	has_2_letters, has_3_letters := Have2Or3Letters(box_id)
	assert.Equal(t, false, has_2_letters)
	assert.Equal(t, true, has_3_letters)
}

func Test(t *testing.T) {
	box_ids := []string{
		"abcdef",
		"bababc",
		"abbcde",
		"abcccd",
		"aabcdd",
		"abcdee",
		"ababab",
	}

	assert.Equal(t, 12, ComputeChecksum(box_ids))
}

func TestDay2Part1(t *testing.T) {
	input := utils.ReadInputAsString("./input.txt")
	assert.Equal(t, 7134, ComputeChecksum(input))
}

var boxids = []string{
	"abcde",
	"fghij",
	"klmno",
	"pqrst",
	"fguij",
	"axcye",
	"wvxyz",
}

func TestCountMany(t *testing.T) {
	assert.Equal(t, 1, CountMany(boxids, "axcye"))
	assert.Equal(t, 0, CountMany(boxids, "axdcy"))
}

func TestFind2Occurences(t *testing.T) {
	boxids_with_two := append(boxids, []string{"axcye", "another"}...)
	fmt.Println(boxids_with_two)
	assert.Equal(t, "axcye", Find2Occurences(boxids_with_two))
}

func TestTruncatedBoxids(t *testing.T) {
	truncatedAtIndex2 := []string{
		"abde",
		"fgij",
		"klno",
		"pqst",
		"fgij",
		"axye",
		"wvyz",
	}
	assert.Equal(t, truncatedAtIndex2, Truncate(boxids, 2))
}

func TestFindLettersOfTwoCommonBoxes(t *testing.T) {
	assert.Equal(t, "fgij", FindLettersOfTwoCommonBoxes(boxids))
}

func TestFindLettersOfTwoCommonBoxesFromInputPart2(t *testing.T) {
	input := utils.ReadInputAsString("./input.txt")
	assert.Equal(t, "kbqwtcvzhmhpoelrnaxydifyb", FindLettersOfTwoCommonBoxes(input))
}
