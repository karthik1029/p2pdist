package proj;


import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CommonModel
{

    public CommonModel()
    {
        a = new ArrayList();
    }

    public ArrayList getAllHosts()
    {
        String s1 = "net view";
        String s2 = null;
        int count=0;
        int i = 0;
        try
        {
            s2 = runConsoleCommand(s1);
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("exception is").append(exception.toString()).toString());
            exception.printStackTrace();
        }
        StringTokenizer stringtokenizer = new StringTokenizer(s2, "\n");
        try
        {
            do
            {
                if(!stringtokenizer.hasMoreTokens())
                    break;
                String s3 = stringtokenizer.nextToken().trim();
                int j = s3.length();
                if(j > 2)
                {
                    String s = s3.substring(0, 2);
                    if(s.compareTo("\\") == 1)
                    {
                        i++;
                       // s3 = s3.substring(2, 8);
                        count++;
						if(j<=23)
						{
						s3 = s3.substring(2, j);
						s3=s3.replaceAll(" ","");
						}
						if(j>=22)
						{
						s3 = s3.substring(2, 23);
						s3=s3.replaceAll(" ","");
}
                        try
                        {
                            String s4 = InetAddress.getByName(s3).getHostAddress();
                            a.add(s4);
                        }
                        catch(UnknownHostException unknownhostexception) { }
                        catch(Exception exception2)
                        {
                            System.out.println((new StringBuilder()).append("Exception eeeee").append(exception2).toString());
                        }
                    }
                }
            } while(true);
        }
        catch(Exception exception1)
        {
            System.out.println("");
        }
        return a;
    }

    protected static String runConsoleCommand(String s)
        throws IOException
    {
        Process process = Runtime.getRuntime().exec(s);
        BufferedInputStream bufferedinputstream = new BufferedInputStream(process.getInputStream());
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        do
        {
            i = bufferedinputstream.read();
            stringbuffer.append((char)i);
        } while(i != -1);
        String s1 = stringbuffer.toString();
        bufferedinputstream.close();
        return s1;
    }

    public String[] getPingDetails(String s)
    {
        System.out.println((new StringBuilder()).append("The Ip address is:").append(s).toString());
        String s1 = null;
        try
        {
            s1 = new String(runConsoleCommand((new StringBuilder()).append("ping ").append(s).toString()));
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("The Error is: ").append(exception).toString());
        }
        System.out.println((new StringBuilder()).append("%%%%%%%%%").append(s1).toString());
        StringTokenizer stringtokenizer = new StringTokenizer(s1, "\n");
        String as[] = new String[stringtokenizer.countTokens()];
        for(int i = 0; stringtokenizer.hasMoreTokens(); i++)
            as[i] = stringtokenizer.nextToken().trim();

        return as;
    }

    public void getNetState()
    {
        Object obj = null;
        try
        {
            String s = new String(runConsoleCommand("netstat"));
            StringTokenizer stringtokenizer = new StringTokenizer(s, "\n");
            String as[] = new String[stringtokenizer.countTokens()];
            for(int i = 0; stringtokenizer.hasMoreTokens(); i++)
                as[i] = stringtokenizer.nextToken().trim();

        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("The Error is: ").append(exception).toString());
        }
    }

    ArrayList a;
}
