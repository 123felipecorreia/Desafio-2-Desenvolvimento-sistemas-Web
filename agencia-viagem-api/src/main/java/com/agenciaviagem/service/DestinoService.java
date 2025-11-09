package com.agenciaviagem.service;

import com.agenciaviagem.dto.AvaliacaoRequestDTO;
import com.agenciaviagem.dto.DestinoRequestDTO;
import com.agenciaviagem.dto.DestinoResponseDTO;
import com.agenciaviagem.model.Destino;
import com.agenciaviagem.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    /**
     * Cadastrar novo destino
     */
    @Transactional
    public DestinoResponseDTO cadastrarDestino(DestinoRequestDTO requestDTO) {
        Destino destino = new Destino();
        destino.setNome(requestDTO.getNome());
        destino.setLocalizacao(requestDTO.getLocalizacao());
        destino.setDescricao(requestDTO.getDescricao());
        destino.setPreco(requestDTO.getPreco());
        destino.setAtracoesTuristicas(requestDTO.getAtracoesTuristicas());
        destino.setTotalAvaliacoes(0);

        Destino destinoSalvo = destinoRepository.save(destino);
        return convertToDTO(destinoSalvo);
    }

    /**
     * Listar todos os destinos
     */
    public List<DestinoResponseDTO> listarTodos() {
        return destinoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Buscar destino por ID
     */
    public DestinoResponseDTO buscarPorId(Long id) {
        Destino destino = destinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado com ID: " + id));
        return convertToDTO(destino);
    }

    /**
     * Pesquisar destinos por nome ou localização
     */
    public List<DestinoResponseDTO> pesquisarDestinos(String termo) {
        List<Destino> destinos;
        
        if (termo == null || termo.trim().isEmpty()) {
            destinos = destinoRepository.findAll();
        } else {
            destinos = destinoRepository.pesquisarPorNomeOuLocalizacao(termo);
        }
        
        return destinos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Avaliar destino (adiciona nova avaliação e recalcula média)
     */
    @Transactional
    public DestinoResponseDTO avaliarDestino(Long id, AvaliacaoRequestDTO avaliacaoDTO) {
        Destino destino = destinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado com ID: " + id));

        // Calcula nova média ponderada
        BigDecimal somaAvaliacoes;
        if (destino.getAvaliacaoMedia() != null) {
            somaAvaliacoes = destino.getAvaliacaoMedia()
                    .multiply(BigDecimal.valueOf(destino.getTotalAvaliacoes()));
        } else {
            somaAvaliacoes = BigDecimal.ZERO;
        }

        somaAvaliacoes = somaAvaliacoes.add(BigDecimal.valueOf(avaliacaoDTO.getNota()));
        int novoTotal = destino.getTotalAvaliacoes() + 1;
        
        BigDecimal novaMedia = somaAvaliacoes.divide(
                BigDecimal.valueOf(novoTotal), 
                2, 
                RoundingMode.HALF_UP
        );

        destino.setAvaliacaoMedia(novaMedia);
        destino.setTotalAvaliacoes(novoTotal);

        Destino destinoAtualizado = destinoRepository.save(destino);
        return convertToDTO(destinoAtualizado);
    }

    /**
     * Excluir destino
     */
    @Transactional
    public void excluirDestino(Long id) {
        if (!destinoRepository.existsById(id)) {
            throw new RuntimeException("Destino não encontrado com ID: " + id);
        }
        destinoRepository.deleteById(id);
    }

    /**
     * Converte entidade para DTO
     */
    private DestinoResponseDTO convertToDTO(Destino destino) {
        DestinoResponseDTO dto = new DestinoResponseDTO();
        dto.setId(destino.getId());
        dto.setNome(destino.getNome());
        dto.setLocalizacao(destino.getLocalizacao());
        dto.setDescricao(destino.getDescricao());
        dto.setPreco(destino.getPreco());
        dto.setAtracoesTuristicas(destino.getAtracoesTuristicas());
        dto.setAvaliacaoMedia(destino.getAvaliacaoMedia());
        dto.setTotalAvaliacoes(destino.getTotalAvaliacoes());
        dto.setDataCriacao(destino.getDataCriacao());
        dto.setDataAtualizacao(destino.getDataAtualizacao());
        return dto;
    }
}
