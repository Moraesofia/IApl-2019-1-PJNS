package com.github.moraesofia.pjns.files;

import com.github.moraesofia.pjns.entities.Filme;
import com.github.moraesofia.pjns.entities.Pessoa;
import com.github.moraesofia.pjns.entities.Premiacao;
import com.github.moraesofia.pjns.entities.Premio;
import com.github.moraesofia.pjns.entities.enums.CargoEnum;
import com.github.moraesofia.pjns.entities.enums.CategoriaEnum;
import com.github.moraesofia.pjns.entities.enums.GeneroEnum;
import com.github.moraesofia.pjns.files.enums.TipoLoteEnum;
import com.github.moraesofia.pjns.files.enums.TipoRegistroDetalheEnum;
import com.github.moraesofia.pjns.files.enums.TipoRegistroEnum;
import com.github.moraesofia.pjns.files.exceptions.FileFormatException;
import com.github.moraesofia.pjns.files.exceptions.UnsupportedLayoutException;
import com.github.moraesofia.pjns.files.exceptions.UnsupportedTypeException;
import com.github.moraesofia.pjns.files.registros.*;

import java.io.*;

public class ArquivoJnsLoader implements AutoCloseable {

    private final boolean permitirQualquerModo;
    private final FileReader fileReader;

    private BufferedReader reader;
    private ArquivoJns dados = new ArquivoJns();

    private int currentPosition = 0;
    private int currentLote = 0;
    private int totalLotes = 0; // Contagem de registros no arquivo, incluindo header e trailer
    private int totalRegistros = 0; // Contagem de registros no arquivo, incluindo header e trailer

    public ArquivoJnsLoader(File file, boolean permitirQualquerModo) throws FileNotFoundException {
        this.permitirQualquerModo = permitirQualquerModo;
        this.fileReader = new FileReader(file);
    }

    @Override
    public void close() throws Exception {
        if (reader != null)
            reader.close();
        if (fileReader != null)
            fileReader.close();
    }

    /**
     * Obtém um ArquivoJns a partir de um arquivo de texto que
     * siga o formato definido pelo esquema de integração (como
     * os arquivos gerados no método "save").
     *
     * @return ArquivoJns com os dados obtidos do arquivo lido.
     * @throws IOException Caso não seja possível ler o arquivo.
     */
    public ArquivoJns load() throws IOException {
        reader = new BufferedReader(fileReader);
        currentLote = 0;
        totalLotes = 0;
        totalRegistros = 0;
        currentPosition = 0;

        // Lê o primeiro registro, que deve ser o header do arquivo.
        Registro registro = readRegistro();
        int lote = registro.getNumeroLote();
        TipoRegistroEnum tipo = registro.getTipoRegistro();
        totalRegistros++;

        if (lote != 0 || tipo != TipoRegistroEnum.FILE_HEADER) {
            throw new FileFormatException("O primeiro lote deve ser o header do arquivo.",
                    currentPosition);
        }

        // O lote do file header só tem ele, então já contabiliza o lote como lido.
        totalLotes++;

        // Lê o resto do arquivo com base na versão do layout.
        RegistroFileHeader fileHeader = readFileHeader(registro);
        if (fileHeader.getVersaoLayoutArquivo() == 1) {
            return readFile();
        } else {
            throw new UnsupportedLayoutException(fileHeader.getVersaoLayoutArquivo());
        }
    }


