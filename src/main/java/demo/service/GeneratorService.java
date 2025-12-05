package demo.service;

public interface GeneratorService {

    String generateCorrespAccount(String bic, String currencyCode, String bankType, boolean nostroFlag);

    String generateInn();

    String generateKpp();

    String generateOgrn();

    String generateOkpo();


}
