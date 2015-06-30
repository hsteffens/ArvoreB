package algoritmos.furb.facade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algoritmos.furb.nodo.NodoArvoreB;

public class FacadeArquivo {
	private static FileOutputStream fileOutputStream;
	
	public static boolean criarArquivo(String arquivo) throws IOException{
		File file = new File(arquivo);
		if (file.exists()) {
			return false;
		}
		Files.createFile(file.toPath());
		return file.exists();
	}
	
	public static void criaConteudoArquivo(String arquivo, String conteudo) throws IOException{
		fileOutputStream = new FileOutputStream(new File(arquivo));
		byte[] bytes = conteudo.getBytes();
		try {
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileOutputStream.close();
		}
	}

	public static List<NodoArvoreB> recuperaRaizArvore(String arquivo) {
		List<NodoArvoreB> listaNodo = new ArrayList<>();
		BufferedReader reader = null;

		try {
		    File file = new File(arquivo);
		    reader = new BufferedReader(new FileReader(file));
		    String line = null;
		    line = reader.readLine();
		    while ((line = reader.readLine()) != null) {
		    	NodoArvoreB nodoArvoreB = new NodoArvoreB();
				nodoArvoreB.getNodo().putAll(getNodo(line));
		    	nodoArvoreB.setQuantidadeAlocada(nodoArvoreB.getNodo().keySet().size());
		    	listaNodo.add(nodoArvoreB);
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		return listaNodo;
	}
	
	public static int getOrdem(String arquivo){
		BufferedReader reader = null;
		String valor = "";
		try {
		    File file = new File(arquivo);
		    reader = new BufferedReader(new FileReader(file));
		    String linhaOrdem = reader.readLine();
		    int indice = linhaOrdem.indexOf(":");
			valor = linhaOrdem.substring(indice+1);
		} catch (IOException e) {
		    e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Integer.parseInt(valor);
	}	
	
	private static Map<Integer, int[]> getNodo(String linhaNodo){
		HashMap<Integer, int[]> mapaNodo = new HashMap<Integer, int[]>();
		montaMapa(linhaNodo, mapaNodo);
		return mapaNodo;
	}

	//0|0000|0|0000|0
	private static void montaMapa(String linhaNodo,HashMap<Integer, int[]> mapaNodo) {
		String chave = "";
		int[] posicoesFilhos = new int[0];
		String posicaoFilho = "";
		boolean ehChave = false;
		int indiciFilho = 0;
		int indeceUltMarcador = 0;
		for (int i = 0; i < linhaNodo.length(); i++) {
			Character caracter = linhaNodo.charAt(i);
			if (!caracter.equals('|')) {
				if (posicoesFilhos.length > 0 && posicoesFilhos.length < 2 && ehChave) {
					chave = chave.concat(caracter.toString());
					indiciFilho = 1;
				}else{
					posicaoFilho = posicaoFilho.concat(caracter.toString());
					int valor = 0;
					if (posicoesFilhos.length > 0) {
						valor = posicoesFilhos[0];
						ehChave = true;
					}
					posicoesFilhos  = new int[posicoesFilhos.length + 1];
					if (valor > 0) {
						posicoesFilhos[0] = valor;
					}
					posicoesFilhos[indiciFilho] = Integer.parseInt(posicaoFilho);
				}
			}else{
				indeceUltMarcador = i;
				ehChave = !ehChave;
				posicaoFilho = "";
			}
			
			if (i + 1 == linhaNodo.length()) {
				ehChave = !ehChave;
			}
			if (posicoesFilhos.length == 2 && !ehChave) {
				mapaNodo.put(Integer.parseInt(chave), posicoesFilhos);
				montaMapa(linhaNodo.substring(indeceUltMarcador - 1), mapaNodo);
			}
		}
	}

	public static NodoArvoreB recupersaRamificacao(String arquivo, int linha) {
		BufferedReader reader = null;
		NodoArvoreB nodoArvoreB = new NodoArvoreB();
		try {
		    File file = new File(arquivo);
		    reader = new BufferedReader(new FileReader(file));
		    for (int i = 0; i < linha + 1; i++) {
		    	reader.readLine();
			}
		    String line = reader.readLine();
		    nodoArvoreB.setNodo(getNodo(line));
		    nodoArvoreB.setQuantidadeAlocada(nodoArvoreB.getNodo().keySet().size());
		} catch (IOException e) {
		    e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return nodoArvoreB;
	}
	
}
