package com.dmdev.util;

import com.dmdev.converter.BirthdayConverter;
import com.dmdev.entity.User;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.util.CamelCaseToSnakeCaseNamingStrategy;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToSnakeCaseNamingStrategy());
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConverter());
        configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
