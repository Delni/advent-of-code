fn main() {
    println!(
        "{}:\t{}",
        "Day 2 - Part 1",
        part1(include_str!("../../../resources/day02.txt"))
    );
    println!(
        "{}:\t{}",
        "Day 2 - Part 2",
        part2(include_str!("../../../resources/day02.txt"))
    );
}

fn parse_input(input: &str) -> Vec<[i32; 3]> {
    return input
        .split("\n")
        .map(|line| line.split("x"))
        .map(|line| line.map(|x| x.parse::<i32>().unwrap()))
        .map(|line| line.collect::<Vec<i32>>().try_into().unwrap())
        .collect();
}

fn part1(input: &str) -> i32 {
    return parse_input(input)
        .iter()
        .map(|size| smallest_area(size) + present_area(size))
        .sum();
}

fn present_area([l, w, h]: &[i32; 3]) -> i32 {
    return 2 * l * w + 2 * w * h + 2 * h * l;
}

fn smallest_area(size: &[i32; 3]) -> i32 {
    let mut mut_size = size.clone();
    mut_size.as_mut().sort();
    return mut_size[0] * mut_size[1];
}

fn part2(input: &str) -> i32 {
    return parse_input(input)
        .iter()
        .map(|size| ribbon_length(size) + ribbon_volume(size))
        .sum();
}

fn ribbon_length(size: &[i32; 3]) -> i32 {
    let mut mut_size = size.clone();
    mut_size.as_mut().sort();
    return 2 * mut_size[0] + 2 * mut_size[1];
}

fn ribbon_volume(size: &[i32; 3]) -> i32 {
    return size.iter().fold(1, |result, element| result * element);
}

#[cfg(test)]
mod daytwo {
    use super::*;

    #[test]
    fn it_should_parse_input() {
        let parsed = parse_input(include_str!("./input.txt"));
        assert_eq!(parsed.len(), 1000);
        assert_eq!(parsed[0], [20, 3, 11])
    }

    #[test]
    fn it_should_return_area() {
        assert_eq!(present_area(&[2, 3, 4]), 52);
        assert_eq!(present_area(&[1, 1, 10]), 42);
    }

    #[test]
    fn it_should_return_smallest_area() {
        assert_eq!(smallest_area(&[2, 3, 4]), 6);
        assert_eq!(smallest_area(&[1, 1, 10]), 1);
    }

    #[test]
    fn it_should_pass_part1() {
        assert_eq!(part1("2x3x4"), 58);
        assert_eq!(part1("1x1x10"), 43)
    }

    #[test]
    fn it_should_return_ribbon_length() {
        assert_eq!(ribbon_length(&[2, 3, 4]), 10);
        assert_eq!(ribbon_length(&[1, 1, 10]), 4);
    }

    #[test]
    fn it_should_return_ribbon_volume() {
        assert_eq!(ribbon_volume(&[2, 3, 4]), 24);
        assert_eq!(ribbon_volume(&[1, 1, 10]), 10);
    }

    #[test]
    fn it_should_pass_part2() {
        assert_eq!(part2("2x3x4"), 34);
        assert_eq!(part2("1x1x10"), 14)
    }
}
