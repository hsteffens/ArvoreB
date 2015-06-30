package algoritmos.furb.arvore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import algoritmos.furb.facade.FacadeArquivo;
import algoritmos.furb.nodo.NodoArvoreB;

public class ArvoreB implements IArvoreB {
	private int ordem;
	private NodoArvoreB raiz;
	private String arquivo;

	public int getCapacidade() {
		return ordem * 2;
	}

	public int getNroSubArvores() {
		return getCapacidade() + 1;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public ArvoreB cria(int ordem, String nomeArquivo) {
		this.ordem = ordem;
		setArquivo(nomeArquivo);
		raiz = new NodoArvoreB(getCapacidade());
		try {
			FacadeArquivo.criarArquivo(nomeArquivo);
			FacadeArquivo.criaConteudoArquivo(nomeArquivo, criaArvoreVazia(raiz).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public ArvoreB abre(String NomeArquivo) {
		setArquivo(NomeArquivo);
		return null;
	}

	@Override
	public boolean insere(int chave) {
		List<NodoArvoreB> lNodoArvore = FacadeArquivo.recuperaRaizArvore(getArquivo());
		this.ordem = FacadeArquivo.getOrdem(getArquivo());
		List<NodoArvoreB> lNodo = new ArrayList<NodoArvoreB>();
		inseriElementos(chave, lNodoArvore);
		NodoArvoreB nodoRemover = null;
		int indiciIncersao = 0;
		laco:
		for (int i = 0; i < lNodoArvore.size(); i++) {
			for (Integer info : lNodoArvore.get(i).getNodo().keySet()) {
				if (raiz != null && raiz.getNodo().get(info) != null) {
					nodoRemover = lNodoArvore.get(i);
					indiciIncersao = i;
					break laco;
				}
				
			}
		}
		if (nodoRemover != null) {
			int quantidadeExistente = lNodoArvore.size();
			lNodoArvore.remove(nodoRemover);
			inseriElementosComp(chave, lNodo);
			if (lNodo.size() > 1) {
				for (Integer info : lNodo.get(0).getNodo().keySet()) {
					Map<Integer, int[]> nodo = lNodoArvore.get(quantidadeExistente - lNodo.size()).getNodo();
					int[] valores = new int[2];
					for (Integer chaveNodo : nodo.keySet()) {
						if (chaveNodo > info) {
							valores[0] = lNodo.get(0).getNodo().get(info)[0];
							valores[1] = nodo.get(chaveNodo)[0];
						}else{
							valores[0] = nodo.get(chaveNodo)[1];
							if (lNodo.get(0).getNodo().get(info)[1] != 0) {
								valores[1] = lNodo.get(0).getNodo().get(info)[1] + 1;
							}else{
								valores[1] = lNodo.get(0).getNodo().get(info)[1];
							}
						}
					}
					nodo.put(info, valores);
				} 
				lNodo.remove(0);
				lNodoArvore.addAll(indiciIncersao, lNodo);
			}else{
				lNodoArvore.addAll(indiciIncersao, lNodo);
			}
		}
		try {
			FacadeArquivo.criaConteudoArquivo(getArquivo(), atualizaArvore(lNodoArvore).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	private void inseriElementosComp(int chave,List<NodoArvoreB> lNodo) {
		if (raiz.getNodo().size() > 0) {
			NodoArvoreB nodo = new NodoArvoreB();
			Map<Integer, int []> mapa = new HashMap<Integer, int[]>();
			for (Integer indice : raiz.getNodo().keySet()) {
				mapa.put(indice, raiz.getNodo().get(indice));
			}
			nodo.setNodo(mapa);
			nodo.setQuantidadeAlocada(raiz.getQuantidadeAlocada());
			lNodo.add(nodo);
			raiz.getNodo().clear();
			inseriElementos(chave, lNodo);
			inseriElementosComp(chave, lNodo);
		}
	}

	private void inseriElementos(int chave, List<NodoArvoreB> lNodoArvore) {
		List<NodoArvoreB> nodosNovos = new ArrayList<NodoArvoreB>();
		paraLaco:
		for (NodoArvoreB nodoArvoreB : lNodoArvore) {
			List<Integer> listaChaves = new ArrayList<Integer>();
			TreeSet<Integer> chaves = new TreeSet<Integer>();
			chaves.addAll(nodoArvoreB.getNodo().keySet());
			listaChaves.addAll(chaves);
			for (int i = 0; i < listaChaves.size(); i++) {
				if(i == 0 && (chave < listaChaves.get(i) || listaChaves.get(i) == 0)){
					if (nodoArvoreB.getQuantidadeAlocada() < getCapacidade()) {
						int[] subArvores = nodoArvoreB.getNodo().get(listaChaves.get(i));
						boolean temFilhos = false;
						if (subArvores[0] > 0) {
							temFilhos = true;
							raiz = FacadeArquivo.recupersaRamificacao(getArquivo(), subArvores[0]);
							break paraLaco;
						}
						//Valida se não tem filhos, se não há filhos Inseri
						if (!temFilhos) {
							if (nodoArvoreB.getNodo().get(0) != null) {
								nodoArvoreB.getNodo().remove(0);
							}
							nodoArvoreB.getNodo().put(chave, new int[2]);
							break paraLaco;
						}
					}else{
						resolveEstouroNodo(chave, nodosNovos, nodoArvoreB);
					}
				}else if(listaChaves.size() == (i + 1) && chave > listaChaves.get(i)){
					if (nodoArvoreB.getQuantidadeAlocada() < getCapacidade()) {
						int[] subArvores = nodoArvoreB.getNodo().get(listaChaves.get(i));
						boolean temFilhos = false;
						if (subArvores[1] > 0) {
							temFilhos = true;
							raiz = FacadeArquivo.recupersaRamificacao(getArquivo(), subArvores[1]);
							break paraLaco;
						}
						//Valida se não tem filhos, se não há filhos Inseri
						if (!temFilhos) {
							nodoArvoreB.getNodo().put(chave, new int[2]);
							break paraLaco;
						}
					}else{
						resolveEstouroNodo(chave, nodosNovos, nodoArvoreB);
					}
				}else if (chave > listaChaves.get(i) && chave < listaChaves.get(i + 1) ) {
					if (nodoArvoreB.getQuantidadeAlocada() < getCapacidade()) {
						int[] subArvores = nodoArvoreB.getNodo().get(listaChaves.get(i));
						boolean temFilhos = false;
						if (subArvores[1] > 0) {
							temFilhos = true;
							raiz = FacadeArquivo.recupersaRamificacao(getArquivo(), subArvores[1]);
							break paraLaco;
						}
						//Valida se não tem filhos, se não há filhos Inseri
						if (!temFilhos) {
							nodoArvoreB.getNodo().put(chave, new int[2]);
							break paraLaco;
						}
						//Inseri
					}else{
						resolveEstouroNodo(chave, nodosNovos, nodoArvoreB);
					}
				}
			}
		}
		if (!nodosNovos.isEmpty()) {
			lNodoArvore.addAll(nodosNovos);
		}
	}

	private void resolveEstouroNodo(int chave, List<NodoArvoreB> nodosNovos,
			NodoArvoreB nodoArvoreB) {
		nodoArvoreB.getNodo().put(chave, new int[2]);
		TreeSet<Integer> infos = new TreeSet<Integer>(nodoArvoreB.getNodo().keySet());
		int indice = (infos.size() / 2) + 1;
		Iterator<Integer> iterator = infos.iterator();
		NodoArvoreB nodoArvoreMenor = new NodoArvoreB();
		NodoArvoreB nodoArvoreMaior = new NodoArvoreB();
		boolean divisor = false;
		for (int j = 0; j < infos.size(); j++) {
			if (iterator.hasNext()) {
				Integer info = iterator.next();
				if (j + 1 == indice) {
					int[] keys = nodoArvoreB.getNodo().get(info);
					nodoArvoreB.getNodo().clear();
					nodoArvoreB.getNodo().put(info, new int[]{keys[0] + 1,keys[1]+ 2});
					divisor = true;
					continue;
				}
				if (!divisor) {
					nodoArvoreMenor.setQuantidadeAlocada(nodoArvoreMenor.getQuantidadeAlocada()+1);
					nodoArvoreMenor.getNodo().put(info,  new int[2]);
				}
				if (divisor) {
					nodoArvoreMaior.setQuantidadeAlocada(nodoArvoreMaior.getQuantidadeAlocada()+1);
					nodoArvoreMaior.getNodo().put(info, new int[2]);
				}
			}
		}
		nodosNovos.add(nodoArvoreMenor);
		nodosNovos.add(nodoArvoreMaior);
	}

	@Override
	public boolean remove(int chave) {
		return false;
	}

	@Override
	public boolean pesquisa(int chave) {
		return false;
	}

	private StringBuilder criaArvoreVazia(NodoArvoreB nodo) {
		StringBuilder conteudo = new StringBuilder();
		conteudo.append("Ordem:");
		conteudo.append(ordem);
		conteudo.append("\n");
		for (Integer valor : nodo.getNodo().keySet()) {
			int[] subArvores = nodo.getNodo().get(valor);
			conteudo.append(subArvores[0]);
			conteudo.append("|");
			String campValor = addMakValor(valor);
			conteudo.append(campValor);
			conteudo.append("|");
			conteudo.append(subArvores[1]);
		}
		return conteudo;
	}

	private StringBuilder atualizaArvore(List<NodoArvoreB> lNodo) {
		StringBuilder conteudo = new StringBuilder();
		conteudo.append("Ordem:");
		conteudo.append(ordem);
		conteudo.append("\n");
		for (NodoArvoreB nodo : lNodo) {
			TreeSet<Integer> infos = new TreeSet<Integer>(nodo.getNodo().keySet());
			Integer ultInfo = infos.pollLast();
			infos.add(ultInfo);
			for (Integer valor : infos) {
				int[] subArvores = nodo.getNodo().get(valor);
				conteudo.append(subArvores[0]);
				conteudo.append("|");
				String campValor = addMakValor(valor);
				conteudo.append(campValor);
				conteudo.append("|");
				if (valor == ultInfo) {
					conteudo.append(subArvores[1]);
				}
			}
			conteudo.append("\n");
		}
		return conteudo;
	}

	private String addMakValor(Integer valor) {
		String campValor = "";
		if (valor >= 100 && valor < 1000) {
			campValor = "0" + valor;
		}else if(valor >= 10){
			campValor = "00" + valor;
		}else if(valor < 10){
			campValor = "000" + valor;
		}
		return campValor;
	}

	@Override
	public ArvoreB fecha(String nomeArquivo) {
		return null;
	}
}
