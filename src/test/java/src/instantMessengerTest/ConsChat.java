package src.instantMessengerTest;

/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id: LICENSE,v 1.8 2004/02/09 03:33:38 ian Exp $
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java
 * language and environment is gratefully acknowledged.
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Simple console-mode (command-line) chat client.
 * @author Ian Darwin, http://www.darwinsys.com/
 * @version $Id: ConsChat.java,v 1.6 2004/02/16 02:44:43 ian Exp $
 */
@SuppressWarnings("javadoc")
public class ConsChat {

	public static void main(String[] args) throws IOException {
		new ConsChat().chat();
	}

	protected Socket sock;
	protected BufferedReader is;
	protected PrintWriter pw;
	protected BufferedReader cons;

	protected ConsChat() throws IOException {
		sock = new Socket("67.80.254.141", Chat.PORTNUM);
		is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		pw = new PrintWriter(sock.getOutputStream(), true);
		cons = new BufferedReader(new InputStreamReader(System.in));

		// Construct and start the reader: from server to stdout.
		// Make a Thread to avoid lockups.

		new Thread() {

			public void run() {
				setName("socket reader thread");
				System.out.println("Starting " + getName());
				System.out.flush();
				String line;
				try {
					// reader thread blocks here
					while ((line = is.readLine()) != null) {
						System.out.println(line);
						System.out.flush();
					}
				} catch (IOException ex) {
					System.err.println("Read error on socket: " + ex);
					return;
				}
			}
		}.start();
	}

	protected void chat() throws IOException {
		String text;

		System.out.print("Login name: ");
		System.out.flush();
		text = cons.readLine();
		send(Chat.CMD_LOGIN + text);

		// Main thread blocks here
		while ((text = cons.readLine()) != null) {
			if (text.length() == 0 || text.charAt(0) == '#')
				continue;      // ignore null lines and comments
			if (text.charAt(0) == '/')
				send(text.substring(1));
			else
				send("B" + text);
		}
	}

	protected void send(String s) {
		pw.println(s);
		pw.flush();
	}

}

/**
 * Constants and Class Methods for Java Chat Clients and Server.
 *
 * The protocol:
 * --> Lusername
 * --> Btext_to_broadcast
 * --> Musername\Message
 * --> Q
 * <-- any text to be displayed.
 *
 * @author Ian Darwin
 * @version $Id: Chat.java,v 1.3 2004/02/16 02:44:43 ian Exp $
 */
@SuppressWarnings("javadoc")
class Chat {

	// These are the first character of messages from client to server

	public static final int PORTNUM = 27015;
	public static final int MAX_LOGIN_LENGTH = 20;
	public static final char SEPARATOR = '\\';
	public static final char COMMAND = '\\';
	public static final char CMD_LOGIN = 'L';
	public static final char CMD_QUIT = 'Q';
	public static final char CMD_MESG = 'M';
	public static final char CMD_BCAST = 'B';

	// These are the first character of messages from server to client

	public static final char RESP_PUBLIC = 'P';
	public static final char RESP_PRIVATE = 'M';
	public static final char RESP_SYSTEM = 'S';

	// if (text.charAt(0) == '/')
	// send(text);
	// else send("B"+text);

	public static boolean isValidLoginName(String login) {
		// check length
		if (login.length() > MAX_LOGIN_LENGTH)
			return false;

		// check for bad chars
		// if (contains bad chars)
		// return false

		// Passed above tests, is OK
		return true;
	}
}

/**
 * Trivial Chat Server to go with our Trivial Chat Client.
 *
 * WARNING -- this code is believed thread-safe but has NOT been 100% vetted
 * by a team of world-class experts for Thread-safeness.
 * DO NOT BUILD ANYTHING CRITICAL BASED ON THIS until you have done so.
 * See the various books on Threaded Java for design issues.
 *
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: ChatServer.java,v 1.10 2004/03/13 21:56:32 ian Exp $
 */
@SuppressWarnings("javadoc")
class ChatServer {

	/** What I call myself in system messages */
	protected final static String CHATMASTER_ID = "ChatMaster";
	/** What goes between any handle and the message */
	protected final static String SEP = ": ";
	/** The Server Socket */
	protected ServerSocket servSock;
	/** The list of my current clients */
	@SuppressWarnings("rawtypes")
	protected ArrayList clients;
	/** Debugging state */
	private static boolean DEBUG = false;

	/** Main just constructs a ChatServer, which should never return */
	public static void main(String[] argv) {
		System.out.println("DarwinSys Chat Server 0.1 starting...");
		if (argv.length == 1 && argv[0].equals("-debug"))
			DEBUG = true;
		ChatServer w = new ChatServer();
		w.runServer();      // should never return.
		System.out.println("**ERROR* Chat Server 0.1 quitting");
	}

	/** Construct (and run!) a Chat Service */
	@SuppressWarnings("rawtypes")
	ChatServer() {
		clients = new ArrayList();
		try {
			servSock = new ServerSocket(Chat.PORTNUM);
			System.out.println("DarwinSys Chat Server Listening on port " + Chat.PORTNUM);
		} catch (IOException e) {
			log("IO Exception in ChatServer.<init>" + e);
			System.exit(0);
		}
	}

