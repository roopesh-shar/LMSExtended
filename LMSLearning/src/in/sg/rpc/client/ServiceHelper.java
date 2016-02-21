package in.sg.rpc.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import in.sg.rpc.common.PropertiesReader;

public class ServiceHelper {

	public static Service getRemoteService() throws MalformedURLException {
		
		String serviceURI = "http://server.rpc.sg.in/";
		String serviceName = "RPCServerService";
		URL url = new URL("http://" + PropertiesReader.getProperty("server.ip") + ":"
				+ PropertiesReader.getProperty("server.port") + "/ws/register?wsdl");

		QName qname = new QName(serviceURI, serviceName);
		return Service.create(url, qname);

	}

}
