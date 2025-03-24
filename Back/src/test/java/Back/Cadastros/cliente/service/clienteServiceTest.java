package Back.Cadastros.cliente.service;

import Back.Cadastros.cliente.model.DTO.clienteDTO;
import Back.Cadastros.cliente.model.entity.Clientes;
import Back.Cadastros.cliente.repository.clienteRepository;
import Back._config.Exeptions.serviceExceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class clienteServiceTest {

    public static final Integer ID   = 1;
    public static final String NOME  = "Joao";
    public static final String EMAIL = "joao@joao.com";
    public static final String FONE  = "44999999999";
    public static final int INDEX = 0;
    public static final String OBJETO_NÃO_ENCONTRADO_ID = "Objeto não encontrado ID: " + ID;

    @InjectMocks
    private clienteService service;

    @Mock
    private clienteRepository repository;

    @Mock
    private ModelMapper mapper;


    private Clientes clientes;
    private clienteDTO clienteDTO;
    private Optional<Clientes> optionalCliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void create() {
        when(repository.save(any())).thenReturn(clientes);

        Clientes response = service.create(clienteDTO);

        assertNotNull(response);

        assertEquals(Clientes.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(FONE, response.getTelefone());


    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(clientes));
        when(mapper.map(clientes, Clientes.class)).thenReturn(clientes);

        List<Clientes> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Clientes.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(FONE, response.get(INDEX).getTelefone());
    }

    @Test
    void whenFindByIdThenReturnAnClienteIntance() {
        // Arrange
        Clientes clienteEntity = new Clientes();
        clienteDTO clienteDTO = new clienteDTO();

        when(repository.findById(anyInt())).thenReturn(Optional.of(clienteEntity));
        when(mapper.map(clienteEntity, clienteDTO.class)).thenReturn(clienteDTO);

        // Act
        clienteDTO response = service.findById(ID);

        // Assert
        assertNotNull(response);
        assertEquals(clienteDTO, response);
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO_ID));

        try{
            service.findById(ID);
        } catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJETO_NÃO_ENCONTRADO_ID, e.getMessage());
        }
    }

    @Test
    void upDate() {
        when(repository.findById(ID)).thenReturn(Optional.of(clientes));
        when(repository.save(any())).thenReturn(clientes);

        Clientes response = service.upDate(ID, clienteDTO);

        assertNotNull(response);

        assertEquals(Clientes.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(FONE, response.getTelefone());

    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(ID)).thenReturn(Optional.of(clientes));
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNoFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO_ID));

        try{
            service.delete(ID);
        }catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJETO_NÃO_ENCONTRADO_ID, e.getMessage());
        }

    }


    private void startCliente(){
        clientes = new Clientes(ID, NOME, EMAIL, FONE);
        clienteDTO = new clienteDTO( ID,NOME, EMAIL, FONE);
    }
}