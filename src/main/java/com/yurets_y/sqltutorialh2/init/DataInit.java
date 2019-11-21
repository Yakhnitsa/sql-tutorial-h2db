package com.yurets_y.sqltutorialh2.init;


import com.yurets_y.sqltutorialh2.dao.PersonDAO;
import com.yurets_y.sqltutorialh2.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataInit implements ApplicationRunner {

    private PersonDAO personDAO;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public DataInit(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = personDAO.count();

        if (count == 0) {
            Person p1 = new Person();

            p1.setFullName("John Harrington");

            Date d1 = df.parse("1980-12-20");
            p1.setDateOfBirth(d1);
            //
            Person p2 = new Person();
            p2.setFullName("Will Smith");
            Date d2 = df.parse("1985-11-11");
            p2.setDateOfBirth(d2);

            Person p3 = new Person();
            p3.setFullName("Angelina Jolie");
            Date d3 = df.parse("1975-06-04");
            p3.setDateOfBirth(d3);

            personDAO.save(p1);
            personDAO.save(p2);
            personDAO.save(p3);
        }

    }

}
