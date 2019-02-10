package com.voucher.api.v1.core.mapper;

import com.voucher.api.v1.core.model.Client;
import com.voucher.api.v1.core.dto.ClientDto;
import com.voucher.api.v1.core.dto.SearchClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );

    ClientDto mapToDto(Client client);

    List<ClientDto> mapToDto(Collection<Client> clients);

    Client mapToModel(SearchClientDto searchClientDto);
}
