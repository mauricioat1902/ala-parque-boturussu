package br.com.alaboturussu.utils;

import java.math.BigDecimal;

public class Utils {

    public static BigDecimal converterBigDecimal(String numero) {
        return new BigDecimal(numero.trim().replace(".", "").replace(",", "."));
    }
}
