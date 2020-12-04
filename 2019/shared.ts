export class IntCodeComputer {
	private initialMemory: number[]
	private memory: number[]

	constructor(memory: number[]) {
		this.memory = [...memory]
		this.initialMemory = [...memory]
	}

	get output(): number {
		return this.memory[0]
	}

	get(address: number): number {
		return this.memory[address];
	}

	get instruction(): number {
		return 100 * this.noun + this.verb
	}

	put(address: number, value: number): IntCodeComputer {
		this.memory[address] = value;
		return this;
	}

	reset(): IntCodeComputer {
		this.memory = [...this.initialMemory]
		return this
	}

	run(noun: number, verb: number): IntCodeComputer {
		this.noun = noun
		this.verb = verb
		for (let index = 0; index < this.memory.length; index+=4) {
			const opcode = this.memory[index];
			const firstInput = this.memory[this.memory[index + 1]];
			const secondInput = this.memory[this.memory[index + 2]];
			const output = this.memory[index + 3]
			
			switch (opcode) {
				case 1:
					this.memory[output] = firstInput + secondInput
					break;
				case 2:
					this.memory[output] = firstInput * secondInput
					break;
				case 99:
					return this;
				default:
					break;
			}
		}
		return this
	}

	private get noun() {
		return this.memory[1]
	}

	private set noun(value:number) {
		this.put(1, value)
	}

	private get verb() {
		return this.memory[2]
	}

	private set verb(value:number) {
		this.put(2, value)
	}

}