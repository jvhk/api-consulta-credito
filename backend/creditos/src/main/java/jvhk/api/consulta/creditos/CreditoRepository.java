package jvhk.api.consulta.creditos;

import jvhk.api.consulta.creditos.entities.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {

    @Query("SELECT creditos FROM Credito creditos WHERE creditos.numeroNfse = :numeroNfse ")
    List<Credito> findAllCreditosByNumeroNfse(@Param("numeroNfse") String numeroNfse);

    @Query("SELECT credito FROM Credito credito WHERE credito.numeroCredito = :numeroCredito")
    Optional<Credito> findByNumeroCredito(@Param("numeroCredito") String numeroCredito);

}
