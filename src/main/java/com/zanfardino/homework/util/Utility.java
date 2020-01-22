package com.zanfardino.homework.util;

import com.zanfardino.homework.exceptions.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    private static final Logger LOG = LogManager.getLogger(Utility.class);

    public Utility() {
        System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
    }

    public String marshallPOJO(final Object pojo, final Class genericClass) {
        LOG.debug("Marshalling POJO ({}) into JSON string...", genericClass.toString());
        final StringWriter json = new StringWriter();
        try {
            final JAXBContext context = JAXBContext.newInstance(genericClass);
            final Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);

            marshaller.marshal(pojo, json);
        } catch (Exception e) {
            LOG.error("Failed to marshal POJO ({}) successfully: {}", pojo, e.getStackTrace());
            throw new JsonProcessingException("Failed to marshal (" + pojo + ") successfully", e);
        }
        return json.toString();
    }

    public <T> List<T> unmarshalJSON(final String json, final Class genericClass) {
        LOG.debug("Unmarshalling JSON string ({}) into POJO ...", json);
        List<T> results = new ArrayList<>();
        try {
            final JAXBContext context = JAXBContext.newInstance(genericClass);
            final Unmarshaller unmarshaller = context.createUnmarshaller();

            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);

            results = (List<T>) unmarshaller.unmarshal(new StreamSource(new StringReader(json)), genericClass).getValue();
        } catch (Exception e) {
            LOG.error("Failed to unmarshal JSON ({}) successfully: {}", json, e.getStackTrace());
            throw new JsonProcessingException("Failed to unmarshal JSON (" + json + ") successfully", e);
        }
        return results;
    }

    public <T> List<T> unmarshalFile(final File file, final Class genericClass) {
        LOG.debug("Unmarshalling JSON fail ({}) into POJO ...", file.getName());
        List<T> results = new ArrayList<>();
        try {
            final JAXBContext context = JAXBContext.newInstance(genericClass);
            final Unmarshaller unmarshaller = context.createUnmarshaller();

            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);

            results = (List<T>) unmarshaller.unmarshal(new StreamSource(file), genericClass).getValue();
        } catch (Exception e) {
            LOG.error("Failed to unmarshal JSON file ({}) successfully: {}", file.getName(), e.getStackTrace());
            throw new JsonProcessingException("Failed to unmarshal JSON file (" + file.getName() + ") successfully", e);
        }
        return results;
    }

}
