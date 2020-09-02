package net.pechorina.config;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@ApplicationScoped
public class AwsConfig {
    String region = "ap-southeast-2";

    @Produces
    @Named("snsClient")
    public SnsClient snsClient() {
        return SnsClient.builder().region(Region.of(region)).build();
    }
}
