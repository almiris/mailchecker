package fr.almiris.open.mailchecker;

import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

public class MailChecker {

	private Cache<String, CheckResult> cache;
		
	public MailChecker() {
	}
	
	public MailChecker(int size) {
		cache = new Cache<String, CheckResult>();
		cache.setSize(size);
	}

	public MailChecker(int size, long duration) {
		cache = new Cache<String, CheckResult>();
		cache.setSize(size);
		cache.setDuration(duration);
	}

	public Record[] lookupMxRecords(String domain) throws TextParseException {
		CheckResult result = cache == null ? null : cache.get(domain);
		if (result != null) {
			return result.getRecords();
		}
	    final Lookup dnsLookup = new Lookup(domain, Type.MX);
	    Record[] records = dnsLookup.run();
	    updateCache(domain, records);
	    return records;
	}
	
	public boolean isDomain(String domain) {
		try {
			Record[] records = lookupMxRecords(domain);
			return records != null && records.length > 0;
		}
		catch (TextParseException tpe) {
			return false;
		}
	}
	
	private void updateCache(String domain, Record[] records) {
		if (records == null) {
			return;
		}
		if (cache != null) {
			CheckResult result = new CheckResult();
			result.setDomain(domain);
			result.setRecords(records);
			cache.put(domain, result);
		}
	}

}
