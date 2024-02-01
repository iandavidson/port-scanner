# port-scanner Java based Multi-threaded Port Scanner

## Use cases:
- Kick off port scan at IP addresses (that you own) for specific ports and port ranges (to be scanned for each IP address). Then hit endpoint to get results.
    - User makes POST request endpoint, providing IP, List<Port> and List<PortRange> in body.
    - Application kicks off process of scanning all provided ports at IP
    - GET endpoint is available to show results and if task is complete

## How it works


### Port Scanning

Multi-threaded approach to execute atomic port scanning actions. 
Threads will then write to data structure containing representation of Scannning Task  


#### Default set of ports to scan :
```text
Port 20 (UDP) — File Transfer Protocol (FTP)
Port 22 (TCP) — Secure Shell (SSH)
Port 23 (TCP) — Telnet protocol - usually not available these days due to it being unencrypted and therefore unsecure
Port 53 (UDP) — Domain Name System (DNS)
Port 80 (TCP) — HTTP
Port 443 (TCP) — HTTPS
```

#### Scan range of IPs

### Data storage:
- Planning on using some sort of temporary relational storage like h2


