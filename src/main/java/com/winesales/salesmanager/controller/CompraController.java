package com.winesales.salesmanager.controller;

import com.winesales.salesmanager.dto.*;
import com.winesales.salesmanager.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompraController {

    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping("/compras")
    public ResponseEntity<List<ClienteComprasDTO>> getCompras() {
        List<ClienteComprasDTO> compras = compraService.findAllSortedByValor();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/maior-compra/{ano}")
    public ResponseEntity<ClienteComprasDTO> getMaiorCompraPorAno(@PathVariable int ano) {
        ClienteComprasDTO compra = compraService.findMaiorCompraPorAno(ano);
        if (compra != null) {
            return ResponseEntity.ok(compra);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clientes-fieis")
    public ResponseEntity<List<ClienteDTO>> getClientesFieis() {
        List<ClienteDTO> clientesFieis = compraService.findTop3ClientesFieis();
        return ResponseEntity.ok(clientesFieis);
    }

    @GetMapping("/recomendacao/cliente/tipo")
    public ResponseEntity<List<ClienteRecomendacaoDTO>> getRecomendacaoPorClienteETipo() {
        List<ClienteRecomendacaoDTO> recomendacoes = compraService.findRecomendacaoPorClienteETipo();
        return ResponseEntity.ok(recomendacoes);
    }
}
