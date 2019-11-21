import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class Janela {

private JFrame janela_principal;

private JLabel nTentivaRestantes;
private JLabel nTentativas;

private JLabel durChave;
private JLabel nDurChave;

private JLabel dEncontradas_label; 
private JLabel dEncotradas;



    public Janela() {
        janela_principal = new JFrame("World of ZUUL");
        nTentivaRestantes = new JLabel("Numero de Tentaivas Restantantes");
        nTentativas = new JLabel("n");
        durChave = new JLabel("Durabilidade da cheve mestra");
        nDurChave = new JLabel("N");
        dEncontradas_label = new JLabel("Dicas Encontradas");
        dEncotradas = new JLabel("N");
        
        montarJanela();
    }
    
    public void montarJanela(){
        
        janela_principal.setSize(1280,720);
        janela_principal.setLayout(new BorderLayout());
                
        JPanel painelEsq = new JPanel();
        painelEsq.setLayout(new GridLayout(4,1));
        painelEsq.add(nTentivaRestantes);
        painelEsq.add(nTentativas);
        painelEsq.add(durChave);
        painelEsq.add(nDurChave);
        janela_principal.add(painelEsq,BorderLayout.WEST);
        
        
        
        
        janela_principal.pack();
        
    }
    
    public void exibir(){
        
        janela_principal.setVisible(true);
   
    }
    














}
