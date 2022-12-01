package day4

import (
	"testing"
	"time"

	"delni.me/2018/utils"
	"github.com/stretchr/testify/assert"
)

var input = []string{"[1518-11-01 00:00] Guard #10 begins shift",
	"[1518-11-01 00:05] falls asleep",
	"[1518-11-01 23:58] Guard #99 begins shift",
	"[1518-11-01 00:30] falls asleep",
	"[1518-11-01 00:25] wakes up",
	"[1518-11-02 00:40] falls asleep",
	"[1518-11-02 00:50] wakes up",
	"[1518-11-01 00:55] wakes up"}

var input_for_frequent = []string{
	"[1518-11-01 00:00] Guard #10 begins shift",
	"[1518-11-01 00:05] falls asleep",
	"[1518-11-01 00:25] wakes up",
	"[1518-11-01 00:30] falls asleep",
	"[1518-11-01 00:55] wakes up",
	"[1518-11-04 00:00] Guard #10 begins shift",
	"[1518-11-04 00:40] falls asleep",
	"[1518-11-04 00:41] wakes up",
	"[1518-11-01 23:58] Guard #99 begins shift",
	"[1518-11-02 00:35] falls asleep",
	"[1518-11-02 00:39] wakes up",
	"[1518-11-06 00:51] Guard #99 begins shift",
	"[1518-11-06 00:52] falls asleep",
	"[1518-11-06 00:53] wakes up",
}

// 000000000011111111112222222222333333333344444444445555555555
// 012345678901234567890123456789012345678901234567890123456789
// .....####################.....#########################.....
// ........................................##########..........
var input2 = []string{"[1518-11-03 00:00] Guard #10 begins shift",
	"[1518-11-03 00:05] falls asleep",
	"[1518-11-03 23:58] Guard #10 begins shift",
	"[1518-11-03 00:30] falls asleep",
	"[1518-11-03 00:25] wakes up",
	"[1518-11-04 00:40] falls asleep",
	"[1518-11-04 00:50] wakes up",
	"[1518-11-03 00:55] wakes up"}

var sorted_input = []string{"[1518-11-01 00:00] Guard #10 begins shift",
	"[1518-11-01 00:05] falls asleep",
	"[1518-11-01 00:25] wakes up",
	"[1518-11-01 00:30] falls asleep",
	"[1518-11-01 00:55] wakes up",
	"[1518-11-01 23:58] Guard #99 begins shift",
	"[1518-11-02 00:40] falls asleep",
	"[1518-11-02 00:50] wakes up"}

func TestSortRecordList(t *testing.T) {
	expected_output := sorted_input
	assert.Equal(t, expected_output, sortRecordsAsStrings(input))
}

func TestMakeShiftsForSortedRecords(t *testing.T) {
	shift10 := makeShiftWithGuardId(10)
	for i := 5; i < 25; i++ {
		shift10.awake[i] = false
	}
	for i := 30; i < 55; i++ {
		shift10.awake[i] = false
	}
	shift10.date = time.Date(1518, time.Month(11), 1, 0, 0, 0, 0, time.UTC)
	shift99 := makeShiftWithGuardId(99)
	for i := 40; i < 50; i++ {
		shift99.awake[i] = false
	}
	shift99.date = time.Date(1518, time.Month(11), 2, 0, 0, 0, 0, time.UTC)

	result_shiftList := makeShiftsForSortedRecords(sorted_input)
	assert.Equal(t, result_shiftList[0], shift10)
	assert.Equal(t, result_shiftList[1], shift99)
}

func TestParseGuardRecordBeginShift(t *testing.T) {
	input := "[1518-11-01 00:00] Guard #10 begins shift"
	shift := makeShift()
	date, _ := time.Parse("2006-01-02 15:04", "1518-11-01 00:00")
	parseGuardRecordBeginShift(input, &shift)

	assert.Equal(t, 10, shift.guardId)
	assert.Equal(t, date, shift.date)
}

func TestParseGuardRecordBeginShiftIsRounded(t *testing.T) {
	input := "[1518-11-02 23:58] Guard #99 begins shift"
	shift := makeShift()
	date, _ := time.Parse("2006-01-02 15:04", "1518-11-03 00:00")
	parseGuardRecordBeginShift(input, &shift)

	assert.Equal(t, 99, shift.guardId)
	assert.Equal(t, date, shift.date)
}

