package com.github.moraesofia.pjns.files;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;

public class ArquivoJnsLoader {

    /**
     * Obtém um objeto ArquivoJns a partir de um arquivo XML.
     *
     * @return ArquivoJns com os dados obtidos do XML lido.
     * @throws FileNotFoundException Caso o arquivo não seja encontrado.
     */
    public ArquivoJns load(File file) throws FileNotFoundException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ArquivoJns.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ArquivoJns arquivoJns = (ArquivoJns) jaxbUnmarshaller.unmarshal(file);
            return arquivoJns;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
