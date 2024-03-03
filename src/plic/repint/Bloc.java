package plic.repint;

import java.util.List;

public class Bloc {
    private List<Instruction> instructions;

    public Bloc(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Bloc{" +
                "instructions=" + instructions +
                '}';
    }
}
