package io.pivotal.training.service;

import io.pivotal.training.errors.NutNotFoundException;
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

    public boolean delete(Long id) {
        boolean exists = nutsRepository.exists(id);
        if (exists)
            nutsRepository.delete(id);
        return exists;
    }

    public Nut get(Long id) {
        boolean exists = nutsRepository.exists(id);

        if(exists)
            return nutsRepository.getOne(id);
        else
            throw new NutNotFoundException("Could not find nut with id: " + id);
    }
}
