package demo.service;

public interface GeneratorService {

    String generateCorrespAccount(String bic, String currencyCode, boolean nostroFlag);

    String generateInn();

    String generateKpp();

    String generateOgrn();

    String generateOkpo();


}
