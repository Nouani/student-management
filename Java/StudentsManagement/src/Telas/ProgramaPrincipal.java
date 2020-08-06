package Telas;

import ClassesBase.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProgramaPrincipal extends JFrame 
{

	private JPanel contentPane;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ProgramaPrincipal frame = new ProgramaPrincipal();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public ProgramaPrincipal() 
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblStudentsManagement = new JLabel("Student's Management");
		lblStudentsManagement.setFont(new Font("Verdana", Font.PLAIN, 40));
		panel.add(lblStudentsManagement);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblRa = new JLabel("RA:");
		lblRa.setToolTipText("Registro Acad\u00EAmico do Aluno");
		lblRa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblRa.setBounds(64, 44, 46, 14);
		panel_1.add(lblRa);
		
		JComboBox cbRA = new JComboBox();
		cbRA.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbRA.setBounds(290, 41, 103, 20);
		panel_1.add(cbRA);
		
		JLabel lblCdigoDaDisciplina = new JLabel("C\u00F3digo da Disciplina: ");
		lblCdigoDaDisciplina.setToolTipText("C\u00F3digo da determinada Disciplina Escolar");
		lblCdigoDaDisciplina.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCdigoDaDisciplina.setBounds(64, 86, 187, 24);
		panel_1.add(lblCdigoDaDisciplina);
		
		JComboBox cbCod = new JComboBox();
		cbCod.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbCod.setBounds(290, 88, 64, 20);
		panel_1.add(cbCod);
		
		JLabel lblNota = new JLabel("Nota: ");
		lblNota.setToolTipText("Nota do Aluno nesta Disciplina");
		lblNota.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNota.setBounds(64, 140, 72, 14);
		panel_1.add(lblNota);
		
		JSpinner spnNota = new JSpinner();
		spnNota.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spnNota.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		spnNota.setBounds(290, 137, 103, 20);
		JComponent editor = spnNota.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			JSpinner.DefaultEditor spnEditor = (JSpinner.DefaultEditor)editor;
			spnEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
			spnEditor.getTextField().setEditable(false);
		}
		panel_1.add(spnNota);
		
		JLabel lblFrequncia = new JLabel("Frequ\u00EAncia");
		lblFrequncia.setToolTipText("Frequ\u00EAncia do Aluno nesta Disciplina");
		lblFrequncia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblFrequncia.setBounds(64, 184, 135, 20);
		panel_1.add(lblFrequncia);
		
		JSpinner spnFreq = new JSpinner();
		spnFreq.setModel(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.1));
		spnFreq.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		spnFreq.setBounds(290, 184, 103, 20);
		JComponent editor2 = spnFreq.getEditor();
		if (editor2 instanceof JSpinner.DefaultEditor) {
			JSpinner.DefaultEditor spnEditor2 = (JSpinner.DefaultEditor)editor2;
			spnEditor2.getTextField().setHorizontalAlignment(JTextField.CENTER);
			spnEditor2.getTextField().setEditable(false);
		}
		panel_1.add(spnFreq);
		
		JButton btnDefinir = new JButton("Definir Resultado Final do Aluno");
		btnDefinir.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					int ra = (int)cbRA.getSelectedItem();
					int cod = (int)cbCod.getSelectedItem();
					double nota = (double)spnNota.getValue();
					double freq = (double)spnFreq.getValue();
					
					Matricula matricula = new Matricula(ra, cod, nota, freq);
					ClienteWS.postObjeto(matricula, Matricula.class, "http://localhost:3333/resultados");
				}
				catch(Exception ex)
				{
					
				}
			}
		});
		btnDefinir.setToolTipText("Armazena os dados especificados - N\u00E3o adiciona automaticamente, \u00E9 necess\u00E1rio confirmar o procedimento atrav\u00E9s do outro bot\u00E3o!");
		btnDefinir.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDefinir.setBounds(73, 270, 320, 50);
		panel_1.add(btnDefinir);
		
		JButton btnConfirmar = new JButton("<html><center>Confirmar Resultados<br>"
										+  "        do(s)       <br>"
										+  "       Aluno(s)     </center></html>");
		btnConfirmar.setToolTipText("Confirma Todos os Resultados adicionados nesta sess\u00E3o e registra no banco");
		btnConfirmar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnConfirmar.setBounds(449, 148, 243, 78);

		panel_1.add(btnConfirmar);
		
		addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowOpened(WindowEvent arg0) 
			{
				Alunos[] alunos = (Alunos[])ClienteWS.getObjeto(Alunos.class, "http://localhost:3333/alunos");
				for(int i = 0; i < alunos.length; i++)
					cbRA.addItem(alunos[i].getRa());
				
				Disciplinas[] disc = (Disciplinas[])ClienteWS.getObjeto(Disciplinas.class, "http://localhost:3333/disciplinas");
				for(int i = 0; i < disc.length; i++)
					cbCod.addItem(disc[i].getCod());
			}
		});
	}
}
