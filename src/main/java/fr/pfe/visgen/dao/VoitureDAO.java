package fr.pfe.visgen.dao;

import fr.pfe.visgen.pojo.Voiture;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoitureDAO extends CrudRepository<Voiture, Long> {

    @Override
    List<Voiture> findAll();

    List<Voiture> findVoituresByPriceLessThanEqual(int prix);

    @Override
    Optional<Voiture> findById(Long integer);

    @Override
    <S extends Voiture> S save(S s);

    @Transactional
    void deleteVoituresByTimeBefore(Timestamp t);
}
