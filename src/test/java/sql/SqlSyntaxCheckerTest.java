package sql;

import org.junit.Test;

import static org.junit.Assert.*;

public class SqlSyntaxCheckerTest {
    @Test
    public void isSyntaxCorrect() throws Exception {
        String sentence = "SELECT * FROM sth WHERE DADSA (DSA ) DSA DA ORDER BY DSAD";
        SqlSyntaxChecker checker = new SqlSyntaxChecker(sentence);
        assertEquals(true, checker.isSyntaxCorrect());
    }
    @Test
    public void isSyntaxCorrect1() throws Exception {
        String sentence = "FROM sth WHERE DADSA (DSA ) DSA DA ORDER BY";
        SqlSyntaxChecker checker = new SqlSyntaxChecker(sentence);
        assertEquals(false, checker.isSyntaxCorrect());
    }
    @Test
    public void isSyntaxCorrect2() throws Exception {
        String sentence = "SELECT * FROM sth WHERE DADSA (DSA ) DSA DA ORDER BY SELECT";
        SqlSyntaxChecker checker = new SqlSyntaxChecker(sentence);
        assertEquals(false, checker.isSyntaxCorrect());
    }
    @Test
    public void isSyntaxCorrect3() throws Exception {
        String sentence = "SELECT * WHERE sth FROM DADSA (DSA ) DSA DA ORDER BY FROM";
        SqlSyntaxChecker checker = new SqlSyntaxChecker(sentence);
        assertEquals(false, checker.isSyntaxCorrect());
    }
    @Test
    public void isSyntaxCorrect4() throws Exception {
        String sentence = "SELECT * FROM sth WHERE DADSA (DSA ) DSA DA ORDER BY SELECT * FROM DSAHD ";
        SqlSyntaxChecker checker = new SqlSyntaxChecker(sentence);
        assertEquals(true, checker.isSyntaxCorrect());
    }
    @Test
    public void isSyntaxCorrect5() throws Exception {
        String sentence = "SELECT * FROM sth WHERE DADSA (DSA ) DSA DA ORDER BY SELECT";
        SqlSyntaxChecker checker = new SqlSyntaxChecker(sentence);
        assertEquals(false, checker.isSyntaxCorrect());
    }
}