    private ArquivoJns readFile() throws IOException {
        // Vai lendo os lotes no arquivo (até encontrar o file trailer)
        while (true) {
            Registro registro = readRegistro();
            int lote = registro.getNumeroLote();
            TipoRegistroEnum tipo = registro.getTipoRegistro();
            totalRegistros++;

            // Vê se chegou no file trailer
            if (lote == 9999 || tipo == TipoRegistroEnum.FILE_TRAILER) {
                if (lote != 9999) {
                    throw new FileFormatException("O trailer do arquivo deve ficar no lote 9999."
                            , currentPosition);
                }
                if (tipo != TipoRegistroEnum.FILE_TRAILER) {
                    throw new FileFormatException("O lote 9999 deve ser o trailer do arquivo.",
                            currentPosition);
                }

                // O lote do file trailer só tem ele, então já contabiliza o lote como lido.
                totalLotes++;

                // Checa os totais do file trailer.
                RegistroFileTrailer fileTrailer = readFileTrailer(registro);
                if (fileTrailer.getQuantidadeLotes() != totalLotes) {
                    throw new FileFormatException("O arquivo não possui a quantidade esperada de " +
                            "lotes.", currentPosition);
                }
                if (fileTrailer.getQuantidadeRegistros() != totalRegistros) {
                    throw new FileFormatException("O arquivo não possui a quantidade esperada de " +
                            "registros.", currentPosition);
                }

                // Terminou o arquivo
                return dados;
            }

            // Como não é file trailer, deve ser um lote header
            if (tipo != TipoRegistroEnum.LOTE_HEADER) {
                throw new FileFormatException("O primeiro registro do lote deve ser o header " +
                        "do lote.", currentPosition);
            }
            if (lote != currentLote + 1) {
                throw new FileFormatException("Headers de lotes devem possuir numero igual ao " +
                        "lote anterior acrescido de 1.", currentPosition);
            }

            // Lê o header do lote
            RegistroLoteHeader loteHeader = readLoteHeader(registro);

            // Lê o resto do lote com base no tipo
            if (loteHeader.getTipoLote() == TipoLoteEnum.INSERCAO_OU_OBTENCAO_DE_DADOS) {
                readLote_InsercaoOuObtencaoDeDados(loteHeader);
            } else {
                throw new UnsupportedTypeException();
            }
        }
    }

    private void readLote_InsercaoOuObtencaoDeDados(RegistroLoteHeader header) throws IOException {
        if (header.getTamanho() < 14)
            throw new FileFormatException("O tamanho do registro não pode ser menor que o mínimo " +
                    "(14)", currentPosition - 8);

        currentLote = header.getNumeroLote();
        int currentRegistro = 0;
        int totalRegistrosLote = 1; // Contagem de registros no lote, incluindo header e trailer

        // Lê os dados exclusivos desse lote header.
        String insercaoOuObtencao = readString(1);

        // Pula possível espaço sobrando no header
        int tamanhoLido = currentPosition - header.getPosicaoInicial();
        int tamanhoFaltando = header.getTamanho() - tamanhoLido;
        if (tamanhoFaltando > 0)
            skip(tamanhoFaltando);

        if (!permitirQualquerModo) {
            // Como estamos carregando, o modo deveria ser inserção
            if (!insercaoOuObtencao.equals("I")) {
                throw new IllegalArgumentException("Não é possível inserir um ArquivoJns que não " +
                        "esteja no modo inserção.");
            }
        }

        // Vai lendo registros de detalhe (até achar o trailer do lote)
        while (true) {
            Registro registro = readRegistro();
            int lote = registro.getNumeroLote();
            TipoRegistroEnum tipo = registro.getTipoRegistro();
            totalRegistros++;
            totalRegistrosLote++;

            // Checa se avançou de lote indevidamente
            if (lote != currentLote) {
                throw new FileFormatException("O número do lote não pode avançar antes o trailer " +
                        "do lote anterior", currentPosition);
            }

            // Checa se chegou no lote trailer
            if (tipo == TipoRegistroEnum.LOTE_TRAILER) {
                if (registro.getTamanho() < 13)
                    throw new FileFormatException("O tamanho do registro não pode ser menor que o" +
                            " mínimo (13)", currentPosition - 4);

                // Checa os totais do lote trailer.
                RegistroLoteTrailer loteTrailer = readLoteTrailer(registro);

                // Pula possível espaço sobrando no trailer
                tamanhoLido = currentPosition - loteTrailer.getPosicaoInicial();
                tamanhoFaltando = loteTrailer.getTamanho() - tamanhoLido;
                if (tamanhoFaltando > 0)
                    skip(tamanhoFaltando);

                // Verifica totais
                if (loteTrailer.getQuantidadeRegistros() != totalRegistrosLote) {
                    throw new FileFormatException("O lote não possui a quantidade esperada de " +
                            "registros.", currentPosition);
                }

                totalLotes++;
                break;
            }

            // Checa se chegou em algum lote que não é detalhe
            if (tipo != TipoRegistroEnum.LOTE_DETALHE) {
                throw new FileFormatException("O lote deve acabar (com trailer) antes de inserir " +
                        "algum registro que não seja de detalhe.", currentPosition);
            }

            // Lê o registro de detalhe
            RegistroLoteDetalhe detalhe = readDetalhe(registro);

            // Checa se avançou de registro indevidamente
            if (detalhe.getNumeroRegistro() != currentRegistro + 1) {
                throw new FileFormatException("Registros de detalhe devem possuir numero igual ao" +
                        " registro anterior acrescido de 1.", currentPosition);
            }

            // Lê o resto do registro de detalhe com base no tipo
            currentRegistro++;
            switch (detalhe.getTipoRegistroDetalhe()) {
                case RESPOSTA_INSERCAO_DADOS:
                case RESPOSTA_OBTENCAO_DADOS:
                    // Ignora pois não tem impacto na obtenção dos dados.
                    break;
                case DADOS_PREMIACAO:
                    readDetalhe_DadosPremiacao(detalhe);
                    break;
                case DADOS_PREMIO:
                    readDetalhe_DadosPremio(detalhe);
                    break;
                case DADOS_PESSOA:
                    readDetalhe_DadosPessoa(detalhe);
                    break;
                case DADOS_FILME:
                    readDetalhe_DadosFilme(detalhe);
                    break;
                default:
                    throw new UnsupportedTypeException();
            }

            // Pula possível espaço sobrando no registro de detalhe
            tamanhoLido = currentPosition - detalhe.getPosicaoInicial();
            tamanhoFaltando = detalhe.getTamanho() - tamanhoLido;
            if (tamanhoFaltando > 0)
                skip(tamanhoFaltando);
        }
    }

