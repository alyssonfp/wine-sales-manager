package com.winesales.salesmanager.service;

import com.winesales.salesmanager.dto.*;

import java.util.List;

public interface CompraService {
    List<ClienteComprasDTO> findAllSortedByValor();
    ClienteComprasDTO  findMaiorCompraPorAno(int ano);
    List<ClienteDTO> findTop3ClientesFieis();
    List<ClienteRecomendacaoDTO> findRecomendacaoPorClienteETipo();
}
