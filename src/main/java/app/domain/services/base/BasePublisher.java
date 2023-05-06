package app.domain.services.base;

import java.util.ArrayList;
import java.util.List;

import app.common.Logger;

public abstract class BasePublisher<MessageType> implements IPublisher<MessageType> {
    private List<ISubscriber<MessageType>> subscribers;
    private MessageType message;

    public BasePublisher(MessageType message) {
        subscribers = new ArrayList<>();
        this.message = message;
    }

    @Override
    public boolean addSubscriber(ISubscriber<MessageType> subscriber) {
        boolean response = subscribers.add(subscriber);
        if (response) {
            Logger.info(String.format("New subscriber added: %s", subscriber.toString()));
        } else {
            Logger.error(String.format("Failed to add new subscriber: %s", subscriber.toString()));
        }

        return response;
    }

    @Override
    public boolean removeSubscriber(ISubscriber<MessageType> subscriber) {
        boolean response = subscribers.remove(subscriber);
        if (!response) {
            Logger.error(String.format("Failed to remove subscriber: %s", subscriber.toString()));
        }
        return response;
    }

    @Override
    public void notifySubscribers() {
        for (int i = 0; i < subscribers.size(); i++) {
            ISubscriber<MessageType> subscriber = subscribers.get(i);
            subscriber.update(message);
        }
    }

    @Override
    public void setState(MessageType newState) {
        this.message = newState;
    }

    @Override
    public MessageType getState() {
        return this.message;
    }
}
