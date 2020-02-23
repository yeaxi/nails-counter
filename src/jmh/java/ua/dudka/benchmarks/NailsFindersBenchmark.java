package ua.dudka.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import ua.dudka.finder.LinkedGroupsNailsFinder;
import ua.dudka.finder.NailsFinder;
import ua.dudka.finder.SimpleNailsFinder;
import ua.dudka.state.A3KNailsState;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class NailsFindersBenchmark {

    private final NailsFinder groupsFinder = new LinkedGroupsNailsFinder();
    private final NailsFinder simpleFinder = new SimpleNailsFinder();

    @Benchmark
    public void groupsFinderBenchmark(A3KNailsState state, Blackhole blackhole) {
        int solution = groupsFinder.find(state.nails, state.changesCount);
        blackhole.consume(solution);
    }

    @Benchmark
    public void simpleFinderBenchmark(A3KNailsState state, Blackhole blackhole) {
        int solution = simpleFinder.find(state.nails, state.changesCount);
        blackhole.consume(solution);
    }

}
