package app.domain.services;

import app.common.Logger;
import app.domain.services.base.ISubscriber;

public class TestSubscriver implements ISubscriber<Integer> {

    @Override
    public void update(Integer message) {
        Logger.log(String.format("New state is: %d", message));
    }

}
