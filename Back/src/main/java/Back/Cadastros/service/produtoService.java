package Back.Cadastros.service;

import Back.Cadastros.model.DTO.produtoDTO;
import Back.Cadastros.model.entity.Produtos;
import Back.Cadastros.repository.produtoRepository;
import Back._config.Exeptions.serviceExceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class produtoService {

    @Autowired
    public ModelMapper mapper;

    @Autowired
    public produtoRepository repository;


    public Produtos create(produtoDTO DTO){
        return repository.save(
                mapper.map(DTO, Produtos.class)
        );
    }

    public List<Produtos> findAll(){
        return repository.findAll() .stream().map(x -> mapper.map(
                x, Produtos.class)
        ).toList();
    }

    public produtoDTO findById(Integer id){
        return mapper.map(
                repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado ID: " + id)
        ), produtoDTO.class);
    }

    public Produtos upDate(Integer id, produtoDTO DTO){
        findById(id);
        return repository.save(
                mapper.map(DTO, Produtos.class)
        );
    }

    public void delete(Integer id){
        findById(id);
        repository.deleteById(id);
    }
}
