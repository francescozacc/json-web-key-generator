/**
 *
 */
package org.mitre.jose.jwk;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;

/**
 * @author jricher
 */
public class RSAKeyMaker {

    /**
     * @param keySize
     * @param keyUse
     * @param keyAlg
     * @param kid
     * @return
     */
    public static RSAKey make(Integer keySize, KeyUse keyUse, Algorithm keyAlg, String kid) {

        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(keySize);
            KeyPair kp = generator.generateKeyPair();

            RSAPublicKey pub = (RSAPublicKey) kp.getPublic();
            RSAPrivateCrtKey priv = (RSAPrivateCrtKey) kp.getPrivate();

            RSAKey rsaKey = new RSAKey.Builder(pub)
                    .privateKey(priv)
                    .keyUse(keyUse)
                    .algorithm(keyAlg)
                    .keyID(kid)
                    .build();

            return rsaKey;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
