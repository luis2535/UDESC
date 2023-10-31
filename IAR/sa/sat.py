import random

class ThreeSAT:
    clauses : list = []
    variables : set[int] = set()
    initial_solution : list[int] = []
    num_clauses = 0

    def __init__(self, filename: str) -> None:
        self.clauses, self.variables = self.read_file(filename)
        self.initial_solution = self.generate_solution()
        self.num_clauses = len(self.clauses)

    def generate_solution(self) -> list[int]:
        return [random.choice([True, False]) for _ in range(len(self.variables))]
    

    def evaluate(self, solution : list[int]):
        satisfied = 0
        for clause in self.clauses:

            a = not solution[abs(clause[0]) - 1] if clause[0] < 0 else solution[abs(clause[0]) - 1]
            b = not solution[abs(clause[1]) - 1] if clause[1] < 0 else solution[abs(clause[1]) - 1]
            c = not solution[abs(clause[2]) - 1] if clause[2] < 0 else solution[abs(clause[2]) - 1]


            if a or b or c:
                satisfied += 1

        return satisfied
    
    def read_file(self, filename: str):
        clauses = []
        variables = set()
        with open(filename, 'r', encoding='utf-8') as f:
            for line in f:
                if line.startswith('%'):
                    break
                if line.startswith('c'):
                    continue
                if line.startswith('p'):
                    continue
                clause = [int(x) for x in line.split()]
                clause.pop() 
                
                clauses.append(clause)
                for x in clause:
                    variables.add(abs(x))
        return clauses, variables