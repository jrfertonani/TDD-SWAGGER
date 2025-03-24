package Back.Cadastros.cliente.service;

import Back.Cadastros.cliente.model.DTO.clienteDTO;
import Back.Cadastros.cliente.model.entity.Clientes;
import Back.Cadastros.cliente.repository.clienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class clienteServiceTest {

    public static final Integer ID   = 1;
    public static final String NOME  = "Joao";
    public static final String EMAIL = "joao@joao.com";
    public static final String FONE  = "44999999999";

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
    }

    @Test
    void findAll() {
    }

    @Test
    void whenFindByIdThenReturnAnClienteIntance() {
        // Arrange
        Clientes clienteEntity = new Clientes();
        clienteDTO clienteDTO = new clienteDTO();

        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(clienteEntity));
        when(mapper.map(clienteEntity, clienteDTO.class)).thenReturn(clienteDTO);

        // Act
        clienteDTO response = service.findById(ID);

        // Assert
        assertNotNull(response);
        assertEquals(clienteDTO, response);

    }

    @Test
    void upDate() {
    }

    @Test
    void delete() {
    }


    private void startCliente(){
        clientes = new Clientes(ID, NOME, EMAIL, FONE);
        clienteDTO = new clienteDTO( NOME, EMAIL, FONE);
    }
}