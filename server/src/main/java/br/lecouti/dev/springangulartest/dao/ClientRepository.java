package br.lecouti.dev.springangulartest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.lecouti.dev.springangulartest.model.Client;

/**
 * Client repository
 * 
 * @author Leandro Coutinho
 */
@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

}
