import fs from 'fs';

export function getInputFrom(path: string, extension = 'txt'): string[] {
	const input = fs.readFileSync(`inputs/${path}.${extension}`,'utf8');
	return input.split('\n')
}