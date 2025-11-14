package com.agenciaviagem.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoRequestDTO {

    @NotNull(message = "Nota é obrigatória")
    @Min(value = 1, message = "Nota mínima é 1")
    @Max(value = 10, message = "Nota máxima é 10")
    private Integer nota;
}
