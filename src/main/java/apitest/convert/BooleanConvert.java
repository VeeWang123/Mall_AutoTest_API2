package apitest.convert;

import com.github.crab2died.converter.ReadConvertible;
import com.github.crab2died.converter.WriteConvertible;

public class BooleanConvert implements WriteConvertible,ReadConvertible{

	@Override
	public Object execRead(String object) {
        return "是".equals(object);
    }

	@Override
	public Object execWrite(Object object) {
		if(object instanceof Boolean) {
			Boolean boolean1 =(Boolean)object;
			if(boolean1) {
				return "是";
			}
		}
		return "未开启";
	}

}
