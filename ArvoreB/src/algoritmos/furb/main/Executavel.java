package algoritmos.furb.main;

import algoritmos.furb.arvore.ArvoreB;

public class Executavel {
	public static void main(String[] args) {
		ArvoreB arvoreB = new ArvoreB();
		String nomeArquivo = "arvore2.txt";
//		arvoreB.cria(2, nomeArquivo);
		arvoreB.abre(nomeArquivo);
//		arvoreB.insere(10);
//		arvoreB.insere(20);
//		arvoreB.insere(30);
//		arvoreB.insere(40);
//		arvoreB.insere(1);
//		arvoreB.insere(5);
//		arvoreB.insere(15);
//		arvoreB.insere(25);
//		arvoreB.insere(35);
//		arvoreB.insere(45);
		arvoreB.insere(9);		
		
	}
}
