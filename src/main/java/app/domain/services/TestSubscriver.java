package app.domain.services;

import app.common.Logger;

public class TestSubscriver implements ISubscriber<Integer> {

    @Override
    public void update(Integer message) {
        Logger.log(String.format("New state is: %d", message));
    }

}
