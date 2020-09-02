package net.pechorina.routes;

import net.pechorina.model.PRecord;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.aws2Sns;
import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.timer;

@ApplicationScoped
public class HttpReader extends RouteBuilder {
    private static final Logger LOG = Logger.getLogger(HttpReader.class);

    @ConfigProperty(name = "srcUrl", defaultValue = "localhost")
    String url;

    @ConfigProperty(name = "targetDate", defaultValue = "2020-09-06")
    String targetDate;

    @ConfigProperty(name = "snsTopic")
    String snsTopic;


    @Override
    public void configure() throws Exception {
        LOG.info("URL: " + url);
        LOG.info("Target Date: " + targetDate);

        from(timer("timer1").fixedRate(true).period(10000).delay(3000))
                .setHeader("User-Agent", constant("Mozilla/5.0 (X11; Fedora; Linux x86_64; rv:80.0) Gecko/20100101 Firefox/80.0"))
                .setHeader("Referer", constant("https://parking.evacheckin.com/car_park_booking/select_dates?siteId=2"))
                .toD(url)
                .unmarshal().json(JsonLibrary.Jackson, PRecord.class)
                .transform().simple("${body.dates}")
                .split().body()
                .filter().simple("${body.localDate} startsWith '" + targetDate + "'")
                .log("${body}")
                .filter().simple("${body.percentFull} < 100.0")
                .log("Alert!!! ${body}")
                .transform().simple("${body.asMessage()}")
                .to(aws2Sns(snsTopic).amazonSNSClient("snsClient"))
                .end();
    }
}
