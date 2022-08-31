package com.common.utils;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {

    private volatile static DBUtil instance;

    private static String datasourceUrl;

    private static Connection conn;

    public DBUtil(String datasourceUrl) {
        try {
            this.datasourceUrl = datasourceUrl;
            conn = DriverManager.getConnection(datasourceUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBUtil getInstance(String datasourceUrl) {
        if (instance == null) {
            synchronized (DBUtil.class) {
                if (instance == null) {
                    instance = new DBUtil(datasourceUrl);
                }
            }
        }
        return instance;
    }

    public List<HashMap<String, Object>> getRefResultList(String sql) {
        List<HashMap<String, Object>> list = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsm = null;
        HashMap item = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            int cols_len = rsm.getColumnCount();

            while (rs.next()) {
                item = new HashMap();

                for (int i = 0; i < cols_len; ++i) {
                    String cols_name = rsm.getColumnName(i + 1);
                    Object cols_value = rs.getObject(cols_name);
                    if (cols_value != null) {
                        if (cols_value instanceof Long) {
                            cols_value = Long.valueOf(cols_value.toString());
                        } else if (cols_value instanceof String) {
                            cols_value = String.valueOf(cols_value);
                        } else if (cols_value instanceof Short) {
                            cols_value = Short.valueOf(cols_value.toString());
                        } else if (cols_value instanceof Double) {
                            cols_value = Double.valueOf(cols_value.toString());
                        } else if (cols_value instanceof Float) {
                            cols_value = Float.valueOf(cols_value.toString());
                        } else if (cols_value instanceof Date) {
                            cols_value = (Date) cols_value;
                        }
                    } else {
                        cols_value = "";
                    }

                    item.put(cols_name, cols_value);
                }

                list.add(item);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return list;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rset) {
        try {
            if (rset != null) {
                rset.close();
                rset = null;
            }
        } catch (Exception exception) {
            exception.printStackTrace(System.err);
        }
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (Exception exception) {
            exception.printStackTrace(System.err);
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException exception) {
                ;
            }
        }
    }
}
