package algoritmos.furb.arvore;

public interface IArvoreB {
	
	/**
	 * Esse método deverá criar a árvore vazia.
	 * O arquivo onde ficará armazenada a árvore deverá ser criado.
	 * @param ordem
	 * @param NomeArquivo
	 * @return
	 */
	ArvoreB cria(int ordem, String NomeArquivo);

	/**
	 * Esse método deverá abrir o arquivo, deixando  a árvore disponível 
	 * para as operações de inserção, remoção, pesquisa e fechamento. 
	 * @param NomeArquivo
	 * @return
	 */
	ArvoreB abre(String nomeArquivo);

	/**
	 * Esse método deverá fechar o arquivo, deixando  a árvore indisponivel 
	 * para as operações de inserção, remoção, pesquisa e fechamento. 
	 * @param NomeArquivo
	 * @return
	 */
	ArvoreB fecha(String nomeArquivo);
	
	/**
	 * Esse método deverá inserir a chave na árvore se não existir, 
	 * caso contrário retorna falso indicando erro.
	 * @param chave
	 * @return
	 */
	boolean insere(int chave);
	
	/**
	 * Esse método deverá remove a chave especificada como parâmetro se existir, 
	 * caso contrário retorna falso indicando que a operação não foi executada.
	 * @param chave
	 * @return
	 */
	boolean remove(int chave);
	
	/**
	 * Esse método deverá pesquisar se a chave existe ou não na árvore,
	 * para isso deverá verificar se a Árvore foi aberta e se a chave
	 * existe seguindo o algoritmo de pesquisa na árvore B.
	 * @param chave
	 * @return
	 */
	boolean pesquisa(int chave);
}
