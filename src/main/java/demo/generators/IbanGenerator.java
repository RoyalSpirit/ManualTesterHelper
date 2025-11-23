package demo.generators;

import demo.domain.IbanCountry;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class IbanGenerator {

    private static final SecureRandom RND = new SecureRandom();
    private static final char[] ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] ALNUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    private IbanGenerator() {
    }

    public static String generate(IbanCountry country) {
        String template = country.getBbanTemplate();
        if (template == null || template.isEmpty()) {
            throw new IllegalArgumentException("BBAN template not defined for " + country);
        }
        String bban = generateFromTemplate(template);
        String checksum = computeChecksum(country.getCode(), bban);
        return country.getCode() + checksum + bban;
    }

    private static String generateFromTemplate(String template) {
        StringBuilder sb = new StringBuilder(template.length());
        for (char t : template.toCharArray()) {
            switch (t) {
                case 'A' -> sb.append(ALPHA[RND.nextInt(ALPHA.length)]);
                case 'N' -> sb.append((char) ('0' + RND.nextInt(10)));
                case 'C' -> sb.append(ALNUM[RND.nextInt(ALNUM.length)]);
                default -> sb.append(t); // literal (if any)
            }
        }
        return sb.toString();
    }

    // compute 2-digit checksum according to ISO 13616
    private static String computeChecksum(String cc, String bban) {
        String rearranged = bban + cc + "00";
        // convert to numeric string
        String numeric = toNumeric(rearranged);
        int mod = mod97(numeric);
        int check = 98 - mod;
        return (check < 10 ? "0" : "") + check;
    }

    // convert letters -> 10..35, digits remain
    private static String toNumeric(String input) {
        StringBuilder sb = new StringBuilder(input.length() * 2);
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                int val = Character.toUpperCase(ch) - 'A' + 10;
                sb.append(val);
            } else if (Character.isDigit(ch)) {
                sb.append(ch);
            } else {
                // ignore other chars (spaces etc.)
            }
        }
        return sb.toString();
    }

    // fast mod97 over big numeric string using BigInteger
    private static int mod97(String numeric) {
        // BigInteger is fine here for simplicity and correctness
        BigInteger bi = new BigInteger(numeric);
        return bi.mod(BigInteger.valueOf(97)).intValue();
    }

    private static boolean mod97Check(String rearranged) {
        String numeric = toNumeric(rearranged);
        return mod97(numeric) == 1;
    }

}
