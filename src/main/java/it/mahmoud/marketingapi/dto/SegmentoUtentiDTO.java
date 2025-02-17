package it.mahmoud.marketingapi.dto;

import it.mahmoud.marketingapi.model.CampagnaMarketing;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SegmentoUtentiDTO {

    Long id;

    @NotBlank(message = "Il nome del segmento è obbligatorio")
    String nome;

    String descrizione;

    String criteri;

    Set<Long> campagnaIds;
}
