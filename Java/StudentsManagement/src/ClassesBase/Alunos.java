package ClassesBase;

public class Alunos
{
	private int ra;
	private String nome;
	
	public Alunos(int ra, String nome) throws Exception
	{
		this.setRa(ra);
		this.setNome(nome);
	}
	
	public int getRa()
	{
		return this.ra;
	}
	public String getNome()
	{
		return this.nome;
	}
	
	public void setRa(int ra) throws Exception
	{
		if (ra < 0)
			throw new Exception ("Ra do Aluno Inválido");
		
		this.ra = ra;
	}
	public void setNome(String nome) throws Exception
	{
		if (nome==null || nome.equals(""))
			throw new Exception ("Nome do Aluno Inválido");
		
		this.nome = nome;
	}
	
	public String toString()
	{
		String ret = "Aluno: \n";
		
		ret += "Ra do Aluno: " + this.ra + "\n";
		ret += "Nome do Aluno: " + this.nome + "\n";
		
		return ret;
	}
	public int hashCode()
	{
		int ret = 9;
		
		ret += ret*13 + new Integer(this.ra).hashCode();
		ret += ret*13 + this.nome.hashCode();
		
		if(ret < 0)
			ret = -ret;
		
		return ret;
	}
	public boolean equals(Object obj)
	{
		if (obj==null)
			return false;
		if (obj==this)
			return true;
		if (obj.getClass()!=this.getClass())
			return false;
		
		Alunos alu = (Alunos)obj;
		if (alu.ra != this.ra)
			return false;
		
		return true;
	}
}
