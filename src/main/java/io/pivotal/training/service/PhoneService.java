package io.pivotal.training.service;

import io.pivotal.training.model.Phone;
import io.pivotal.training.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class PhoneService {

    @Autowired
    PhoneRepository phoneRepository;

    @Transactional
    public Phone createPhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Transactional
    public List<Phone> getPhones() {
        return phoneRepository.findAll();
    }
}
