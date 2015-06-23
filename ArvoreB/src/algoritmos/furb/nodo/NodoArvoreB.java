package algoritmos.furb.nodo;

import java.util.HashMap;
import java.util.Map;

public class NodoArvoreB {
	private int capacidade;
	private Map<Integer, int[]> nodo;

	public Map<Integer, int[]> getNodo() {
		if (nodo == null) {
			nodo = new HashMap<Integer, int[]>();
		}
		return nodo;
	}

	public void setNodo(Map<Integer, int[]> nodo) {
		this.nodo = nodo;
	}

	public NodoArvoreB(int capacidade) {
		this.capacidade = capacidade;
		criaArvoreVazia();
	}

	public void criaArvoreVazia() {
		for (int i = 0; i < capacidade; i++) {
			getNodo().put(0, new int[]{0,0});
		}
	}
	
	//
	// public boolean insereChave(int chave) {
	// return false;
	// }
	//
	// public int getnSubArvores() {
	//
	// return nSubArvores;
	// }
	//
	// public boolean setFilhoI(int i, int filho) {
	// if (i < 0 || i > capacidade + 1) {
	// return false;
	// } else {
	// this.filho[i] = filho;
	// return true;
	// }
	// }
	//
	// public int getnChaves() {
	// return nChaves;
	// }
	//
	// public int getFilhoI(int i) {
	// if (i < 0 || i > capacidade)
	// return -1;
	// else
	// return filho[i];
	// }
	//
	// public int getChaveI(int i) {
	// if (i < 0 || i > capacidade - 1)
	// return -1;
	// else
	// return chave[i];
	// }
	//
	// public boolean setChaveI(int i, int chave) {
	// if (i < 0 || i >= capacidade)
	// return false;
	// else {
	// this.chave[i] = chave;
	// return true;
	// }
	// }
	//
	// public int getCapacidade() {
	// return capacidade;
	// }
	//
	// public void setCapacidade(int capacidade) {
	// this.capacidade = capacidade;
	// }
	//
	// public void setNChaves(int chaves) {
	// nChaves = chaves;
	// }
	//
	// public void setNSubArvores(int subArvores) {
	// nSubArvores = subArvores;
	// }
}
