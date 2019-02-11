package com.voucher.api.client.mapper;

import com.voucher.api.client.core.dto.VoucherDto;
import com.voucher.api.client.core.model.Voucher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VoucherMapper {

    VoucherMapper INSTANCE = Mappers.getMapper( VoucherMapper.class );

    VoucherDto mapToDto(Voucher voucher);

    List<VoucherDto> mapToDto(Collection<Voucher> vouchers);
}
