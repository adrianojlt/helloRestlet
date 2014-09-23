package pt.adrz.hellorestlet.security;

import org.restlet.security.SecretVerifier;

public class MySecretVerifier extends SecretVerifier {
	
	private String user = "admin";
	private String pass = "admin";

	@Override
	public int verify(String identifier, char[] secret) {
		return ( user.equals(identifier) && compare(secret,pass.toCharArray()) ) ? SecretVerifier.RESULT_VALID : SecretVerifier.RESULT_INVALID;
	}
}
