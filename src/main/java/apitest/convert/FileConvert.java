package apitest.convert;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import com.github.crab2died.converter.ReadConvertible;
import com.github.crab2died.converter.WriteConvertible;

public class FileConvert implements WriteConvertible,ReadConvertible{

	@Override
	public Object execRead(String object) {
	    if(StringUtils.endsWithIgnoreCase(object, "csv")||StringUtils.endsWithIgnoreCase(object, "txt")) {
	    	String filePath=System.getProperty("user.dir")+File.separator+"data"+File.separator+object;
	    	try {
				return FileUtils.readFileToString(new File(filePath),"utf-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		return object;
	}

	@Override
	public Object execWrite(Object object) {
		return object;
	}

}
