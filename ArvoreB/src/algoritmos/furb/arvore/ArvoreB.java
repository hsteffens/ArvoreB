package algoritmos.furb.arvore;

import java.io.IOException;

import algoritmos.furb.facade.FacadeArquivo;
import algoritmos.furb.nodo.NodoArvoreB;

public class ArvoreB implements IArvoreB {
	private int ordem;
	private NodoArvoreB raiz;

	public int getCapacidade() {
		return ordem * 2;
	}

	public int getNroSubArvores() {
		return getCapacidade() + 1;
	}

	@Override
	public ArvoreB cria(int ordem, String nomeArquivo) {
		this.ordem = ordem;
		raiz = new NodoArvoreB(getCapacidade());
		try {
			FacadeArquivo.criarArquivo(nomeArquivo);
			FacadeArquivo.criaConteudoArquivo(nomeArquivo, criaArvoreVazia()
					.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public ArvoreB abre(String NomeArquivo) {
		return null;
	}

	@Override
	public boolean insere(int chave) {
		return false;
	}

	@Override
	public boolean remove(int chave) {
		return false;
	}

	@Override
	public boolean pesquisa(int chave) {
		return false;
	}

	private StringBuilder criaArvoreVazia() {
		StringBuilder conteudo = new StringBuilder();
		conteudo.append("Ordem:");
		conteudo.append(ordem);
		conteudo.append("\n");
		for (Integer valor : raiz.getNodo().keySet()) {
			int[] subArvores = raiz.getNodo().get(valor);
			addIndiciSubArvore(conteudo, subArvores[0]);
			String campValor = addMakValor(valor);
			conteudo.append(campValor);
			addIndiciSubArvore(conteudo, subArvores[1]);
		}
		return conteudo;
	}

	private String addMakValor(Integer valor) {
		String campValor = "";
		if (valor > 100 && valor < 1000) {
			campValor = "0" + valor;
		}else if(valor > 10){
			campValor = "00" + valor;
		}else if(valor < 10){
			campValor = "000" + valor;
		}
		return campValor;
	}

	private void addIndiciSubArvore(StringBuilder conteudo, int subArvore) {
		conteudo.append("|");
		conteudo.append(subArvore);
		conteudo.append("|");
	}

	@Override
	public ArvoreB fecha(String nomeArquivo) {
		return null;
	}
}
