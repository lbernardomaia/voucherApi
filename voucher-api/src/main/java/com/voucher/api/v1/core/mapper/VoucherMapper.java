package com.voucher.api.v1.core.mapper;

import com.voucher.api.v1.core.dto.voucher.CreateVoucherDto;
import com.voucher.api.v1.core.dto.voucher.SearchVoucherDto;
import com.voucher.api.v1.core.dto.voucher.VoucherDto;
import com.voucher.api.v1.core.model.Voucher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoucherMapper {

    VoucherMapper INSTANCE = Mappers.getMapper( VoucherMapper.class );

    VoucherDto mapToDto(Voucher voucher);

    List<VoucherDto> mapToDto(List<Voucher> vouchers);

    Voucher mapToModel(SearchVoucherDto searchVoucherDto);

    @Mapping(source = "balance", target = "originalBalance")
    Voucher mapToModel(CreateVoucherDto createVoucherDto);
}
