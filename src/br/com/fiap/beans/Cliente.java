package br.com.fiap.beans;

public class Cliente {

	private int codigo;
	private String nome;
	private String email;
	private Long numeroTelefone;
	private String perfil;
	private String login;
	private String senha;
	//Necessário ter a propriedade para o JSTL do HTML recuperar a informacao
	private Boolean usuarioAdministrador;

	public Cliente() {
		super();
	}

	public Cliente(int codigo, String nome, String email, Long numeroTelefone, String perfil, String login,
			String senha) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.numeroTelefone = numeroTelefone;
		this.perfil = perfil;
		this.login = login;
		this.senha = senha;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public Long getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(Long numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
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
	
	public void setUsuarioAdministrador(Boolean usuarioAdministrador) {
		this.usuarioAdministrador = usuarioAdministrador;
	}

	public Boolean getUsuarioAdministrador() {
		return usuarioAdministrador;
	}

}
