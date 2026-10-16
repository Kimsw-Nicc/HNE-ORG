package egovframework.nicc.hne.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class IpConverter {
	private final String address;

	public static class Builder {
		private String address;
		
		public Builder() {
		}
		
		public Builder address (String address) {
			this.address = address;
			return this;
		}
		
		public IpConverter build() {
			return new IpConverter(this);
		}
	}
	
	private IpConverter(Builder builder) {
		address = builder.address;
	}
	
	public String getIPv4Address() {
		if(address.equals("0:0:0:0:0:0:0:1")) {
			return "127.0.0.1";
		} if(address.contains(":")) {
			return convertIPv6ToIPv4Address(address);
		} else if (address.contains(".")) {
			return address;
		} else {
			return "";
		} 
	}
	
	public String getIPv6Address() {
		if(address.contains(":")) {
			return address;
		} else if (address.contains(".")) {
			return convertIPv4ToIPv6Address(address);
		} else {
			return "";
		} 
	}

	private String convertIPv6ToIPv4Address(String address) {
		try {
			StringTokenizer stringTokenizer = new StringTokenizer(address, ":");
			String iPv4Address = "";
			List<String> tokens = new ArrayList<>();
			while(stringTokenizer.hasMoreTokens()) {
				tokens.add(stringTokenizer.nextToken());
			}
			iPv4Address = convertIPv6TokenToIPv4Tokens(tokens.get(6)) + "." + convertIPv6TokenToIPv4Tokens(tokens.get(7));
			
			return iPv4Address;
		} catch(Exception e) {
			return "";
		}
	}

	private String convertIPv6TokenToIPv4Tokens(String token) {
		String hexString = token;
		if(token.length() == 1) {
			hexString = token + token + token + token;
		}
		String binaryString = "";
		
		binaryString += String.valueOf(Integer.parseInt(hexString.substring(0, 2), 16));
		binaryString += ".";
		binaryString += String.valueOf(Integer.parseInt(hexString.substring(2, 4), 16));
		
		return binaryString;
	}
	
	private String convertIPv4ToIPv6Address(String address) {
		try {
			StringTokenizer stringTokenizer = new StringTokenizer(address, ".");
			String iPv6Address = "";
			List<String> tokens = new ArrayList<>();
			if(address.equals("0:0:0:0:0:0:0:1")) {
				return "127.0.0.1";
			}
			while(stringTokenizer.hasMoreTokens()) {
				tokens.add(stringTokenizer.nextToken());
			}
			iPv6Address ="0000:0000:0000:0000:0000:ffff:" + convertIPv4TokenToIPv6Tokens(tokens.get(0), tokens.get(1)) + ":" + convertIPv4TokenToIPv6Tokens(tokens.get(2), tokens.get(3));
			
			return iPv6Address;
		} catch(Exception e) {
			return "";
		}
	}

	private String convertIPv4TokenToIPv6Tokens(String token1, String token2) {

		String hexString = "";
		System.out.println("token1 : " + Integer.toHexString(Integer.parseInt(token1)));
		System.out.println("token2 : " + Integer.toHexString(Integer.parseInt(token2)));
		hexString += integerToHexString(token1);
		hexString += integerToHexString(token2);
		
		return hexString;
	}

	private String integerToHexString(String token1) {
		String hexedToken = Integer.toHexString(Integer.parseInt(token1));
		if(hexedToken.length() == 1) {
			hexedToken += hexedToken;
		}
		return hexedToken;
	}
}
