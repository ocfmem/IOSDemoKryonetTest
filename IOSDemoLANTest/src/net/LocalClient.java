package net;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import net.LocalNetwork.TimeStamp;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.util.TcpIdleSender;
import com.esotericsoftware.minlog.Log;

public class LocalClient {

	final Client client;
	
	public LocalClient() {
		Log.set(Log.LEVEL_DEBUG);
		client = new Client();
	    LocalNetwork.register(client.getKryo());
	    client.addListener(new Listener(){
			@Override
			public void connected(Connection arg0) {
				// TODO Auto-generated method stub
				super.connected(arg0);
				System.out.println("[Client] connected");
			}
			
			@Override
			public void received(Connection arg0, Object arg1) {
				// TODO Auto-generated method stub
				if(arg1 instanceof TimeStamp){
					TimeStamp timestamp = (TimeStamp)arg1;
					System.out.println("[Client] timeStamp received: " + timestamp.value);
					long newTimestamp = new Date().getTime();
					sendData(arg0, new TimeStamp(newTimestamp));
					System.out.println("[Client] timeStamp sent: " + newTimestamp);
				}
			}
		});

	   
	}
	
	public void connect(){
		client.start();
		System.out.println("[Client] Start DiscoverHost");
		InetAddress adress = client.discoverHost(LocalNetwork.UDP_PORT, 5000);
		System.out.println("[Client] End DiscoverHost ");
		try {
			if(adress != null)System.out.println("[Client] DicoverHost completed!");
			client.connect(5000, adress, LocalNetwork.TCP_PORT, LocalNetwork.UDP_PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[Client] DicoverHost failed" + e.getMessage());
		}
	}
	
	private void sendData(Connection connection, Object object){
		if(LocalNetwork.USE_UDP){
			client.sendUDP(object);
		}else{
			client.sendTCP(object);
		}
	}
	
}
