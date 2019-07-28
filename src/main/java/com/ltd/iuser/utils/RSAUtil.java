package com.ltd.iuser.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public abstract class RSAUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtil.class);

	/**
	 * 加密算法RSA
	 */
	private static final String KEY_ALGORITHM = "RSA";

	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/**
	 * RSA密钥大小(字节)
	 */
	private static final int KEY_BYTE = 128;

	/**
	 * RSA密钥大小(bit)
	 */
	private static final int KEY_BIT = KEY_BYTE * 8;

	/**
	 * RSA最大加密明文大小(字节)
	 */
	private static final int MAX_ENCRYPT_BLOCK = KEY_BYTE - 11;

	/**
	 * RSA最大解密密文大小(字节)
	 */
	private static final int MAX_DECRYPT_BLOCK = KEY_BYTE;

	/**
	 * 生成密钥对(公钥和私钥)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() {
		return genKeyPair(null);
	}

	/**
	 * 生成密钥对(公钥和私钥)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair(String seed) {
		SecureRandom secureRandom = null;

		if (seed != null) {
			secureRandom = new SecureRandom(seed.getBytes(StandardCharsets.UTF_8));
		} else {
			secureRandom = new SecureRandom();
		}

		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
		keyPairGen.initialize(KEY_BIT, secureRandom);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/**
	 * 获取私钥
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64Util.encodeToString(key.getEncoded());
	}

	/**
	 * 获取公钥
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64Util.encodeToString(key.getEncoded());
	}

	/**
	 * 私钥加密
	 * 
	 * @param data
	 *            源数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws BadPaddingException
	 * @throws Exception
	 */
	public static String encryptByPrivateKey(String data, String privateKey)
			throws InvalidKeySpecException, BadPaddingException {
		byte[] keyBytes = Base64Util.decode(privateKey.getBytes(StandardCharsets.UTF_8));
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

		try {
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateK);
			byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
			int inputLen = bytes.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(bytes, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return Base64Util.encodeToString(encryptedData);
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
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 私钥解密
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws BadPaddingException
	 * @throws InvalidKeySpecException
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(String encryptedData, String privateKey)
			throws BadPaddingException, InvalidKeySpecException {
		byte[] keyBytes = Base64Util.decode(privateKey.getBytes(StandardCharsets.UTF_8));
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

		try {
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateK);
			byte[] bytes = Base64Util.decode(encryptedData.getBytes(StandardCharsets.UTF_8));
			int inputLen = bytes.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(bytes, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return new String(decryptedData, StandardCharsets.UTF_8);
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
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 公钥加密
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws BadPaddingException
	 * @throws Exception
	 */
	public static String encryptByPublicKey(String data, String publicKey)
			throws InvalidKeySpecException, BadPaddingException {
		byte[] keyBytes = Base64Util.decode(publicKey.getBytes(StandardCharsets.UTF_8));
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);

		try {
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicK = keyFactory.generatePublic(x509KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, publicK);
			byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
			int inputLen = bytes.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(bytes, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return Base64Util.encodeToString(encryptedData);
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
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 公钥解密
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws BadPaddingException
	 * @throws Exception
	 */
	public static String decryptByPublicKey(String encryptedData, String publicKey)
			throws BadPaddingException, InvalidKeySpecException {
		byte[] keyBytes = Base64Util.decode(publicKey.getBytes(StandardCharsets.UTF_8));
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);

		try {
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicK = keyFactory.generatePublic(x509KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicK);
			byte[] bytes = Base64Util.decode(encryptedData.getBytes(StandardCharsets.UTF_8));
			int inputLen = bytes.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(bytes, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return new String(decryptedData, StandardCharsets.UTF_8);
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
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	public static String sign(String data, String privateKey) throws InvalidKeySpecException {
		return sign(data, privateKey, null);
	}

	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * 
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws Exception
	 */
	public static String sign(String data, String privateKey, String signatureAlgorithm) throws InvalidKeySpecException {
		byte[] keyBytes = Base64Util.decode(privateKey.getBytes(StandardCharsets.UTF_8));
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

		try {
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			if (StringUtils.isBlank(signatureAlgorithm)) {
				signatureAlgorithm = "MD5withRSA";
			}
			Signature signature = Signature.getInstance(signatureAlgorithm);
			signature.initSign(privateK);
			signature.update(data.getBytes(StandardCharsets.UTF_8));
			return Base64Util.encodeToString(signature.sign());
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (InvalidKeyException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (SignatureException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	public static boolean verify(String data, String publicKey, String sign) throws InvalidKeySpecException {
		return verify(data, publicKey, sign);
	}

	/**
	 * 校验数字签名
	 * 
	 * @param data
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @param sign
	 *            数字签名
	 * 
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws Exception
	 * 
	 */
	public static boolean verify(String data, String publicKey, String sign, String signatureAalgorithm) throws InvalidKeySpecException {
		byte[] keyBytes = Base64Util.decode(publicKey.getBytes(StandardCharsets.UTF_8));
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

		try {
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PublicKey publicK = keyFactory.generatePublic(keySpec);
			if (StringUtils.isBlank(signatureAalgorithm)) {
				signatureAalgorithm = "MD5withRSA";
			}
			Signature signature = Signature.getInstance(signatureAalgorithm);
			signature.initVerify(publicK);
			signature.update(data.getBytes(StandardCharsets.UTF_8));
			return signature.verify(Base64Util.decode(sign.getBytes(StandardCharsets.UTF_8)));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		} catch (InvalidKeyException e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		} catch (SignatureException e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		}
	}
}
