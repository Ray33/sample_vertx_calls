package io.mobitech.sample_calls.services;

import com.github.aesteve.vertx.nubes.services.Service;
import io.mobitech.sample_calls.services.consumers.EventBusConsumer;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Created by Gal Levinsky on 14-Nov-16.
 */
public class HttpCallService implements Service {

    public static final String LOCAL_EVENTBUS_ADDRESS = "/localbus/add";
    private static Logger logger = LoggerFactory.getLogger(HttpCallService.class.getSimpleName());
    private HttpClient httpClient;
    private Vertx vertx;

    @Override
    public void init(Vertx vertx, JsonObject config) {
        this.vertx = vertx;
        //init http cilent
        this.httpClient = vertx.createHttpClient();
        //init local bus
        vertx.eventBus().localConsumer(LOCAL_EVENTBUS_ADDRESS, new EventBusConsumer(this));
    }

    @Override
    public void start(Future<Void> future) {
        future.complete();
    }

    @Override
    public void stop(Future<Void> future) {
        future.complete();
    }

    public String getAsyncCall() {
        logger.info("About to execute async call");
        httpClient.getAbs("http://www.bing.com/search?q=mobitech.io").handler(handler -> {
            logger.info("Got response from async call");
        }).end();
        return "Done calling async call";
    }

    public String callEventBus() {
        vertx.eventBus().send(LOCAL_EVENTBUS_ADDRESS,
                new JsonObject());
        return "Done sending event to bus";
    }
}
