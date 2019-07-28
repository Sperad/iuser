package com.ltd.iuser.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public abstract class DESUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DESUtil.class);

	private static final String ALGORITHM = "DES";

	/**
	 * 生成密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initKey() {
		return initKey(null);
	}

	/**
	 * 生成密钥
	 * 
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	public static String initKey(String seed) {
		SecureRandom secureRandom = null;

		if (seed != null) {
			secureRandom = new SecureRandom(seed.getBytes(StandardCharsets.UTF_8));
		} else {
			secureRandom = new SecureRandom();
		}

		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
		kg.init(secureRandom);

		SecretKey secretKey = kg.generateKey();

		return Base64Util.encodeToString(secretKey.getEncoded());
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) throws InvalidKeyException {
		Key k = toKey(key.getBytes(StandardCharsets.UTF_8));

		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, k);
			return Base64Util.encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (NoSuchPaddingException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (InvalidKeyException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (IllegalBlockSizeException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (BadPaddingException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws Exception
	 */
	public static String decrypt(String data, String key)
			throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
		Key k = toKey(key.getBytes(StandardCharsets.UTF_8));

		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, k);
			return new String(cipher.doFinal(Base64Util.decode(data.getBytes(StandardCharsets.UTF_8))),
					StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (NoSuchPaddingException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (InvalidKeyException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws InvalidKeyException {
		try {
			DESKeySpec dks = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			return keyFactory.generateSecret(dks);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (InvalidKeySpecException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}
}
