package it.mahmoud.marketingapi.service;

import it.mahmoud.marketingapi.Exception.ResourceNotFoundException;
import it.mahmoud.marketingapi.dto.SegmentoUtentiDTO;
import it.mahmoud.marketingapi.model.CampagnaMarketing;
import it.mahmoud.marketingapi.model.SegmentoUtenti;
import it.mahmoud.marketingapi.repo.CampagnaMarketingRepository;
import it.mahmoud.marketingapi.repo.SegmentoUtentiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SegmentoUtentiService {

    private final CampagnaMarketingRepository campagnaRepository;
    private final SegmentoUtentiRepository segmentoRepository;

    public SegmentoUtenti createSegmento(SegmentoUtentiDTO dto) {
        SegmentoUtenti segmento = new SegmentoUtenti();
        segmento.setNome(dto.getNome());
        segmento.setDescrizione(dto.getDescrizione());
        segmento.setCriteriSegmentazione(dto.getCriteri());

        if (dto.getCampagnaIds() != null) {
            Set<CampagnaMarketing> segmenti = campagnaRepository.findAllById(dto.getCampagnaIds()).stream().collect(Collectors.toSet());
            segmento.setCampagne(segmenti);
        }

        return segmentoRepository.save(segmento);
    }

    public SegmentoUtenti updateSegmento(Long id, SegmentoUtentiDTO dto) {

        SegmentoUtenti segmento = segmentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Segmento non trovato con ID " + id));

        segmento.setNome(dto.getNome());
        segmento.setDescrizione(dto.getDescrizione());
        segmento.setCriteriSegmentazione(dto.getCriteri());

        if (dto.getCampagnaIds() != null) {
            Set<CampagnaMarketing> segmenti = campagnaRepository.findAllById(dto.getCampagnaIds()).stream().collect(Collectors.toSet());
            segmento.setCampagne(segmenti);
        }

        return segmentoRepository.save(segmento);
    }

    public List<SegmentoUtenti> getAllSegmenti() {
        return segmentoRepository.findAll();
    }

    public SegmentoUtenti getSegmentoById(Long id){
        return segmentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Segmento non trovato con ID " + id));
    }
    public void deleteSegmento(Long id) {
        if (!segmentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Segmento non trovato con ID " + id);
        }
        segmentoRepository.deleteById(id);
    }
}
