import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class Mesurment {
	private static AtomicInteger count=new AtomicInteger();
	public static final int SIZE=50;
	private String name;
	private int id;
	private boolean initialized;
	private ConcurrentLinkedQueue<Data> dataList =new ConcurrentLinkedQueue<Data>();
	
	public Mesurment(String name){
		this.name=name;
		id=count.incrementAndGet();
		initialized=false;
	}
	
	
	public void addData(long timestamp, String data){
		if(dataList.size()>SIZE)
			dataList.poll();
		dataList.add(new Data(timestamp,data));
	}
	public ArrayList<Data> getData(){
		return new ArrayList<Data>(dataList);
	}
	public String getJSON(){
		StringBuilder sb=new StringBuilder();
		sb.append("{ ");
		
		sb.append("\"Name\" : \"").append(name).append("\" ,");
		
		sb.append("\"data\": [ ");
		for(Data d:dataList){
			sb.append(" { \"timestamp\":\"").append(d.timestamp).append("\" , \"data\":\"").append(d.data).append("\" },");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");

		sb.append("} ");
		return sb.toString();
	}
	public String getName(){
		return name;
	}
	public int getID(){
		return id;
	}
	public class Data{
		private long timestamp;
		private String data;	
		public Data(long timestamp, String data) {
			this.timestamp = timestamp;
			this.data = data;
		}
		public long getTimestamp() {
			return timestamp;
		}
		public String getData() {
			return data;
		}
		
	}
}
