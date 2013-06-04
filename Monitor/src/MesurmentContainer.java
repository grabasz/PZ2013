import java.util.ArrayList;
import java.util.Date;


public  class MesurmentContainer {
	private static Object lock=new Object();
	private static ArrayList<Mesurment> mesurments=new ArrayList<Mesurment>();
	static{
		Mesurment m=new Mesurment("test1");
		m.addData((new Date()).getTime(), "0.5");
		m.addData((new Date()).getTime(), "0.56");
		m.addData((new Date()).getTime(), "0.6");
		mesurments.add(m);
		mesurments.add(new Mesurment("test2"));
		mesurments.add(new Mesurment("test3"));
			
		(new Thread(new SensorReciver())).start();
		
	}
	//TODO do zmiany gdy dogadamy sie co do ramki
	public static void parseSensorData(String recievedData){
		String [] data= recievedData.split(" ");
		synchronized(lock){
		if(existMesurment(data[0])){
			getByName(data[0]).addData((new Date()).getTime(), data[2]);
		}
		else{
			Mesurment m=new Mesurment(data[0]);
			mesurments.add(m);
			m.addData((new Date()).getTime(), data[2]);
		}
			
		}
	}
	public static ArrayList<Integer> getIDs(){
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(Mesurment m:mesurments){
			list.add(m.getID());
		}
		return list;
	}
	public static Mesurment getById(int id){
		for(Mesurment m:mesurments){
			if(m.getID()==id)
				return m;
		}
		return null;
	}
	public static Mesurment getByName(String name){
		for(Mesurment m:mesurments){
			if(m.getName().equals(name))
				return m;
		}
		return null;
	}
	public static boolean existMesurment(String name){
		System.out.println("name: "+name);
		for(Mesurment m:mesurments){
			System.out.println("m.name: "+m.getName());
			if(m.getName().equals(name))
				return true;
		}
		return false;
	}
	
	synchronized public static void addMesurment(Mesurment m){
		if(existMesurment(m.getName()))
			return;
		mesurments.add(m);
	}
}
