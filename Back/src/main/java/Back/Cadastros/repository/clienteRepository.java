package Back.Cadastros.repository;

import Back.Cadastros.model.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface clienteRepository extends JpaRepository<Clientes, Integer> {

}
