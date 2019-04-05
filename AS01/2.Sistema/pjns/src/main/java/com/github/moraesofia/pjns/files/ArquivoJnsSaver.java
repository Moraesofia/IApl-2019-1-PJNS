package com.github.moraesofia.pjns.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.github.moraesofia.pjns.entities.Filme;
import com.github.moraesofia.pjns.entities.Pessoa;
import com.github.moraesofia.pjns.entities.Premiacao;
import com.github.moraesofia.pjns.entities.Premio;

public class ArquivoJnsSaver {

	/**
	 * Versão do layout do arquivo JSN.
	 */
	private static String VERSAO = "01";

	/**
	 * Quantidade de lotes;
	 */
	private Integer countLotes = 1;
	/**
	 *
	 */
	private Integer countRegistros = 0;

	/**
	 * Salva um ArquivoJns como um arquivo de texto conforme formato definido
	 * pelo esquema de integração.
	 *
	 * @param dados
	 *            ArquivoJns com os dados a serem salvos.
	 * @param file
	 *            File no qual será salvo o texto gerado.
	 */
	public void save(ArquivoJns dados, File file) {
		StringBuilder conteudo = new StringBuilder();

		// Header do arquivo // tam 11 // ...
		String headerArquivo = headerArquivo(11, 0, 0, VERSAO);
		countRegistros++;
		conteudo.append(headerArquivo);

		// Header lote // tam 14
		String headerLote = headerLote(14, 1, 1, 1, VERSAO);
		countRegistros++;
		conteudo.append(headerLote);

		// resposta para obtenção de arquivo // tam 36 // tipo "AC"
		String regObtencao = beginRegistro(36, 1, 2, countLotes, "AC");
		String condicao = "S" + formatStr("20", "Ocorrido com sucesso");
		countRegistros++;
		countLotes++;
		conteudo.append(regObtencao).append(condicao);

		// rd's premiacao // tam 43 // tipo "AD"
		for (Premiacao p : dados.getPremiacoes()) {
			if (!validPremiacao(p)) {
				throw new IllegalArgumentException("Premiação ID " + p.getId() + " inválida");

			} else {
				String headRePremiacao = beginRegistro(43, 1, 2, countLotes, "AD");
				String idP = formatNum("4", p.getId());
				String nomeP = formatStr("20", p.getNome());
				String anoP = formatNum("4", p.getAno());
				countRegistros++;
				countLotes++;

				conteudo.append(headRePremiacao).append(idP).append(nomeP).append(anoP);

			}

		}

		// rd's pessoa // tam 98 // tipo "AF"
		for (Pessoa pe : dados.getPessoas()) {
			if (!validPessoa(pe)) {
				throw new IllegalArgumentException("Pessoa ID " + pe.getId() + " inválida");

			} else {
				String headRePessoa = beginRegistro(98, 1, 2, countLotes, "AF");
				String idPe = formatNum("4", pe.getId());
				String nomePe = formatStr("50", pe.getNome());
				String cargoPe = formatStr("20", pe.getCargo().getText());
				String nascP = formatNum("8", pe.getNascimento());
				String genPe = formatStr("1", pe.getGenero().getText());
				countRegistros++;
				countLotes++;

				conteudo.append(headRePessoa).append(idPe).append(nomePe).append(cargoPe).append(nascP).append(genPe);

			}

		}

		// rd's filme // tam 85 // tipo "AG"
		for (Filme f : dados.getFilmes()) {
			if (!validFilm(f)) {
				throw new IllegalArgumentException("Filme ID " + f.getId() + " inválido");

			} else {
				String headReFilme = beginRegistro(85, 1, 2, countLotes, "AG");
				String idF = formatNum("4", f.getId());
				String tituloF = formatStr("30", f.getTitulo());
				String anoF = formatNum("4", f.getAno());
				String generoF = formatStr("20", f.getGenero());
				String idAtorF = formatNum("4", f.getIdAtor());
				String idAtrizF = formatNum("4", f.getIdAtriz());
				String idDiretorF = formatNum("4", f.getIdDiretor());
				countRegistros++;
				countLotes++;

				conteudo.append(headReFilme).append(idF).append(tituloF).append(anoF).append(generoF).append(idAtorF)
						.append(idAtrizF).append(idDiretorF);
			}

		}

		// rd's premio // tam 51 // tipo "AE"
		/**
		 * TODO Flag IdFilme nulo?? como formata?
		 */
		for (Premio pr : dados.getPremios()) {
			if (!validPremio(pr)) {
				throw new IllegalArgumentException("Premio ID " + pr.getId() + " inválido");

			} else {
				String headRePremio = beginRegistro(51, 1, 2, countLotes, "AE");
				String idPr = formatNum("4", pr.getId());
				String catPr = formatStr("20", pr.getCategoria().getText());
				String idVenPr = formatNum("4", pr.getIdVencedor());
				String idFilmPr = formatNum("4", pr.getIdFilme());
				String idPremPr = formatNum("4", pr.getIdPremiacao());
				countRegistros++;
				countLotes++;

				conteudo.append(headRePremio).append(idPr).append(catPr).append(idVenPr).append(idFilmPr)
						.append(idPremPr);

			}

		}

		// trailer lotes // tam 13 // tipoReg '3'
		String trailerLote = trailerLote(13, 1, 3, countRegistros);
		conteudo.append(trailerLote);
		countRegistros++;

		// trailer arquivo // tam 19 // tipoReg '9' // Lotes: 3...
		String trailerArquivo = trailerArquivo(19, 9999, 9, 3, countRegistros + 1);
		conteudo.append(trailerArquivo);

		writeFile(conteudo.toString(), file);

	}

