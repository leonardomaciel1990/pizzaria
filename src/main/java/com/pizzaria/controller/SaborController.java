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
import com.pizzaria.entity.Sabor;
import com.pizzaria.repository.SaborRepository;

@RestController
@RequestMapping("/sabor")
public class SaborController {

	@Autowired
	private SaborRepository saborRepository;
	
	@GetMapping
    public List<Sabor> get() {
        return saborRepository.findAll();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Sabor> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Sabor> sabor = saborRepository.findById(id);
        if(sabor.isPresent()) {
            return new ResponseEntity<Sabor>(sabor.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PostMapping()
    public Sabor Post(@Valid @RequestBody Sabor sabor)
    {
        return saborRepository.save(sabor);
    }

	
}
