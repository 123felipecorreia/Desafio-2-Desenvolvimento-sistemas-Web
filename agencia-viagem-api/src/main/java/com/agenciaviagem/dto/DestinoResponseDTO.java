package com.agenciaviagem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DestinoResponseDTO {

    private Long id;
    private String nome;
    private String localizacao;
    private String descricao;
    private BigDecimal preco;
    private String atracoesTuristicas;
    private BigDecimal avaliacaoMedia;
    private Integer totalAvaliacoes;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
