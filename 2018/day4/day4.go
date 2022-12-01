package day4

import (
	"fmt"
	"log"
	"regexp"
	"sort"
	"strconv"
	"strings"
	"time"
)

type Shift struct {
	guardId int
	date    time.Time
	awake   []bool
}
type ShiftList []Shift

type occurenceMinute struct {
	count  int
	minute int
}

func (shiftList ShiftList) byGuards() map[int]ShiftList {
	shiftsByGuards := map[int]ShiftList{}
	for _, shift := range shiftList {
		if shiftsByGuards[shift.guardId] == nil {
			shiftsByGuards[shift.guardId] = make(ShiftList, 0)
		}
		shiftsByGuards[shift.guardId] = append(shiftsByGuards[shift.guardId], shift)
	}
	return shiftsByGuards
}

func sortRecordsAsStrings(input []string) []string {
	input_copy := make([]string, len(input))
	copy(input_copy, input)
	sort.Strings(input_copy)
	return input_copy
}

func parseGuardRecordBeginShift(input string, shift *Shift) {
	guardBeginShiftRegex, err := regexp.Compile(`\[(?P<date>\d+-\d+-\d+\s\d+:\d+)\] Guard #(?P<id>\d+) begins shift`)
	if err != nil {
		log.Fatal("Error compiling: ", err)
	}
	match := guardBeginShiftRegex.FindStringSubmatch(input)
	if match == nil {
		log.Fatal("no match:", input)
	}

	for i, name := range guardBeginShiftRegex.SubexpNames() {
		if name != "" {
			if name == "id" {
				(*shift).guardId, err = strconv.Atoi(match[i])
			}
			if name == "date" {
				var date time.Time
				date, err = time.Parse("2006-01-02 15:04", match[i])
				if date.Hour() == 23 {
					next_day := date.AddDate(0, 0, 1)
					(*shift).date = time.Date(next_day.Year(), next_day.Month(), next_day.Day(),
						0, 0, 0, 0, next_day.Location())
				} else {
					(*shift).date = date
				}

			}
			if err != nil {
				log.Fatal("Error compiling: ", err)
			}
		}
	}
}

func parseFallsAsleep(sleepLog string) int {
	guardAsleepRegex, err := regexp.Compile(`\[\d+-\d+-\d+\s\d+:(?P<minute>\d+)\] falls asleep`)
	match := guardAsleepRegex.FindStringSubmatch(sleepLog)
	minute := 0
	if match == nil {
		log.Fatal("no sleep match in ", sleepLog)
	}
	for i, name := range guardAsleepRegex.SubexpNames() {
		if name != "" {
			if name == "minute" {
				minute, _ = strconv.Atoi(match[i])
			}
			if err != nil {
				log.Fatal("Error compiling: ", err)
			}
		}
	}
	return minute
}

func parseWakesUp(awakeLog string) int {
	guardAwakeRegex, err := regexp.Compile(`\[\d+-\d+-\d+\s\d+:(?P<minute>\d+)\] wakes up`)
	match := guardAwakeRegex.FindStringSubmatch(awakeLog)
	minute := 0
	if match == nil {
		log.Fatal("no awake match in ", awakeLog)
	}
	for i, name := range guardAwakeRegex.SubexpNames() {
		if name != "" {
			if name == "minute" {
				minute, _ = strconv.Atoi(match[i])
			}
			if err != nil {
				log.Fatal("Error compiling: ", err)
			}
		}
	}
	return minute
}

func parseTwoLinesOfSleepAwake(sleep string, wake string, shift *Shift) {
	minute_sleep := parseFallsAsleep(sleep)
	minute_wake := parseWakesUp(wake)
	for i := minute_sleep; i < minute_wake; i++ {
		(*shift).awake[i] = false
	}
}

func makeShift() Shift {
	shift := Shift{
		awake: make([]bool, 60),
	}
	for i := 0; i < 60; i++ {
		shift.awake[i] = true
	}
	return shift
}

