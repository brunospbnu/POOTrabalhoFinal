package visao;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NovaPartida extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtArqueiro;
	private JTextField txtMago;
	
	
	/**
	 * Create the panel.
	 */
	public NovaPartida() {
		
		setLayout(null);
		
		txtArqueiro = new JTextField();
		txtArqueiro.setText("Arqueiro");
		txtArqueiro.setColumns(10);
		txtArqueiro.setBounds(263, 130, 86, 20);
		add(txtArqueiro);
		
		txtMago = new JTextField();
		txtMago.setText("Mago");
		txtMago.setColumns(10);
		txtMago.setBounds(415, 130, 86, 20);
		add(txtMago);
		
		JLabel lblNewLabel_1 = new JLabel("Escolha uma classe para sua aventura:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(149, 30, 352, 48);
		add(lblNewLabel_1);
		
		JLabel ImagemDeFundoNvPartida = new JLabel("");
		ImagemDeFundoNvPartida.setBounds(0, 0, 753, 463);
		add(ImagemDeFundoNvPartida);
		
		JLabel lblNewLabel = new JLabel("Guerreiro");
		lblNewLabel.setBounds(85, 214, 72, 12);
		add(lblNewLabel);

	}
}


