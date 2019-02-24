package com.voucher.api.client.core.mapper;

import com.voucher.api.client.core.dto.ClientDto;
import com.voucher.api.client.core.dto.SearchClientDto;
import com.voucher.api.client.core.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );

    Client mapToModel(SearchClientDto searchClientDto);

    ClientDto mapToDto(Client client);

    List<ClientDto> mapToDto(Collection<Client> clients);
}
