# AnomalyDetection
Develop a powerful tool to analyze network logs and detect specific anomalies related to packet size and the protocol used (e.g., ICMP, TCP, UDP). The project highlights the critical importance of the protocol in identifying suspicious events to enhance network security.
### 🎯 Focus on the Protocol:
▸ **ICMP**: ICMP packets, often used for network connectivity tests (ping), can be exploited to carry out denial-of-service (DoS) attacks if their size is abnormal. My tool detects these anomalies in real-time to prevent any suspicious behavior.
▸ **TCP & UDP**: The packet size in these protocols must also be monitored to avoid sending excessively large packets that could cause vulnerabilities or be used for malicious purposes.

### 💡 In-Depth Analysis: 
Thanks to a Java algorithm, each network log is scanned to detect:
▸ Excessively large packets that could overload a network.
▸ Specific anomalies in ICMP, where large packet sizes may signal a potential attack.
▸ Unknown or improperly used protocols that could compromise security.

### 🎯 Technologies Used:
▸ **Java**: For the logic of analyzing logs and detecting protocol-related anomalies.
▸ **Swing**: For designing the graphical user interface, making the analysis accessible and interactive.
▸ **BufferedReader**: For efficient reading of log files.

### 🔒 Why the Protocol is Crucial:
The network protocol is a key element in detecting suspicious behavior. In an increasingly connected world, understanding the role of protocols and being able to detect their anomalies in real-time is essential to anticipate attacks and protect data.

### 🔍 Impact:
▸ **Prevention of Network Attacks**: This project helps identify protocol-related anomalies before they become serious threats.
▸ **Enhanced Security**: Active monitoring of network logs allows for better protection of critical infrastructures against targeted attacks.
