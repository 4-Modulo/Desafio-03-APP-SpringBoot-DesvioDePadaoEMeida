package com.AppSpring.api.Services;

import com.AppSpring.api.Entity.App;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class AppService {
    public App create(App app) {
        List<Integer> list = app.getEntradas();
        validateList(list);
        BigDecimal media = calculateMean(list).setScale(2, RoundingMode.HALF_UP);
        BigDecimal variance = calculateVariance(list, media).setScale(2, RoundingMode.HALF_UP);
        BigDecimal desvioPadrao = calculateStandardDeviation(variance).setScale(2, RoundingMode.HALF_UP);
        setStatistics(app, media, desvioPadrao);
        return app;
    }

    public void validateList(List<Integer> list) {
        if (list == null || list.size() < 19|| list.size() < 41) {
            throw new IllegalArgumentException("A lista deve conter pelo menos 20 números e no maximo 40");
        }
    }

    public BigDecimal calculateMean(List<Integer> numbers) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Integer number : numbers) {
            sum = sum.add(BigDecimal.valueOf(number));
        }
        return sum.divide(BigDecimal.valueOf(numbers.size()), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateVariance(List<Integer> numbers, BigDecimal mean) {
        BigDecimal sumOf = BigDecimal.ZERO;
        for (Integer number : numbers) {
            BigDecimal difference = BigDecimal.valueOf(number).subtract(mean);
            sumOf = sumOf.add(difference.pow(2));
        }
        return sumOf.divide(BigDecimal.valueOf(numbers.size()), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateStandardDeviation(BigDecimal variance) {
        return BigDecimal.valueOf(Math.sqrt(variance.doubleValue())).setScale(2, RoundingMode.HALF_UP);
    }

    public void setStatistics(App app, BigDecimal media, BigDecimal desvioPadrao) {
        app.setMedia(media);
        app.setDesvioPadrao(desvioPadrao);
    }
}
