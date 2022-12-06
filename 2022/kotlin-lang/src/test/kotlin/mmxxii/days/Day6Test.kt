package mmxxii.days

import org.junit.jupiter.api.Test

import kotlin.test.expect

class Day6Test {

    @Test
    fun `part 1 should return index on different inputs`() {
        expect(7) {Day6().part1(listOf("mjqjpqmgbljsphdztnvjfqwrcgsmlb")) }
        expect(5) {Day6().part1(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")) }
        expect(6) {Day6().part1(listOf("nppdvjthqldpwncqszvftbrmjlhg")) }
        expect(10) {Day6().part1(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")) }
        expect(11) {Day6().part1(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")) }
    }

    @Test
    fun `part 2 should return index on different inputs`()  {
        expect(19) {Day6().part2(listOf("mjqjpqmgbljsphdztnvjfqwrcgsmlb")) }
        expect(23) {Day6().part2(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")) }
        expect(23) {Day6().part2(listOf("nppdvjthqldpwncqszvftbrmjlhg")) }
        expect(29) {Day6().part2(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")) }
        expect(26) {Day6().part2(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")) }
    }

}