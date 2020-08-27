import javafx.scene.text.Text;

public class Host {
  String host;
  String ip;
  
  public String getHost() {
    return host;
  }
  
  public String getIp() {
    return ip;
  }
  
  public String getPorts() {
    return ports;
  }
  
  public String getVulns() {
    return vulns;
  }
  
  String ports;
  String vulns;
  public Host(String host, String ip, String ports, String vulns) {
    this.host = host;
    this.ip = ip;
    this.ports = ports;
    this.vulns = vulns;
  }
  
  public void displayData(Text host, Text ip, Text ports, Text vulns) {
    host.setText(getHost());
    ip.setText(getIp());
    ports.setText(getPorts());
    vulns.setText(getVulns());
  }
}
