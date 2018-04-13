package entertainment;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

class Binary {

    public String toBin(String input) throws UnsupportedEncodingException {
        BigInteger bi = new BigInteger(1, input.getBytes("UTF-8"));
        return ("0" + bi.toString(2));

    }

    public String fromBin(String in) throws UnsupportedEncodingException {
        byte[] data = toBites(in);
        return new String(data, "UTF-8");
    }

    private byte[] toBites(String in) {
        byte[] ret = new byte[in.length() / 8];
        for (int i = 0; i < ret.length; i++) {
            String chunk = in.substring(i * 8, i * 8 + 8);
            ret[i] = (byte) Short.parseShort(chunk, 2);
        }
        return ret;
    }

    @Test
    void toBinTest() throws UnsupportedEncodingException {
        System.out.println(toBin("Go to smoke"));
    }

    @Test
    void fromBinTest() throws UnsupportedEncodingException {
        System.out.println(fromBin("0100011101101111001000000111010001101111001000000111001101101101011011110110101101100101"));
    }
}
