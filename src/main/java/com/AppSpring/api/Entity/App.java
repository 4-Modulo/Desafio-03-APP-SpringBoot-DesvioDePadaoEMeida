package com.AppSpring.api.Entity;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class App {
    private List<Integer> Entradas;
    private BigDecimal media;
    private BigDecimal desvioPadrao;

}
