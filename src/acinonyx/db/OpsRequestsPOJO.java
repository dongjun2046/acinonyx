package acinonyx.db;

import java.sql.Timestamp;

public class OpsRequestsPOJO {
	// |-----------+-------------+-----------+-----------+---------+-------------+---------------|
	// | ticket_id | ticket_type | requestor | req_start | req_end | ticket_text
	// | ticket_status |
	// |-----------+-------------+-----------+-----------+---------+-------------+---------------|

	String ticket_id;
	String ticket_type;
	String requestor;
	Timestamp req_start;
	Timestamp req_end;
	String ticket_text;
	String ticket_status;

	public String getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getTicket_type() {
		return ticket_type;
	}

	public void setTicket_type(String ticket_type) {
		this.ticket_type = ticket_type;
	}

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public Timestamp getReq_start() {
		return req_start;
	}

	public void setReq_start(Timestamp req_start) {
		this.req_start = req_start;
	}

	public Timestamp getReq_end() {
		return req_end;
	}

	public void setReq_end(Timestamp req_end) {
		this.req_end = req_end;
	}

	public String getTicket_text() {
		return ticket_text;
	}

	public void setTicket_text(String ticket_text) {
		this.ticket_text = ticket_text;
	}

	public String getTicket_status() {
		return ticket_status;
	}

	public void setTicket_status(String ticket_status) {
		this.ticket_status = ticket_status;
	}

	@Override
	public String toString() {
		return "OpsRequestsPOJO [ticket_id=" + ticket_id + ", ticket_type=" + ticket_type + ", requestor=" + requestor
				+ ", req_start=" + req_start + ", req_end=" + req_end + ", ticket_text=" + ticket_text
				+ ", ticket_status=" + ticket_status + "]";
	}

}
