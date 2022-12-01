package day3

import (
	"log"
	"regexp"
	"strconv"
)

type CoordMap = map[Coord][]string
type Offset struct {
	x int
	y int
}

type Claim struct {
	id     string
	offset Offset
	width  int
	height int
}

type Coord struct {
	x int
	y int
}

func ParseClaim(claim string) Claim {
	claimParseRegex, err := regexp.Compile(`#(?P<id>\d+) @ (?P<offset_x>\d+),(?P<offset_y>\d+): (?P<width>\d+)x(?P<height>\d+)`)
	if err != nil {
		log.Fatal("Error compiling: ", err)
	}
	match := claimParseRegex.FindStringSubmatch(claim)
	if match == nil {
		log.Fatal("no match:", claim)
	}
	result := Claim{}
	for i, name := range claimParseRegex.SubexpNames() {
		if i != 0 && name != "" {
			if name == "id" {
				result.id = match[i]
			}
			if name == "offset_x" {
				result.offset.x, err = strconv.Atoi(match[i])
			}
			if name == "offset_y" {
				result.offset.y, err = strconv.Atoi(match[i])
			}
			if name == "width" {
				result.width, err = strconv.Atoi(match[i])
			}
			if name == "height" {
				result.height, err = strconv.Atoi(match[i])
			}
			if err != nil {
				log.Fatal("Error compiling: ", err)
			}
		}
	}
	return result
}

func BuildClaimRect(claim Claim) []Coord {
	rect := []Coord{}
	for j := 0; j < claim.height; j++ {
		for i := 0; i < claim.width; i++ {
			rect = append(rect, Coord{x: claim.offset.x + i, y: claim.offset.y + j})
		}
	}
	return rect
}

func BuildClaimRectInMap(claim Claim, bigmap CoordMap) (CoordMap, int) {
	overlapCount := 0
	result_map := bigmap
	for j := 0; j < claim.height; j++ {
		for i := 0; i < claim.width; i++ {
			coord_key := Coord{x: claim.offset.x + i, y: claim.offset.y + j}
			if len(result_map[coord_key]) == 1 {
				overlapCount += 1
			}
			result_map[coord_key] = append(result_map[coord_key], claim.id)
		}
	}
	return result_map, overlapCount
}

func BuildStringClaimRectInMap(claim_as_string string, bigmap CoordMap) (CoordMap, int) {
	claim := ParseClaim(claim_as_string)
	return BuildClaimRectInMap(claim, bigmap)
}

func CountCommonInches(text_claim_list []string) int {
	totalOverlapCount := 0
	bigmap := make(CoordMap)
	for _, text_claim := range text_claim_list {
		filledmap, overlapCount := BuildStringClaimRectInMap(text_claim, bigmap)
		totalOverlapCount += overlapCount
		bigmap = filledmap
	}
	return totalOverlapCount

}

func NotInSecondList(l1 []string, l2 []string) string {
	found := false
	for _, x1 := range l1 {
		for _, x2 := range l2 {
			if x1 == x2 {
				found = true
				break
			}
		}
		if !found {
			return x1
		}
		found = false
	}
	return "-1"

}

func FindUniqueNotOverlapping(text_claim_list []string) string {
	bigmap := make(CoordMap)
	for _, text_claim := range text_claim_list {
		filledmap, _ := BuildStringClaimRectInMap(text_claim, bigmap)
		bigmap = filledmap
	}
	non_intersecting_claims := []string{}
	intersecting_claims := []string{}
	for _, claims := range bigmap {
		if len(claims) == 1 {
			non_intersecting_claims = append(non_intersecting_claims, claims[0])
		} else {
			intersecting_claims = append(intersecting_claims, claims...)
		}
	}
	unique_claim := NotInSecondList(non_intersecting_claims, intersecting_claims)
	return unique_claim
}
