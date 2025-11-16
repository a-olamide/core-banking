package common.domian.dto;

import java.math.BigDecimal;

public record MoneyDto(BigDecimal amount, String currency) {}

