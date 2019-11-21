package com.yurets_y.sqltutorialh2;


import com.yurets_y.sqltutorialh2.dao.PersonDAO;
import com.yurets_y.sqltutorialh2.entity.Person;
import com.yurets_y.sqltutorialh2.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private PersonDAO personDAO;

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        Iterable<Person> all = personDAO.findAll();

        StringBuilder sb = new StringBuilder();

        all.forEach(p -> sb.append(p.getFullName() + "<br>"));

        return sb.toString();
    }
    @ResponseBody
    @GetMapping("users")
    public Iterable<Person> personList(@PathVariable(required = false) Integer id){
        return personDAO.findAll();
    }

    @ResponseBody
    @GetMapping("users/{id}")
    public Person personList(@PathVariable(required = false) Long id){
        Optional<Person> optPerson = personDAO.findById(id);
        return optPerson.orElseThrow(NotFoundException::new);
    }
}
