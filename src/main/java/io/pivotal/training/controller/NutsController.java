package io.pivotal.training.controller;

import io.pivotal.training.model.Nut;
import io.pivotal.training.service.NutsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Nut> create(@RequestBody Nut nut) {
        Nut createdNut = nutsService.create(nut);

        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(createdNut.getId()).toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        return new ResponseEntity<Nut>(createdNut, responseHeaders, HttpStatus.CREATED);
    }

}
