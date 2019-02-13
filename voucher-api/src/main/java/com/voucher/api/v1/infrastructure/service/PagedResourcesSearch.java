package com.voucher.api.v1.infrastructure.service;

import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Component
public class PagedResourcesSearch<T> {

    public List<T> search(Function<Long, ResponseEntity<PagedResources<T>>> api) {
        List<T> result = new ArrayList<>();

        long nextPage = 0L;

        Collection<T> responseBody;

        do{
            ResponseEntity<PagedResources<T>> responseEntity = api.apply(nextPage);

            if (responseEntity.getBody() == null){
                break;
            }

            responseBody = responseEntity.getBody().getContent();

            result.addAll(responseBody);
            ++nextPage;
        }while (!responseBody.isEmpty());

        return result;
    }
}