    private Registro readRegistro() throws IOException {
        Registro registro = new Registro();
        registro.setPosicaoInicial(currentPosition);
        registro.setTamanho(readInt(4));
        registro.setNumeroLote(readInt(4));
        registro.setTipoRegistro(TipoRegistroEnum.fromCode(readInt(1)));
        return registro;
    }

    private RegistroFileHeader readFileHeader(Registro registro) throws IOException {
        if (registro.getTamanho() < 11)
            throw new FileFormatException("O tamanho do registro não pode ser menor que o mínimo " +
                    "(11)", currentPosition - 4);

        RegistroFileHeader fileHeader = new RegistroFileHeader(registro);
        fileHeader.setVersaoLayoutArquivo(readInt(2));

        // Pula possíveis espaços sobrando
        int tamanhoLido = currentPosition - registro.getPosicaoInicial();
        int tamanhoFaltando = registro.getTamanho() - tamanhoLido;
        if (tamanhoFaltando > 0)
            skip(tamanhoFaltando);
        return fileHeader;
    }

    private RegistroFileTrailer readFileTrailer(Registro registro) throws IOException {
        if (registro.getTamanho() < 19)
            throw new FileFormatException("O tamanho do registro não pode ser menor que o mínimo " +
                    "(19)", currentPosition - 3);

        RegistroFileTrailer fileTrailer = new RegistroFileTrailer(registro);
        fileTrailer.setQuantidadeLotes(readInt(4));
        fileTrailer.setQuantidadeRegistros(readInt(6));

        // Pula possíveis espaços sobrando
        int tamanhoLido = currentPosition - registro.getPosicaoInicial();
        int tamanhoFaltando = registro.getTamanho() - tamanhoLido;
        if (tamanhoFaltando > 0)
            skip(tamanhoFaltando);
        return fileTrailer;
    }

    private RegistroLoteHeader readLoteHeader(Registro registro) throws IOException {
        RegistroLoteHeader loteHeader = new RegistroLoteHeader(registro);
        loteHeader.setTipoLote(TipoLoteEnum.fromCode(readInt(2)));
        loteHeader.setVersaoLayoutLote(readInt(2));
        return loteHeader;
    }

