/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedict_server;

/**
 *
 * @author Phan Duc Chien
 */
public interface Driver {
//    public static final String dbUrl = "jdbc:sqlserver://GOBOM-PC:1433";
    public static final String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    public static  final String dbUser = "sa";
//    public static final String dbPass = "Gobom31249";
//    public static final String dbDatabase = "EVDICT";
    public static final String dbString = "jdbc:sqlserver://GOBOM-PC:1433;user=sa;password=Gobom31249;database=EVDICT";
}
