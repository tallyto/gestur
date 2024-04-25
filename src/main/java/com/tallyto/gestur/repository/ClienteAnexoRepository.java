package com.tallyto.gestur.repository;

import com.tallyto.gestur.model.ClienteAnexo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteAnexoRepository extends JpaRepository<ClienteAnexo, Long>{

    ClienteAnexo findClienteAnexoByClienteIdAndAnexoId(Long idCliente, Long idAnexo);
}
