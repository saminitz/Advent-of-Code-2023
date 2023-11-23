package de.samintiz.adventofcode2023.day;

public class DayFactory {

    private Class<? extends Day> dayClass;

    public DayFactory(Class<? extends Day> dayClass) {
        this.dayClass = dayClass;
    }

    public Day newInstance() throws InstantiationException, IllegalAccessException {
        Day instance = dayClass.newInstance();
        instance.init();
        return instance;
    }
}
