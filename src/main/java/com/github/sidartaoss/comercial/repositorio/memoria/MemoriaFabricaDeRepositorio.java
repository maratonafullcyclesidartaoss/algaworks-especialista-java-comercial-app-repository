package com.github.sidartaoss.comercial.repositorio.memoria;

import com.github.sidartaoss.comercial.repositorio.FabricaDeRepositorio;
import com.github.sidartaoss.comercial.repositorio.VendaRepositorio;

public class MemoriaFabricaDeRepositorio implements FabricaDeRepositorio {

    @Override
    public VendaRepositorio criarVendaRepositorio() {
        return new MemoriaVendaRepositorio();
    }

    @Override
    public void close() {
    }
}
