package algoritmos.furb.main;

import algoritmos.furb.arvore.ArvoreB;

public class Executavel {
	public static void main(String[] args) {
		ArvoreB arvoreB = new ArvoreB();
		String nomeArquivo = "arvore1.txt";
		
		
		arvoreB.cria(2, nomeArquivo);
		arvoreB.abre(nomeArquivo);
	}
}
