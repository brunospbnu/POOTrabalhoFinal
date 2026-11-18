package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class TelaCriacaoPersonagem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSelecione;
	private JTextField txtGuerreiro;
	private JTextField txtArqueiro;
	private JTextField txtMago;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCriacaoPersonagem frame = new TelaCriacaoPersonagem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCriacaoPersonagem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSelecione = new JTextField();
		txtSelecione.setBounds(118, 10, 198, 20);
		txtSelecione.setText("Escolha uma classe para sua aventura");
		contentPane.add(txtSelecione);
		txtSelecione.setColumns(24);
		
		txtGuerreiro = new JTextField();
		txtGuerreiro.setText("Guerreiro");
		txtGuerreiro.setBounds(10, 113, 68, 20);
		contentPane.add(txtGuerreiro);
		txtGuerreiro.setColumns(10);
		
		txtArqueiro = new JTextField();
		txtArqueiro.setText("Arqueiro");
		txtArqueiro.setColumns(10);
		txtArqueiro.setBounds(175, 113, 68, 20);
		contentPane.add(txtArqueiro);
		
		txtMago = new JTextField();
		txtMago.setText("Mago");
		txtMago.setColumns(10);
		txtMago.setBounds(330, 113, 68, 20);
		contentPane.add(txtMago);

	}
}