    private RegistroLoteTrailer readLoteTrailer(Registro registro) throws IOException {
        RegistroLoteTrailer loteTrailer = new RegistroLoteTrailer(registro);
        loteTrailer.setQuantidadeRegistros(readInt(4));
        return loteTrailer;
    }

    private RegistroLoteDetalhe readDetalhe(Registro registro) throws IOException {
        RegistroLoteDetalhe loteDetalhe = new RegistroLoteDetalhe(registro);
        loteDetalhe.setNumeroRegistro(readInt(4));
        loteDetalhe.setTipoRegistroDetalhe(TipoRegistroDetalheEnum.fromCode(readString(2)));
        return loteDetalhe;
    }

    private void readDetalhe_DadosFilme(RegistroLoteDetalhe registro) throws IOException {
        if (registro.getTamanho() < 85)
            throw new FileFormatException("O tamanho do registro não pode ser menor que o mínimo " +
                    "(85)", currentPosition - 10);

        Filme filme = new Filme();
        filme.setId(readInt(4));
        filme.setTitulo(readString(30));
        filme.setAno(readInt(4));
        filme.setGenero(readString(20));
        filme.setIdAtor(readInteger(4));
        filme.setIdAtriz(readInteger(4));
        filme.setIdDiretor(readInteger(4));
        dados.getFilmes().add(filme);
    }


    private void readDetalhe_DadosPremio(RegistroLoteDetalhe registro) throws IOException {
        if (registro.getTamanho() < 51)
            throw new FileFormatException("O tamanho do registro não pode ser menor que o mínimo " +
                    "(51)", currentPosition - 10);

        Premio premio = new Premio();
        premio.setId(readInt(4));
        premio.setCategoria(CategoriaEnum.fromText(readString(20)));
        premio.setIdVencedor(readInteger(4));
        premio.setIdFilme(readInt(4));
        premio.setIdPremiacao(readInt(4));
        dados.getPremios().add(premio);
    }

    private void readDetalhe_DadosPessoa(RegistroLoteDetalhe registro) throws IOException {
        if (registro.getTamanho() < 98)
            throw new FileFormatException("O tamanho do registro não pode ser menor que o mínimo " +
                    "(98)", currentPosition - 10);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(readInt(4));
        pessoa.setNome(readString(50));
        pessoa.setCargo(CargoEnum.fromText(readString(20)));
        pessoa.setNascimento(readString(8));
        pessoa.setGenero(GeneroEnum.fromText(readString(1)));
        dados.getPessoas().add(pessoa);
    }

    private void readDetalhe_DadosPremiacao(RegistroLoteDetalhe registro) throws IOException {
        if (registro.getTamanho() < 43)
            throw new FileFormatException("O tamanho do registro não pode ser menor que o mínimo " +
                    "(43)", currentPosition - 10);

        Premiacao premiacao = new Premiacao();
        premiacao.setId(readInt(4));
        premiacao.setNome(readString(20));
        premiacao.setAno(readInt(4));
        dados.getPremiacoes().add(premiacao);
    }

    private Integer readInteger(int length) throws IOException {
        char[] chars = new char[length];
        if (reader.read(chars) < 0)
            throw new FileFormatException("Campo faltando caracteres", currentPosition);
        currentPosition += length;
        String string = new String(chars);
        if (string.trim().isEmpty())
            return null;
        else
            return Integer.parseInt(string);
    }

    private Integer readInt(int length) throws IOException {
        char[] chars = new char[length];
        if (reader.read(chars) < 0)
            throw new FileFormatException("Campo faltando caracteres", currentPosition);
        currentPosition += length;
        String string = new String(chars);
        if (string.trim().isEmpty())
            return null;
        else
            return Integer.parseInt(string);
    }

    private String readString(int length) throws IOException {
        char[] chars = new char[length];
        if (reader.read(chars) < 0)
            throw new FileFormatException("Campo faltando caracteres", currentPosition);
        currentPosition += length;
        return new String(chars).trim();
    }

    private void skip(int length) throws IOException {
        long skipped = reader.skip(length);
        if (skipped < length)
            throw new FileFormatException("Campo faltando caracteres", currentPosition);
        currentPosition += length;
    }
}
