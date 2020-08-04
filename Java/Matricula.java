public class Matricula
{
	private int ra;
	private int codDisciplina;
	private double nota;
	private double frequencia;

	public Matricula(int ra, int codDisciplina, double nota, double frequencia)
	{
		this.setRa(ra);
		this.setCodDisciplina(codDisciplina);
		this.setNota(nota);
		this.setFrequencia(frequencia);
	}

	public int getRa()
	{
		return this.ra;
	}
	public int getCodDisciplina()
	{
		return this.codDisciplina;
	}
	public double getNota()
	{
		return this.nota;
	}
	public double getFrequencia()
	{
		return this.frequencia;
	}

	public void setRa(int ra)
	{
		if (ra < 0)
			throw new Exception("Ra Inválido!");
		this.ra = ra;
	}
	public void setCodDisciplina(int codDisciplina)
	{
		if (codDisciplina < 0)
			throw new Exception("Código Inválido!");
		this.codDisciplina = codDisciplina;
	}
	public void setNota(double nota)
	{
		if(nota < 0 || nota > 10)
			throw new Exception("Nota Inválida! É necessário estar entre 0 e 10.")
		this.nota = nota;
	}
	public void setFrequencia(double frequencia)
	{
		if(frequencia < 0 || frequencia > 1)
			throw new Exception("Freqência Inválida! É necessário estar entre 0.0 e 1.0");
		this.frequencia = frequencia;
	}

	public String toString()
	{
		String ret = "Matricula: " + "\n";
		ret += "RA: " + this.getRa() + "\n";
		ret += "Código da Disciplina: " + this.getCodDisciplina() + "\n";
		ret += "Nota na Disciplina: " + this.getNota() + "\n";
		ret += "Frequência na Disciplina: " + this.getFrequencia() + "\n";
		return ret;
	}

	public int hashCode()
	{
		int ret = 777;
		ret += ret*13 + new Integer(this.ra).hashCode();
		ret += ret*13 + new Integer(this.codDisciplina).hashCode();
		ret += ret*13 + new Double(this.nota).hashCode();
		ret += ret*13 + new Double(this.frequencia).hashCode();

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

		Matricula mat = (Matricula)obj;
		if(mat.ra != this.ra && mat.codDisciplina != this.codDisciplina)
			return false;

		return true;
	}
}