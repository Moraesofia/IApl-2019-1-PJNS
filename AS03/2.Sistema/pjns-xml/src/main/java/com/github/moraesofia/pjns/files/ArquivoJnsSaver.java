package com.github.moraesofia.pjns.files;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

public class ArquivoJnsSaver {

    /**
     * Salva um XML com todos os dados de um objeto ArquivoJns.
     *
     * @param dados ArquivoJns com os dados a serem salvos.
     * @param file  File no qual será salvo o XML gerado.
     * @throws IOException
     */
    public void save(ArquivoJns dados, File file) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(ArquivoJns.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(dados, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
