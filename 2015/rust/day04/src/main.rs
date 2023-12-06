fn main() {
    println!(
        "{}:\t{}",
        "Day 4 - Part 1",
        part1(include_str!("../../../resources/day04.txt"))
    );
    println!(
        "{}:\t{}",
        "Day 4 - Part 2",
        part2(include_str!("../../../resources/day04.txt"))
    );
}

fn find_md5_that_starts_with(pattern: &str, input: &str) -> i32 {
    let mut result = String::new();
    let mut i = 0;
    while !result.starts_with(pattern) {
        let test = format!("{}{}", input, i);
        result = format!("{:x}", md5::compute(&test));
        i += 1;
        //println!("{} - {}", result, &test)
    }
    return i -  1;
}

fn part1(input: &str) -> i32 {
    return find_md5_that_starts_with("00000", input);
}

fn part2(input: &str) -> i32 {
    return find_md5_that_starts_with("000000", input);
}

#[cfg(test)]
mod daytwo {
    use super::*;

    #[test]
    fn it_should_pass_part1() {
        assert_eq!(part1("abcdef"), 609043);
        assert_eq!(part1("pqrstuv"), 1048970);
    }
}
