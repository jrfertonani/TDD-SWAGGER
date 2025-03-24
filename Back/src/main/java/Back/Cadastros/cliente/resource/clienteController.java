package Back.Cadastros.cliente.resource;

import Back.Cadastros.cliente.model.DTO.clienteDTO;
import Back.Cadastros.cliente.model.entity.Clientes;
import Back.Cadastros.cliente.service.clienteService;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class clienteController {

    @Autowired
    public ModelMapper mapper;

    @Autowired
    private clienteService service;

    @PostMapping
    @Tag(name = "Clientes", description = "Cadastro do clientes")
    @Operation(summary = "Cadastro de clientes",description = "Esta função e responsavel para cadastrar cliente")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = clienteDTO.class))})
    public ResponseEntity<clienteDTO> create(@RequestBody clienteDTO DTO){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(service.create(DTO)).toUri();
        return ResponseEntity.created(uri).body(DTO);
    }

    @GetMapping
    @Tag(name = "Clientes", description = "Informações do clientes")
    @Operation(summary = "Listagem de clientes",description = "Esta função lista todos os clientes disponiveis")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = clienteDTO.class))))
    public ResponseEntity<List<Clientes>> findAll(){
        return ResponseEntity.ok().body(
                service.findAll()

        );
    }

    @GetMapping("/{id}")
    @Tag(name= "Clientes", description = "Informações sobre cliente ID")
    @Operation(summary = "Busca por cliente ID", description = "Esta função busca um cliente por vez 'ID'")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Clientes.class))))@ApiResponse(responseCode = "400", description = "ClienteNotFound")
    public ResponseEntity<clienteDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(
                mapper.map(service.findById(id)
                ,clienteDTO.class)
        );
    }

    @PutMapping("/{id}")
    @Tag(name = "Clientes", description = "Alterar cadastro de clientes")
    @Operation(summary = "Alterar cliente",description = "Esta função e responsavel para alterar caastro de cliente")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = clienteDTO.class))})
    public ResponseEntity<clienteDTO> upDate(@PathVariable Integer id,
                                             @RequestBody clienteDTO DTO){
        DTO.setId(id);
        Clientes obj = service.upDate(id, DTO);
        return ResponseEntity.ok().body(DTO);
    }

    @DeleteMapping("/{id}")
    @Tag(name = "Clientes", description = "Remover clientes por vez")
    @Operation(summary = "Remover cliente", description = "Esta função e responsavel para remover clientes")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Clientes.class))))@ApiResponse(responseCode = "400", description = "ClienteNotFound")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
