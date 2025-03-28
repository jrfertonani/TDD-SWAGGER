package Back.Cadastros.repository;

import Back.Cadastros.model.entity.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface produtoRepository extends JpaRepository<Produtos, Integer> {

}
