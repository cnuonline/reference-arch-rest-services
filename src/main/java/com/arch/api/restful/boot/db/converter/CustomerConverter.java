package com.arch.api.restful.boot.db.converter;

/**
 * Created by SRINIVASULU on 05/11/16.
 */

import com.arch.api.restful.boot.beans.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Component
public class CustomerConverter implements Converter<Customer, DBObject> {

    public CustomerConverter(){

    }
    @Override
    public DBObject convert(final Customer user) {

        final DBObject dbObject = new BasicDBObject();
       /*
        dbObject.put("name", user.getName());
        dbObject.put("age", user.getAge());
        if (user.getEmailAddress() != null) {
            final DBObject emailDbObject = new BasicDBObject();
            emailDbObject.put("value", user.getEmailAddress().getValue());
            dbObject.put("email", emailDbObject);
        }
        dbObject.removeField("_class");*/
        return dbObject;
    }

}