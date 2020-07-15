package com.mastercard.connectedcities.util;

import com.mastercard.connectedcities.constants.FileType;
import com.mastercard.connectedcities.model.CityRouteTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class TestCsvFileParsingUtil {

    @Autowired
    private CsvFileParsingUtil fileParsingUtil;

    @Test
    public void test_parseFileAt() throws Exception{
        List<CityRouteTO> connectedList = fileParsingUtil.parseFileAt();
        Assert.isTrue(!connectedList.isEmpty(),"File parsing successful");
    }

    @Test
    public void test_parseFileAt_using_abstraction() throws Exception{
        List<CityRouteTO> connectedListFromAbst = FileParsingUtil.getParserForFileType(FileType.CSV).parseFileAt();
        Assert.isTrue(!connectedListFromAbst.isEmpty(),"File parsing successful");
    }
}
