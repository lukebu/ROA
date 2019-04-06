package com.lukebu.workmt.events;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class EventProcessor {

    private static EventProcessor instance = new EventProcessor();

    public static EventProcessor getInstance() {
        return instance;
    }

    private List<Consumer<Event>> eventConsumers = new LinkedList<>();

    public void registerListener(Consumer<Event> eventConsumer) {
        eventConsumers.add(eventConsumer);
    }

    public void sendEvent(Event event) {
        eventConsumers.forEach(eventConsumer -> eventConsumer.accept(event));
    }
}
