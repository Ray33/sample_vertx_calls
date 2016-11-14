package io.mobitech.sample_calls;

import com.github.aesteve.vertx.nubes.NubesServer;
import io.mobitech.sample_calls.services.HttpCallService;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


public class StartServerVerticle extends NubesServer {


    private static Logger logger = LoggerFactory.getLogger(StartServerVerticle.class.getSimpleName());

    @Override
    public void init(Vertx vertx, Context context) {
        //set DNS resolving to Google, as AWS is not updated and return unresolved
        vertx = Vertx.vertx(new VertxOptions().
                setAddressResolverOptions(
                        new AddressResolverOptions()
                                .addServer("8.8.8.8")//Google
                                .addServer("8.8.4.4")//Google
                                .addServer("84.200.69.80") //DNS.Watch
                                .addServer("208.67.220.220")//cisco
                ));

        super.init(vertx, context);
    }


    public void start(Future<Void> future) {
        registerServices(vertx, context);
        super.start(future);
    }

    private void registerServices(Vertx vertx, Context context) {
        nubes.registerService("HttpCallService", new HttpCallService());


    }
}
