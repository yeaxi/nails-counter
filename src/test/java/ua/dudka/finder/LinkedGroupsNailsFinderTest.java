package ua.dudka.finder;

class LinkedGroupsNailsFinderTest extends AbstractNailsFinderTest {

    @Override
    protected NailsFinder getFinder() {
        return new LinkedGroupsNailsFinder();
    }
}