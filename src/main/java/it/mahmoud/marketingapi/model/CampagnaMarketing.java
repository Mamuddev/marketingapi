package it.mahmoud.marketingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class CampagnaMarketing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    String nome;

    @NotNull(message = "La data di inizio è obbligatoria")
    LocalDate dataInizio;

    @NotNull(message = "La data di fine è obbligatoria")
    LocalDate dataFine;

    @DecimalMin(value = "0.0", message = "Il budget deve essere positivo")
    BigDecimal budget;

    @Enumerated(EnumType.STRING)
    @NotNull
    StatoCampagna stato;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "campagna_segmento",
            joinColumns = @JoinColumn(name = "campagna_id"),
            inverseJoinColumns = @JoinColumn(name = "segmento_id")
    )
    Set<SegmentoUtenti> segmenti = new HashSet<>();

}
