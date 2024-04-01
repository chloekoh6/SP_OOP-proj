package application;

public class TbData {
	
	private String day;
	private String mod;
	private String start;
	private String end;
	private String lec;
	private String loc;
	
	TbData(String day, String mod, String start, String end, String lec, String loc){
		this.day = day;
		this.mod = mod;
		this.start = start;
		this.end = end;
		this.lec = lec;
		this.loc = loc;
	}
	
	public String getDay() {
		return day;
	}

	public void setday(String day) {
		this.day = day;
	}

	public String getMod() {
		return mod;
	}

	public void setmod(String mod) {
		this.mod = mod;
	}

	public String getStart() {
		return start;
	}

	public void setstart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setend(String end) {
		this.end = end;
	}

	public String getLec() {
		return lec;
	}

	public void setlec(String lec) {
		this.lec = lec;
	}

	public final String getLoc() {
	   return loc;
	}

	public final void setloc(String loc) {
	    this.loc = loc;
	}

}