func TestParseTwoLinesOfSleepAwake(t *testing.T) {
	sleep := "[1518-11-01 00:05] falls asleep"
	wake := "[1518-11-01 00:25] wakes up"
	shift := makeShift()
	expected := make([]bool, 60)
	for i := 0; i < 60; i++ {
		expected[i] = true
	}
	parseTwoLinesOfSleepAwake(sleep, wake, &shift)
	for i := 5; i < 25; i++ {
		expected[i] = false
	}

	assert.Equal(t, expected, shift.awake)
}

func TestMakeShift(t *testing.T) {
	expected_shift_awake := make([]bool, 60)
	shift := makeShift()
	for i := 0; i < 60; i++ {
		expected_shift_awake[i] = true
	}
	assert.Equal(t, expected_shift_awake, shift.awake)
}

func TestParseFallsAsleep(t *testing.T) {
	sleep := "[1518-11-01 00:05] falls asleep"
	minute := parseFallsAsleep(sleep)

	assert.Equal(t, 5, minute)
}

func TestParseWakeUp(t *testing.T) {
	wakes_up := "[1518-11-01 00:25] wakes up"
	minute := parseWakesUp(wakes_up)

	assert.Equal(t, 25, minute)
}

func TestMakeShiftsForRecords(t *testing.T) {
	shift10 := makeShiftWithGuardId(10)
	for i := 5; i < 25; i++ {
		shift10.awake[i] = false
	}
	for i := 30; i < 55; i++ {
		shift10.awake[i] = false
	}
	shift10.date = time.Date(1518, time.Month(11), 1, 0, 0, 0, 0, time.UTC)
	shift99 := makeShiftWithGuardId(99)
	for i := 40; i < 50; i++ {
		shift99.awake[i] = false
	}
	shift99.date = time.Date(1518, time.Month(11), 2, 0, 0, 0, 0, time.UTC)

	result_shiftList := makeShiftsForRecords(input)
	assert.Equal(t, result_shiftList[0], shift10)
	assert.Equal(t, result_shiftList[1], shift99)
}

func TestCountSleepMinutesOfGuard(t *testing.T) {
	shifts := makeShiftsForRecords(input2)
	result := countSleepMinutesOfGuard(shifts)
	assert.Equal(t, 20+25+10, result)
}

func TestFindSleepiestGuard(t *testing.T) {
	shifts := makeShiftsForRecords(input)
	result, _ := findSleepiestGuard(shifts)
	assert.Equal(t, 10, result)
}

func TestMostAsleepMinuteForSleepiestGuard(t *testing.T) {
	shifts := makeShiftsForRecords(input2)
	_, result := mostAsleepMinuteForSleepiestGuard(shifts)
	assert.Equal(t, 40, result)
}

func TestDay4Part1(t *testing.T) {
	shifts := makeShiftsForRecords(utils.ReadInputAsString("./input.txt"))
	guard, minute := mostAsleepMinuteForSleepiestGuard(shifts)
	assert.Equal(t, 14346, guard*minute)
}

func TestMostAsleepMinuteForGuard(t *testing.T) {
	shifts_for_guard := makeShiftsForRecords(input2)
	occurrences, most_frequently_slept_minute := mostAsleepMinuteForGuard(shifts_for_guard)
	assert.Equal(t, 40, most_frequently_slept_minute)
	assert.Equal(t, 2, occurrences)
}

func TestGuardMostFrequentlyAsleepOnTheSameMinute(t *testing.T) {
	shifts := makeShiftsForRecords(input_for_frequent)
	guardId, most_frequently_slept_minute := guardMostFrequentlyAsleepOnTheSameMinute(shifts)
	assert.Equal(t, 10, guardId)
	assert.Equal(t, 40, most_frequently_slept_minute)

}

func TestDay4Part2(t *testing.T) {
	shifts := makeShiftsForRecords(utils.ReadInputAsString("./input.txt"))
	guardId, most_frequently_slept_minute := guardMostFrequentlyAsleepOnTheSameMinute(shifts)
	assert.Equal(t, 5705, guardId*most_frequently_slept_minute)

}
