package algoritmos.furb.arvore;

public interface IArvoreB {
	
	/**
	 * Esse m�todo dever� criar a �rvore vazia.
	 * O arquivo onde ficar� armazenada a �rvore dever� ser criado.
	 * @param ordem
	 * @param NomeArquivo
	 * @return
	 */
	ArvoreB cria(int ordem, String NomeArquivo);

	/**
	 * Esse m�todo dever� abrir o arquivo, deixando  a �rvore dispon�vel 
	 * para as opera��es de inser��o, remo��o, pesquisa e fechamento. 
	 * @param NomeArquivo
	 * @return
	 */
	ArvoreB abre(String nomeArquivo);

	/**
	 * Esse m�todo dever� fechar o arquivo, deixando  a �rvore indisponivel 
	 * para as opera��es de inser��o, remo��o, pesquisa e fechamento. 
	 * @param NomeArquivo
	 * @return
	 */
	ArvoreB fecha(String nomeArquivo);
	
	/**
	 * Esse m�todo dever� inserir a chave na �rvore se n�o existir, 
	 * caso contr�rio retorna falso indicando erro.
	 * @param chave
	 * @return
	 */
	boolean insere(int chave);
	
	/**
	 * Esse m�todo dever� remove a chave especificada como par�metro se existir, 
	 * caso contr�rio retorna falso indicando que a opera��o n�o foi executada.
	 * @param chave
	 * @return
	 */
	boolean remove(int chave);
	
	/**
	 * Esse m�todo dever� pesquisar se a chave existe ou n�o na �rvore,
	 * para isso dever� verificar se a �rvore foi aberta e se a chave
	 * existe seguindo o algoritmo de pesquisa na �rvore B.
	 * @param chave
	 * @return
	 */
	boolean pesquisa(int chave);
}
