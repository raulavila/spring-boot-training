package io.pivotal.training.service;

import io.pivotal.training.model.Nut;
import io.pivotal.training.repository.NutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutsService {
    private NutsRepository nutsRepository;

    @Autowired
    public NutsService(NutsRepository nutsRepository) {
        this.nutsRepository = nutsRepository;
    }

    public Nut create(Nut nut) {
        return nutsRepository.save(nut);
    }

    public List<Nut> list() {
        return nutsRepository.findAll();
    }
}
