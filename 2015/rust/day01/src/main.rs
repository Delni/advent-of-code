fn main() {
    println!(
        "{}:\t{}",
        "Day 1 - Part 1",
        part1(include_str!("../../../resources/day01.txt"))
    );
    println!(
        "{}:\t{}",
        "Day 1 - Part 2",
        part2(include_str!("../../../resources/day01.txt"))
    )
}

fn part1(instructions: &str) -> i32 {
    return instructions
        .split("")
        .filter(|char| !char.is_empty())
        .map(|char| if char == "(" { 1 } else { -1 })
        .sum();
}

fn part2(instructions: &str) -> i32 {
    return instructions
        .split("")
        .filter(|char| !char.is_empty())
        .map(|char| if char == "(" { 1 } else { -1 })
        .enumerate()
        .map(|(index, value)| (<usize as TryInto<i32>>::try_into(index).unwrap() + 1, value))
        .fold(
            (0, 0, true),
            |(floor, index, more), (current_index, next)| {
                if !more {
                    return (floor, index, more);
                }
                return if floor + next < 0 {
                    (floor, current_index, false)
                } else {
                    (floor + next, index, true)
                };
            },
        )
        .1;
}

#[cfg(test)]
mod test {
    use super::*;

    #[test]
    fn it_should_read_instructions() {
        assert_eq!(0, part1("(())"));
        assert_eq!(0, part1("()()"));
        assert_eq!(3, part1("((("));
        assert_eq!(3, part1("(()(()("));
        assert_eq!(3, part1("))((((("));
        assert_eq!(-1, part1("())"));
        assert_eq!(-1, part1("))("));
        assert_eq!(-3, part1(")))"));
        assert_eq!(-3, part1(")())())"));
    }

    #[test]
    fn it_should_return_basement_position() {
        assert_eq!(1, part2(")"));
        assert_eq!(5, part2("()())"));
    }
}
