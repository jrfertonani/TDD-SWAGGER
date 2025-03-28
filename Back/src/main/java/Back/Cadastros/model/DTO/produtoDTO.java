package Back.Cadastros.model.DTO;

import Back.Cadastros.model.Enum.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class produtoDTO {

    @Schema(description = "Identificador único do produto", example = "1")
    private Integer id;

    @Schema(description = "Nome do produto", example = "Pão Frances", required = true)
    private String nome;

    @Schema(description = "Preço do produto", example = "29.99", minimum = "0.01")
    private Double preco;

    @Schema(description = "Categoria do produto", example = "PADARIA", required = true)
    private Categoria categoria;

    public produtoDTO() {
    }

    public produtoDTO(Integer id, String nome, Double preco, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "produtoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                '}';
    }
}
