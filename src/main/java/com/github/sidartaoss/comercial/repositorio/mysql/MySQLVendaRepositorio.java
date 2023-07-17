package com.github.sidartaoss.comercial.repositorio.mysql;

import com.github.sidartaoss.comercial.entidade.Venda;
import com.github.sidartaoss.comercial.repositorio.PersistenciaException;
import com.github.sidartaoss.comercial.repositorio.VendaRepositorio;
import com.github.sidartaoss.comercial.servico.CadastroVendaServico;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLVendaRepositorio implements VendaRepositorio {

    private static final Logger logger = Logger.getLogger(CadastroVendaServico.class.getName());

    private final Connection conexao;

    public MySQLVendaRepositorio(Connection conexao) {
        Objects.requireNonNull(conexao);
        this.conexao = conexao;
    }

    @Override
    public Venda adicionar(Venda venda) {
        String dml = """
                insert into venda(
                    nome_cliente,
                    valor_total,
                    data_pagamento
                ) values (?, ?, ?)
                """;
        try (PreparedStatement comandoPreparado = conexao.prepareStatement(dml, Statement.RETURN_GENERATED_KEYS)) {
            comandoPreparado.setString(1, venda.getNomeCliente());
            comandoPreparado.setBigDecimal(2, venda.getValorTotal());
            comandoPreparado.setDate(3, Date.valueOf(venda.getDataPagamento()));
            comandoPreparado.executeUpdate();

            ResultSet codigoGeradoResultSet = comandoPreparado.getGeneratedKeys();
            codigoGeradoResultSet.next();
            Long codigoGerado = codigoGeradoResultSet.getLong(1);

            return new Venda(codigoGerado, venda.getNomeCliente(), venda.getValorTotal(), venda.getDataPagamento());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro de banco de dados.", e);
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Venda> consultar() {
        String query = """
            select *
            from venda
            """;
        try (Statement comando = conexao.createStatement();
             ResultSet resultado = comando.executeQuery(query)) {
            List<Venda> vendas = new ArrayList<>();
            while (resultado.next()) {
                Long id = resultado.getLong("id");
                String nomeCliente = resultado.getString("nome_cliente");
                BigDecimal valorTotal = resultado.getBigDecimal("valor_total");
                Date dataPagamento = resultado.getDate("data_pagamento");
                vendas.add(new Venda(id, nomeCliente, valorTotal, dataPagamento.toLocalDate()));
            }
            return vendas;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro de banco de dados.", e);
            throw new PersistenciaException("Erro de banco de dados.", e);
        }
    }
}
