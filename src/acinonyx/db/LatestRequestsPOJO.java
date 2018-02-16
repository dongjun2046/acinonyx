package acinonyx.db;

public class LatestRequestsPOJO {
	String date;
	int ticketCount;
	String ticketType;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	@Override
	public String toString() {
		return "LatestRequestsPOJO [date=" + date + ", ticketCount=" + ticketCount + ", ticketType=" + ticketType + "]";
	}

}
