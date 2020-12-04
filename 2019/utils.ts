export async function getInputFrom(path: string, splitPattern = '\n'): Promise<string[]> {
	const file = await Deno.readTextFile(`./inputs/${path}.txt`);
	return file.split(splitPattern)
}

export const sum = (total: number, value: number) : number => total + value
export const toInt = (string: string) => parseInt(string)


type Entry = {
	prefix: string,
	result: number
}

export const prettyPrint = (day: string, entries: Entry[]): void => {
	const maxLength = 10 + Math.max(...entries.map(({prefix, result}) => `${prefix}${result}`.length))
	const separator = Array.from(Array(Math.floor(maxLength / 2) - 3)).map(() => '-').join('')
	console.log(`${separator} DAY ${day} ${separator}`)
	entries.forEach(({prefix, result}) => {
		const space = Array.from(Array(maxLength - prefix.length - result.toString().length)).map(() => ' ').join('')
		console.log(`${prefix}${space}`, result)
	})

}