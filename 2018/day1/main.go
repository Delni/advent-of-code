package day1

func ComputeFrequency(frequency_sequence []int) int {
	result := 0
	for _, value := range frequency_sequence {
		result += value
	}
	return result
}

func FrequencyReachedTwice(frequency_sequence []int) int {
	result := 0
	frequency_map := map[int]int{0: 1}
	for true {
		for _, value := range frequency_sequence {
			result += value
			if _, ok := frequency_map[result]; ok {
				return result
			}
			frequency_map[result] = value
		}
	}
	return 0
}