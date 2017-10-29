package sql;

import java.util.ArrayList;
import java.util.LinkedList;

enum sqlCommands {
    SELECT("SELECT", 1),
    FROM("FROM", 2),
    WHERE("WHERE", 3),
    ORDER_BY("ORDER BY", 4);

    String name;
    int order;

    sqlCommands(String name, int order) {
        this.name = name;
        this.order = order;
    }
}

public class SqlSyntaxChecker {

    private String sentence;
    private sqlCommands sqlSyntax[];


    public SqlSyntaxChecker(String sentence) {
        this.sentence = sentence;
        this.sqlSyntax = this.getSqlSyntax();
    }

    private sqlCommands[] getSqlSyntax() {
        String tokens[] = this.prepareTokensFromSentence();
        LinkedList<sqlCommands> sqlCommands1 = new LinkedList<sqlCommands>();
        for(String element: tokens) {
            for(sqlCommands sql: sqlCommands.values()) {
                if(element.equals(sql.name)) sqlCommands1.add(sql);
            }
        }
        sqlCommands[] result = new sqlCommands[sqlCommands1.size()];
        result = sqlCommands1.toArray(result);
        return result;
    }

    private String[] prepareTokensFromSentence() {
        String tempTokens[] = this.sentence.split(" ");
        ArrayList<String> tokens = new ArrayList<String>();
        for(int i=0; i< tempTokens.length ; i++) {
            if (i < tempTokens.length-1 && tempTokens[i].equals("ORDER") && tempTokens[i + 1].equals("BY")) {
                tokens.add("ORDER BY");
                i++;
            } else {
                tokens.add(tempTokens[i]);
            }
        }
        String result[] = new String[tokens.size()];
        result = tokens.toArray(result);
        return result;
    }

    public boolean isSyntaxCorrect() {
        if(this.sqlSyntax.length == 0
                || this.sqlSyntax[0] != sqlCommands.SELECT
                || this.sqlSyntax[this.sqlSyntax.length-1] == sqlCommands.SELECT) {
            return false;
        } else {
            for(int i = 1 ; i < this.sqlSyntax.length; i++) {
               if(this.sqlSyntax[i].order<this.sqlSyntax[i-1].order
                       && this.sqlSyntax[i] != sqlCommands.SELECT){
                   return false;
               }
            }
        }
        return true;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
        this.sqlSyntax = this.getSqlSyntax();
    }
}
