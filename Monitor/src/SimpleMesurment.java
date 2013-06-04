import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class SimpleMesurment implements Mesurment {
	private static AtomicInteger count=new AtomicInteger();
	public static final int SIZE=50;
	private String name;
	private int id;
	private boolean initialized;
	private ConcurrentLinkedQueue<Data> dataList =new ConcurrentLinkedQueue<Data>();
	
	public SimpleMesurment(String name){
		this.name=name;
		id=count.incrementAndGet();
		initialized=false;
	}
	
	
	


	/* (non-Javadoc)
	 * @see MesurmentI#addData(long, java.lang.String)
	 */
	@Override
	public void addData(long timestamp, String data){
		if(dataList.size()>SIZE)
			dataList.poll();
		dataList.add(new Data(timestamp,data));
	}
	/* (non-Javadoc)
	 * @see MesurmentI#getData()
	 */
	@Override
	public ArrayList<Data> getData(){
		return new ArrayList<Data>(dataList);
	}
	
	/* (non-Javadoc)
	 * @see MesurmentI#getName()
	 */
	@Override
	public String getName(){
		return name;
	}
	/* (non-Javadoc)
	 * @see MesurmentI#getID()
	 */
	@Override
	public int getID(){
		return id;
	}
	public boolean isInitialized() {
		return initialized;
	}


	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
}
