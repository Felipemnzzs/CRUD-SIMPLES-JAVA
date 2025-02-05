package Modelos;

public class Usuario {
	
	private int Id;
	private String Nome;
	private int Idade;
	private String Telefone;
	private String Sexo;
	
	
	public Usuario() {
		
	}
	
	public Usuario(int Id, String Nome, int Idade, String Telefone, String Sexo ) {
		
		this.Id = Id;
		this.Nome = Nome;
		this.Idade = Idade;
		this.Telefone = Telefone;
		this.Sexo = Sexo;
	}

	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public int getIdade() {
		return Idade;
	}

	public void setIdade(int idade) {
		this.Idade = idade;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		this.Telefone = telefone;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		this.Sexo = sexo;
	}
	
	
	
	
	
}
