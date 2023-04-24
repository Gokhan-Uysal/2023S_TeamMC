package app.domain.services;

public interface IPublisher<MessageType> {
    boolean addSubscriber(ISubscriber<MessageType> subscriber);

    boolean removeSubscriber(ISubscriber<MessageType> subscriber);

    void notifySubscribers(MessageType message);

    void setState(MessageType newState);

    MessageType getState();
}
