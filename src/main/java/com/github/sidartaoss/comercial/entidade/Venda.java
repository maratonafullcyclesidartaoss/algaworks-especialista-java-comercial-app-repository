package com.github.sidartaoss.comercial.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Venda {

    private Long id;
    private String nomeCliente;
    private BigDecimal valorTotal;
    private LocalDate dataPagamento;

    public Venda(String nomeCliente, BigDecimal valorTotal, LocalDate dataPagamento) {
        if (nomeCliente == null || nomeCliente.isBlank()) {
            throw new IllegalArgumentException("Informe o nome do cliente.");
        }
        Objects.requireNonNull(valorTotal);
        Objects.requireNonNull(dataPagamento);
        this.nomeCliente = nomeCliente;
        this.valorTotal = valorTotal;
        this.dataPagamento = dataPagamento;
    }

    public Venda(Long id, String nomeCliente, BigDecimal valorTotal, LocalDate dataPagamento) {
        this(nomeCliente, valorTotal, dataPagamento);
        Objects.requireNonNull(id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(id, venda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", valorTotal=" + valorTotal +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