func makeShiftWithGuardId(guardId int) Shift {
	shift := Shift{
		awake: make([]bool, 60),
	}
	shift.guardId = guardId
	for i := 0; i < 60; i++ {
		shift.awake[i] = true
	}
	return shift
}

func makeShiftsForSortedRecords(inputs []string) ShiftList {
	shiftList := []Shift{}
	var current_shift Shift
	i := 0
	for i < len(inputs) {
		input := inputs[i]
		if strings.Contains(input, "Guard") {
			current_shift = makeShift()
			parseGuardRecordBeginShift(input, &current_shift)
			shiftList = append(shiftList, current_shift)
			i += 1
		} else {
			sleep, awake := string(inputs[i]), string(inputs[i+1])
			parseTwoLinesOfSleepAwake(sleep, awake, &current_shift)
			i += 2
		}
	}
	return shiftList
}

func makeShiftsForRecords(inputs []string) ShiftList {
	sorted_input := sortRecordsAsStrings(inputs)
	return makeShiftsForSortedRecords(sorted_input)
}

func countSleepMinutesOfGuard(shifts ShiftList) int {
	result := 0
	for _, shift := range shifts {
		for i := 0; i < len(shift.awake); i++ {
			if !shift.awake[i] {
				result++
			}
		}
	}
	return result
}

func findSleepiestGuard(shifts ShiftList) (int, ShiftList) {
	sleepiestGuardId := 0
	shiftsByGuards := shifts.byGuards()

	maxOfSleepMinutes := -1
	var mostAsleepGuardShifts ShiftList
	for guardId, shiftsOfGuard := range shiftsByGuards {
		minutesAsleep := countSleepMinutesOfGuard(shiftsOfGuard)
		if minutesAsleep > maxOfSleepMinutes {
			sleepiestGuardId = guardId
			maxOfSleepMinutes = minutesAsleep
			mostAsleepGuardShifts = shiftsOfGuard
		}
	}
	return sleepiestGuardId, mostAsleepGuardShifts
}

func mostAsleepMinuteForGuard(shifts ShiftList) (int, int) {
	sleepliestMinute := 0
	allMinutes := [60]int{}
	for _, shift := range shifts {
		for i := 0; i < 60; i++ {
			if !shift.awake[i] {
				allMinutes[i] += 1
			}
		}
	}
	max_occurrences := 0
	for minute, occurrences := range allMinutes {
		if occurrences > max_occurrences {
			max_occurrences = occurrences
			sleepliestMinute = minute
		}

	}
	return max_occurrences, sleepliestMinute
}

func mostAsleepMinuteForSleepiestGuard(shifts ShiftList) (int, int) {
	guardId, shiftsOfGuard := findSleepiestGuard(shifts)
	_, sleepiestMinute := mostAsleepMinuteForGuard(shiftsOfGuard)
	return guardId, sleepiestMinute
}

func guardMostFrequentlyAsleepOnTheSameMinute(shifts ShiftList) (int, int) {
	shiftsByGuards := shifts.byGuards()
	mostFrequentlyAsleepMinuteByGuards := map[int]occurenceMinute{}
	for k, v := range shiftsByGuards {
		occ, min := mostAsleepMinuteForGuard(v)
		mostFrequentlyAsleepMinuteByGuards[k] = occurenceMinute{count: occ, minute: min}
	}
	mostFrequentMinute := -1
	maxCount := 0
	guardId := -1
	for k, v := range mostFrequentlyAsleepMinuteByGuards {
		if v.count > maxCount {
			maxCount = v.count
			mostFrequentMinute = v.minute
			guardId = k
		}
	}
	return guardId, mostFrequentMinute
}

func Part1(input []string) int {
	shifts_for_guard := makeShiftsForRecords(input)
	guardId, sleepiestMinute := mostAsleepMinuteForSleepiestGuard(shifts_for_guard)
	return guardId * sleepiestMinute
}

func Part2(input []string) string {
	shifts_for_guard := makeShiftsForRecords(input)
	guardId, sleepiestMinute := guardMostFrequentlyAsleepOnTheSameMinute(shifts_for_guard)
	return fmt.Sprintf("%d", guardId * sleepiestMinute)
}