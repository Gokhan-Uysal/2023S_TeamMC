package app.domain.services.base;

public interface ISubscriber<MessageType> {
    void update(MessageType message);
}
