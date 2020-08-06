package ClassesBase;

public class Disciplinas 
{
	private int cod;
	private String nome;
	
	public Disciplinas(int cod, String nome) throws Exception
	{
		this.setCod(cod);
		this.setNome(nome);
	}
	
	public int getCod()
	{
		return this.cod;
	}
	public String getNome()
	{
		return this.nome;
	}
	
	public void setCod(int cod) throws Exception
	{
		if(cod < 0)
			throw new Exception ("Código da Disciplina Inválido");
		
		this.cod = cod;
	}
	public void setNome(String nome) throws Exception
	{
		if(nome==null || nome.equals(""))
			throw new Exception ("Nome da Disciplina Inválido");
		
		this.nome = nome;
	}
	
	public String toString()
	{
		String ret = "Disciplina: \n";
		ret += "Código: " + this.getCod() + "\n";
		ret += "Nome: " + this.getNome() + "\n";
		
		return ret;
	}
	public int hashCode()
	{
		int ret = 9;
		ret += ret*13 + new Integer(this.cod).hashCode();
		ret += ret*13 + this.nome.hashCode();
		
		if(ret < 0)
			ret = -ret;
		
		return ret;
	}
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(obj==this)
			return true;
		if(obj.getClass()!=this.getClass())
			return false;
		
		Disciplinas dis = (Disciplinas)obj;
		if(dis.cod!=this.cod)
			return false;
		
		return true;
	}
}
