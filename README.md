# GWT SIPML5 Package

GWT SIPML5 allows Java to access the JavaScript SipML library from sipml5.org using GWT (Google Web Toolkit).
The package consists of a library (gwt-sipml5, originally from [it.netgrid](https://bitbucket.org/netgrid/gwt-sipml5), 
but adapted to work with the demo) and a demo webapp that uses the library. 

The demo is a reimplementation in Java of the sipml5 live demo from [SipMl5](http://sipml5.org) using GWT (Google Web Toolkit).

Release sipml5 1.3.203 from [SipMl5 Downloads](https://code.google.com/p/sipml5/wiki/Downloads) is included.

## Status

[![Build Status](https://buildhive.cloudbees.com/job/duns/job/gwt-sipml5/badge/icon)](https://buildhive.cloudbees.com/job/duns/job/gwt-sipml5/)

## Building the library and the demo

Clone this repository

```
$ git clone https://bitbucket.org/duns/gwt-sipml5.git
```

Run maven

```
$ mvn
```

then startup the resulting jar using the start.sh script in the gwt-sipml5-demo directory, or use the war in a webapp container:

```
$ ./start.sh
```
(start.sh uses the war-bootstrap.jar from [mckamey](https://bitbucket.org/mckamey/war-bootstrap),
which you need to separately clone and create)

goto the following url in a WebRTC supported browser:

```
localhost:1234
```

get a sip account [GetOnSip](http://getonsip.com) and set the authentication details in the panel and make a call. 




Mark Donszelmann [e-mail](mailto:Mark.Donszelmann@gmail.com)
 
