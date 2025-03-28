package Back.Cadastros.produto.resource;

import Back.Cadastros.model.DTO.produtoDTO;
import Back.Cadastros.model.Enum.Categoria;
import Back.Cadastros.model.entity.Produtos;
import Back.Cadastros.resource.produtoController;
import Back.Cadastros.service.produtoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class produtoControllerTest {

    private Produtos produtos;
    private produtoDTO produtoDTO;

    public static final Integer ID   = 1;
    public static final String NOME  = "Joao";
    public static final Double PRECO = 2.99;
    public static final Categoria CATEGORIA = Categoria.valueOf("PADARIA");
    public static final int INDEX = 0;
    public static final String OBJETO_NÃO_ENCONTRADO_ID = "Objeto não encontrado ID: " + ID;

    @InjectMocks
    private produtoController controller;

    @Mock
    private ModelMapper mapper;

    @Mock
    private produtoService service;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void create() {
        when(service.create(any(produtoDTO.getClass()))).thenReturn(produtos);

        ResponseEntity<produtoDTO> response =  controller.create(produtoDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));

    }

    @Test
    void getAll() {
        when(service.findAll()).thenReturn(List.of(produtos));
        when(mapper.map(any(),any())).thenReturn(produtos);

        ResponseEntity<List<Produtos>> response = controller.getAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertTrue(response.getBody() instanceof List);
        assertEquals(Produtos.class, response.getBody().get(INDEX).getClass());
        assertEquals(Produtos.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(PRECO, response.getBody().get(INDEX).getPreco());
        assertEquals(CATEGORIA, response.getBody().get(INDEX).getCategoria());

    }

    @Test
    void getById() {
        when(service.findById(anyInt())).thenReturn(produtoDTO);
        when(mapper.map(any(), any())).thenReturn(produtoDTO);

        ResponseEntity<produtoDTO> response = controller.getById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(produtoDTO.getClass(), response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(PRECO, response.getBody().getPreco());
        assertEquals(CATEGORIA, response.getBody().getCategoria());
    }

    @Test
    void upDate() {
        when(service.upDate(any(), any())).thenReturn(produtos);
        when(mapper.map(any(),any())).thenReturn(produtos);

        ResponseEntity<produtoDTO> response = controller.upDate(ID,produtoDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produtoDTO.getClass(), response.getBody().getClass());
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
        produtos = new Produtos(ID, NOME, PRECO, CATEGORIA);
        produtoDTO = new produtoDTO(ID, NOME, PRECO, CATEGORIA);
    }

}