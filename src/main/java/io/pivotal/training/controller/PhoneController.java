package io.pivotal.training.controller;

import io.pivotal.training.model.Phone;
import io.pivotal.training.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("phones")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Phone> getPhones() {
        return phoneService.getPhones();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPhone(@RequestBody Phone phone) {
        phoneService.createPhone(phone);
    }
}
