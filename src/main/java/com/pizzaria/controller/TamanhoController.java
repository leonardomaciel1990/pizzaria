package com.pizzaria.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzaria.entity.Adicional;
import com.pizzaria.repository.AdicionalRepository;

@RestController
@RequestMapping("/tamanho")
public class TamanhoController {

	@Autowired
	private AdicionalRepository adicionalRepository;
	
	@GetMapping
    public List<Adicional> get() {
        return adicionalRepository.findAll();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Adicional> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Adicional> adicional = adicionalRepository.findById(id);
        if(adicional.isPresent()) {
            return new ResponseEntity<Adicional>(adicional.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PostMapping()
    public Adicional Post(@Valid @RequestBody Adicional adicional)
    {
        return adicionalRepository.save(adicional);
    }

}
