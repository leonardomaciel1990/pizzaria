package com.pizzaria.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pizza")
public class Pizza {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="sabor_fk", nullable = false)
	private Sabor sabor;
	
	@ManyToOne
	@JoinColumn(name="tamanho_fk", nullable = false)
	private Tamanho tamanho;
	
	//@ManyToOne
	//@JoinColumn(name="adicional_fk", nullable = true)
	//private Adicional adicional;
	
	@ManyToMany
    @JoinTable(
        name = "pizza_adicionais",
        joinColumns = @JoinColumn(name = "pizza_id"),
        inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
	private List<Adicional> adicionais;
	
	@ManyToOne
	@JoinColumn(name="pedido_fk", nullable = false)
	private Pedido pedido;
	
	@Column(name="tempo_total")
	private Float tempoTotal;
	
	@Column(name="valor_total")
	private Float valorTotal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Sabor getSabor() {
		return sabor;
	}
	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}
	public Tamanho getTamanho() {
		return tamanho;
	}
	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}
	public List<Adicional> getAdicionais() {
		return adicionais;
	}
	public void setAdicionais(List<Adicional> adicionais) {
		this.adicionais = adicionais;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Float getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(Float tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	public Float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}
}
