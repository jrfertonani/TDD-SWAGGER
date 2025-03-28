package Back.Cadastros.produto.service;

import Back.Cadastros.model.DTO.produtoDTO;
import Back.Cadastros.model.Enum.Categoria;
import Back.Cadastros.model.entity.Produtos;
import Back.Cadastros.repository.produtoRepository;
import Back.Cadastros.service.produtoService;
import Back._config.Exeptions.serviceExceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class produtoServiceTest {

    public static final Integer ID   = 1;
    public static final String NOME  = "Joao";
    public static final Double PRECO = 2.99;
    public static final Categoria CATEGORIA = Categoria.valueOf("PADARIA");
    public static final int INDEX = 0;
    public static final String OBJETO_NÃO_ENCONTRADO_ID = "Objeto não encontrado ID: " + ID;

    private Produtos produtos;
    private Back.Cadastros.model.DTO.produtoDTO produtoDTO;
    private Optional<Produtos> optionalProduto;

    @InjectMocks
    private produtoService service;

    @Mock
    private produtoRepository repository;

    @Mock
    private ModelMapper mapper;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void create() {
        when(repository.save(any())).thenReturn(produtos);

        Produtos response = service.create(produtoDTO);

        assertNotNull(response);
        assertEquals(Produtos.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(PRECO, response.getPreco());
        assertEquals(CATEGORIA, response.getCategoria());
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(produtos));
        when(mapper.map(produtos, Produtos.class)).thenReturn(produtos);

        List<Produtos> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Produtos.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(PRECO, response.get(INDEX).getPreco());
        assertEquals(CATEGORIA, response.get(INDEX).getCategoria());

    }

    @Test
    void findById() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(produtos));
        when(mapper.map(produtos, produtoDTO.class)).thenReturn(produtoDTO);

        produtoDTO response = service.findById(ID);

        assertNotNull(response);
        assertEquals(produtoDTO, response);
    }
    @Test
    void findByIdObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO_ID));

        try{
            service.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NÃO_ENCONTRADO_ID, ex.getMessage());
        }
    }

    @Test
    void upDate() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(produtos));
        when(repository.save(any())).thenReturn(produtos);

        Produtos response = service.upDate(ID, produtoDTO);

        assertNotNull(response);
        assertEquals(Produtos.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(PRECO, response.getPreco());
        assertEquals(CATEGORIA, response.getCategoria());

    }

    @Test
    void delete() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(produtos));
        doNothing().when(repository).deleteById(anyInt());

        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());

    }



    private void startCliente(){
        produtos = new Produtos(ID, NOME, PRECO, CATEGORIA);
        produtoDTO = new produtoDTO(ID, NOME, PRECO, CATEGORIA);
    }
}