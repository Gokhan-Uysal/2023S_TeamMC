package app.domain.services;

public interface ISubscriber<MessageType> {
    void update(MessageType message);
}
