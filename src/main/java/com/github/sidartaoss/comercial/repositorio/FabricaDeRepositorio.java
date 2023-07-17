package com.github.sidartaoss.comercial.repositorio;

import com.github.sidartaoss.comercial.repositorio.memoria.MemoriaFabricaDeRepositorio;
import com.github.sidartaoss.comercial.repositorio.mysql.MySQLFabricaDeRepositorio;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface FabricaDeRepositorio extends AutoCloseable {

    static FabricaDeRepositorio obterInstancia() {
        Properties properties = new Properties();
        try (InputStream inputStream = FabricaDeRepositorio.class.getResourceAsStream("/persistencia.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new PersistenciaException("Erro carregando configurações.", e);
        }
        if ("mysql".equals(properties.getProperty("repositorio"))) {
            return new MySQLFabricaDeRepositorio(properties);
        } else if ("memoria".equals(properties.getProperty("repositorio"))) {
            return new MemoriaFabricaDeRepositorio();
        }
        throw new PersistenciaException("Implementação de repositório não existe");
    }

    VendaRepositorio criarVendaRepositorio();

    @Override
    void close();
}
