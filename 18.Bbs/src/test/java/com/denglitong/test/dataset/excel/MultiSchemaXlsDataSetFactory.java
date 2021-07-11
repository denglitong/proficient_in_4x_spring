package com.denglitong.test.dataset.excel;

import org.unitils.core.UnitilsException;
import org.unitils.dbunit.datasetfactory.DataSetFactory;
import org.unitils.dbunit.util.MultiSchemaDataSet;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/2
 */
public class MultiSchemaXlsDataSetFactory implements DataSetFactory {

    protected static final String FILE_EXT = "xls";

    protected String defaultSchemaName;

    @Override
    public void init(Properties properties, String defaultSchemaName) {
        this.defaultSchemaName = defaultSchemaName;
    }

    @Override
    public MultiSchemaDataSet createDataSet(File... dataSetFiles) {
        try {
            MultiSchemaXlsDataSetReader xlsDataSetReader = new MultiSchemaXlsDataSetReader(defaultSchemaName);
            return xlsDataSetReader.readDataSetXls(dataSetFiles);
        } catch (Exception e) {
            throw new UnitilsException("创建数据集失败: " + Arrays.toString(dataSetFiles), e);
        }
    }

    @Override
    public String getDataSetFileExtension() {
        return FILE_EXT;
    }
}
