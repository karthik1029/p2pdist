package proj;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import javax.media.*;
import javax.media.format.*;
import javax.media.datasink.*;
import javax.media.protocol.*;

import javax.swing.*;
import java.net.*;
import javax.media.control.*;

public class VPeer extends JFrame implements ControllerListener, DataSinkListener,Runnable {
	 private Processor thisProcessor;
	    Player player;
	    private VServerThread clients[] = new VServerThread[50];
	    private ServerSocket server = null;
	    private Thread       thread = null;
	    private MediaLocator locator;
	    private String ipAddress;
	    private String port;
	    private Processor processor = null;
	    private DataSink  rtptransmitter = null;
	    private DataSource dataOutput = null;
	    private int clientCount = 0;
	    public long sysecond;
	    int slep;
	    int si=0;
	    int kl;
	    int co=0;
	    int ikl=0;
	    int cou=0;
	    static int por;
	    static String oips;
	    static int dur1;
	    private Socket csocket= null;
	    private Thread cthread= null;
	    private DataInputStream  console= null;
	    private DataOutputStream streamOut= null;
	    private VPeerThread client= null;
	    CommonModel cm=new CommonModel();
  
    public VPeer() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

    	frame = new JFrame("Show Message Dialog");
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();



        if(ikl==1){
        	setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        }

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Peer");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("IP Address:");
        ArrayList v1= new ArrayList();
 		v1=cm.getAllHosts();
	 	int i=v1.size();
	 	//String[] patternExamples = new String[i+1];
                String[] patternExamples = new String[15];
                
	 	int p=2;
	 	patternExamples[0]="Select";
                patternExamples[1]="127.0.0.1";
                if(i>0) {
                    for(int k=0;k<i;k++)
                    {
	  		patternExamples[p]=(String)v1.get(k);
	  		p++; 
                    }
                }

	  	jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(patternExamples));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Port No: ");

        jLabel4.setText("Save Location: ");

        jButton1.setText("Save");
        jButton1.setActionCommand ("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Connect");
        jButton2.setActionCommand ("Connect");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("(ext .mov)");

         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addGroup(layout.createSequentialGroup()
		                                .addGap(172, 172, 172)
		                                .addComponent(jLabel1))
		                            .addGroup(layout.createSequentialGroup()
		                                .addGap(65, 65, 65)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                    .addComponent(jLabel2)
		                                    .addComponent(jLabel4)
		                                    .addComponent(jLabel3))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
		                                        .addComponent(jTextField2)
		                                        .addComponent(jComboBox1, 0, 106, Short.MAX_VALUE)
		                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)))))
		                        .addGap(18, 18, 18)
		                        .addComponent(jButton1)
		                        .addGap(38, 38, 38))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(159, 159, 159)
		                        .addComponent(jButton2)))
		                .addContainerGap())
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(30, 30, 30)
		                .addComponent(jLabel1)
		                .addGap(30, 30, 30)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jLabel2)
		                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(30, 30, 30)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jLabel4)
		                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jButton1))
		                .addGap(1, 1, 1)
		                .addComponent(jLabel5)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(jLabel3)
		                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(37, 37, 37)
		                .addComponent(jButton2)
		                .addContainerGap(44, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
	String Action;
	 String filename="file:\\";
	 String po="26800";
	 Action = evt.getActionCommand ();

	  if (Action.equals("Save"))
	  {
			if (fd == null)
			{
				fd = new FileDialog(VPeer.this, "Save location",FileDialog.SAVE);
				fd.setDirectory("");
			}
			fd.show();
			if (fd.getFile() != null)
			{
				filename += fd.getDirectory() + fd.getFile();
				jTextField2.setText(filename + ".mov");
				jTextField1.setText(po);
			}
	  }
	  else
	  {
			dispose();
			System.exit(0);
	  }
}
String oldip;
int oldport;
private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
	String Action;

	 Action = evt.getActionCommand ();

	  if (Action.equals("Connect"))
            
              
	  { ikl=1; 
            
              System.out.println("Port no.  "+jTextField1.getText());
              System.out.println("Save location "+jTextField2.getText());
            
		  if(!jTextField1.getText().equals("") && !jTextField2.getText().equals("")){
			  String strr1=jTextField2.getText();
		   	  String outputURL=strr1;
		   	  String strr=jTextField1.getText();
		   	  int port=Integer.parseInt(strr);

		   	  String ips=(String) jComboBox1.getSelectedItem();
		   	  String inputURL="rtp://"+ips+":"+Integer.toString(port)+"/video";
		   	if ((iml = createMediaLocator(inputURL)) == null) {
			    System.err.println("Cannot build media locator from: " + inputURL);
			    System.exit(0);
			}

		   	if ((oml = createMediaLocator(outputURL)) == null) {
			    System.err.println("Cannot build media locator from: " + outputURL);
			    System.exit(0);
			}
		   	outfile=outputURL;
		   	oldip=ips;
		   	oldport=port;

		   	ChatClient(ips, port);
		  }
		  else{

			  JOptionPane.showMessageDialog(frame,"Check IP Address, Save Path & Port No");
			  jTextField2.requestFocus();
			  dispose();
			  System.exit(0);
		  }
	  }
	  else
	  {
			dispose();
			System.exit(0);
	  }
}

