package com.dmdev;

import com.dmdev.converter.BirthdayConverter;
import com.dmdev.entity.Birthday;
import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.dmdev.util.HibernateUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.util.CamelCaseToSnakeCaseNamingStrategy;
import lombok.experimental.UtilityClass;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;

@Slf4j
public class HibernateRunner {
    // https://i.ibb.co/kGCqRDD/image.png

    //    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);
    public static void main(String[] args) throws SQLException {
        User user1 = User.builder()
                .username("petr2@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Petr")
                        .lastname("Petrov")
                        .build())
                .build();
        log.info("User entity: {}", user1);

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session1 = sessionFactory.openSession();
            try(session1) {
                Transaction transaction = session1.beginTransaction();
                log.trace("Transaction is created: {}", transaction);

                session1.saveOrUpdate(user1); // в этой точке user1 будет в состоянии "persistant" в отношении session1, но "transient" в отношении второй сессии
                log.info("User is in persistent state: {}, session: {}", user1, session1);

                session1.getTransaction().commit();
            }
            log.warn("User is in detached state: {}, session is closed: {}", user1, session1);

        } catch(Exception e) {
            log.error("Exception occured: ", e);
            throw e;
        }
    }

}
