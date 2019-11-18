package com.pizzaria.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pizzaria.entity.Pizza;
import com.pizzaria.repository.PizzaRepository;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

	@Autowired
	private PizzaRepository pizzaRepository;
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Pizza> get() {
        return pizzaRepository.findAll();
    }
	
	@RequestMapping(value = "/pizza/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if(pizza.isPresent()) {
            return new ResponseEntity<Pizza>(pizza.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@RequestMapping(value = "/pizza", method =  RequestMethod.POST)
    public Pizza Post(@Valid @RequestBody Pizza pizza)
    {
        return pizzaRepository.save(pizza);
    }

    @RequestMapping(value = "/pizza/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Pizza> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Pizza newPizza)
    {
        Optional<Pizza> oldPizza = pizzaRepository.findById(id);
        if(oldPizza.isPresent()){
        	Pizza pizza = oldPizza.get();
        	//pizza.setNome(newPizza.);
        	pizzaRepository.save(pizza);
            return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pizza/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if(pizza.isPresent()){
        	pizzaRepository.delete(pizza.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
}
