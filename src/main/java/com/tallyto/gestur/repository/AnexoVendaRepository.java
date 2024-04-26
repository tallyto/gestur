package com.tallyto.gestur.repository;

import com.tallyto.gestur.model.AnexoVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnexoVendaRepository extends JpaRepository<AnexoVenda, Long> {

    public AnexoVenda findAnexoVendaByVendaIdAndAnexoId(Long vendaId, Long anexoId);
}
