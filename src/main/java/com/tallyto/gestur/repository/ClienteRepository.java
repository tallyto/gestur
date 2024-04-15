package com.tallyto.gestur.repository;

import com.tallyto.gestur.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aqui você pode adicionar métodos de busca personalizados, se necessário
}
