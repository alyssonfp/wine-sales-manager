package com.winesales.salesmanager.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winesales.salesmanager.domain.Cliente;
import com.winesales.salesmanager.domain.Compra;
import com.winesales.salesmanager.domain.Produto;
import com.winesales.salesmanager.repository.ClienteRepository;
import com.winesales.salesmanager.repository.CompraRepository;
import com.winesales.salesmanager.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataInitializerService implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final CompraRepository compraRepository;

    @Autowired
    public DataInitializerService(ProdutoRepository produtoRepository,
                                  ClienteRepository clienteRepository,
                                  CompraRepository compraRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.compraRepository = compraRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();

        String produtosJsonUrl = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json";
        String produtosJson = restTemplate.getForObject(produtosJsonUrl, String.class);
        List<Produto> produtos = objectMapper.readValue(produtosJson, new TypeReference<List<Produto>>() {});
        produtoRepository.saveAll(produtos);

        String clientesComprasJsonUrl = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json";
        String clientesComprasJson = restTemplate.getForObject(clientesComprasJsonUrl, String.class);
        List<Cliente> clientes = objectMapper.readValue(clientesComprasJson, new TypeReference<List<Cliente>>() {});

        Map<Long, Produto> produtoMap = produtos.stream().collect(Collectors.toMap(Produto::getCodigo, produto -> produto));

        for (Cliente cliente : clientes) {
            for (Compra compra : cliente.getCompras()) {
                Produto produto = produtoMap.get(compra.getCodigoProduto());
                if (produto != null) {
                    compra.setProduto(produto);
                } else {
                    System.err.println("Produto não encontrado para o código: " + compra.getCodigoProduto());
                }
                compra.setCliente(cliente);
            }
        }

        clienteRepository.saveAll(clientes);
        compraRepository.saveAll(clientes.stream().flatMap(c -> c.getCompras().stream()).collect(Collectors.toList()));

        System.out.println("Dados de produtos e clientes inicializados com sucesso.");
    }
}
