package io.pivotal.training.controller;

import io.pivotal.training.model.Nut;
import io.pivotal.training.service.NutsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/nuts")
public class NutsController {

    private final NutsService nutsService;

    @Autowired
    public NutsController(NutsService nutsService) {
        this.nutsService = nutsService;
    }

    @RequestMapping
    public List<Nut> listAll() {
        return nutsService.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Nut nut) {
        nutsService.create(nut);
    }

}
