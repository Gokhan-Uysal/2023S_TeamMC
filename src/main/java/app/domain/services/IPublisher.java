package app.domain.services;

public interface IPublisher {
    boolean addSubscriber(ISubscriber subscriber);

    boolean removeSubscriber(ISubscriber subscriber);

    <T> void notifySubscribers(T message);
}
