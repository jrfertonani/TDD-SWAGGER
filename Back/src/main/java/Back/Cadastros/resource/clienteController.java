package Back.Cadastros.resource;

import Back.Cadastros.model.DTO.clienteDTO;
import Back.Cadastros.model.DTO.produtoDTO;
import Back.Cadastros.model.entity.Clientes;
import Back.Cadastros.service.clienteService;
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

@Tag(name = "Clientes")
@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class clienteController {

    @Autowired
    public ModelMapper mapper;

    @Autowired
    private clienteService service;



    @PostMapping
    @Operation(summary = "Cadastro de clientes",description = "Esta função e responsavel para cadastrar cliente")
    @ApiResponse(responseCode = "200",description = "Cliente cadastrado com sucesso",
            content = {@Content(schema = @Schema(implementation = clienteDTO.class))})
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<clienteDTO> create(@RequestBody clienteDTO DTO){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(service.create(DTO)).toUri();
        return ResponseEntity.created(uri).body(DTO);
    }

    @GetMapping
    @Operation(summary = "Listagem de clientes")
    @ApiResponse(responseCode = "200", description = "Cliente Listado com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = clienteDTO.class))))
    @ApiResponse(responseCode = "400", description = "objectNotFound")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<Clientes>> findAll(){
        return ResponseEntity.ok().body(
                service.findAll()

        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca por cliente ID", description = "Esta função busca um cliente por vez 'ID'")
    @ApiResponse(responseCode = "200", description = "Busca de Produto id com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Clientes.class))))
    @ApiResponse(responseCode = "400", description = "objectNotFound")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<clienteDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(
                mapper.map(service.findById(id)
                ,clienteDTO.class)
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar cliente",description = "Esta função e responsavel para alterar cadastro de cliente")
    @ApiResponse(responseCode = "200", description = "Cliente alterado com sucesso",
            content = {@Content(schema = @Schema(implementation = clienteDTO.class))})
    @ApiResponse(responseCode = "400", description = "objectNotFound")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<clienteDTO> upDate(@PathVariable Integer id,
                                             @RequestBody clienteDTO DTO){
        DTO.setId(id);
        Clientes obj = service.upDate(id, DTO);
        return ResponseEntity.ok().body(DTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover cliente", description = "Esta função e responsavel para remover clientes")
    @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Clientes.class))))
    @ApiResponse(responseCode = "400", description = "objectNotFound")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
