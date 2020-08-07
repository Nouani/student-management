package Telas;

import ClassesBase.*;
import EstruturasDeDados.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
	private JTextField txtRA;
	private JTextField txtCod;
	private Fila<Resultado> filaResultados = new Fila<Resultado>();

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
		
		JLabel lblCdigoDaDisciplina = new JLabel("C\u00F3digo da Disciplina: ");
		lblCdigoDaDisciplina.setToolTipText("C\u00F3digo da determinada Disciplina Escolar");
		lblCdigoDaDisciplina.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCdigoDaDisciplina.setBounds(64, 86, 187, 24);
		panel_1.add(lblCdigoDaDisciplina);
		
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
					int ra = Integer.parseInt(txtRA.getText());
					int cod = Integer.parseInt(txtCod.getText());
					double nota = Double.parseDouble(spnNota.getValue().toString());
					double freq = Double.parseDouble(spnFreq.getValue().toString());
					
					Resultado resultado = new Resultado(ra, cod, nota, freq);
					
					filaResultados.guardeUmItem(resultado);
					LimparCampos();														
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
                    ex.printStackTrace();
				}
			}

			private void LimparCampos() {
				txtRA.setText("");
				txtCod.setText("");
				spnNota.setValue(0);
				spnFreq.setValue(0);
			}
		});
		btnDefinir.setToolTipText("Armazena os dados especificados - N\u00E3o adiciona automaticamente, \u00E9 necess\u00E1rio confirmar o procedimento atrav\u00E9s do outro bot\u00E3o!");
		btnDefinir.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDefinir.setBounds(73, 270, 320, 50);
		panel_1.add(btnDefinir);
		
		JButton btnConfirmar = new JButton("<html><center>Confirmar Resultados<br>"
										+  "        do(s)       <br>"
										+  "       Aluno(s)     </center></html>");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EnviarParaAPI();
				Listagem listagem = new Listagem();
				listagem.setVisible(true);
				
			}

			private void EnviarParaAPI() {
				Fila filaClone = (Fila)filaResultados.clone();
				Fila<String> resultados = new Fila<String>();
				while(!filaResultados.isVazia())
				{
					System.out.println(filaResultados.getQtd());
					try {
						int res = (int) ClienteWS.postObjeto(filaResultados.recupereUmItem(), Integer.class, "http://localhost:3333/resultados");
						filaResultados.removaUmItem();
						
						if(res == 404)
							resultados.guardeUmItem("nao");
						else
							resultados.guardeUmItem("sim");
						
		            } catch (Exception ex) {
		            	JOptionPane.showMessageDialog(null, ex.getMessage());
	                    ex.printStackTrace();
		            }
				}
			}
				
		});
		
		btnConfirmar.setToolTipText("Confirma Todos os Resultados adicionados nesta sess\u00E3o e registra no banco");
		btnConfirmar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnConfirmar.setBounds(449, 148, 243, 78);

		panel_1.add(btnConfirmar);
		
		txtRA = new JTextField();
		txtRA.setBounds(290, 43, 96, 20);
		panel_1.add(txtRA);
		txtRA.setColumns(10);
		
		txtCod = new JTextField();
		txtCod.setBounds(290, 90, 96, 20);
		panel_1.add(txtCod);
		txtCod.setColumns(10);
		
		addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowOpened(WindowEvent arg0) 
			{
				
			}
		});
	}
}
