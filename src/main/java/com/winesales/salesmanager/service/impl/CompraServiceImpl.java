package com.winesales.salesmanager.service.impl;

import com.winesales.salesmanager.domain.Cliente;
import com.winesales.salesmanager.domain.Compra;
import com.winesales.salesmanager.dto.*;
import com.winesales.salesmanager.repository.ClienteRepository;
import com.winesales.salesmanager.repository.CompraRepository;
import com.winesales.salesmanager.repository.ProdutoRepository;
import com.winesales.salesmanager.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public CompraServiceImpl(CompraRepository compraRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<ClienteComprasDTO> findAllSortedByValor() {
        Map<Cliente, List<Compra>> comprasPorCliente = compraRepository.findAllByOrderByValorTotalAsc().stream()
                .collect(Collectors.groupingBy(Compra::getCliente));

        return comprasPorCliente.entrySet().stream()
                .map(entry -> {
                    Cliente cliente = entry.getKey();
                    List<CompraDTO> comprasDTO = entry.getValue().stream()
                            .map(this::toCompraDto)
                            .collect(Collectors.toList());

                    BigDecimal valorTotalCliente = comprasDTO.stream()
                            .map(CompraDTO::getValorTotalCompra)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    ClienteComprasDTO clienteComprasDTO = new ClienteComprasDTO();
                    clienteComprasDTO.setNome(cliente.getNome());
                    clienteComprasDTO.setCpf(cliente.getCpf());
                    clienteComprasDTO.setCompras(comprasDTO);
                    clienteComprasDTO.setValorTotal(valorTotalCliente);

                    return clienteComprasDTO;
                })
                .sorted(Comparator.comparing(ClienteComprasDTO::getValorTotal))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteComprasDTO findMaiorCompraPorAno(int ano) {
        return compraRepository.findTopCompraByAnoOrderByValorTotalDesc(ano)
                .stream()
                .findFirst()
                .map(maiorCompra -> {
                    ClienteComprasDTO clienteComprasDTO = new ClienteComprasDTO();
                    clienteComprasDTO.setNome(maiorCompra.getCliente().getNome());
                    clienteComprasDTO.setCpf(maiorCompra.getCliente().getCpf());

                    CompraDTO compraDTO = toCompraDto(maiorCompra);
                    clienteComprasDTO.setCompras(Collections.singletonList(compraDTO));
                    clienteComprasDTO.setValorTotal(compraDTO.getValorTotalCompra());

                    return clienteComprasDTO;
                })
                .orElse(null);
    }

    @Override
    public List<ClienteDTO> findTop3ClientesFieis() {
        Pageable topThree = PageRequest.of(0, 3);
        return compraRepository.findTopClientesByCompras(topThree).stream()
                .map(this::toClienteDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteRecomendacaoDTO> findRecomendacaoPorClienteETipo() {
        return compraRepository.findAll().stream()
                .collect(Collectors.groupingBy(Compra::getCliente))
                .entrySet().stream()
                .map(entry -> {
                    Cliente cliente = entry.getKey();
                    Compra compraMaisComprada = entry.getValue().stream()
                            .max(Comparator.comparingInt(Compra::getQuantidade))
                            .orElse(null);

                    if (compraMaisComprada != null) {
                        ClienteRecomendacaoDTO dto = new ClienteRecomendacaoDTO();
                        dto.setNome(cliente.getNome());
                        dto.setCpf(cliente.getCpf());
                        dto.setTipoVinhoMaisComprado(compraMaisComprada.getProduto().getTipoVinho());
                        dto.setSafra(compraMaisComprada.getProduto().getSafra());
                        return dto;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private CompraDTO toCompraDto(Compra compra) {
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setProdutoId(compra.getProduto().getCodigo());
        compraDTO.setProdutoNome(compra.getProduto().getTipoVinho());
        compraDTO.setProdutoPreco(compra.getProduto().getPreco());
        compraDTO.setQuantidade(compra.getQuantidade());
        BigDecimal valorTotalCompra = compra.getProduto().getPreco().multiply(BigDecimal.valueOf(compra.getQuantidade()));
        compraDTO.setValorTotalCompra(valorTotalCompra);
        return compraDTO;
    }

    private ClienteDTO toClienteDto(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setCompras(cliente.getCompras().stream().map(this::toCompraDto).collect(Collectors.toList()));
        return clienteDTO;
    }
}