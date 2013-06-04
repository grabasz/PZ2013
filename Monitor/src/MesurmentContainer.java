import java.util.ArrayList;
import java.util.Date;



public  class MesurmentContainer {
	private static Object lock=new Object();
	private static ArrayList<Mesurment> mesurments=new ArrayList<Mesurment>();
	static{
		SimpleMesurment m=new SimpleMesurment("test1");
		m.addData((new Date()).getTime(), "0.5");
		m.addData((new Date()).getTime(), "0.56");
		m.addData((new Date()).getTime(), "0.6");
		mesurments.add(m);
		mesurments.add(new SimpleMesurment("test2"));
		mesurments.add(new SimpleMesurment("test3"));
			
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
			SimpleMesurment m=new SimpleMesurment(data[0]);
			mesurments.add(m);
			m.addData((new Date()).getTime(), data[2]);
		}
			
		}
	}
	public static String getJSON(Mesurment m){
		StringBuilder sb=new StringBuilder();
		sb.append("{ ");
		
		sb.append("\"Name\" : \"").append(m.getName()).append("\" ,");
		
		sb.append("\"data\": [ ");
		for(Data d:m.getData()){
			sb.append(" { \"timestamp\":\"").append(d.getTimestamp()).append("\" , \"data\":\"").append(d.getData()).append("\" },");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");

		sb.append("} ");
		return sb.toString();
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
