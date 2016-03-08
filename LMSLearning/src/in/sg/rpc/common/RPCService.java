package in.sg.rpc.common;

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import in.sg.rpc.common.domain.FeeDetails;
import in.sg.rpc.common.exception.UserExistsException;
import in.sg.rpc.common.exception.UserLoginException;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface RPCService {
	@WebMethod 
	public String register(String userName, String passWord) throws UserExistsException, IOException;
	@WebMethod
	public int login (String userName, String password) throws UserLoginException;
	@WebMethod
	public String getCourseDetailForUser(int userId) throws Exception;
	@WebMethod
	public FeeDetails getFeeDetailsforUserid(int userId) throws Exception;
}
