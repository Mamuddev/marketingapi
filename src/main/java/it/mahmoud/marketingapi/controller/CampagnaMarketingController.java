package it.mahmoud.marketingapi.controller;

import it.mahmoud.marketingapi.dto.CampagnaMarketingDTO;
import it.mahmoud.marketingapi.model.CampagnaMarketing;
import it.mahmoud.marketingapi.model.SegmentoUtenti;
import it.mahmoud.marketingapi.service.CampagnaMarketingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/campagne")
@RequiredArgsConstructor
public class CampagnaMarketingController {

    private final CampagnaMarketingService campagnaService;

    @PostMapping
    public ResponseEntity<CampagnaMarketing> create(@Valid @RequestBody CampagnaMarketingDTO dto) {
        return ResponseEntity.ok(campagnaService.createCampagna(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampagnaMarketing> getById(@PathVariable Long id) {
        return ResponseEntity.ok(campagnaService.getCampagnaById(id));
    }

    @GetMapping
    public ResponseEntity<List<CampagnaMarketingDTO>> getAll() {
        return ResponseEntity.ok(campagnaService.getAllCampagne());
    }

    @GetMapping("/attive")
    public ResponseEntity<List<CampagnaMarketing>> getActiveCampaigns(@RequestParam LocalDate date) {
        return ResponseEntity.ok(campagnaService.getCampagneAttive(date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampagnaMarketing> update(@PathVariable Long id, @Valid @RequestBody CampagnaMarketingDTO dto) {
        return ResponseEntity.ok(campagnaService.updateCampagna(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        campagnaService.deleteCampagna(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{campagnaId}/segmento/{segmentoId}")
    public ResponseEntity<String> associateSegment(@PathVariable Long campagnaId, @PathVariable Long segmentoId) {
        campagnaService.associateSegment(campagnaId, segmentoId);
        return ResponseEntity.ok("Segmento associato con successo!");
    }


}
