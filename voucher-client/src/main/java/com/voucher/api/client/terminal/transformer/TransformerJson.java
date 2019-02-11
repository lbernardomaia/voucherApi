package com.voucher.api.client.terminal.transformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Component
public class TransformerJson implements Transformer {

    private static Logger LOG = LoggerFactory.getLogger(TransformerJson.class);

    private ObjectMapper objectMapper;

    @Autowired
    public TransformerJson(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String transform(Supplier<Object> action) throws IOException {
        Object result;
        try{
            result = action.get();
        }catch (HttpClientErrorException e){
            result = objectMapper.readValue(e.getResponseBodyAsString(), Object.class);
        }

        return convertToJson(result).orElse("");
    }

    private Optional<String> convertToJson(Object object) {
        try {
            return of(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
        }

        return empty();
    }
}
