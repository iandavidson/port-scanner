# port-scanner Java based Multi-threaded Port Scanner

## Use cases:
- Kick off port scan event at IP addresses (that you own) for specific ports and port ranges (to be scanned for each IP address). Then hit endpoint to get results.
    - User makes POST request endpoint, providing IP, List<Port> and List<PortRange> in body.
    - Application kicks off process of scanning all provided ports at IP
    - GET endpoint is available to show results and if task is complete
- Look up previously requested event, by Id
    - Response include the following:
      - Raw Scan Results
      - Original specification (what was requested initially)
      - Meter:
        - time taken

## How it works
![HighLevel](docs/HighLevelSys.png)

### Port Scanning

Multi-threaded approach to execute atomic port scanning actions. 
Threads will then write to data structure containing representation of Scanning Task  


#### Default set of ports to scan:
```text
Port 20 (UDP) — File Transfer Protocol (FTP)
Port 22 (TCP) — Secure Shell (SSH)
Port 23 (TCP) — Telnet protocol - usually not available these days due to it being unencrypted and therefore unsecure
Port 53 (UDP) — Domain Name System (DNS)
Port 80 (TCP) — HTTP
Port 443 (TCP) — HTTPS
```

#### Default IPs to scan:
```text
208.67.222.222 and 208.67.220.220 (OpenDNS)
1.1.1.1 and 1.0.0.1 (Cloudflare)
8.8.8.8 and 8.8.4.4 (Google DNS)
```

### Data storage:
- Using Postgresql locally, no password.
- Check folder "db" for initialization of schema 


## Later on ideas:
- Have separate module for actually execution of port scanning, namely, background-module
- Use module that currently defines API to produce into queue, background processing will execute port scanning and write reports to DB
- API layer will then simply read requested reports and also be able to determine if scanning task is "done".

## Self Notes:
- https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
