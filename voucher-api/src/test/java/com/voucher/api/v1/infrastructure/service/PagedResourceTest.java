package com.voucher.api.v1.infrastructure.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PagedResourceTest {

    private PagedResourcesSearch pagedResourcesSearch;
    private PagedResources<String> pagedResource;

    @Before
    public void setUp() throws Exception {
        pagedResource = mock(PagedResources.class);
        pagedResourcesSearch = new PagedResourcesSearch<String>();
    }

    @Test
    public void givenPagedResourceSearch_whenHasNoResult_ThenReturnEmpty() {
        when(pagedResource.getContent()).thenReturn(Collections.emptyList());

        ResponseEntity<PagedResources<String>> responseEntity = new ResponseEntity<>(pagedResource, null, HttpStatus.OK);

        final List result = this.pagedResourcesSearch.search(responseEntityMock(responseEntity, 1l));

        assert result.isEmpty();
    }

    @Test
    public void givenPagedResourceSearch_whenHasResult_ThenListSizeMatch() {
        final List<String> expectedList = Arrays.asList("A", "B", "C");

        when(pagedResource.getContent()).thenReturn(expectedList);

        ResponseEntity<PagedResources<String>> responseEntity = new ResponseEntity<>(pagedResource, null, HttpStatus.OK);

        final List result = this.pagedResourcesSearch.search(responseEntityMock(responseEntity, 1L));

        assert result.size() == expectedList.size();
    }

    public Function<Long, ResponseEntity<PagedResources<String>>> responseEntityMock(ResponseEntity<PagedResources<String>> responseEntity, Long repeatCalls) {
        return nextPage -> {
            if (nextPage < repeatCalls){
                return responseEntity;
            }else{
                when(pagedResource.getContent()).thenReturn(Collections.emptyList());
                return new ResponseEntity<>(pagedResource, null, HttpStatus.OK);
            }
        };
    }

    @Test
    public void givenPagedResourceSearch_whenHas3PagesResult_ThenListSizeIs9() {
        final List<String> expectedList = Arrays.asList("A", "B", "C");

        when(pagedResource.getContent()).thenReturn(expectedList);

        ResponseEntity<PagedResources<String>> responseEntity = new ResponseEntity<>(pagedResource, null, HttpStatus.OK);

        final List result = this.pagedResourcesSearch.search(responseEntityMock(responseEntity, 3L));

        assert result.size() == 9;
    }

}
