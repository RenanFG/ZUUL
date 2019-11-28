/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgcorp.zuul;


/**
 *
 * @author renan
 */
/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class Jogo 
{
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private Jogador jogador;
    private Estanciador e;
    private Janela janela;
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() 
    {
        e = new Estanciador();
        criarAmbientes();
        analisador = new Analisador();
        jogador= new Jogador();
        janela = new Janela();
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes()
    {
        Ambiente escritorio, sala_tv, jardim, cozinha, sala_jantar, corredor, quarto1, quarto2, quarto3, quarto4, banheiro1, banheiro2;
        // cria os ambientes
        escritorio = new Ambiente("Escritorio", "Escritório, papéis picados e uma marca de tinta em forma de mão na fechadura");
        e.add(escritorio);
        sala_tv = new Ambiente("Sala de tv", "Sala de tv, a tv está desligada, porém você pode ouvir ruidos estranhos pelo auto falante");
        // o tesouro nao pode estar na sala de tv
        jardim= new Ambiente("Jardim","Jardim repleto de rosas vermelhas, parece que elas foram pintadas de sangue");
        e.add(jardim);
        cozinha= new Ambiente("Cozinha", "Cozinha, cheiro forte de comida estragada");
        e.add(cozinha);
        sala_jantar= new Ambiente("Sala de Jantar", "Sala de Jantar, as cadeiras se mechem sozinhas, você sente frio");
        e.add(sala_jantar);
        corredor= new Ambiente("Corredor" , "Corredor, você ouve barulhos estranhos vindo dos quartos");
        e.add(corredor);
        quarto1= new Ambiente("Quarto 1" , "Quarto 1, provavelmente usado por uma pessoa, voce percebe marcas de sangue na fechadura");
        e.add(quarto1);
        quarto2= new Ambiente("Quarto 2" , "Quarto 2, pequeno quarto de serviço, nada álem de uma cama destruida");
        e.add(quarto2);
        quarto3=new Ambiente("Quarto 3" ,  "Quarto 3, o maior quarto da casa, você percebe uma pintura na parede, nesta pintura tem um casal que seus olhos o seguem");
        e.add(quarto3);
        quarto4=new Ambiente("Quarto 4", "Quarto 4, um quarto usado como depósito, varias caixas com bandagens e siringas espalhadas pelo chão");
        e.add(quarto4);
        banheiro1=new Ambiente("Banheiro 1", "Banheiro 1, banheiro com marcas de pegada e sangue por todo lado, provavelment este era o banheiro central da casa" );
        e.add(banheiro1);
        banheiro2=new Ambiente("Banheiro 2", "Banheiro 2, suite do Quarto 3, você percebe varias embalagens de rémedio e uma faca ensanguentada");
        e.add(banheiro2);
        
        // inicializa as saidas dos ambientes
        escritorio.ajustarSaidas("Sala de tv",sala_tv);
        sala_tv.ajustarSaidas("Jardim",jardim);
        sala_tv.ajustarSaidas("Sala de Jantar",sala_jantar);
        sala_tv.ajustarSaidas("Escritorio",escritorio );
        jardim.ajustarSaidas("Cozinha",cozinha );
        jardim.ajustarSaidas("Sala de tv", sala_tv);
        cozinha.ajustarSaidas("Jardim",jardim);
        cozinha.ajustarSaidas("Sala de Jantar",sala_jantar );
        sala_jantar.ajustarSaidas("Cozinha",cozinha );
        sala_jantar.ajustarSaidas("Sala de tv",sala_tv );
        sala_jantar.ajustarSaidas("Corredor",corredor );
        corredor.ajustarSaidas("Sala de Jantar",sala_jantar );
        corredor.ajustarSaidas("Quarto3",quarto3 );
        corredor.ajustarSaidas("Quarto1", quarto1 );
        corredor.ajustarSaidas("Quarto2", quarto2 );
        corredor.ajustarSaidas("Quarto4",quarto4 );
        corredor.ajustarSaidas("Banheiro1",banheiro1 );
        quarto1.ajustarSaidas("Corredor",corredor );
        quarto2.ajustarSaidas("Corredor",corredor );
        quarto3.ajustarSaidas("Banheiro2",banheiro2 );
        quarto3.ajustarSaidas("Corredor",corredor );
        quarto4.ajustarSaidas("Corredor",corredor );
        banheiro1.ajustarSaidas("Corredor",corredor );
        banheiro2.ajustarSaidas("Quarto3",quarto3 );

        ambienteAtual = sala_tv;  // o jogo comeca na sala de tv
        /** Gerando chave mestra e Tesouro em ambiente RNG  **/
        e.plantarTesouro();
        e.plantarChave();
        
    }
    

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {            
        janela.exibir();
        
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        while (! terminado || jogador.get_ntentativas()== 0) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas()
    {
          janela.setBemVindo("Bem vindo ao Caça ao Tesouro, um jogo de aventura super divertido. Digite 'ajuda' se precisar de ajuda");
        
        System.out.println();
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();
        
        imprimirAmbiente();
        
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            janela.setLocalAtual("Eu nao entendi o que voce disse...");
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }
        else if (palavraDeComando.equals("observar")) {
            observar();
        }
        else if (palavraDeComando.equals("explodir")) {
            detonarBomba();
        }
        else if (palavraDeComando.equals("hack")) {
            hack();
        }
        
        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando
     */
    private void imprimirAjuda() 
    {
       janela.setLocalAtual("Suas palavrade de comando são: ir sair ajuda");
        
        System.out.println("Voce esta com medo. Voce esta sozinho. Voce caminha");
        System.out.println("pela casa mal assombrada.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println(" ir sair ajuda explodir(use apenas uma vez)");
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void observar(){
        
        janela.setLocalAtual("Você está no ambiente "+ ambienteAtual.getNome() + " as possiveis saidas são: "+ ambienteAtual.getSaidas());
        System.out.println("Você está no ambiente "+ ambienteAtual.getNome() + " as possiveis saidas são: "+ ambienteAtual.getSaidas());
    }
    private void irParaAmbiente(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            janela.setLocalAtual("Ir pra onde?");
            System.out.println("Ir pra onde?");
            return;
        }
        
        String direcao = comando.getSegundaPalavra();
        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = ambienteAtual.getAmbiente(direcao);
        
        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
            janela.setLocalAtual("Nao ha passagem!");
        } 
        else if(jogador.checarChave()){
            System.out.println("Deseja usar a chave mestra? ");
            janela.setLocalAtual("Deseja usar a chave mestra? ");
        }        
        else {
           boolean abriu = jogador.abrirPorta();
           if(abriu){
           ambienteAtual = proximoAmbiente;
           imprimirAmbiente();
           if(ambienteAtual.checarChave()){
               jogador.achouChave();
           System.out.println("Você achou a chave mestra!! parabens guerreiro");
           janela.setLocalAtual("Você achou a chave mestra!! parabens guerreiro");
           }
           System.out.println("N tentativas restantes: " + jogador.get_ntentativas());
           janela.setLocalAtual("N tentativas restantes: " + jogador.get_ntentativas());
           } else {
           System.out.println("Você falhou em abrir a porta");
           janela.setLocalAtual("Você falhou em abrir a porta");
           System.out.println("N tentativas restantes: " + jogador.get_ntentativas());
           janela.setLocalAtual("N tentativas restantes: " + jogador.get_ntentativas());
           }
        }
    }
    
    public void hack(){
        /*Hack para facilitar testes */
        System.out.println("O jogador tem : " + jogador.get_ntentativas() + "n de tentativas");
        janela.setLocalAtual("O jogador tem : " + jogador.get_ntentativas() + "n de tentativas");
        if(jogador.checarChave()){
        System.out.println("O jogador tem : "+ jogador.get_chaveMestra() + "n de tentativas com a chave mestra");
        janela.setLocalAtual("O jogador tem : "+ jogador.get_chaveMestra() + "n de tentativas com a chave mestra");
        } else {
        System.out.println("O jogador não possui chaves");
        janela.setLocalAtual("O jogador não possui chaves");
        }
        System.out.println("O tesouro está no ambiente: " + e.kdTesouro());
        janela.setLocalAtual("O tesouro está no ambiente: " + e.kdTesouro());
        System.out.println("A Chave está no ambiente: " + e.kdChave());
        janela.setLocalAtual("A Chave está no ambiente: " + e.kdChave());
    }
    
    public void detonarBomba(){
        if(ambienteAtual.checarTesouro()){
            System.out.println("Parabéns você encontrou o Tesouro!!!");
            janela.setLocalAtual("Parabéns você encontrou o Tesouro!!!");
            //terminar jogo com vitória
        } else {
            System.out.println("Você usou sua bomba e não encontrou o Tesouro!!!");
            janela.setLocalAtual("Você usou sua bomba e não encontrou o Tesouro!!!");
            //terminar jogo com derrota
        }
        
    }
    public void imprimirAmbiente(){
        
        janela.setLocalAtual("Voce esta " + ambienteAtual.getDescricao() + "  Saidas: " +  ambienteAtual.getSaidas());
        System.out.println("Voce esta " + ambienteAtual.getDescricao());
    
        System.out.print("Saidas: " + ambienteAtual.getSaidas());
        
        System.out.println();
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nos queremos sair
        }
    }
}