static MediaLocator createMediaLocator(String url) {

MediaLocator ml;

if (url.indexOf(":") > 0 && (ml = new MediaLocator(url)) != null)
    return ml;

if (url.startsWith(File.separator)) {
    if ((ml = new MediaLocator("file:" + url)) != null)
	return ml;
} else {
    String file = "file:" + System.getProperty("user.dir") + File.separator + url;
    if ((ml = new MediaLocator(file)) != null)
	return ml;
}

return null;
}

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
	String item=(String) jComboBox1.getSelectedItem();
	if(item.equals("Select")){
		JOptionPane.showMessageDialog(frame,"Check IP Address");
		jTextField2.requestFocus();
	}

}
public void ChatClient(String serverName, int serverPort)
{  System.out.println("Establishing connection. Please wait ...");
   try
   {  csocket = new Socket(serverName, serverPort);
      System.out.println("Connected");
      ObjectInputStream ois1 = new ObjectInputStream(csocket.getInputStream());
      String newport = (String) ois1.readObject();
      por=Integer.parseInt(newport);

      ObjectInputStream ois2 = new ObjectInputStream(csocket.getInputStream());
      String newport2 = (String) ois2.readObject();
      kl=Integer.parseInt(newport2);

      ObjectInputStream ois11 = new ObjectInputStream(csocket.getInputStream());
      oips = (String) ois11.readObject();

      if(por!=0){
      ChatClient1(oips, por);
      }
      ObjectOutputStream oos = new ObjectOutputStream(csocket.getOutputStream());
      oos.writeObject(csocket.getLocalAddress().getHostAddress());
      if(por==0){
      ObjectInputStream ois = new ObjectInputStream(csocket.getInputStream());
      String message = (String) ois.readObject();
      int dur2=Integer.parseInt(message)+2;if(cou==0){dur1=dur2;cou++;}

  		if (!doIt(iml, oml, dur1)) {
  		    System.err.println("VClient failed");
  		    System.exit(0);
  		}}
  		 if(por!=0){
  			 if ((iml=createMediaLocator("rtp://"+oips+":"+por+"/video")) == null) {
  				    System.err.println("Cannot build media locator ");
  				    System.exit(0);
  				}
  			 if (!doIt(iml, oml, dur1)) {
  				    System.err.println("VClient failed");
  				    System.exit(0);
  				}
  		 }

      start3();
   }
   catch(UnknownHostException uhe)
   {  System.out.println("Host unknown: " + uhe.getMessage());System.exit(0); }
   catch(IOException ioe)
   {
	   System.out.println("Unexpected exception: " + ioe.getMessage());System.exit(0); }
   catch (ClassNotFoundException e) {
	   System.out.println("Unexpected exception: " + e.getMessage());System.exit(0);
	   }
}

