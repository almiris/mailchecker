mailchecker
===========

MailChecker is a JAVA helper class that checks if a domain may be used in email adresses (if the domain has an MX DNS entry).

Code example (without cache) :

    MailChecker mc = new MailChecker();
    
    if (mc.isDomain("mydomain.com") == true) {
      // myemail@mydomain.com might exist
    }
    
Code example (with cache) :

    // A cache of 1000 verified entries is managed; entries expire after 1 hour
    MailChecker mc = new MailChecker(1000, Cache.DEFAULT_DURATION);
    
    if (mc.isDomain("mydomain.com") == true) {
      // myemail@mydomain.com might exist
    }
    
