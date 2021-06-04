package ru.sfedu.diplomabackend.utils;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.hibernate.service.ServiceRegistry;
import static ru.sfedu.diplomabackend.Constants.*;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.sfedu.diplomabackend.model.*;

import java.io.File;

@Service
@Data
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final String CUSTOM_CONFIG_PATH = System.getProperty("configPath");
    private static Logger log = LogManager.getLogger(HibernateUtil.class);
/**
 * Создание фабрики
 *
 */


    @Bean
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            File nf;
            try{
                nf = new File(CUSTOM_CONFIG_PATH);
            }
            catch(NullPointerException e){
                nf = new File(RESOURCE_PATH);
            };
            File file = nf;
            Configuration configuration = new Configuration().configure(file);

            log.error(file.getName());

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            MetadataSources metadataSources = new MetadataSources(serviceRegistry);


            //metadataSources.addResource("named-queries.hbm.xml");// Именованные запросы

            addEntities(metadataSources);
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }

        return sessionFactory;
    }

    private static void addEntities(MetadataSources metadataSources){
        metadataSources.addAnnotatedClass(Goal.class);
        metadataSources.addAnnotatedClass(DiaryDayMental.class);
        metadataSources.addAnnotatedClass(DiaryDayPhysics.class);
        metadataSources.addAnnotatedClass(DiaryDay.class);
        metadataSources.addAnnotatedClass(User.class);
    }

}