public void ChatClient1(String serverName, int serverPort)
{  System.out.println("Establishing connection. Please wait ...");
   try
   {  csocket = new Socket(serverName, serverPort);
      System.out.println("Connected");
      try{

          ObjectInputStream ois = new ObjectInputStream(csocket.getInputStream());
          String message = (String) ois.readObject();
          int dur2=Integer.parseInt(message)+2;if(cou==0){dur1=dur2;cou++;}

      ObjectInputStream ois1122 = new ObjectInputStream(csocket.getInputStream());
	  ArrayList board = (ArrayList)  ois1122.readObject();
	  System.out.println("Play Back: " + board);
	  Object[] a=board.toArray();
	  int si=board.size();
	  int b;
	  int ab[]=new int[si];
	  for(int h=0;h<si;h++){
	  b=((Integer) a[h]).intValue();
	  ab[h]=b;
	  }
	  KMPArray(ab);

	  ObjectOutputStream oos = new ObjectOutputStream(csocket.getOutputStream());
      oos.writeObject(csocket.getLocalAddress().getHostAddress());
      }
	  catch(UnknownHostException uhe)
	   {  System.out.println("Host unknown: " + uhe.getMessage());System.exit(0); }
	   catch(IOException ioe)
	   {
		   System.out.println("Unexpected exception: " + ioe.getMessage());System.exit(0); }
	   catch (ClassNotFoundException e) {
		   System.out.println("Unexpected exception: " + e.getMessage());System.exit(0);
		   }
      start3();

   }
       catch(UnknownHostException uhe)
      {  System.out.println("Host unknown: " + uhe.getMessage());System.exit(0); }
      catch(IOException ioe)
      {
    	  try {
    	         Thread.currentThread().sleep(2000);
    	         }
    	         catch (InterruptedException ie) {
    	      	}
    	  System.out.println("Wait " + ioe.getMessage());
    	  if(co==10){
      		System.out.println("Connection Problem");
      		System.exit(0);
      	}
    	  ++co;
    	  ChatClient1(oips, por);

      }

   }
