package com.github.sidartaoss.comercial.repositorio;

import com.github.sidartaoss.comercial.entidade.Venda;

import java.util.List;

public interface VendaRepositorio {
    Venda adicionar(Venda venda);

    List<Venda> consultar();
}
