package com.mastercard.connectedcities.util;

import com.mastercard.connectedcities.constants.FileType;
import com.mastercard.connectedcities.model.CityRouteTO;

import java.util.List;

/**
 * Below is abstract class which parses given file located at specified path
 * {@link FileParsingUtil} abstracts parsing logic based on File type
 * Below method {@link FileParsingUtil#getParserForFileType(FileType)}
 * retrieves appropriate parser based on {@link FileType} passed
 */
public abstract class FileParsingUtil {

    public abstract List<CityRouteTO> parseFileAt() throws Exception;

    /**
     * gets parser based on {@link FileType} passed
     * @param fileType
     * @return
     */
    public static FileParsingUtil getParserForFileType(FileType fileType) {
        switch (fileType) {
            case CSV:
                return BeanUtil.getClass(CsvFileParsingUtil.class);
            default:
                throw new IllegalArgumentException(String.format("There is no parsing utility for %s given file type",fileType.toString()));
        }
    }
}
