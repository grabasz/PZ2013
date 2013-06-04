import java.util.ArrayList;


public interface Mesurment {

	public abstract void addData(long timestamp, String data);

	public abstract ArrayList<Data> getData();

	public abstract String getName();

	public abstract int getID();
	
	public boolean isInitialized();
	
	public void setInitialized(boolean b);

}