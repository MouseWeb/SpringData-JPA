package spring.data.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "usuariobanco", uniqueConstraints
			= {@UniqueConstraint(columnNames={"login", "senha", "cpf"})})

@SequenceGenerator(initialValue = 1, name = "usuario_sequence")
public class UsuarioSpringData {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario_sequence")
	private Long id;

	private String login;

	private String senha;

	private String nome;

	private String email;
	
	@Version
	@Column(name="OPTLOCK")
	private int version;
	
	@NaturalId
	@Column(name = "cpg_pessoa", unique = true, updatable = false)
	private String cpf;


    @Lob
	private byte[] foto;
    
    
	
	private int idade;
	
	@Transient
	private String nomeAuxilixar;
	
	
	
	@OneToMany(mappedBy = "usuarioSpringData", 
			orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Telefone> telefones;
	
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

}
