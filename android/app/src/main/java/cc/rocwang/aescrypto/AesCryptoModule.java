package cc.rocwang.aescrypto;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** aes crypto
 * Created by Roc on 2017/8/14.
 */

public class AesCryptoModule extends ReactContextBaseJavaModule {
    public AesCryptoModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RCTAesCrypto";
    }

    @ReactMethod
    public void encrypt(String sSrc, String secretKey, String ivParameter, Promise promise) {
        try {
            if (secretKey == null && "".equals(secretKey)) {
                Log.e("secretKey====>>>>", "secretKey is null");
                promise.reject("INVALID_PARAMETER", "secretKey MUST not be null");
            }
            if (ivParameter == null && "".equals(ivParameter)) {
                Log.e("ivParameter====>>>>", "ivParameter is null");
                promise.reject("INVALID_PARAMETER", "ivParameter MUST not be null");
                return;
            }
            byte[] encrypted = null;
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                byte[] raw = secretKey.getBytes();
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
                encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
                promise.reject(e);
            }
            String cipher = new BASE64Encoder().encode(encrypted);
            promise.resolve(cipher);
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject(e);
        }
    }

    @ReactMethod
    public void decrypt(String sSrc, String secretKey, String ivParameter, Promise promise) {
        try {
            if (secretKey == null && "".equals(secretKey)) {
                Log.e("secretKey====>>>>", "secretKey is null");
                promise.reject("INVALID_PARAMETER", "secretKey MUST not be null");
            }
            if (ivParameter == null && "".equals(ivParameter)) {
                Log.e("ivParameter====>>>>", "ivParameter is null");
                promise.reject("INVALID_PARAMETER", "ivParameter MUST not be null");
                return;
            }
            byte[] raw = secretKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            promise.resolve(originalString);

        } catch (Exception e) {
            e.printStackTrace();
            promise.reject(e);
        }
    }
}
