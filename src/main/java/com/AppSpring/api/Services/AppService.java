package com.AppSpring.api.Services;

import com.AppSpring.api.Entity.App;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class AppService {
    public App create (App app){
        List<Integer> list = app.getEntradas();

        if (list == null || list.size() < 20) {
            throw new IllegalArgumentException("A lista deve conter pelo menos 20 números");
        }

        BigDecimal sum = BigDecimal.ZERO;
        for (Integer number : list) {
            sum = sum.add(BigDecimal.valueOf(number));
        }
        BigDecimal media = sum.divide(BigDecimal.valueOf(list.size()), 2, RoundingMode.HALF_UP);
        app.setMedia(media);

        BigDecimal sumOf = BigDecimal.ZERO;
        for (Integer number : list) {
            BigDecimal difference = BigDecimal.valueOf(number).subtract(media);
            sumOf = sumOf.add(difference.pow(2));
        }
        BigDecimal variance = sumOf.divide(BigDecimal.valueOf(list.size()), 2, RoundingMode.HALF_UP);
        BigDecimal desvioPadrao = BigDecimal.valueOf(Math.sqrt(variance.doubleValue()));
        app.setDesvioPadrao(desvioPadrao);

        return app;
    }

}
