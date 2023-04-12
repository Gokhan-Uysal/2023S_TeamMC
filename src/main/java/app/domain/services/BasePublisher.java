package app.domain.services;

import java.util.ArrayList;

import app.common.Logger;

public class BasePublisher implements IPublisher {
    private ArrayList<ISubscriber> subscribers;

    @Override
    public boolean addSubscriber(ISubscriber subscriber) {
        boolean response = subscribers.add(subscriber);
        if (response) {
            Logger.info(String.format("New subscriber added: %s", subscriber.toString()));
        } else {
            Logger.error(String.format("Failed to add new subscriber: %s", subscriber.toString()));
        }

        return response;
    }

    @Override
    public boolean removeSubscriber(ISubscriber subscriber) {
        boolean response = subscribers.remove(subscriber);
        if (response) {
            Logger.info(String.format("New subscriber removed: %s", subscriber.toString()));
        } else {
            Logger.error(String.format("Failed to remove subscriber: %s", subscriber.toString()));
        }

        return response;
    }

    @Override
    public <T> void notifySubscribers(T message) {
        Logger.info("Game state is changed");
        for (int i = 0; i < subscribers.size(); i++) {
            ISubscriber subscriber = subscribers.get(i);
            subscriber.update(message);
        }
    }

}
