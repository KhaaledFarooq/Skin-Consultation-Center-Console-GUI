import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encryptor Class
 * Used to encrypt and decrypt a String
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class Encryptor {

    byte[] encryptedText; //byte[] containing encrypted text
    byte[] decryptedText; //byte[] containing decrypted text
    private String key = ("42069420"); //key
    private String notes; // String to be encrypted
    SecretKey secretKey; // SecretKey Object
    Cipher cipher; // Cipher Object

    /**
     * Constructor
     * Creates a new SecretKey using the key
     * Initialises a new Cipher object
     */
    public Encryptor(){
        secretKey = new SecretKeySpec(key.getBytes(),"DES");
        try {
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Encrypts a String
     * @param notes String to be encrypted
     * @return Encrypted String
     */
    String encrypt(String notes){
        this.notes=notes;
        try {
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            byte[] text = notes.getBytes();
            encryptedText = cipher.doFinal(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (new String(encryptedText));
    }


    /**
     * Decrypts a String
     * @return Decrypted String
     */
    String decrypt(){
        try{
            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            decryptedText = cipher.doFinal(encryptedText);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return (new String(decryptedText));
    }
}
