package Back.Cadastros.cliente.resource;

import Back.Cadastros.cliente.model.DTO.clienteDTO;
import Back.Cadastros.cliente.model.entity.Clientes;
import Back.Cadastros.cliente.service.clienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.xmlunit.util.Mapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class clienteControllerTest {

    private Clientes clientes;
    private clienteDTO clienteDTO;


    public static final Integer ID   = 1;
    public static final String NOME  = "Joao";
    public static final String EMAIL = "joao@joao.com";
    public static final String FONE  = "44999999999";
    public static final int INDEX = 0;
    public static final String OBJETO_NÃO_ENCONTRADO_ID = "Objeto não encontrado ID: " + ID;

    @InjectMocks
    private clienteController controller;

    @Mock
    private ModelMapper mapper;

    @Mock
    private clienteService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void create() {
        when(service.create(any(clienteDTO.getClass()))).thenReturn(clientes);

        ResponseEntity<clienteDTO> response =  controller.create(clienteDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));

    }

    @Test
    void findAll() {
        when(service.findAll()).thenReturn(List.of(clientes));
        when(mapper.map(any(),any())).thenReturn(clienteDTO);

        ResponseEntity<List<Clientes>> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertTrue(response.getBody() instanceof List);
        assertEquals(Clientes.class, response.getBody().get(INDEX).getClass());
        assertEquals(Clientes.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(FONE, response.getBody().get(INDEX).getTelefone());


    }

    @Test
    void findByIdSuccess() {
        when(service.findById(anyInt())).thenReturn(clienteDTO);
        when(mapper.map(any(),any())).thenReturn(clienteDTO);

        ResponseEntity<clienteDTO> response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(clienteDTO.getClass(), response.getBody().getClass());

        assertEquals(ID, response.getBody().getId(ID));
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(FONE, response.getBody().getTelefone());

    }


    @Test
    void upDate() {
        when(service.upDate(any(),any())).thenReturn(clientes);
        when(mapper.map(any(),any())).thenReturn(clientes);

        ResponseEntity<clienteDTO> response =  controller.upDate(ID,clienteDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDTO.getClass(), response.getBody().getClass());

        assertEquals(ID, response.getBody().getId(ID));
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(FONE, response.getBody().getTelefone());
    }

    @Test
    void delete() {
        doNothing().when(service).delete(anyInt());

        ResponseEntity<Void> response = controller.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).delete(anyInt());
    }

    private void startCliente(){
        clientes = new Clientes(ID, NOME, EMAIL, FONE);
        clienteDTO = new clienteDTO( ID,NOME, EMAIL, FONE);
    }
}