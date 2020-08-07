package ClassesBase;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ModeloTabela extends AbstractTableModel {
	
	private ArrayList linhas = null;
	private String[] colunas = null;

	public ModeloTabela(ArrayList lin,String[] col)throws Exception
	{
		setLinhas(lin);
		setColunas(col);
	}
	
	
	public ArrayList getLinhas() {
		return linhas;
	}
	
	private void setLinhas(ArrayList linhas) throws Exception
	{
		if(linhas == null)
			throw new Exception("Informações de linha não passadas");
		
		this.linhas = (ArrayList)linhas.clone();
	}
	
	
	private void setColunas(String[] colunas)throws Exception
	{
		if(colunas == null)
			throw new Exception("Informações de coluna não passadas");
		for(int i=0; i< colunas.length; i++)
			if(colunas[i].trim().equals(""))
				throw new Exception("Coluna vazia");
				
		this.colunas = colunas.clone();
	}
	
	public int getColumnCount() {
		return colunas.length;
	}
	
	public int getRowCount() {
		return linhas.size();
	}
	
	public String getColumnName(int numCol)
	{
		try {
		return colunas[numCol];
		}
		catch(Exception erro)
		{
		 throw erro;
		}
	}

	public Object getValueAt(int numLin, int numCol)
	{

		try{
			Object[] linha = (Object[])getLinhas().get(numLin);
		return linha[numCol];
		}
		catch(Exception erro)
		{
			throw erro;
		}
	}
	
	public String toString() {
		String ret = "Colunas: ";
		for(int i = 0; i<colunas.length;i++)
			ret = ret+colunas[i] +", ";
		ret = ret +" Linhas: ";
		
		for(int i =0; i<linhas.size(); i++)
			ret = ret+linhas.get(i) + ", ";
		return ret;
			
	}

	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(this.getClass() != obj.getClass())
			return false;
		
		ModeloTabela modTab = (ModeloTabela) obj;
		
		if(modTab.colunas.length != this.colunas.length 
				|| this.linhas.size() != modTab.linhas.size())
			return false;
		
		for(int i = 0; i<colunas.length;i++)
			if(!this.colunas[i].equals(modTab.colunas[i]))
				return false;
		for(int i = 0; i<linhas.size();i++)
			if(!this.linhas.get(i).equals(modTab.linhas.get(i)))
				return false;
		
		return true;
	}
	
	public int hashCode()
	{
		int ret= 666;
		for(int i= 0; i< linhas.size(); i++)
			ret =ret*5 + linhas.get(i).hashCode();
		for(int i = 0; i<colunas.length;i++)	
		   ret = ret*5 + colunas[i].hashCode();
		
		if(ret<0)
			ret =-ret;
		return ret;
	}

}