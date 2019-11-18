package com.pizzaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pizzaria.entity.Adicional;
import com.pizzaria.entity.Cliente;
import com.pizzaria.entity.Sabor;
import com.pizzaria.entity.Tamanho;
import com.pizzaria.repository.AdicionalRepository;
import com.pizzaria.repository.ClienteRepository;
import com.pizzaria.repository.SaborRepository;
import com.pizzaria.repository.TamanhoRepository;

@SpringBootApplication
public class PizzariaApplication {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private SaborRepository saborRepository;
	
	@Autowired
	private TamanhoRepository tamanhoRepository;
	
	@Autowired
	private AdicionalRepository adicionalRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PizzariaApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
        	
        	Cliente cliente = new Cliente();
        	cliente.setNome("Leonardo");
        	clienteRepository.save(cliente);
        	
        	Sabor saborCalabresa = new Sabor();
        	saborCalabresa.setDescricao("Calabresa");
        	saborCalabresa.setTempoAdicional(0f);
        	saborRepository.save(saborCalabresa);
        	
        	Sabor saborMarguerita = new Sabor();
        	saborMarguerita.setDescricao("Marguerita");
        	saborMarguerita.setTempoAdicional(0f);
        	saborRepository.save(saborMarguerita);
        	
        	Sabor saborPortuguesa = new Sabor();
        	saborPortuguesa.setDescricao("Portuguesa");
        	saborPortuguesa.setTempoAdicional(5f);
        	saborRepository.save(saborPortuguesa);
        	
        	Tamanho tamanhoPequena = new Tamanho();
        	tamanhoPequena.setDescricao("Pequena");
        	tamanhoPequena.setTempoPreparo(15f);
        	tamanhoPequena.setValor(20f);
        	tamanhoRepository.save(tamanhoPequena);
        	
        	Tamanho tamanhoMedia = new Tamanho();
        	tamanhoMedia.setDescricao("Média");
        	tamanhoMedia.setTempoPreparo(20f);
        	tamanhoMedia.setValor(30f);
        	tamanhoRepository.save(tamanhoMedia);
        	
        	Tamanho tamanhoGrande = new Tamanho();
        	tamanhoGrande.setDescricao("Grande");
        	tamanhoGrande.setTempoPreparo(25f);
        	tamanhoGrande.setValor(40f);
        	tamanhoRepository.save(tamanhoGrande);
        	
        	Adicional adicionalExtraBacon = new Adicional();
        	adicionalExtraBacon.setDescricao("Extra Bacon");
        	adicionalExtraBacon.setValor(3f);
        	adicionalExtraBacon.setTempo(0f);
        	adicionalRepository.save(adicionalExtraBacon);
        	
        	Adicional adicionalSemCebola = new Adicional();
        	adicionalSemCebola.setDescricao("Sem Cebola");
        	adicionalSemCebola.setTempo(0f);
        	adicionalSemCebola.setValor(0f);
        	adicionalRepository.save(adicionalSemCebola);
        	
        	Adicional adicionalBordaRecheada = new Adicional();
        	adicionalBordaRecheada.setDescricao("Borda Recheada");
        	adicionalBordaRecheada.setValor(5f);
        	adicionalBordaRecheada.setTempo(5f);
        	adicionalRepository.save(adicionalBordaRecheada);
        	
        };
	}

}
