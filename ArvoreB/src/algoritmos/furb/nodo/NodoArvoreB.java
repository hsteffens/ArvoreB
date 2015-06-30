package algoritmos.furb.nodo;

import java.util.HashMap;
import java.util.Map;

public class NodoArvoreB{
	private int quantidadeAlocada;
	private Map<Integer, int[]> nodo;

	public Map<Integer, int[]> getNodo() {
		if (nodo == null) {
			nodo = new HashMap<Integer, int[]>();
		}
		return nodo;
	}

	public int getQuantidadeAlocada() {
		return quantidadeAlocada;
	}

	public void setQuantidadeAlocada(int quantidade) {
		this.quantidadeAlocada = quantidade;
	}

	public void setNodo(Map<Integer, int[]> nodo) {
		this.nodo = nodo;
	}

	public NodoArvoreB(int capacidade) {
		this.quantidadeAlocada = capacidade;
		criaArvoreVazia();
	}

	public NodoArvoreB() {
	}

	public void criaArvoreVazia() {
		for (int i = 0; i < quantidadeAlocada; i++) {
			getNodo().put(0, new int[]{0,0});
		}
	}
}
