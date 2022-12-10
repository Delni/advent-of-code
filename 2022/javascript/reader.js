import fs from "fs"
import { fileURLToPath } from 'url'
import { dirname, join } from 'path'
const __filename = fileURLToPath(import.meta.url)
const __dirname = dirname(__filename)

export const readInput = (filename) => fs.readFileSync(join(__dirname,'..', `resources/${filename}.txt`), 'utf-8')