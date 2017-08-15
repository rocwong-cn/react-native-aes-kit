/**
 * Created by Roc on 2017/8/14.
 * desc:
 */
import { NativeModules } from 'react-native';
const { AesCrypto } = NativeModules;
export default {
    encrypt(plaintext, secretKey, iv) {
        if (!plaintext || !secretKey || !iv) {
            return Promise.reject(new Error("invalid parameter: plaintext,secretKey and iv are required"));
        }
        return AesCrypto.encrypt(plaintext, secretKey, iv);
    },
    decrypt(cipherText, secretKey, iv) {
        if (!cipherText || !secretKey || !iv) {
            return Promise.reject(new Error("invalid parameter: cipherText,secretKey and iv are required"));
        }
        return AesCrypto.decrypt(cipherText, secretKey, iv);
    },
}
