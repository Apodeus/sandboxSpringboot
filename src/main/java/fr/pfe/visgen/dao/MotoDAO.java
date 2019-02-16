package fr.pfe.visgen.dao;

import fr.pfe.visgen.pojo.Moto;
import org.springframework.data.repository.CrudRepository;

public interface MotoDAO extends CrudRepository<Moto, String> {

    @Override
    Iterable<Moto> findAll();

    @Override
    <S extends Moto> S save(S s);
}
