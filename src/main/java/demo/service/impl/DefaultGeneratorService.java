package demo.service.impl;

import demo.generators.*;
import demo.service.GeneratorService;

public class DefaultGeneratorService implements GeneratorService {

    @Override
    public String generateCorrespAccount(String bic, String currencyCode, String bankType, boolean nostroFlag) {
        return AccountGenerator.generateAccount(bic, currencyCode, bankType, nostroFlag);
    }

    @Override
    public String generateInn() {
        return InnGenerator.generateInn();
    }

    @Override
    public String generateKpp() {
        return KppGenerator.generateKpp();
    }

    @Override
    public String generateOgrn() {
        return OgrnGenerator.generateOgrn();
    }

    @Override
    public String generateOkpo() {
        return OkpoGenerator.generateOkpo();
    }


}
