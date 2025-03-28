package Back.Cadastros.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

public class clienteDTO {

    @Schema(description = "Identificador único do produto", example = "1")
    private Integer id;

    @Schema(description = "Nome do cliente", example = "João", required = true)
    private String nome;

    @Schema(description = "Email do cliente", example = "email@email.com", required = true)
    private String email;

    @Schema(description = "Telefone", example = "4499999999", required = true)
    private String telefone;

    public clienteDTO() {
    }

    public clienteDTO(Integer id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Integer getId(Integer id) {
        return this.id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "clienteDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
