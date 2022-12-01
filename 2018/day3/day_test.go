package day3

import (
	"testing"

	"delni.me/2018/utils"
	"github.com/stretchr/testify/assert"
)

func TestParseClaim(t *testing.T) {
	claim := "#123 @ 3,2: 5x4"
	result := ParseClaim(claim)
	expected := Claim{
		id: "123",
		offset: Offset{
			x: 3,
			y: 2,
		},
		width:  5,
		height: 4,
	}
	assert.Equal(t, result, expected)
}
func TestParseClaimBigInt(t *testing.T) {
	claim := "#123 @ 33,22: 51x42"
	result := ParseClaim(claim)
	expected := Claim{
		id: "123",
		offset: Offset{
			x: 33,
			y: 22,
		},
		width:  51,
		height: 42,
	}
	assert.Equal(t, result, expected)
}

func TestBuildClaimRect(t *testing.T) {
	text_claim := "#3 @ 5,5: 2x2"
	claim := ParseClaim(text_claim)
	expected := []Coord{
		{x: 5, y: 5},
		{x: 6, y: 5},
		{x: 5, y: 6},
		{x: 6, y: 6},
	}
	assert.Equal(t, expected, BuildClaimRect(claim))
}

func TestBuildClaimRectInEmptyMap(t *testing.T) {
	text_claim := "#3 @ 5,5: 2x2"
	claim := ParseClaim(text_claim)
	expected_bigmap := make(CoordMap)
	emptymap := make(CoordMap)
	coords := []Coord{
		{x: 5, y: 5},
		{x: 6, y: 5},
		{x: 5, y: 6},
		{x: 6, y: 6},
	}
	for _, coord := range coords {
		expected_bigmap[coord] = []string{"3"}
	}
	bigmap, overlapCount := BuildClaimRectInMap(claim, emptymap)
	assert.Equal(t, expected_bigmap, bigmap)
	assert.Equal(t, overlapCount, 0)
}

func TestBuildClaimRectInNonEmptyMap(t *testing.T) {
	text_claim := "#3 @ 5,5: 2x2"
	claim := ParseClaim(text_claim)
	expected_bigmap := make(CoordMap)
	initialmap := make(CoordMap)
	expected_bigmap[Coord{x: 5, y: 6}] = []string{"3"}
	expected_bigmap[Coord{x: 6, y: 6}] = []string{"3"}
	expected_bigmap[Coord{x: 5, y: 5}] = []string{"1", "3"}
	expected_bigmap[Coord{x: 6, y: 5}] = []string{"1", "3"}
	initialmap[Coord{x: 5, y: 5}] = []string{"1"}
	initialmap[Coord{x: 6, y: 5}] = []string{"1"}
	bigmap, overlapCount := BuildClaimRectInMap(claim, initialmap)
	assert.Equal(t, expected_bigmap, bigmap)
	assert.Equal(t, overlapCount, 2)
}

func TestCountCommonInches(t *testing.T) {
	text_claim_list := []string{
		"#1 @ 1,3: 4x4",
		"#2 @ 3,1: 4x4",
		"#3 @ 5,5: 2x2"}
	overlapCount := CountCommonInches(text_claim_list)
	assert.Equal(t, overlapCount, 4)
}

func TestCountCommonInchesOverlapTwice(t *testing.T) {
	text_claim_list := []string{
		"#1 @ 1,3: 4x4",
		"#2 @ 3,1: 4x4",
		"#3 @ 5,5: 2x2",
		"#3 @ 3,3: 1x1"}
	overlapCount := CountCommonInches(text_claim_list)
	assert.Equal(t, overlapCount, 4)
}

func TestCommonInchesFromInput(t *testing.T) {
	assert.Equal(t, 109143, CountCommonInches(utils.ReadInputAsString("./input.txt")))
}

func TestNotInSecondList(t *testing.T) {
	l1 := []string{"1", "2", "3"}
	l2 := []string{"1", "2", "6"}
	assert.Equal(t, "3", NotInSecondList(l1, l2))

}

func TestFindNoClaimRectNotOverlapping(t *testing.T) {
	text_claim_list := []string{
		"#1 @ 1,3: 4x4",
		"#2 @ 3,1: 4x4",
		"#3 @ 5,5: 2x2"}
	assert.Equal(t, "3", FindUniqueNotOverlapping(text_claim_list))
}

func TestFindNoClaimRectNotOverlappingFromInput(t *testing.T) {
	assert.Equal(t, "506", FindUniqueNotOverlapping(utils.ReadInputAsString("./input.txt")))
}
