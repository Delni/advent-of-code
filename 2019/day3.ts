import { getInputFrom, prettyPrint } from './utils.ts';


type WireMap = string[][]
type Point = {
	x: number,
	y: number
}

const input = (await getInputFrom('day3')).map(it => it.split(','));

const manhantanDistance = (p1: Point, p2: Point) : number => Math.abs(p2.x - p1.x) + Math.abs(p2.y - p1.y)
const distanceFromOrigin = (point: Point) => manhantanDistance(point, {x: 0, y: 0})
const intersection = (path: Point[], path2: Point[]) => path.filter(point => {
	return path2.some(({x, y}) => x == point.x && y == point.y )
})

const visit = (path: string[]) : Point[] => {
	const visitedPoints: Point[] = []
	let x = 0, y = 0
	path.forEach(segment => {
		const direction = segment[0]
		const amount = parseInt(segment.substr(1))
		switch (direction) {
			case 'R': {
				let localAmount = 0
				while(localAmount < amount) {
					x++
					localAmount++
					visitedPoints.push({x, y})
				}
				break;
			}
			case 'L': {
				let localAmount = x
				while(x - localAmount < amount) {
					localAmount--
					visitedPoints.push({x:localAmount, y})
				}
				x = localAmount
				break;
			}
			case 'D': {
				let localAmount = 0
				while(localAmount < amount) {
					y++
					localAmount++
					visitedPoints.push({x, y})
				}
				break;
			}
			case 'U': {
				let localAmount = y
				while(y - localAmount < amount) {
					localAmount--
					visitedPoints.push({x, y: localAmount})
				}
				y = localAmount
				break;
			}
			default:
				break;
		}
	})
	return visitedPoints
}

const findStepsTo = ({x, y}: Point, path: Point[]) => path.findIndex(p => p.x == x && p.y == y)

const part1 = (entries: string[][]) : number => {
	const [path1, path2] = entries.map(visit)
	return intersection(path1, path2).map(distanceFromOrigin).sort()[0]
};
const part2 = (entries: string[][]) : number => {
	const [path1, path2] = entries.map(visit)
	return intersection(path1, path2)
		.map((point) => findStepsTo(point, path1) +  findStepsTo(point, path2))
		.sort()[0]
};


prettyPrint('03', [{
	prefix: 'Part 1',
	result: part1(input)
},{
	prefix: 'Part 2',
	result: part2(input)
}])