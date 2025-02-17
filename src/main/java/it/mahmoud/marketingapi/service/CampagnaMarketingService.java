package it.mahmoud.marketingapi.service;

import it.mahmoud.marketingapi.Exception.ResourceNotFoundException;
import it.mahmoud.marketingapi.dto.CampagnaMarketingDTO;
import it.mahmoud.marketingapi.model.CampagnaMarketing;
import it.mahmoud.marketingapi.model.SegmentoUtenti;
import it.mahmoud.marketingapi.repo.CampagnaMarketingRepository;
import it.mahmoud.marketingapi.repo.SegmentoUtentiRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampagnaMarketingService {

    private final CampagnaMarketingRepository campagnaRepository;
    private final SegmentoUtentiRepository segmentoRepository;

    public CampagnaMarketing createCampagna(CampagnaMarketingDTO dto) {
        CampagnaMarketing campagna = new CampagnaMarketing();
        campagna.setNome(dto.getNome());
        campagna.setDataInizio(dto.getDataInizio());
        campagna.setDataFine(dto.getDataFine());
        campagna.setBudget(dto.getBudget());
        campagna.setStato(dto.getStato());

        if (dto.getSegmentiIds() != null) {
            Set<SegmentoUtenti> segmenti = segmentoRepository.findAllById(dto.getSegmentiIds()).stream().collect(Collectors.toSet());
            campagna.setSegmenti(segmenti);
        }

        return campagnaRepository.save(campagna);
    }

    public List<CampagnaMarketingDTO> getAllCampagne() {

        List<CampagnaMarketing> campagna = campagnaRepository.findAll();
        return campagna.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    public CampagnaMarketing getCampagnaById(Long id) {
        return campagnaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campagna non trovata con ID " + id));
    }

    public List<CampagnaMarketing> getCampagneAttive(LocalDate date) {
        return campagnaRepository.findByDataInizioBeforeAndDataFineAfter(date, date);
    }

    @Transactional
    public void associateSegment(Long campagnaId, Long segmentoId) {
        CampagnaMarketing campagna = getCampagnaById(campagnaId);
        SegmentoUtenti segmento = segmentoRepository.findById(segmentoId)
                .orElseThrow(() -> new ResourceNotFoundException("Segmento non trovato con ID " + segmentoId));

        campagna.getSegmenti().add(segmento);
        campagnaRepository.save(campagna);
    }

    @Transactional
    public CampagnaMarketing updateCampagna(Long id, CampagnaMarketingDTO dto) {
        CampagnaMarketing campagna = campagnaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campagna non trovata con ID " + id));

        campagna.setNome(dto.getNome());
        campagna.setDataInizio(dto.getDataInizio());
        campagna.setDataFine(dto.getDataFine());
        campagna.setBudget(dto.getBudget());
        campagna.setStato(dto.getStato());

        if (dto.getSegmentiIds() != null) {
            Set<SegmentoUtenti> segmenti = segmentoRepository.findAllById(dto.getSegmentiIds())
                    .stream()
                    .collect(Collectors.toSet());
            campagna.setSegmenti(segmenti);
        }

        return campagnaRepository.save(campagna);
    }
    public void deleteCampagna(Long id) {
        if (!campagnaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Campagna non trovata con ID " + id);
        }
        campagnaRepository.deleteById(id);
    }

    private CampagnaMarketingDTO convertToDTO(CampagnaMarketing campagna) {
        CampagnaMarketingDTO dto = new CampagnaMarketingDTO();
        dto.setId(campagna.getId());
        dto.setNome(campagna.getNome());
        dto.setDataInizio(campagna.getDataInizio());
        dto.setDataFine(campagna.getDataFine());
        dto.setBudget(campagna.getBudget());
        dto.setStato(campagna.getStato());
        dto.setSegmentiIds(campagna.getSegmenti().stream()
                .map(SegmentoUtenti::getId)
                .collect(Collectors.toSet()));
        return dto;
    }
}
