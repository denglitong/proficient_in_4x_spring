package com.denglitong.test.dataset.excel;

import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.dataset.*;
import org.dbunit.dataset.excel.XlsDataSet;
import org.springframework.util.StringUtils;
import org.unitils.core.UnitilsException;
import org.unitils.dbunit.util.MultiSchemaDataSet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/2
 */
public class MultiSchemaXlsDataSetReader {

    private String defaultSchemaName;

    public MultiSchemaXlsDataSetReader(String defaultSchemaName) {
        this.defaultSchemaName = defaultSchemaName;
    }

    public MultiSchemaDataSet readDataSetXls(File... dataSetFiles) {
        try {
            Map<String, List<ITable>> tableMap = getTables(dataSetFiles);
            MultiSchemaDataSet dataSets = new MultiSchemaDataSet();
            for (Map.Entry<String, List<ITable>> entry : tableMap.entrySet()) {
                String schema = entry.getKey();
                List<ITable> tables = entry.getValue();
                try {
                    DefaultDataSet dataSet = new DefaultDataSet(tables.toArray(new ITable[]{}));
                    dataSets.setDataSetForSchema(schema, dataSet);
                } catch (Exception e) {
                    throw new UnitilsException("DataSet 报错", e);
                }
            }
            return dataSets;
        } catch (UnitilsException e) {
            throw new UnitilsException("数据集 EXCEL 报错", e);
        }
    }

    private Map<String, List<ITable>> getTables(File... dataSetFiles) {
        Pattern pattern = Pattern.compile("\\.");
        Map<String, List<ITable>> tableMap = new HashMap<>();
        for (File file : dataSetFiles) {
            try {
                IDataSet dataSet = new XlsDataSet(new FileInputStream(file));
                String[] tableNames = dataSet.getTableNames();
                for (String each : tableNames) {
                    String schema = null;
                    String tableName = null;
                    String[] temp = pattern.split(each);
                    if (temp.length == 2) {
                        schema = temp[0];
                        tableName = temp[1];
                    } else {
                        schema = this.defaultSchemaName;
                        tableName = each;
                    }
                    ITable table = dataSet.getTable(each);
                    if (!tableMap.containsKey(schema)) {
                        tableMap.put(schema, new ArrayList<>());
                    }
                    tableMap.get(schema).add(new XlsTable(tableName, table));
                }
            } catch (Exception e) {
                throw new UnitilsException("数据集转换出错:" + Arrays.toString(dataSetFiles), e);
            }
        }
        return tableMap;
    }
}

class XlsTable extends AbstractTable {
    private ITable delegate;
    private String tableName;

    public XlsTable(String tableName, ITable table) {
        this.delegate = table;
        this.tableName = tableName;
    }

    @Override
    public ITableMetaData getTableMetaData() {
        ITableMetaData meta = delegate.getTableMetaData();
        try {
            return new DefaultTableMetaData(tableName, meta.getColumns(), meta.getPrimaryKeys());
        } catch (DataSetException e) {
            throw new UnitilsException("Don't get the meta info from " + meta, e);
        }
    }

    @Override
    public int getRowCount() {
        return delegate.getRowCount();
    }

    @Override
    public Object getValue(int row, String column) throws DataSetException {
        Object value = delegate.getValue(row, column);
        if (value instanceof String) {
            if (StringUtils.isEmpty(value)) {
                return null;
            }
        }
        return value;
    }
}