public void run1()
{  while (cthread != null)
   {  try
      {  streamOut.writeUTF(console.readLine());
         streamOut.flush();
      }
      catch(IOException ioe)
      {  System.out.println("Sending error: " + ioe.getMessage());
         stop3();
      }
   }
}
public void handle(String msg)
{  if (msg.equals(".bye"))
   {
      stop3();
   }
   else
      System.out.println(msg);
}
public void start3() throws IOException
{  console   = new DataInputStream(System.in);
   streamOut = new DataOutputStream(csocket.getOutputStream());

}
public void stop3()
{  if (cthread != null)
   {  cthread.stop();
      cthread = null;
   }
   try
   {  if (console   != null)  console.close();
      if (streamOut != null)  streamOut.close();
      if (csocket    != null)  csocket.close();
   }
   catch(IOException ioe)
   {  System.out.println("Error closing ..."); }
   client.close();
   client.stop();
}
int newport;
public void ChatServer(int port)
{  try
   {  System.out.println("Binding to port " + port + ", please wait  ...");
      server = new ServerSocket(port);
      System.out.println("Server started: " + server);
      newport=port;
      start2(); }
   catch(IOException ioe)
   {
	   System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
	   ChatServer(port+2);
   }
}
public void run()
{  while (thread != null)
   {  try
      {
	   System.out.println("Waiting for Peer...");
         addThread(server.accept()); }
      catch(IOException ioe)
      {  System.out.println("Server accept error: " + ioe); stop2(); }
   }
}
public void start2()
{
	   if (thread == null)
   {  thread = new Thread(this);
      thread.start();
   }
}
public void stop2()
{
	   if (thread != null)
   {  thread.stop();
      thread = null;
      start2();
   }
}
private int findClient(int ID)
{  for (int i = 0; i < clientCount; i++)
      if (clients[i].getID() == ID)
         return i;
   return -1;
}
public synchronized void handle(int ID, String input)
{  if (input.equals(".bye"))
   {  clients[findClient(ID)].send(".bye");
      remove(ID); }
   else
      for (int i = 0; i < clientCount; i++)
         clients[i].send(ID + ": " + input);
}
public synchronized void remove(int ID)
{  int pos = findClient(ID);
   if (pos >= 0)
   {  VServerThread toTerminate = clients[pos];
         if (pos < clientCount-1)
         for (int i = pos+1; i < clientCount; i++)
            clients[i-1] = clients[i];
      clientCount--;
      try
      {  toTerminate.close(); }
      catch(IOException ioe)
      {  System.out.println("Error closing thread: " + ioe); }
      toTerminate.stop(); }
}
String iip;
private void addThread(Socket socket)
{  if (clientCount < clients.length)
   {  System.out.println("Peer accepted");
  String ss=Integer.toString(socket.getLocalPort());
  try {
	     Thread.currentThread().sleep(2000);
	     }
	     catch (InterruptedException ie) {
	  	}
	     try
	     {

	    	 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	    	 String slp=Integer.toString(slep);
	    	 oos.writeObject(slp);
if(ar.size()==0){
	long se=System.currentTimeMillis();
	int i=(int)mediat;
	ar.add(i);
	long dur=se-sysecond;
	long ssemi=dur/1000;
	int kk=i+1;
	if(ssemi>dur1){
		ssemi=dur1-1;
	}
	for(int hg=0;hg<ssemi;hg++){
	ar.add(kk);
	kk++;
	}
}
	    	 ObjectOutputStream oos2 = new ObjectOutputStream(socket.getOutputStream());
	     	 oos2.writeObject(ar);
	     	 ObjectInputStream ois22 = new ObjectInputStream(socket.getInputStream());
	     	 iip = (String)  ois22.readObject();
	   	  System.out.println("IP " + iip);
	     }
	     catch(Exception ioe)
	     {  System.out.println("Error in send UrgentData: " + ioe); }
  VideoTransmit(oml, iip, ss);
  String result =start1();

	if (result != null) {
	    System.err.println("Error : " + result);
	    System.exit(0);
	}

	System.err.println("Start transmission...");
	try {


		int gf=slep+2;
		String saa=Integer.toString(gf)+"000";
		int spt=Integer.parseInt(saa);
		Thread.currentThread().sleep(spt);
	} catch (InterruptedException ie) {
	}

	stop();

	System.err.println("...transmission ended.");

   }
}
public void VideoTransmit(MediaLocator locator,
			 String ipAddress,
			 String port) {

	this.locator = locator;
	this.ipAddress = ipAddress;
	this.port = port;
}

public synchronized String start1() {
	String result;

	result = createProcessor();
	if (result != null)
	    return result;

	result = createTransmitter();
	if (result != null) {
	    processor.close();
	    processor = null;
	    return result;
	}

	processor.start();

	return null;
}

public void stop() {
	synchronized (this) {
	    if (processor != null) {
		processor.stop();
		processor.close();
		processor = null;
		rtptransmitter.close();
		rtptransmitter = null;
	    }
	}
}

