enigma_demonstration_java
=========================

A Code demonstration - The German Enigma machine in Java.

I wrote a short utility in C to help with the conversion of the rotor Wirings
to int[]. It is available [here](https://gist.github.com/rrbrussell/9530217).

```
usage: enigma [options] message
 -decode                                   decode a message
 -encode                                   Encode a message
 -g,--grundstellung <grundstellung>        3 characters like ABC or ZYX
 -ms,--message-setting <message-setting>   3 characters like ADE or ZIO
 -r,--ringstellung <ringstellung>          3 numbers seperated by :
 -s,--steckerverbindungen <pairs>          Up to 10 pairs of letters seperated by :
 -w,--walzenlage <walzenlage>              3 rotor names sperated by :
 ```
 
 Binary version is available [here](https://dl.dropboxusercontent.com/u/19495297/enigma.jar).