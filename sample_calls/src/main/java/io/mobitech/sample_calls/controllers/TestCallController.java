package io.mobitech.sample_calls.controllers;

import com.github.aesteve.vertx.nubes.annotations.Controller;
import com.github.aesteve.vertx.nubes.annotations.routing.http.GET;
import com.github.aesteve.vertx.nubes.annotations.services.Service;
import io.mobitech.sample_calls.services.HttpCallService;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by Gal Levinsky on 14-Nov-16.
 */
@Controller("/test")
public class TestCallController {

    @Service("HttpCallService")
    private HttpCallService httpCallService;


    @GET("/1")
    public void asyncCallOne(RoutingContext context) {
        context.response().end(httpCallService.getAsyncCall());
    }

    @GET("/2")
    public void asyncCallWithEventBus(RoutingContext context) {
        context.response().end(httpCallService.callEventBus());
    }



}