private String createProcessor() {
	if (locator == null)
	    return "Locator is null";

	DataSource ds;
	DataSource clone;

	try {
	    ds = Manager.createDataSource(locator);
	} catch (Exception e) {
	    return "Couldn't create DataSource";
	}

	try {
	    processor = Manager.createProcessor(ds);
	} catch (NoProcessorException npe) {
	    return "Couldn't create processor";
	} catch (IOException ioe) {
	    return "IOException creating processor";
	}

	boolean result = waitForState(processor, Processor.Configured);
	if (result == false)
	    return "Couldn't configure processor";

	TrackControl [] tracks = processor.getTrackControls();


	if (tracks == null || tracks.length < 1)
	    return "Couldn't find tracks in processor";

	boolean programmed = false;

	for (int i = 0; i < tracks.length; i++) {
	    Format format = tracks[i].getFormat();
	    if (  tracks[i].isEnabled() &&
		  format instanceof VideoFormat &&
		  !programmed) {

		Dimension size = ((VideoFormat)format).getSize();
		float frameRate = ((VideoFormat)format).getFrameRate();
		int w = (size.width % 8 == 0 ? size.width :
				(int)(size.width / 8) * 8);
		int h = (size.height % 8 == 0 ? size.height :
				(int)(size.height / 8) * 8);
		VideoFormat jpegFormat = new VideoFormat(VideoFormat.JPEG_RTP,
							 new Dimension(w, h),
							 Format.NOT_SPECIFIED,
							 Format.byteArray,
							 frameRate);
		tracks[i].setFormat(jpegFormat);

		programmed = true;
	    } else
		tracks[i].setEnabled(false);
	}

	if (!programmed)
	    return "Couldn't find video track";


	ContentDescriptor cd = new ContentDescriptor(ContentDescriptor.RAW_RTP);
	processor.setContentDescriptor(cd);

	result = waitForState(processor, Controller.Realized);
	if (result == false)
	    return "Couldn't realize processor";


	setJPEGQuality(processor, 0.5f);


	dataOutput = processor.getDataOutput();
	return null;
}

private String createTransmitter() {
	String rtpURL = "rtp://" + ipAddress + ":" + port + "/video";
	MediaLocator outputLocator = new MediaLocator(rtpURL);

	try {
	    rtptransmitter = Manager.createDataSink(dataOutput, outputLocator);
	    rtptransmitter.open();
	   try {
        Thread.currentThread().sleep(4000);
        }
        catch (InterruptedException ie) {
     	}
    rtptransmitter.start();
    try {
        Thread.currentThread().sleep(4000);
        }
        catch (InterruptedException ie) {
     	}
	    dataOutput.start();
	} catch (MediaException me) {
	    return "Couldn't create RTP data sink";
	} catch (IOException ioe) {
	    return "Couldn't create RTP data sink";
	}

	return null;
}

void setJPEGQuality(Player p, float val) {

	Control cs[] = p.getControls();
	QualityControl qc = null;
	VideoFormat jpegFmt = new VideoFormat(VideoFormat.JPEG);

	for (int i = 0; i < cs.length; i++) {

	    if (cs[i] instanceof QualityControl &&
		cs[i] instanceof Owned) {
		Object owner = ((Owned)cs[i]).getOwner();

		if (owner instanceof Codec) {
		    Format fmts[] = ((Codec)owner).getSupportedOutputFormats(null);
		    for (int j = 0; j < fmts.length; j++) {
			if (fmts[j].matches(jpegFmt)) {
			    qc = (QualityControl)cs[i];
	    		    qc.setQuality(val);

			    break;
			}
		    }
		}
		if (qc != null)
		    break;
	    }
	}
}

private Integer stateLock = new Integer(0);
private boolean failed = false;

Integer getStateLock() {
	return stateLock;
}

void setFailed() {
	failed = true;
}

private synchronized boolean waitForState(Processor p, int state) {
	p.addControllerListener(new StateListener());
	failed = false;

	if (state == Processor.Configured) {
	    p.configure();
	} else if (state == Processor.Realized) {
	    p.realize();
	}

	while (p.getState() < state && !failed) {
	    synchronized (getStateLock()) {
		try {
		    getStateLock().wait();
		} catch (InterruptedException ie) {
		    return false;
		}
	    }
	}

	if (failed)
	    return false;
	else
	    return true;
}
static void KMPArray(int[] a)
{
for (int i = 0; i < a.length - 1; i++)
for (int j = i + 1; j < a.length; j++)
if (a[i] > a[j])
    {
    int temp = a[i]; a[i] = a[j]; a[j] = temp;
}

if(a.length>=7){
	System.out.print("Sub String: ");
	  for(int k=4;k<7;k++){
		  System.out.print(a[k]+" ");
	  }}System.out.println();
}
int searchKMP(char[] w, char[] s, int[] t)
{
 int m=0;
 int i=0;
 while( ((m + i) < s.length) && (i<w.length) )
 {
  if(s[m+i] == w[i]) i++;
  else
  {
   m += i - t[i];
   if (i > 0) i = t[i];
   i++;
  }
 }
 if(i == w.length) return m;
 else return -1;
}


