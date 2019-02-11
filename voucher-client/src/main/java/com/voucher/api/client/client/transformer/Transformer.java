package com.voucher.api.client.client.transformer;

import java.io.IOException;
import java.util.function.Supplier;

public interface Transformer {

    String transform(Supplier<Object> action) throws IOException;
}
