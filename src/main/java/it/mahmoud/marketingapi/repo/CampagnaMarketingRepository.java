package it.mahmoud.marketingapi.repo;

import it.mahmoud.marketingapi.model.CampagnaMarketing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;

public interface CampagnaMarketingRepository extends JpaRepository<CampagnaMarketing, Long> {

    List<CampagnaMarketing> findByDataInizioBeforeAndDataFineAfter(LocalDate inizio, LocalDate fine);

}
