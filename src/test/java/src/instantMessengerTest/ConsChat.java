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
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Simple console-mode (command-line) chat client.
 * @author Ian Darwin, http://www.darwinsys.com/
 * @version $Id: ConsChat.java,v 1.6 2004/02/16 02:44:43 ian Exp $
 */
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

	// TODO in main loop:
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
class ChatServer {

	/** What I call myself in system messages */
	protected final static String CHATMASTER_ID = "ChatMaster";
	/** What goes between any handle and the message */
	protected final static String SEP = ": ";
	/** The Server Socket */
	protected ServerSocket servSock;
	/** The list of my current clients */
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

/**
 * <p>
 * Simple Chat Room Applet.
 * Writing a Chat Room seems to be one of many obligatory rites (or wrongs)
 * of passage for Java experts these days.</p>
 * <p>
 * This one is a toy because it doesn't have much of a protocol, which
 * means we can't query the server as to * who's logged in,
 * or anything fancy like that. However, it works OK for small groups.</p>
 * <p>
 * Uses client socket w/ two Threads (main and one constructed),
 * one for reading and one for writing.</p>
 * <p>
 * Server multiplexes messages back to all clients.</p>
 * @author Ian Darwin
 * @version $Id: ChatRoom.java,v 1.8 2004/03/09 03:59:37 ian Exp $
 */
class ChatRoom extends Applet {

	/** Whether we are being run as an Applet or an Application */
	protected boolean inAnApplet = true;
	/** The state of logged-in-ness */
	protected boolean loggedIn;
	/* The Frame, for a pop-up, durable Chat Room. */
	protected Frame cp;
	/** The default port number */
	protected static int PORTNUM = Chat.PORTNUM;
	/** The actual port number */
	protected int port;
	/** The network socket */
	protected Socket sock;
	/** BufferedReader for reading from socket */
	protected BufferedReader is;
	/** PrintWriter for sending lines on socket */
	protected PrintWriter pw;
	/** TextField for input */
	protected TextField tf;
	/** TextArea to display conversations */
	protected TextArea ta;
	/** The Login button */
	protected Button lib;
	/** The LogOUT button */
	protected Button lob;
	/** The TitleBar title */
	final static String TITLE = "Chat: Ian Darwin's Toy Chat Room Client";
	/** The message that we paint */
	protected String paintMessage;

	/** init, overriding the version inherited from Applet */
	public void init() {
		paintMessage = "Creating Window for Chat";
		repaint();
		cp = new Frame(TITLE);
		cp.setLayout(new BorderLayout());
		String portNum = null;
		if (inAnApplet)
			portNum = getParameter("port");
		port = PORTNUM;
		if (portNum != null)
			port = Integer.parseInt(portNum);

		// The GUI
		ta = new TextArea(14, 80);
		ta.setEditable(false);    // readonly
		ta.setFont(new Font("Monospaced", Font.PLAIN, 11));
		cp.add(BorderLayout.NORTH, ta);

		Panel p = new Panel();
		Button b;

		// The login button
		p.add(lib = new Button("Login"));
		lib.setEnabled(true);
		lib.requestFocus();
		lib.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				login();
				lib.setEnabled(false);
				lob.setEnabled(true);
				tf.requestFocus();  // set keyboard focus in right place!
			}
		});

		// The logout button
		p.add(lob = new Button("Logout"));
		lob.setEnabled(false);
		lob.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				logout();
				lib.setEnabled(true);
				lob.setEnabled(false);
				lib.requestFocus();
			}
		});

		p.add(new Label("Message here:"));
		tf = new TextField(40);
		tf.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (loggedIn) {
					pw.println(Chat.CMD_BCAST + tf.getText());
					tf.setText("");
				}
			}
		});
		p.add(tf);

		cp.add(BorderLayout.SOUTH, p);

		cp.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				// If we do setVisible and dispose, then the Close completes
				ChatRoom.this.cp.setVisible(false);
				ChatRoom.this.cp.dispose();
				logout();
			}
		});
		cp.pack();
		// After packing the Frame, centre it on the screen.
		Dimension us = cp.getSize(), them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height - us.height) / 2;
		cp.setLocation(newX, newY);
		cp.setVisible(true);
		paintMessage = "Window should now be visible";
		repaint();
	}

	protected String serverHost = "67.80.254.141";

	/** LOG ME IN TO THE CHAT */
	public void login() {
		showStatus("In login!");
		if (loggedIn)
			return;
		if (inAnApplet)
			serverHost = getCodeBase().getHost();
		try {
			sock = new Socket(serverHost, port);
			is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pw = new PrintWriter(sock.getOutputStream(), true);
		} catch (IOException e) {
			showStatus("Can't get socket to " + serverHost + "/" + port + ": " + e);
			cp.add(new Label("Can't get socket: " + e));
			return;
		}
		showStatus("Got socket");

		// Construct and start the reader: from server to textarea.
		// Make a Thread to avoid lockups.
		new Thread(new Runnable() {

			public void run() {
				String line;
				try {
					while (loggedIn && ((line = is.readLine()) != null))
						ta.append(line + "\n");
				} catch (IOException e) {
					showStatus("GAA! LOST THE LINK!!");
					return;
				}
			}
		}).start();

		// FAKE LOGIN FOR NOW
		pw.println(Chat.CMD_LOGIN + "AppletUser");
		loggedIn = true;
	}

	/** Log me out, Scotty, there's no intelligent life here! */
	public void logout() {
		if (!loggedIn)
			return;
		loggedIn = false;
		try {
			if (sock != null)
				sock.close();
		} catch (IOException ign) {
			// so what?
		}
	}

	// It is deliberate that there is no STOP method - we want to keep
	// going even if the user moves the browser to another page.
	// Anti-social? Maybe, but you can use the CLOSE button to kill
	// the Frame, or you can exit the Browser.

	/**
	 * Paint paints the small window that appears in the HTML,
	 * telling the user to look elsewhere!
	 */
	public void paint(Graphics g) {
		Dimension d = getSize();
		int h = d.height;
		int w = d.width;
		g.fillRect(0, 0, w, 0);
		g.setColor(Color.black);
		g.drawString(paintMessage, 10, (h / 2) - 5);
	}

	/** a showStatus that works for Applets or non-Applets alike */
	public void showStatus(String mesg) {
		if (inAnApplet)
			super.showStatus(mesg);
		System.out.println(mesg);
	}

	/** A main method to allow the client to be run as an Application */
	public static void main(String[] args) {
		ChatRoom room101 = new ChatRoom();
		room101.inAnApplet = false;
		room101.init();
		room101.start();
	}
}
