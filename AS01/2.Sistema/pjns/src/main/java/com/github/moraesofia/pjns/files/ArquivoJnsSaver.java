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
     *
     */
    private static Integer countRegistros = 1;

    /**
     * Salva um ArquivoJns como um arquivo de texto conforme formato definido pelo
     * esquema de integração.
     *
     * @param dados ArquivoJns com os dados a serem salvos.
     * @param file File no qual será salvo o texto gerado.
     */
    public static void save(ArquivoJns dados, File file) {
        StringBuilder conteudo = new StringBuilder();

        // Header do arquivo // tam 11 // ...
        String headerArquivo = headerArquivo("0011", "0000", "0", VERSAO);
        conteudo.append(headerArquivo);

        // Header lote // tam 14
        String headerLote = headerLote("0014", "0001", "1", "01", VERSAO);
        conteudo.append(headerLote);

        // resposta para obtenção de arquivo // tam 36 // tipo "AC"
        String regObtencao = BeginRegistro("0036", "0001", "2", countRegistros.toString(), "AC");
        countRegistros++;
        String condicao = "S" + formatStr("20", "Ocorrido com sucesso");
        conteudo.append(regObtencao).append(condicao);

        // rd's premiacao // tam 43 // tipo "AD"
        for (Premiacao p : dados.getPremiacoes()) {
            if (validPremiacao(p)) {
                throw new IllegalArgumentException("Premiação ID " + p.getId() + " inválida");

            } else {
                String headRePremiacao = BeginRegistro("0043", "0001", "2", countRegistros.toString(), "AD");
                String idP = formatNum("4", Integer.toString(p.getId()));
                String nomeP = formatStr("20", p.getNome());
                String anoP = formatNum("4", Integer.toString(p.getAno()));
                countRegistros++;

                conteudo.append(headRePremiacao).append(idP).append(nomeP).append(anoP);
            }

        }

        // rd's premio // tam 51 // tipo "AE"
        /**
         * TODO Flag IdFilme nulo?? como formata?
         */
        for (Premio pr : dados.getPremios()) {
            if (validPremio(pr)) {
                throw new IllegalArgumentException("Premio ID " + pr.getId() + " inválido");

            } else {
                String headRePremio = BeginRegistro("0051", "0001", "2", countRegistros.toString(), "AE");
                String idPr = formatNum("4", Integer.toString(pr.getId()));
                String catPr = formatStr("20", pr.getCategoria().getText());
                String idVenPr = formatNum("4", Integer.toString(pr.getId()));
                String idFilmPr = formatNum("4", Integer.toString(pr.getIdFilme()));
                String idPremPr = formatNum("4", Integer.toString(pr.getIdPremiacao()));
                countRegistros++;

                conteudo.append(headRePremio).append(idPr).append(catPr).append(idVenPr).append(idFilmPr)
                        .append(idPremPr);
            }

        }

        // rd's pessoa // tam 98 // tipo "AF"
        for (Pessoa pe : dados.getPessoas()) {
            if (validPessoa(pe)) {
                throw new IllegalArgumentException("Pessoa ID " + pe.getId() + " inválida");

            } else {
                String headRePessoa = BeginRegistro("0098", "0001", "2", countRegistros.toString(), "AF");
                String idPe = formatNum("4", Integer.toString(pe.getId()));
                String nomePe = formatStr("50", pe.getNome());
                String cargoPe = formatStr("20", pe.getCargo().getText());
                String nascP = formatNum("8", Integer.toString(pe.getNascimento()));
                String genPe = formatStr("1", pe.getGenero().getText());
                countRegistros++;

                conteudo.append(headRePessoa).append(idPe).append(nomePe).append(cargoPe).append(nascP).append(genPe);
            }

        }

        // rd's filme // tam 85 // tipo "AG"
        for (Filme f : dados.getFilmes()) {
            if (validFilm(f)) {
                throw new IllegalArgumentException("Filme ID " + f.getId() + " inválido");

            } else {
                String headReFilme = BeginRegistro("0085", "0001", "2", countRegistros.toString(), "AG");
                String idF = formatNum("4", Integer.toString(f.getId()));
                String tituloF = formatStr("30", f.getTitulo());
                String anoF = formatNum("4", Integer.toString(f.getAno()));
                String generoF = formatStr("20", f.getGenero());
                String idAtorF = formatNum("4", f.getIdAtor().toString());
                String idAtrizF = formatNum("4", f.getIdAtriz().toString());
                String idDiretorF = formatNum("4", f.getIdDiretor().toString());

                // A PARTIR DAQUI NÃO HAVERÃO MAIS REGISTROS - PARAR DE CONTAR!

                conteudo.append(headReFilme).append(idF).append(tituloF).append(anoF).append(generoF).append(idAtorF)
                        .append(idAtrizF).append(idDiretorF);
            }

        }

        // trailer lotes // tam 12 // tipoReg '3'
        String trailerLote = trailerLote("0012", "0001", "3", countRegistros.toString());
        conteudo.append(trailerLote);

        // trailer arquivo // tam 19 // tipoReg '9' ...
        String trailerArquivo = trailerArquivo("0019", "9999", "9", "0001", countRegistros.toString());
        conteudo.append(trailerArquivo);

        writeFile(conteudo.toString(), file);

    }

    private static String trailerArquivo(String tamReg, String numLote, String tipoReg, String qntLotesArq,
            String qntRegArq) {
        StringBuilder tail = new StringBuilder();
        tail.append(formatNum("4", tamReg));
        tail.append(formatNum("4", numLote));
        tail.append(formatNum("1", tipoReg));
        tail.append(formatNum("4", qntLotesArq));
        tail.append(formatNum("6", qntRegArq));

        return tail.toString();
    }

    private static String trailerLote(String tamReg, String numLote, String tipoReg, String qntRegLote) {
        StringBuilder tail = new StringBuilder();
        tail.append(formatNum("4", tamReg));
        tail.append(formatNum("4", numLote));
        tail.append(formatNum("1", tipoReg));
        tail.append(formatNum("3", qntRegLote));

        return tail.toString();
    }

    private static String headerLote(String tamReg, String numLote, String tipoReg, String tipolote, String versao) {
        StringBuilder head = new StringBuilder();
        head.append(formatNum("4", tamReg));
        head.append(formatNum("4", numLote));
        head.append(formatNum("1", tipoReg));
        head.append(formatNum("2", tipolote));
        head.append(versao);
        head.append("O"); // Obtenção (TAMANHO 1)

        return head.toString();
    }

    private static String headerArquivo(String tamReg, String numLote, String tipoReg, String versao) {
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
    private static String BeginRegistro(String tamReg, String numLote, String tipoReg, String numReg,
            String tipoRegDetalhe) {
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
        if (!pr.getIdVencedor().equals(null) && !validSize(Integer.toString(pr.getIdVencedor()), 4)) {
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

    private static String formatNum(String size, String num) {
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
