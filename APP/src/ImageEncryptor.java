import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * ImageEncryptor Class
 *
 * Encrypts and Decrypts an Image
 *
 * @author Khaaled Farooq
 * IIT ID - 20210370
 * UOW ID - 18671159
 */
public class ImageEncryptor {

    String path; //Path of the image to be encrypted
    Cipher cipher; //Cipher object
    FileInputStream file; //File input stream
    FileOutputStream outputStream; //File output stream
    String pID; //String containing patient ID
    String extension; // String containing the extension of the uploaded image

    private String key = ("42069420"); //key
    SecretKey secretKey; // SecretKey Object

    /**
     * Constructor
     * Initialises new secret key using the key
     */
    public ImageEncryptor(){
        secretKey = new SecretKeySpec(key.getBytes(),"DES");
    }


    /**
     * Encrypts an image
     * @param path String containing the path of the image
     * @param extension String containing the extension of the image file
     * @param pID String containing the patient ID
     */
    void EncryptImage(String path, String extension,String pID){
        this.path=path;
        this.extension=extension;
        this.pID=pID;
        try {
            file = new FileInputStream(path);
            outputStream = new FileOutputStream(pID+"Encrypted."+extension);
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            CipherOutputStream cos = new CipherOutputStream(outputStream,cipher);
            byte[] buffer = new byte[1024];
            int read;
            while(((read=file.read(buffer))!=-1)){
                cos.write(buffer,0,read);
            }
            file.close();
            outputStream.flush();
            cos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Decrypts Image using Key
     */
    void decryptImage(){

        try{
            file = new FileInputStream(pID+"Encrypted."+extension);
            outputStream = new FileOutputStream(pID+"Decrypted."+extension);
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            CipherOutputStream cos = new CipherOutputStream(outputStream,cipher);
            byte[] buffer = new byte[1024];
            int read;
            while(((read=file.read(buffer))!=-1)){
                cos.write(buffer,0,read);
            }
            file.close();
            outputStream.flush();
            cos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
