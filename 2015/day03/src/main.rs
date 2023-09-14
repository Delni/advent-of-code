use std::collections::HashSet;

fn main() {
    println!(
        "{}:\t{}",
        "Day 3 - Part 1",
        part1(include_str!("./input.txt"))
    );
    println!(
        "{}:\t{}",
        "Day 3 - Part 2",
        part2(include_str!("./input.txt"))
    );
}

fn part1(input: &str) -> i32 {
    let mut coord = (0, 0);
    let mut visited_house = HashSet::new();
    visited_house.insert(coord);
    for instruction in input.split("") {
        visited_house.insert(update_coord(&mut coord, instruction));
    }
    return visited_house.len().try_into().unwrap();
}

fn update_coord(coord: &mut (i32, i32), instruction: &str) -> (i32, i32) {
    match instruction {
        ">" => coord.1 += 1,
        "<" => coord.1 -= 1,
        "^" => coord.0 += 1,
        "v" => coord.0 -= 1,
        _ => (),
    };
    return *coord;
}

fn part2(input: &str) -> i32 {
    let mut santa_coord = (0, 0);
    let mut robot_santa_coord = (0, 0);
    let mut visited_house = HashSet::new();
    visited_house.insert(santa_coord);
    visited_house.insert(robot_santa_coord);
    for (index, instruction) in input.split("").enumerate() {
        visited_house.insert(update_coord(
            if index % 2 == 0 {
                &mut santa_coord
            } else {
                &mut robot_santa_coord
            },
            instruction,
        ));
    }
    return visited_house.len().try_into().unwrap();
}

#[cfg(test)]
mod daytwo {
    use super::*;

    #[test]
    fn it_should_pass_part1() {
        assert_eq!(part1(">"), 2);
        assert_eq!(part1("^>v<"), 4);
        assert_eq!(part1("^v^v^v^v^v"), 2);
    }

    #[test]
    fn it_should_pass_part2() {
        assert_eq!(part2("^v"), 3);
        assert_eq!(part2("^>v<"), 3);
        assert_eq!(part2("^v^v^v^v^v"), 11);
    }
}
