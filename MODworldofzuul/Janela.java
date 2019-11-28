package rpgcorp.zuul;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.*;


public class Janela {

	private JFrame janela_principal;

	private JLabel nTentivaRestantes;
	private JLabel nTentativas;

	private JLabel durChave;
	private JLabel nDurChave;

	private JLabel dEncontradas_label; 
	private JLabel dEncotradas;
	
	private JLabel bemVimdo;
	private JLabel localAtual;
	private JTextField comando; 
	
	private JLabel image;


    public Janela() {
        
        janela_principal = new JFrame("Caça ao Tesouro");
        nTentivaRestantes = new JLabel("Numero de Tentaivas Restantantes:");
        nTentativas = new JLabel();
        
        durChave = new JLabel("Durabilidade da cheve mestra: ");
        nDurChave = new JLabel();
        
        dEncontradas_label = new JLabel("Dicas Encontradas: ");
        dEncotradas = new JLabel();
        
        bemVimdo = new JLabel();
        localAtual = new JLabel();
        comando = new JTextField();
	
	// endereço da imagem no image icon construtor
        image = new JLabel(new ImageIcon("src/mapa zull.jpg"));
        
        //montarJanela();
    }
    
    public void montarJanela(){
        
        janela_principal.setSize(1280,720);
        janela_principal.setLayout(new BorderLayout());
            
        //painel esquerda        
        JPanel painelEsq = new JPanel();
        painelEsq.setLayout(new GridLayout(4,1));
        painelEsq.add(nTentivaRestantes);
        painelEsq.add(nTentativas);
        painelEsq.add(durChave);
        painelEsq.add(nDurChave);
        janela_principal.add(painelEsq,BorderLayout.WEST);
		
	//painel direita
	JPanel painelDir = new JPanel();
        painelDir.setLayout(new GridLayout(2,1));
        painelDir.add(dEncontradas_label);
        painelDir.add( dEncotradas);
        janela_principal.add(painelDir,BorderLayout.EAST);
        
        //painel sul/abaixo
        JPanel painelSul = new JPanel();
        painelSul.setLayout(new GridLayout(3,1));
        painelSul.add(bemVimdo);
        painelSul.add(localAtual);
        painelSul.add(comando);
        janela_principal.add(painelSul,BorderLayout.SOUTH);
        
        //painelcentral
        JPanel painelCent = new JPanel();
        painelCent.setLayout(new FlowLayout());
        painelCent.add(image);
        janela_principal.add(painelCent,BorderLayout.CENTER);
        
        janela_principal.pack();
        
    }

    //set's
    public void setnTentivaRestantes(String s) {
        
        nTentivaRestantes.setText(s);
    }

    public void setnDurChave(String s) {
        
        nDurChave.setText(s);
    }

    public void setdEncotradas(String s) {
        
        dEncotradas.setText(s);
    }

    public void setBemVindo(String s) {
        
         bemVimdo.setText(s);
    }

    public void setLocalAtual(String s) {
        
        localAtual.setText(s);
    }
    
    
    
    
    
    
    
    
    public void exibir(){
     
        janela_principal.setVisible(true);
    }

}