class StateListener implements ControllerListener {

	public void controllerUpdate(ControllerEvent ce) {

		    if (ce instanceof ControllerClosedEvent)
		setFailed();
	    if (ce instanceof ControllerEvent) {
		synchronized (getStateLock()) {
		    getStateLock().notifyAll();
		}
	    }
	}
}
ArrayList ar=new ArrayList();
String pb;
double mediat;
public void VideoPlayer(String outfile2) {
	setVisible(false);
	jLabel1.hide();
	jLabel2.hide();
	jComboBox1.hide();
	 jLabel3.hide();
     jTextField1.hide();
     jLabel4.hide();
     jTextField2.hide();
     jButton1.hide();
     jButton2.hide();
     jLabel5.hide();

setLayout(new BorderLayout());

addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent we) {
if (player != null) {
player.stop();
player.close();
}
}
});

ControllerListener cl = new ControllerAdapter() {
public void configureComplete(ConfigureCompleteEvent cce) {

}
public void controllerError(ControllerErrorEvent cee) {
System.out.println("controller error event");
System.exit(0);
}
public void controllerClosed(ControllerClosedEvent cce) {
System.out.println("controller closed event");
setVisible(false);
}
public void deallocate(DeallocateEvent de) {

}
public void endOfMedia(EndOfMediaEvent eome) {

}
public void formatChange(FormatChangeEvent fce) {

pack();
}
public void internalError(InternalErrorEvent iee) {
System.out.println("internal error");
}
public void mediaTimeSet(MediaTimeSetEvent mtse) {
long se = System.currentTimeMillis();
//long nana=System.nanoTime();

int i=(int)mediat;
ar.add(i);
long dur=se-sysecond;
long ssemi=dur/1000;
int kk=i+1;
if(ssemi>dur1){
	ssemi=dur1-1;
}
for(int hg=0;hg<ssemi;hg++){
ar.add(kk);
kk++;
}

sysecond=se;
}
public void prefetchComplete(PrefetchCompleteEvent pce) {
System.out.println("prefetch completed");
player.start();
}
public void realizeComplete(RealizeCompleteEvent rce) {
System.out.println("realize complete event");
Component c = player.getVisualComponent();
if (c != null)
add(c,BorderLayout.CENTER);
else
System.out.println("no visual component");

c = player.getControlPanelComponent();
if (c != null)
add(c,BorderLayout.SOUTH);

FormatControl formatControl = (FormatControl)
player.getControl("javax.media.control.FormatControl");

if (formatControl != null) {
c = formatControl.getControlComponent();
if (c != null)
add(c,BorderLayout.EAST);
}

pack();
setVisible(true);
}
public void restarting(RestartingEvent re) {

}
public void sizeChange(SizeChangeEvent sce) {

}

public void start(StartEvent se) {


}
public void stop(StopEvent se) {

}
public void transition(TransitionEvent te) {
System.out.print("transition event : ");
int state = te.getCurrentState();
switch (state) {
case Processor.Configuring:
System.out.println(" configuring");
break;
case Processor.Configured:
System.out.println(" configured");
break;
case Processor.Prefetching:
System.out.println(" prefetching");
break;
case Processor.Prefetched:
{
System.out.println(" prefetched");
break;
}
case Processor.Realizing:
System.out.println(" realizing");
break;
case Processor.Realized:
{
System.out.println(" realized");

slep = (int) player.getDuration().getSeconds();
int sl=slep+3;
//if(sl>=dur1){
si+=1;
if(si==1)
{
ChatServer(kl);
}
break;
}
case Processor.Unrealized:
System.out.println(" unrealized");
break;
case Processor.Started:

mediat=player.getMediaTime().getSeconds();
//Calendar calendar = new GregorianCalendar();
sysecond = System.currentTimeMillis();//calendar.get(Calendar.SECOND);

break;
}
}
};
try {
MediaLocator ml;
File file = new File(outfile2);
if (file.exists()) {
ml = new MediaLocator(file.toURL());
} else
ml = new MediaLocator(outfile2);
player = Manager.createPlayer(ml);
player.addControllerListener(cl);
player.prefetch();


} catch (NoPlayerException npe) {
System.out.println(npe);
System.exit(0);
} catch (IOException ioe) {
System.out.println(ioe);System.exit(0);
}
}

