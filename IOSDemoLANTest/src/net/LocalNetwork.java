package net;
import com.esotericsoftware.kryo.Kryo;


public class LocalNetwork {

	public static final boolean USE_UDP = true;
	
    public static final int UDP_PORT=54777;
    public static final int TCP_PORT=54555;
    
    
    public static void register(Kryo kryo){
    	kryo.register(TimeStamp.class);
    }
    
    public static class TimeStamp{
    	public long value;
    	public TimeStamp() {
			// TODO Auto-generated constructor stub
		}
    	public TimeStamp(long value) {
			this.value = value;
		}
    }
}
