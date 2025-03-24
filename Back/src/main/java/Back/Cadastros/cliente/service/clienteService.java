package Back.Cadastros.cliente.service;

import Back.Cadastros.cliente.model.DTO.clienteDTO;
import Back.Cadastros.cliente.model.entity.Clientes;
import Back.Cadastros.cliente.repository.clienteRepository;
import Back._config.Exeptions.serviceExceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class clienteService {

    @Autowired
    public ModelMapper mapper;

    @Autowired
    public clienteRepository repository;


    public Clientes create(clienteDTO DTO){
        return repository.save(
                mapper.map(DTO, Clientes.class)
        );
    }

    public List<Clientes> findAll(){
        return repository.findAll() .stream().map(x -> mapper.map(
                x, Clientes.class)
        ).toList();
    }

    public clienteDTO findById(Integer id){
        return mapper.map(
                repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado ID: " + id)
        ), clienteDTO.class);
    }

    public Clientes upDate(Integer id, clienteDTO DTO){
        findById(id);
        return repository.save(
                mapper.map(DTO, Clientes.class)
        );
    }

    public void delete(Integer id){
        findById(id);
        repository.deleteById(id);
    }
}
