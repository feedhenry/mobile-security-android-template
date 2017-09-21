package com.feedhenry.securenativeandroidtemplate;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.feedhenry.securenativeandroidtemplate.di.SecureTestApplication;
import com.feedhenry.securenativeandroidtemplate.domain.crypto.AesGcmCrypto;
import com.feedhenry.securenativeandroidtemplate.domain.utils.StreamUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;

/**
 * Created by weili on 18/09/2017.
 */

@RunWith(AndroidJUnit4.class)
public class CryptoTest {

//    public static final String KEY_ALIAS = "AESGCM_TEST_KEY";
//
//    @Inject Context context;
//    @Inject AesGcmCrypto aesGcmCrypto;
//
//    @Before
//    public void setUp() {
//        SecureTestApplication application = (SecureTestApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
//        application.getComponent().inject(this);
//    }
//
//    @Test
//    public void testEncryptDecrypt() throws GeneralSecurityException, IOException {
//        String textToTest = "this is a test text";
//        String encrypted = aesGcmCrypto.encryptString(KEY_ALIAS, textToTest, "utf-8");
//        String decrypted = aesGcmCrypto.decryptString(KEY_ALIAS, encrypted, "utf-8");
//        assertEquals(textToTest, decrypted);
//    }
//
//    @Test
//    public void testEncryptDecryptStream() throws IOException, GeneralSecurityException {
//        String textToTest = "this is a test text";
//        FileOutputStream out = context.getApplicationContext().openFileOutput("test.txt", Context.MODE_PRIVATE);
//
//        OutputStream encryptedStream = aesGcmCrypto.encryptStream(KEY_ALIAS, out);
//        encryptedStream.write(textToTest.getBytes());
//        encryptedStream.flush();
//        encryptedStream.close();
//
//        FileInputStream in = context.getApplicationContext().openFileInput("test.txt");
//        InputStream decryptedStream = aesGcmCrypto.decryptStream(KEY_ALIAS, in);
//
//        String decrypted = StreamUtils.readStream(decryptedStream);
//        assertEquals(textToTest, decrypted);
//    }
}
