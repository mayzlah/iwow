import java.io.*;
import java.net.*;

class iWOW
{
	public static void main(String args[])
	{
		try
		{
			byte buf[] = new byte[64*1024];
			String host = "eu.battle.net";
			String request = "GET /api/wow/character/rexxar/mon?fields=items";
			int port = 80;
			Socket s_wow = new Socket(host, port);
			request = request+"\nHost: "+host+"\n\n";
			System.out.println(request);
			System.out.println(request.getBytes());
			s_wow.getOutputStream().write(request.getBytes());
			InputStream g_wow = s_wow.getInputStream();
			FileOutputStream r_wow = new FileOutputStream("result.txt");
			int r_res = 1;
			while (r_res > 0)
			{
				r_res = g_wow.read(buf);
				if(r_res > 0)
					r_wow.write(buf, 0, r_res);
			}
			r_wow.close();
			s_wow.close();			
		}
	catch(Exception e)
        {e.printStackTrace();}
	}
}
