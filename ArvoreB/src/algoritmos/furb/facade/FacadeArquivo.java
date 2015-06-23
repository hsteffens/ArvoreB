package algoritmos.furb.facade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

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
	
	public static void criaConteudoArquivo(String arquivo,String conteudo) throws IOException{
		fileOutputStream = new FileOutputStream(new File(arquivo));
		byte[] bytes = conteudo.getBytes();
		try {
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			fileOutputStream.close();
		}
	}
}