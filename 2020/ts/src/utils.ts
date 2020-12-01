import fs from 'fs';

export function getInputFrom(path: string): string[] {
	const input = fs.readFileSync(`../inputs/${path}.txt`,'utf8');
	return input.split('\n')
}

export const isNotIndex = (index: number) : (value: unknown, i: number) => boolean => (value: unknown, i: number) => i !== index
export const isDefined = (ok?: unknown) : boolean => !!ok
export const sum = (total: number, value: number) : number => total + value
export const times = (total: number, value: number) : number => total * value