	private static String trailerArquivo(int tamReg, int numLote, int tipoReg, int qntLotesArq, int qntRegArq) {
		StringBuilder tail = new StringBuilder();
		tail.append(formatNum("4", tamReg));
		tail.append(formatNum("4", numLote));
		tail.append(formatNum("1", tipoReg));
		tail.append(formatNum("4", qntLotesArq));
		tail.append(formatNum("6", qntRegArq));

		return tail.toString();
	}

	private static String trailerLote(int tamReg, int numLote, int tipoReg, int qntRegLote) {
		StringBuilder tail = new StringBuilder();
		tail.append(formatNum("4", tamReg));
		tail.append(formatNum("4", numLote));
		tail.append(formatNum("1", tipoReg));
		tail.append(formatNum("4", qntRegLote));

		return tail.toString();
	}

	private static String headerLote(int tamReg, int numLote, int tipoReg, int tipolote, String versao) {
		StringBuilder head = new StringBuilder();
		head.append(formatNum("4", tamReg));
		head.append(formatNum("4", numLote));
		head.append(formatNum("1", tipoReg));
		head.append(formatNum("2", tipolote));
		head.append(versao);
		head.append("O"); // Obtenção (TAMANHO 1)

		return head.toString();
	}

	private static String headerArquivo(int tamReg, int numLote, int tipoReg, String versao) {
		StringBuilder head = new StringBuilder();
		head.append(formatNum("4", tamReg));
		head.append(formatNum("4", numLote));
		head.append(formatNum("1", tipoReg));
		head.append(versao);

		return head.toString();
	}

	/**
	 * Parte comum de todos os registros dentro do lote.
	 *
	 * @param tamReg
	 * @param numLote
	 * @param tipoReg
	 * @param numReg
	 * @param tipoRegDetalhe
	 * @return
	 */
	private static String beginRegistro(int tamReg, int numLote, int tipoReg, int numReg, String tipoRegDetalhe) {
		StringBuilder head = new StringBuilder();
		head.append(formatNum("4", tamReg));
		head.append(formatNum("4", numLote));
		head.append(formatNum("1", tipoReg));
		head.append(formatNum("4", numReg));
		head.append(formatStr("2", tipoRegDetalhe));

		return head.toString();
	}

	private static boolean validSize(String str, int size) {
		if (str.length() > size) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean validFilm(Filme f) {
		if (!validSize(Integer.toString(f.getAno()), 4)) {
			return false;
		}
		if (!validSize(f.getGenero(), 20)) {
			return false;
		}
		if (!validSize(f.getTitulo(), 30)) {
			return false;
		}
		if (!validSize(Integer.toString(f.getId()), 4)) {
			return false;
		}
		if (!validSize(Integer.toString(f.getIdAtor()), 4)) {
			return false;
		}
		if (!validSize(Integer.toString(f.getIdAtriz()), 4)) {
			return false;
		}
		if (!validSize(Integer.toString(f.getIdDiretor()), 4)) {
			return false;
		}
		return true;
	}

	public static boolean validPremiacao(Premiacao p) {
		if (!validSize(Integer.toString(p.getId()), 4)) {
			return false;
		}
		if (!validSize(Integer.toString(p.getAno()), 4)) {
			return false;
		}
		if (!validSize(p.getNome(), 20)) {
			return false;
		}

		return true;
	}

	public static boolean validPremio(Premio pr) {
		if (!validSize(pr.getCategoria().getText(), 20)) {
			return false;
		}
		if (!validSize(Integer.toString(pr.getId()), 4)) {
			return false;
		}
		if (pr.getIdVencedor() != null && !validSize(Integer.toString(pr.getIdVencedor()), 4)) {
			return false;
		}
		if (!validSize(Integer.toString(pr.getIdFilme()), 4)) {
			return false;
		}
		if (!validSize(Integer.toString(pr.getIdPremiacao()), 4)) {
			return false;
		}
		return true;
	}

	public static boolean validPessoa(Pessoa pe) {
		if (!validSize(pe.getNome(), 50)) {
			return false;
		}
		if (!validSize(pe.getCargo().getText(), 30)) {
			return false;
		}
		if (!validSize(Integer.toString(pe.getId()), 4)) {
			return false;
		}
		if (!validSize(Integer.toString(pe.getNascimento()), 8)) {
			return false;
		}
		if (!validSize(pe.getGenero().getText(), 1)) {
			return false;
		}
		return true;
	}

	private static String formatStr(String size, String alfa) {
		String form = "%-" + size + "s";
		String formatted = String.format(form, alfa);

		return formatted;
	}

	private static String formatNum(String size, Integer num) {
		if (num == null) {
			return formatStr(size, "");
		}
		String form = "%0" + size + "d";
		String formatted = String.format(form, num);

		return formatted;
	}

	private static void writeFile(String conteudo, File arq) {
		try {
			FileWriter fw = new FileWriter(arq);
			fw.write(conteudo);
			fw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
