package it.mahmoud.marketingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class SegmentoUtenti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    String nome;

    String descrizione;

    String criteriSegmentazione;

    @ManyToMany(mappedBy = "segmenti", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<CampagnaMarketing> campagne = new HashSet<>();
}
