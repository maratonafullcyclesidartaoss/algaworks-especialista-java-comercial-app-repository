package com.github.sidartaoss.comercial.servico;

import com.github.sidartaoss.comercial.entidade.Venda;
import com.github.sidartaoss.comercial.repositorio.VendaRepositorio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CadastroVendaServico {

    private final VendaRepositorio vendaRepositorio;

    public CadastroVendaServico(VendaRepositorio vendaRepositorio) {
        Objects.requireNonNull(vendaRepositorio);
        this.vendaRepositorio = vendaRepositorio;
    }

    public Venda cadastrar(String nomeCliente, BigDecimal valorTotal, LocalDate dataPagamento) {
        if (valorTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegocioException("Valor total deve ser maior que 0.");
        }
        if (dataPagamento.isAfter(LocalDate.now())) {
            throw new NegocioException("Data do pagamento não pode ser uma data futura.");
        }

        return this.vendaRepositorio
                .adicionar(new Venda(nomeCliente, valorTotal, dataPagamento));
    }

    public List<Venda> consultar() {
        return this.vendaRepositorio.consultar();
    }
}
