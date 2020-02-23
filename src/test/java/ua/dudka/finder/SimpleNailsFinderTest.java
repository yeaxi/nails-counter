package ua.dudka.finder;

class SimpleNailsFinderTest extends AbstractNailsFinderTest {

    @Override
    protected NailsFinder getFinder() {
        return new SimpleNailsFinder();
    }
}