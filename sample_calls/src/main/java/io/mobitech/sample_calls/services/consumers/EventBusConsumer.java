package io.mobitech.sample_calls.services.consumers;

import io.mobitech.sample_calls.services.HttpCallService;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class EventBusConsumer implements Handler<Message<JsonObject>> {

    private static Logger logger = LoggerFactory.getLogger(EventBusConsumer.class.getSimpleName());
    private final HttpCallService httpCallService;

    public EventBusConsumer(HttpCallService httpCallService){
        this.httpCallService = httpCallService;
    }

    @Override
    public void handle(Message<JsonObject> message) {
        logger.info("Event bus consumer got call");
        JsonObject body = message.body();
        httpCallService.getAsyncCall();
    }
}
