package com.agenciaviagem.controller;

import com.agenciaviagem.dto.AvaliacaoRequestDTO;
import com.agenciaviagem.dto.DestinoRequestDTO;
import com.agenciaviagem.dto.DestinoResponseDTO;
import com.agenciaviagem.service.DestinoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/destinos")
@CrossOrigin(origins = "*")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    /**
     * POST /api/destinos - Cadastrar novo destino
     */
    @PostMapping
    public ResponseEntity<DestinoResponseDTO> cadastrarDestino(
            @Valid @RequestBody DestinoRequestDTO requestDTO) {
        
        DestinoResponseDTO response = destinoService.cadastrarDestino(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET /api/destinos - Listar todos os destinos
     */
    @GetMapping
    public ResponseEntity<List<DestinoResponseDTO>> listarDestinos() {
        List<DestinoResponseDTO> destinos = destinoService.listarTodos();
        return ResponseEntity.ok(destinos);
    }

    /**
     * GET /api/destinos/{id} - Visualizar detalhes de um destino
     */
    @GetMapping("/{id}")
    public ResponseEntity<DestinoResponseDTO> buscarDestinoPorId(@PathVariable Long id) {
        try {
            DestinoResponseDTO destino = destinoService.buscarPorId(id);
            return ResponseEntity.ok(destino);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * GET /api/destinos/pesquisa?termo=... - Pesquisar por nome ou localização
     */
    @GetMapping("/pesquisa")
    public ResponseEntity<List<DestinoResponseDTO>> pesquisarDestinos(
            @RequestParam(required = false) String termo) {
        
        List<DestinoResponseDTO> destinos = destinoService.pesquisarDestinos(termo);
        return ResponseEntity.ok(destinos);
    }

    /**
     * PATCH /api/destinos/{id}/avaliar - Avaliar destino
     */
    @PatchMapping("/{id}/avaliar")
    public ResponseEntity<DestinoResponseDTO> avaliarDestino(
            @PathVariable Long id,
            @Valid @RequestBody AvaliacaoRequestDTO avaliacaoDTO) {
        
        try {
            DestinoResponseDTO destino = destinoService.avaliarDestino(id, avaliacaoDTO);
            return ResponseEntity.ok(destino);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * DELETE /api/destinos/{id} - Excluir destino
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> excluirDestino(@PathVariable Long id) {
        try {
            destinoService.excluirDestino(id);
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Destino excluído com sucesso");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Tratamento global de exceções de validação
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("erro", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
