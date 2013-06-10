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