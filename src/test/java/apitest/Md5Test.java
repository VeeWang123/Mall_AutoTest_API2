package apitest;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Test {

	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("abcabc".getBytes()));

	}

}
