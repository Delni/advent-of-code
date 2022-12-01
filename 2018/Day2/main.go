package day2

import (
	"golang.org/x/exp/maps"
	"golang.org/x/exp/slices"
)

func Have2Or3Letters(boxid string) (bool, bool) {
	two_letters := false
	three_letters := false
	letter_counter := map[rune]int{}
	for _, char := range boxid {
		letter_counter[char] += 1
	}
	two_letters = slices.Contains(maps.Values(letter_counter), 2)
	three_letters = slices.Contains(maps.Values(letter_counter), 3)
	return two_letters, three_letters
}

func ComputeChecksum(boxids []string) int {
	//two_and_threes := [](bool, bool)
	count_of_two := 0
	count_of_three := 0
	for _, boxid := range boxids {
		has_two, has_three := Have2Or3Letters(boxid)
		if has_two {
			count_of_two += 1
		}
		if has_three {
			count_of_three += 1
		}
	}
	return count_of_two * count_of_three
}

func CountMany(boxids []string, maybe_boxid string) int {
	count := 0
	for _, boxid := range boxids {
		if maybe_boxid == boxid {
			count += 1
		}
	}
	return count
}

func Find2Occurences(boxids []string) string {
	for _, boxid := range boxids {
		if CountMany(boxids, boxid) == 2 {
			return boxid
		}
	}
	return ""
}

func Truncate(boxids []string, atPos int) []string {
	truncated := []string{}
	for _, boxid := range boxids {
		truncated = append(truncated, boxid[:atPos]+boxid[atPos+1:])
	}
	return truncated
}

func FindLettersOfTwoCommonBoxes(boxids []string) string {
	for i := 0; i < len(boxids[0]); i++ { // assume all ID have the same length
		truncated_boxids := Truncate(boxids, i)
		occ := Find2Occurences(truncated_boxids)
		if len(occ) > 0 {
			return occ
		}
	}
	return ""
}
