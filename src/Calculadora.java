import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {

    // Componentes da interface
    private JTextField visor;
    private double num1 = 0;
    private double num2 = 0;
    private double resultado = 0;
    private char operador;

    public Calculadora() {
        // Configurações da Janela
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela
        setLayout(new BorderLayout());

        // Visor/Display
        visor = new JTextField();
        visor.setFont(new Font("Arial", Font.BOLD, 24));
        visor.setEditable(false);
        visor.setHorizontalAlignment(JTextField.RIGHT);
        add(visor, BorderLayout.NORTH);

        // Painel dos Botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(4, 4, 5, 5));

        // Rótulos dos botões
        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        // Adiciona os botões ao painel
        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 18));
            botao.addActionListener(this);
            painelBotoes.add(botao);
        }

        add(painelBotoes, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        // Se for um número
        if ((comando.charAt(0) >= '0' && comando.charAt(0) <= '9')) {
            visor.setText(visor.getText() + comando);
        }
        // Se for o botão Limpar (C)
        else if (comando.equals("C")) {
            visor.setText("");
            num1 = num2 = resultado = 0;
        }
        // Se foi clicado o botão de Igual (=)
        else if (comando.equals("=")) {
            if (!visor.getText().isEmpty()) {
                num2 = Double.parseDouble(visor.getText());

                switch (operador) {
                    case '+':
                        resultado = num1 + num2;
                        break;
                    case '-':
                        resultado = num1 - num2;
                        break;
                    case '*':
                        resultado = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            resultado = num1 / num2;
                        } else {
                            visor.setText("Erro (Div/0)");
                            return;
                        }
                        break;
                }
                visor.setText(String.valueOf(resultado));
            }
        }
        // Se for um operador (+, -, *, /)
        else {
            if (!visor.getText().isEmpty()) {
                num1 = Double.parseDouble(visor.getText());
                operador = comando.charAt(0);
                visor.setText("");
            }
        }
    }

    public static void main(String[] args) {
        // Garante a execução na thread correta da interface gráfica
        SwingUtilities.invokeLater(() -> {
            Calculadora calc = new Calculadora();
            calc.setVisible(true);
        });
    }
}