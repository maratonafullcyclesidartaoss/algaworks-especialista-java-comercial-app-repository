package com.github.sidartaoss.comercial;

import com.github.sidartaoss.comercial.entidade.Venda;
import com.github.sidartaoss.comercial.repositorio.FabricaDeRepositorio;
import com.github.sidartaoss.comercial.repositorio.memoria.MemoriaFabricaDeRepositorio;
import com.github.sidartaoss.comercial.repositorio.mysql.MySQLFabricaDeRepositorio;
import com.github.sidartaoss.comercial.servico.CadastroVendaServico;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) throws SQLException {
        try (var fabricaDeRepositorio = FabricaDeRepositorio.obterInstancia();) {
            var cadastroVendaServico = new CadastroVendaServico(
                    fabricaDeRepositorio.criarVendaRepositorio());
            Venda vendaCadastrada = cadastroVendaServico.cadastrar("Sinovaldo Pereira", BigDecimal.valueOf(7899.99),
                    LocalDate.parse("2023-06-03"));

            System.out.println("Venda cadastrada: " + vendaCadastrada);

            System.out.println("Listando todas as vendas: ");
            var todasVendas = cadastroVendaServico.consultar();
            todasVendas.forEach(System.out::println);
        }
    }
}
