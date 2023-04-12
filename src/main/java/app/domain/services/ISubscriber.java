package app.domain.services;

public interface ISubscriber {
    <T> void update(T message);
}
