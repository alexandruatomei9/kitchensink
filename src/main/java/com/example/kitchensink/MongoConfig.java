package com.example.kitchensink;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.kitchensink.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    private MongodExecutable mongodExecutable;

    @Bean
    public MongoClient mongoClient() {
        String bindIp = "localhost";
        int port = 27017;

        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongodConfig mongodConfig = null;
        try {
            mongodConfig = MongodConfig.builder()
                    .version(Version.Main.PRODUCTION)
                    .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                    .build();

            mongodExecutable = starter.prepare(mongodConfig);
            mongodExecutable.start();

            return MongoClients.create(String.format("mongodb://%s:%d", bindIp, port));
        } catch (IOException e) {
            throw new RuntimeException("Unable to create embedded mongodb instance", e);
        }
    }

    @PreDestroy
    public void stopMongo() {
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
    }

    @Override
    protected String getDatabaseName() {
        return "testdb";
    }
}
