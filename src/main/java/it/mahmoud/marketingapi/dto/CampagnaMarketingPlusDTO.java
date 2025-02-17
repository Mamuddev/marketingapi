package it.mahmoud.marketingapi.dto;

import it.mahmoud.marketingapi.model.StatoCampagna;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


@Data
public class CampagnaMarketingPlusDTO {

    Long id;

    @NotBlank(message = "Il nome della campagna è oblligatoria")
    String nome;

    @NotNull(message = "La data di inizio è obbligatoria")
    LocalDate dataInizio;

    @NotNull(message = "La data di fine è obbligatoria")
    LocalDate dataFine;

    @NotNull(message = "Il budget è obbligatorio")
    BigDecimal budget;

    @NotNull(message = "Lo stato è obbligatorio")
    StatoCampagna stato;

    Set<SegmentoUtentiDTO> segmenti;

}