public boolean doIt(MediaLocator inML, MediaLocator outML, int duration) {

Processor p;

try {
	  System.err.println("- create processor for: " + inML);
    p = Manager.createProcessor(inML);
    thisProcessor = p;

} catch (Exception e) {
    System.err.println("Cannot create a processor from the given url: " + e);
    return false;
}

p.addControllerListener(this);

p.configure();
if (!waitForState1(p, p.Configured)) {
    System.err.println("Failed to configure the processor.");
    return false;
}

setContentDescriptor(p, outML);


if (!setTrackFormats(p))
    return false;

p.realize();
 	if (!waitForState(p, p.Realized)) {
    System.err.println("Failed to realize the processor.");
    return false;
}


setJPEGQuality1(p, 0.5f);


DataSink dsink;
if ((dsink = createDataSink(p, outML)) == null) {
    System.err.println("Failed to create a DataSink for the given output MediaLocator: " + outML);
    return false;
}

dsink.addDataSinkListener(this);
fileDone = false;


if (duration > 0)
    p.setStopTime(new Time((double)duration));

System.err.println("Receiving video segments...");


try {
    p.start();
    dsink.start();
} catch (IOException e) {
    System.err.println("IO error during receving");
    return false;
}


waitForFileDone(duration);


try {
	  try {
    	     Thread.currentThread().sleep(1000);
    	     }
    	     catch (InterruptedException ie) {
    	  	}
    dsink.close();
} catch (Exception e) {}
p.removeControllerListener(this);


System.err.println("...Enough segments received. Now Starting playback");
VideoPlayer(outfile);

return true;
}


void setContentDescriptor(Processor p, MediaLocator outML) {

	ContentDescriptor cd;

if ((cd = fileExtToCD(outML.getRemainder())) != null) {



    if ((p.setContentDescriptor(cd)) == null) {

	p.setContentDescriptor(new ContentDescriptor(ContentDescriptor.RAW));
    }
}
}


boolean setTrackFormats(Processor p) {

Format supported[];

TrackControl [] tracks = p.getTrackControls();

if (tracks == null || tracks.length < 1) {
    System.err.println("Couldn't find tracks in processor");
    return false;
}

for (int i = 0; i < tracks.length; i++) {
    if (tracks[i].isEnabled()) {
	supported = tracks[i].getSupportedFormats();
	if (supported.length > 0) {
	    tracks[i].setFormat(supported[0]);
	} else {
	    System.err.println("Cannot transcode track [" + i + "]");
	    tracks[i].setEnabled(false);
	    return false;
	}
    } else {
	tracks[i].setEnabled(false);
	return false;
    }
}
return true;
}


