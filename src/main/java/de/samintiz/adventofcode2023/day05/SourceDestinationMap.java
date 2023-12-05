package de.samintiz.adventofcode2023.day05;

public record SourceDestinationMap(String sourceName, String destinationName, long destinationRangeStart,
        long sourceRangeStart, long rangeLength) {

    public boolean isSourceIdInRange(long sourceId) {
        return sourceId >= sourceRangeStart && sourceId < sourceRangeStart + rangeLength;
    }

    public long getDestinationIdOfSourceId(long sourceId) {
        long difference = sourceRangeStart - destinationRangeStart;
        return sourceId - difference;
    }
}
