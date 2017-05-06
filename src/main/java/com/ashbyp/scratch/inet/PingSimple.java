package com.ashbyp.scratch.inet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingSimple {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: PingSimple <hostname>");
			System.exit(-1);
		}
		String ipAddress = args[0];
		
		System.out.println("Will ping " + ipAddress);

		try {
			InetAddress inet = InetAddress.getByName(ipAddress);
			System.out.println("Sending Ping Request to " + ipAddress);

			boolean status = inet.isReachable(5000); 
			if (status) {
				System.out.println("Status : Host is reachable");
			} else {
				System.out.println("Status : Host is not reachable");
			}
		} catch (UnknownHostException e) {
			System.err.println("Host does not exists");
		} catch (IOException e) {
			System.err.println("Error in reaching the Host");
		}
	}
}
