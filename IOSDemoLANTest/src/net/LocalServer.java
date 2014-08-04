package net;

import java.io.IOException;
import java.util.Date;

import net.LocalNetwork.TimeStamp;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class LocalServer {
	
		final Server server;
		
		public LocalServer() {
			Log.set(Log.LEVEL_DEBUG);
			server = new Server();
			LocalNetwork.register(server.getKryo());
			server.addListener(new Listener(){
				@Override
				public void connected(Connection arg0) {
					// TODO Auto-generated method stub
					System.out.println("[Server] connection done");
					long newTimestamp = new Date().getTime();
					sendData(arg0, new TimeStamp(newTimestamp));
					System.out.println("[Server] timestamp sent: " + newTimestamp);
				}
				
				@Override
				public void received(Connection arg0, Object arg1) {
					// TODO Auto-generated method stub
					if(arg1 instanceof TimeStamp){
						TimeStamp timeStamp = (TimeStamp)arg1;
						System.out.println("[Server] timeStamp received: " + timeStamp.value);
					}
					
				}
			});
		}
	
		public void start(){
			server.start();
			
		    try {
		    	if(LocalNetwork.USE_UDP){
					server.bind(LocalNetwork.TCP_PORT, LocalNetwork.UDP_PORT);
		    	}else{
					server.bind(LocalNetwork.TCP_PORT);
		    	}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void sendData(Connection connection, Object object){
			if(LocalNetwork.USE_UDP){
				server.sendToUDP(connection.getID(), object);
			}else{
				server.sendToTCP(connection.getID(), object);
			}
		}
}
