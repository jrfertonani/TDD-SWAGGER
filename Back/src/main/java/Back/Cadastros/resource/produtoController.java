package Back.Cadastros.resource;

import Back.Cadastros.model.DTO.produtoDTO;
import Back.Cadastros.model.entity.Produtos;
import Back.Cadastros.service.produtoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@Tag(name = "Produtos")
@CrossOrigin("*")
@RestController
@RequestMapping("/produtos")
public class produtoController {


    @Autowired
    public ModelMapper mapper;

    @Autowired
    public produtoService service;

    @PostMapping
    @Operation(summary = "Cadastrar produto", description = "Este merodo é responsavel para cadastrar um produtos")
    @ApiResponse(responseCode = "200",description = "Produto cadastrado com sucesso",
                  content = {@Content(schema = @Schema(implementation = produtoDTO.class))})
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<produtoDTO> create(@RequestBody produtoDTO DTO){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(
                        service.create(DTO)
                ).toUri();
                return ResponseEntity.created(uri).body(DTO);
    }

    @GetMapping
    @Operation(summary = "Listagem de produtos")
    @ApiResponse(responseCode = "200",description = "Produto Listado com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = produtoDTO.class))))
    @ApiResponse(responseCode = "400", description = "objectNotFound")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<Produtos>> getAll(){
        return ResponseEntity.ok().body(
                service.findAll()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca por ID", description = "Esta função busca um produto por vez")
    @ApiResponse(responseCode = "200", description = "Busca com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Produtos.class))))
    @ApiResponse(responseCode = "400", description = "objectNotFound")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<produtoDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok().body(
                mapper.map(
                        service.findById(id)
                ,produtoDTO.class)
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar um produto", description = "Esta função altera um produto")
    @ApiResponse(responseCode = "200", description = "Produto alterado com sucesso",
            content = {@Content(schema = @Schema(implementation = produtoDTO.class))})
    @ApiResponse(responseCode = "400", description = "objectNotFound")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<produtoDTO> upDate(@PathVariable Integer id,
                                             @RequestBody produtoDTO DTO){

        Produtos obj = service.upDate(id, DTO);
        return ResponseEntity.ok().body(DTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um produto")
    @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Produtos.class))))
    @ApiResponse(responseCode = "400", description = "objectNotFound")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