	@SuppressWarnings("unchecked")
	public void runServer() {
		try {
			while (true) {
				Socket us = servSock.accept();
				String hostName = us.getInetAddress().getHostName();
				System.out.println("Accepted from " + hostName);
				ChatHandler cl = new ChatHandler(us, hostName);
				synchronized (clients) {
					clients.add(cl);
					cl.start();
					if (clients.size() == 1)
						cl.send(CHATMASTER_ID, "Welcome! you're the first one here");
					else {
						cl.send(CHATMASTER_ID, "Welcome! you're the latest of " + clients.size() + " users.");
					}
				}
			}
		} catch (IOException e) {
			log("IO Exception in runServer: " + e);
			System.exit(0);
		}
	}

	protected void log(String s) {
		System.out.println(s);
	}

	/** Inner class to handle one conversation */
	protected class ChatHandler extends Thread {

		/** The client socket */
		protected Socket clientSock;
		/** BufferedReader for reading from socket */
		protected BufferedReader is;
		/** PrintWriter for sending lines on socket */
		protected PrintWriter pw;
		/** The client's host */
		protected String clientIP;
		/** String form of user's handle (name) */
		protected String login;

		/* Construct a Chat Handler */
		public ChatHandler(Socket sock, String clnt) throws IOException {
			clientSock = sock;
			clientIP = clnt;
			is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pw = new PrintWriter(sock.getOutputStream(), true);
		}

		/**
		 * Each ChatHandler is a Thread, so here's the run() method,
		 * which handles this conversation.
		 */
		public void run() {
			String line;
			try {
				while ((line = is.readLine()) != null) {
					char c = line.charAt(0);
					line = line.substring(1);
					switch (c) {
						case Chat.CMD_LOGIN:
							if (!Chat.isValidLoginName(line)) {
								send(CHATMASTER_ID, "LOGIN " + line + " invalid");
								log("LOGIN INVALID from " + clientIP);
								continue;
							}
							login = line;
							broadcast(CHATMASTER_ID, login + " joins us, for a total of " + clients.size() + " users");
							break;
						case Chat.CMD_MESG:
							if (login == null) {
								send(CHATMASTER_ID, "please login first");
								continue;
							}
							int where = line.indexOf(Chat.SEPARATOR);
							String recip = line.substring(0, where);
							String mesg = line.substring(where + 1);
							log("MESG: " + login + "-->" + recip + ": " + mesg);
							ChatHandler cl = lookup(recip);
							if (cl == null)
								psend(CHATMASTER_ID, recip + " not logged in.");
							else
								cl.psend(login, mesg);
							break;
						case Chat.CMD_QUIT:
							broadcast(CHATMASTER_ID, "Goodbye to " + login + "@" + clientIP);
							close();
							return;    // The end of this ChatHandler

						case Chat.CMD_BCAST:
							if (login != null)
								broadcast(login, line);
							else
								log("B<L FROM " + clientIP);
							break;
						default:
							log("Unknown cmd " + c + " from " + login + "@" + clientIP);
					}
				}
			} catch (IOException e) {
				log("IO Exception: " + e);
			} finally {
				// the sock ended, so we're done, bye now
				System.out.println(login + SEP + "All Done");
				synchronized (clients) {
					clients.remove(this);
					if (clients.size() == 0) {
						System.out.println(CHATMASTER_ID + SEP + "I'm so lonely I could cry...");
					} else if (clients.size() == 1) {
						ChatHandler last = (ChatHandler)clients.get(0);
						last.send(CHATMASTER_ID, "Hey, you're talking to yourself again");
					} else {
						broadcast(CHATMASTER_ID, "There are now " + clients.size() + " users");
					}
				}
			}
		}

		protected void close() {
			if (clientSock == null) {
				log("close when not open");
				return;
			}
			try {
				clientSock.close();
				clientSock = null;
			} catch (IOException e) {
				log("Failure during close to " + clientIP);
			}
		}

		/** Send one message to this user */
		public void send(String sender, String mesg) {
			pw.println(sender + SEP + mesg);
		}

		/** Send a private message */
		protected void psend(String sender, String msg) {
			send("<*" + sender + "*>", msg);
		}

		/** Send one message to all users */
		public void broadcast(String sender, String mesg) {
			System.out.println("Broadcasting " + sender + SEP + mesg);
			for (int i = 0; i < clients.size(); i++) {
				ChatHandler sib = (ChatHandler)clients.get(i);
				if (DEBUG)
					System.out.println("Sending to " + sib);
				sib.send(sender, mesg);
			}
			if (DEBUG)
				System.out.println("Done broadcast");
		}

		protected ChatHandler lookup(String nick) {
			synchronized (clients) {
				for (int i = 0; i < clients.size(); i++) {
					ChatHandler cl = (ChatHandler)clients.get(i);
					if (cl.login.equals(nick))
						return cl;
				}
			}
			return null;
		}

		/** Present this ChatHandler as a String */
		public String toString() {
			return "ChatHandler[" + login + "]";
		}
	}
}