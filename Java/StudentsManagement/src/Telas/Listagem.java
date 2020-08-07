package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClassesBase.ModeloTabela;
import ClassesBase.Resultado;
import EstruturasDeDados.Fila;

import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;

public class Listagem extends JFrame 
{

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Listagem frame = new Listagem(null, null);
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Listagem(Fila<Resultado> resultadoAlunos, Fila<String> resultadoRequest) 
	{
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				FazerListagem(resultadoAlunos, resultadoRequest);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	private void FazerListagem(Fila<Resultado> resultadoAlunos, Fila<String> resultadoRequest) {
		String [] colunas = {"RA","Código da discplina", "Nota", "Frequência", "Foi inserido?"};
		
        try 
        {
            ArrayList dados = new ArrayList();

            do 
            {
                Resultado res = resultadoAlunos.recupereUmItem();
                System.out.println(res);
                dados.add(new Object[] {res.getRa(), res.getCodDisciplina(), res.getNota(), res.getFrequencia(), resultadoRequest.recupereUmItem()});
                resultadoAlunos.removaUmItem();
            }
            while (!resultadoAlunos.isVazia());

            ModeloTabela modTab = new ModeloTabela(dados,colunas);

            table.setModel(modTab);
            table.getColumnModel().getColumn(0).setPreferredWidth(210);
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(211);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getTableHeader().setReorderingAllowed(false);
        }
        catch(Exception erro)
        {
            System.out.println(erro.getMessage());
        }
	}

}
