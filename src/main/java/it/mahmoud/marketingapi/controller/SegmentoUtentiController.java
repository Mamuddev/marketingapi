package it.mahmoud.marketingapi.controller;

import it.mahmoud.marketingapi.dto.SegmentoUtentiDTO;
import it.mahmoud.marketingapi.model.SegmentoUtenti;
import it.mahmoud.marketingapi.service.SegmentoUtentiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/segmenti")
@RequiredArgsConstructor
public class SegmentoUtentiController {

    private final SegmentoUtentiService segmentoService;

    @PostMapping
    public ResponseEntity<SegmentoUtenti> create(@Valid @RequestBody SegmentoUtentiDTO dto) {
        return ResponseEntity.ok(segmentoService.createSegmento(dto));
    }

    @GetMapping
    public ResponseEntity<List<SegmentoUtenti>> getAll() {
        return ResponseEntity.ok(segmentoService.getAllSegmenti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SegmentoUtenti> getById(@PathVariable Long id) {
        return ResponseEntity.ok(segmentoService.getSegmentoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SegmentoUtenti> update(@PathVariable Long id, @Valid @RequestBody SegmentoUtentiDTO dto) {
        return ResponseEntity.ok(segmentoService.updateSegmento(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        segmentoService.deleteSegmento(id);
        return ResponseEntity.noContent().build();
    }

}
