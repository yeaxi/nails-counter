package ua.dudka.state;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import static ua.dudka.util.IntegersGenerator.generateNails;

@State(Scope.Benchmark)
public abstract class BaseNailsState {

    public int[] nails;
    public int changesCount;

    @Setup(Level.Trial)
    public void setUp() {
        this.nails = generateNails(getSize());
        this.changesCount = this.nails.length - 1;
    }

    public abstract int getSize();
}
