package burp.randomheader;

import burp.*;

import java.util.*;
import java.util.Objects;
import java.net.URL;//editor
public class HttpRequestTweaker 
{
    private byte [] contents;
    private List <String> headers;
    private IExtensionHelpers helpers;
    private IHttpRequestResponse currentRequest;
    
    private  String RURL  ="";//editor
    private  String RMETHOD ="";//editor
    private  String RHOST ="";//editor
    private  String RealHOST ="";//editor
    private  String RPORT ="";//editor
    private  String RPROTOCOL ="";//editor
        
    private  String RURLPATH  ="";//editor
    private  String RURLQUERY ="";//editor
    private  String RCOOKIE ="";//editor
     
	public
	HttpRequestTweaker (BurpExtender extension, IHttpRequestResponse messageInfo)
	{
		IRequestInfo rqInfo;
		byte [] fullReq;
		
		this.helpers = extension.getExtensionHelpers ();
		
		rqInfo = helpers.analyzeRequest (messageInfo);
		this.headers  = rqInfo.getHeaders ();
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		this.RURL = String.valueOf(rqInfo.getUrl()) == "null" ? "": String.valueOf(rqInfo.getUrl());//editor
		this.RHOST = String.valueOf(messageInfo.getHttpService().getHost()) == "null" ? "": String.valueOf(messageInfo.getHttpService().getHost());//editor
		this.RPORT = String.valueOf(messageInfo.getHttpService().getPort()) == "null" ? "": String.valueOf(messageInfo.getHttpService().getPort());//editor
		this.RPROTOCOL = String.valueOf(messageInfo.getHttpService().getProtocol()) == "null" ? "" : String.valueOf(messageInfo.getHttpService().getProtocol());//editor
		this.RURLPATH = String.valueOf(rqInfo.getUrl().getPath()) == "null" ? "" : String.valueOf(rqInfo.getUrl().getPath()) ;//editor
		this.RURLQUERY = String.valueOf(rqInfo.getUrl().getQuery()) == "null" ? "" : String.valueOf(rqInfo.getUrl().getQuery()) ;//editor
		this.RMETHOD = String.valueOf(rqInfo.getMethod()) == "null" ? "" : String.valueOf(rqInfo.getMethod());//editor
		
		if (this.RPORT.equals("443") ||  this.RPORT.equals("80")){this.RealHOST = this.RHOST; } else { this.RealHOST = this.RHOST+":"+this.RPORT; }
		/////////////////////////////////////////////////////////////////////////////////////////////////////////

		fullReq = messageInfo.getRequest ();
		
		this.contents = Arrays.copyOfRange(fullReq, rqInfo.getBodyOffset (),fullReq.length);
		this.currentRequest = messageInfo;
	}
	
	public int
	indexOfHeader (String name)
	{
		for (int i = 0; i < this.headers.size (); ++i)
			if (this.headers.get (i).startsWith (name + ": "))
				return i;
		
		return -1;
	}
	
	public boolean
	hasHeader (String name)
	{
		return indexOfHeader (name) != -1;
	}
	
	public void
	setHeader (String name, String value)
	{
		//////////////////////////////////////////////////////
		if (name.startsWith("--")) return;
		//////////////////////////////////////////////////////
		value = value.replace("%RURL%",this.RURL);
		value = value.replace("%RHOST%",this.RHOST);
		value = value.replace("%RPORT%",this.RPORT);
		value = value.replace("%RealHOST%",this.RealHOST);
		value = value.replace("%RPROTOCOL%",this.RPROTOCOL);
		value = value.replace("%RURLPATH%",this.RURLPATH);
		value = value.replace("%RURLQUERY%",this.RURLQUERY);
		value = value.replace("%RMETHOD%",this.RMETHOD);
		//////////////////////////////////////////////////////
		int i;
		String fullHeader = name + ": " + value;

	if ((i = indexOfHeader (name)) != -1)
			this.headers.set (i, fullHeader);
		else
			this.headers.add (fullHeader);
	}
	
	public void
	compose ()
	{
		this.currentRequest.setRequest (this.helpers.buildHttpMessage (this.headers, this.contents));
	}
}
