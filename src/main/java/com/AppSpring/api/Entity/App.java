package com.AppSpring.api.Entity;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;


public class App {
    @Getter @Setter
    private List<Integer> Entradas;
    @Setter @Getter
    private BigDecimal media;
    @Getter @Setter
    private BigDecimal desvioPadrao;

}
