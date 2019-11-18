package com.pizzaria.dto;

import com.pizzaria.entity.Cliente;
import com.pizzaria.entity.Pedido;
import com.pizzaria.entity.Adicional;

public class PedidoDTO {

	private Pedido pedido;
	private Cliente cliente;
	private Float tempoTotal;
	private Float valorTotal;

	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	
	@Override
	public String toString() {	
				
				String adicionais = "\n";
				if(getPedido().getPizzas().size() > 0 && getPedido().getPizzas().get(0).getAdicionais().size() > 0 ) {
					for (Adicional adicional :getPedido().getPizzas().get(0).getAdicionais()) {
						adicionais 	+= " * " + adicional.getDescricao() + " R$ " + adicional.getValor() + "\n";
					}
				}
				String resumo = "Código Pedido = " + getPedido().getId() + "\n"
				+"Cliente = " + cliente.getNome() + "\n"
				+"Tamanho = " + getPedido().getPizzas().get(0).getTamanho().getDescricao() + " R$ " + getPedido().getPizzas().get(0).getTamanho().getValor() + "\n"
				+"Sabor = " + getPedido().getPizzas().get(0).getSabor().getDescricao() + "\n"
				+"Adicionais = " + adicionais
				+"Valor Total = " + "R$ " + valorTotal + "\n"
				+"Tempo Preparo Total = " + tempoTotal +  " minutos";
				return resumo;	
	}
	
	
}
