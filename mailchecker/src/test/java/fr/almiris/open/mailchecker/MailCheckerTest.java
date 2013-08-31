package fr.almiris.open.mailchecker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for MailChecker.
 */
public class MailCheckerTest {
	
	MailChecker checker;;
	
	final static String[] mxDomains = {
			"gmail.com",
			"facebook.com",
			"twitter.com",
			"github.com",
			"almiris.fr"
	};

	final static String[] noMxOrFakeDomains = {
		"dnsjava.org",
		"this_should_be_a_fake_domain.fake"
	};

	@Before
	public void init() {
		checker = new MailChecker(100, Cache.DEFAULT_DURATION);
	}
	
	@Test
	public void checkMxDomains() {
		checkDomains(mxDomains, true);
	}

	@Test
	public void checkNoMxOrFakeDomains() {
		checkDomains(noMxOrFakeDomains, false);
	}

	private void checkDomains(String[] domains, boolean success) {
		for (String domain : domains) {
			Assert.assertTrue(checker.isDomain(domain) == success);	
		}
	}
}
