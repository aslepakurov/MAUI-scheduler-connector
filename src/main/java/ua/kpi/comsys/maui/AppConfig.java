package ua.kpi.comsys.maui;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

/**
 * AppConfig Class
 *
 * @author Andrew S. Slepakurov
 * @version 28/03/2014
 */
@Configuration
public class AppConfig {

    public @Bean
    Mongo mongo() throws UnknownHostException {
        MongoOptions options = new MongoOptions();
        options.setConnectionsPerHost(200);
        options.setW(1);
        return new Mongo("localhost", options);
    }

    public @Bean
    MongoOperations mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(mongo(), "maui");
    }
}
