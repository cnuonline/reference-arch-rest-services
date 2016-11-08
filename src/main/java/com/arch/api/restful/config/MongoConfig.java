package com.arch.api.restful.config;

/**
 * Created by SRINIVASULU on 05/11/16.
 */
import com.arch.api.restful.boot.db.converter.CustomerConverter;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


import java.util.ArrayList;
import java.util.List;

/*import org.baeldung.converter.UserWriterConverter;
import org.baeldung.event.CascadeSaveMongoEventListener;
import org.baeldung.event.UserCascadeSaveMongoEventListener;*/

@Configuration
@EnableMongoRepositories(basePackages = "com.arch.api.restful.boot.db.repositories")
//@EnableMongoAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database:test}")
    String database;

    @Value("${spring.data.mongodb.host:localhost}:${spring.data.mongodb.port:27017}")
    String host;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(host);
    }


    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
   @Bean
    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
       // converterList.add(new CustomerConverter());
       // converterList.add(new MongoColorReader());
        return new CustomConversions(converterList);
    }


    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Bean
    public LoggingEventListener mappingEventsListener() {
        return new LoggingEventListener();
    }
}
