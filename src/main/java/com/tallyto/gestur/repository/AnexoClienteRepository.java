package com.tallyto.gestur.repository;

import com.tallyto.gestur.model.AnexoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnexoClienteRepository extends JpaRepository<AnexoCliente, Long>{

    AnexoCliente findAnexoClienteByClienteIdAndAnexoId(Long clienteId, Long anexoId);
}
