package fr.almiris.open.mailchecker;

import org.xbill.DNS.Record;

public class CheckResult {
	
	String domain = null;
	
	Record[] records = null;

	public CheckResult() {
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Record[] getRecords() {
		return records;
	}

	public void setRecords(Record[] records) {
		this.records = records;
	}
}