void setJPEGQuality1(Player p, float val) {

Control cs[] = p.getControls();
QualityControl qc = null;
VideoFormat jpegFmt = new VideoFormat(VideoFormat.JPEG);

for (int i = 0; i < cs.length; i++) {

    if (cs[i] instanceof QualityControl &&
	cs[i] instanceof Owned) {
	Object owner = ((Owned)cs[i]).getOwner();

	if (owner instanceof Codec) {
	    Format fmts[] = ((Codec)owner).getSupportedOutputFormats(null);
	    for (int j = 0; j < fmts.length; j++) {
		if (fmts[j].matches(jpegFmt)) {
		    qc = (QualityControl)cs[i];
    		    qc.setQuality(val);

		    break;
		}
	    }
	}
	if (qc != null)
	    break;
    }
}
}



DataSink createDataSink(Processor p, MediaLocator outML) {

DataSource ds;

if ((ds = p.getDataOutput()) == null) {
    System.err.println("Error : The processor does not have an output DataSource");
    return null;
}

DataSink dsink;

try {
    System.err.println("- create DataSink for: " + outML);
    dsink = Manager.createDataSink(ds, outML);
    dsink.open();
} catch (Exception e) {
	    System.err.println("Cannot create the DataSink: " + e);
    return null;
}

return dsink;
}


Object waitSync = new Object();
boolean stateTransitionOK = true;

boolean waitForState1(Processor p, int state) {
synchronized (waitSync) {
    try {
	while (p.getState() < state && stateTransitionOK)
	    waitSync.wait();
    } catch (Exception e) {}
}
return stateTransitionOK;
}



public void controllerUpdate(ControllerEvent evt) {

if (evt instanceof ConfigureCompleteEvent ||
    evt instanceof RealizeCompleteEvent ||
    evt instanceof PrefetchCompleteEvent) {
    synchronized (waitSync) {
	stateTransitionOK = true;
	waitSync.notifyAll();
    }
} else if (evt instanceof ResourceUnavailableEvent) {
    synchronized (waitSync) {
	stateTransitionOK = false;
	waitSync.notifyAll();
    }
} else if (evt instanceof MediaTimeSetEvent) {
    System.err.println("- mediaTime set: " +
	((MediaTimeSetEvent)evt).getMediaTime().getSeconds());
} else if (evt instanceof StopAtTimeEvent) {
    System.err.println("- stop at time: " +
	((StopAtTimeEvent)evt).getMediaTime().getSeconds());
    evt.getSourceController().close();
}
}


Object waitFileSync = new Object();
boolean fileDone = false;
boolean fileSuccess = true;


boolean waitForFileDone(int duration) {
System.err.print("  ");
synchronized (waitFileSync) {
    try {
	while (!fileDone) {
	    if(thisProcessor.getMediaTime().getSeconds() > duration)
		thisProcessor.close();
	    waitFileSync.wait(1000);
	    System.err.print(".");
	}
    } catch (Exception e) {}
}
System.err.println("");

return fileSuccess;
}

public void dataSinkUpdate(DataSinkEvent evt) {

if (evt instanceof EndOfStreamEvent) {
    synchronized (waitFileSync) {
	fileDone = true;
	waitFileSync.notifyAll();
    }
} else if (evt instanceof DataSinkErrorEvent) {
    synchronized (waitFileSync) {
	fileDone = true;
	fileSuccess = false;
	waitFileSync.notifyAll();
    }
}
}

ContentDescriptor fileExtToCD(String name) {

String ext;
int p;


if ((p = name.lastIndexOf('.')) < 0)
    return null;

ext = (name.substring(p + 1)).toLowerCase();

String type;


if ( ext.equals("mp3")) {
    type = FileTypeDescriptor.MPEG_AUDIO;
} else {
    if ((type = com.sun.media.MimeManager.getMimeType(ext)) == null)
	return null;
    type = ContentDescriptor.mimeTypeToPackageName(type);
}

return new FileTypeDescriptor(type);
}


/**
 * Main program
 */
static MediaLocator iml;
static MediaLocator oml;
static String outfile;
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VPeer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    FileDialog fd = null;
    JFrame frame;
    // End of variables declaration

}
