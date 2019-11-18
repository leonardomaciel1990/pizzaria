package com.pizzaria.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pizzaria.dto.PedidoDTO;
import com.pizzaria.entity.Adicional;
import com.pizzaria.entity.Cliente;
import com.pizzaria.entity.Pedido;
import com.pizzaria.entity.Pizza;
import com.pizzaria.entity.Sabor;
import com.pizzaria.entity.Tamanho;
import com.pizzaria.repository.AdicionalRepository;
import com.pizzaria.repository.ClienteRepository;
import com.pizzaria.repository.PedidoRepository;
import com.pizzaria.repository.PizzaRepository;
import com.pizzaria.repository.SaborRepository;
import com.pizzaria.repository.TamanhoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TamanhoRepository tamanhoRepository;
	
	@Autowired
	private SaborRepository saborRepository;
	
	@Autowired
	private AdicionalRepository adicionalRepository;
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@GetMapping()
    public List<Pedido> get() {
        return pedidoRepository.findAll();
    }
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(pedido.isPresent()) {
            return new ResponseEntity<Pedido>(pedido.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
//	@RequestMapping(value = "/pedido", method =  RequestMethod.POST)
//    public Pedido Post(@Valid @RequestBody Pedido pedido)
//    {
//        return pedidoRepository.save(pedido);
//    }

	@PostMapping()
    public String Post(
    		@RequestParam(value = "cliente", 	required=true) 	final Long idCliente,
    		@RequestParam(value = "tamanho", 	required=true) 	final Long idTamanho,
    		@RequestParam(value = "sabor", 		required=true) 	final Long idSabor,
    		@RequestParam(value = "adicional1", required=false) final Long idAdicional1,
    		@RequestParam(value = "adicional2", required=false) final Long idAdicional2,
    		@RequestParam(value = "adicional3", required=false) final Long idAdicional3
    		)
    {
		
		Pedido pedido = new Pedido();
		pedido.setPizzas(new ArrayList<Pizza>());
		Pizza pizza = new Pizza();
		pizza.setAdicionais(new ArrayList<Adicional>());
		List<Adicional> adicionais = new ArrayList<Adicional>();
		Float tempoTotal = 0f;
		Float valorTotal = 0f;
		
		if(idAdicional1 != null) {
			Optional<Adicional> adicional = adicionalRepository.findById(idAdicional1);
			if(adicional.isPresent()) {
				tempoTotal += adicional.get().getTempo();
				valorTotal += adicional.get().getValor();
				adicionais.add(adicional.get());
			}
		}
		
		if(idAdicional2 != null) {
			Optional<Adicional> adicional = adicionalRepository.findById(idAdicional2);
			if(adicional.isPresent()) {
				tempoTotal += adicional.get().getTempo();
				valorTotal += adicional.get().getValor();
				adicionais.add(adicional.get());
			}
		}
		
		if(idAdicional3 != null) {
			Optional<Adicional> adicional = adicionalRepository.findById(idAdicional3);
			if(adicional.isPresent()) {
				tempoTotal += adicional.get().getTempo();
				valorTotal += adicional.get().getValor();
				adicionais.add(adicional.get());
			}
		}
		
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		if(cliente.isPresent()) {
			pedido.setCliente(cliente.get());
		}
		
		Optional<Tamanho> tamanho = tamanhoRepository.findById(idTamanho);
		if(tamanho.isPresent()) {
			tempoTotal += tamanho.get().getTempoPreparo();
			valorTotal += tamanho.get().getValor();
			pizza.setTamanho(tamanho.get());
		}
		
		Optional<Sabor> sabor = saborRepository.findById(idSabor);
		if(sabor.isPresent()) {
			tempoTotal += sabor.get().getTempoAdicional();
			pizza.setSabor(sabor.get());
		}
		
		pizza.setAdicionais(adicionais);
		pizza.setPedido(pedido);
		pedido.getPizzas().add(pizza);
		pedido.setDataPedido(new Date());
		pedido = pedidoRepository.save(pedido);
		pizza.setPedido(pedido);
		pizzaRepository.save(pizza);
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setPedido(pedido);
		pedidoDTO.setCliente(pedido.getCliente());
		pedidoDTO.setTempoTotal(tempoTotal);
		pedidoDTO.setValorTotal(valorTotal);
		
		return pedidoDTO.toString();
    }
	
    @PutMapping(value = "/{id}")
    public ResponseEntity<Pedido> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Pedido newPedido)
    {
        Optional<Pedido> oldPedido = pedidoRepository.findById(id);
        if(oldPedido.isPresent()){
        	Pedido pedido = oldPedido.get();
        	//pizza.setNome(newPizza);
        	pedidoRepository.save(pedido);
            return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(pedido.isPresent()){
        	pedidoRepository.delete(pedido.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	

	
}
