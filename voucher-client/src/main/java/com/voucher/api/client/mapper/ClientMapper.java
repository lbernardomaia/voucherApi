package com.voucher.api.client.mapper;

import com.voucher.api.client.core.dto.SearchClientDto;
import com.voucher.api.client.core.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );

    Client mapToModel(SearchClientDto searchClientDto);
}
