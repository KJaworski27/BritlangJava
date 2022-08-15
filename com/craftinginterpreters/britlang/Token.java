package com.craftinginterpreters.britlang;

class Token 
{
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenType type, String lexeme, Object literal, int line)
    {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }


    /*
     * if you can't figure out what code this is without this comment
     * you probably can't undertstand any of the rest of the code
     * with the comments.
     */
    public String tostrString()
    {
        return type + " " + lexeme + " " + literal;
    }
}
