import { readInput } from '../reader.js';
import sleep from 'sleep'
import chalk, {Chalk} from 'chalk';

// #region State variable
const input = readInput('Day01').split("\n\n")
	.map(it => it.split("\n"))
	.map(it => it.map(that => parseInt(that)))
let maxWeight = [0,0,0]
// #endregion

// #region Render variable
const customChalk = new Chalk({level: 3})
const titleChalk = customChalk.hex("#04843B")
const title = titleChalk.underline.bold("Day 01:")  + titleChalk` Calories Counting`
const toPodium = (calories) => chalk.yellow.bold(`${calories == 0 ? '' : calories}`.padStart(5, '.'))
// #endregion



const render = (id, step, cal = ".") => {
	const matricule = (id + 1).toString().padStart(3, '0')
	const weight = chalk.red(`${cal}`.padStart(5, '.'))
	let box = [
		"  ┌───┬─┬───┐",
		"  │   └─┘   │",
		"  │  ┌───┐  │",
		`  │  │${matricule}│  │`,
		"  │  └───┘  │",
		"  └─────────┘",
	]
	box = box.slice(box.length -step, box.length)
	if(box.length < 6) {
		box.push(...Array(6 - box.length - 1).fill(""))
	}
	const weighter = [
		step == 6 ? "╔═└─────────┘═╗" : "╔═════════════╗",
		`╠═════════════╣               ${toPodium(maxWeight[0])}`,
		`║  ┌───────┐  ║             ┌───────┐ ${toPodium(maxWeight[1])}`,
		`║  │ ${weight} │  ║       ${toPodium(maxWeight[2])} │       ├───────┐`,
		"║  └───────┘  ║     ┌───────┤       │       │",
		"╩═           ═╩     └───────┴───────┴───────┘",
	]

	const max = [
		"┌─────"
	]
	const builder = [
		title,
		"",
		...box.slice(0, step == 6 ? box.length - 1 : box.length),
		...weighter,
	].filter(it => it != null)
	console.clear()
	console.log(builder.join("\n"))
}

for(let i = 0; i < input.length; i++) {
	const speed = 100
	for(let j = 0; j < 6; j++) {
		render(i, j)
	sleep.msleep(speed)
	}
	
	const weight = input[i].reduce((a, b) => a + b, 0)
	if(Math.max(weight, maxWeight[0]) == weight) {
		maxWeight[0] = weight
	} else if(Math.max(weight, maxWeight[1]) == weight) {
		maxWeight[1] = weight
	} else if(Math.max(weight, maxWeight[2]) == weight) {
		maxWeight[2] = weight
	}
	render(i, 6, weight)

	sleep.msleep(15*speed)
	
}