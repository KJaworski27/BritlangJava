package com.craftinginterpreters.britlang;

import static com.craftinginterpreters.britlang.TokenType.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Scanner 
{
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    private static final Map<String, TokenType> keywords;
    static 
    {
        keywords = new HashMap<>();
        //keywords.put("and", AND);
        keywords.put("ace", AND);
        //keywords.put("class", CLASS); 
        keywords.put("cuppa", CLASS);
        //keywords.put("else", ELSE);
        keywords.put("gutted", ELSE);
        //keywords.put("false", FALSE);
        keywords.put("naff", FALSE);
        keywords.put("for", FOR);
        //keywords.put("fun", FUN);
        keywords.put("codswallop", FUN);
        //keywords.put("if", IF);
        keywords.put("gander", IF);
        //keywords.put("nil", NIL);
        keywords.put("nowt", NIL);
        //keywords.put("or", OR);
        keywords.put("kerfuffle", OR);
        //keywords.put("print", PRINT);
        keywords.put("chinwag", PRINT);
        //keywords.put("return", RETURN);
        keywords.put("cheers", RETURN);
        //keywords.put("super", SUPER);
        keywords.put("posh", SUPER);
        //keywords.put("this", THIS);
        keywords.put("bagsy", THIS);
        //keywords.put("true", TRUE);
        keywords.put("scrummy", TRUE);
        //keywords.put("var", VAR);
        keywords.put("tea", VAR);
        keywords.put("while", WHILE);
        //keywords.put("inherits", INHERITS);
        keywords.put("bobsyouruncle", INHERITS);
        keywords.put("innit", SEMICOLON);
    }

    Scanner(String source)
    {
        this.source = source;
    }

    List<Token> scanTokens()
    {
        while (!isAtEnd())
        {
            // Start new lexeme
            start = current;
            scanToken();
        }


        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    } 

    private void scanToken()
    {
        char c = advance();
        switch (c)
        {
            // Single Char
            case '(' : addToken(LEFT_PAREN); break;
            case ')' : addToken(RIGHT_PAREN); break;
            case '{' : addToken(LEFT_BRACE); break;
            case '}' : addToken(RIGHT_BRACE); break;
            case ',' : addToken(COMMA); break;
            case '.' : addToken(DOT); break;
            case '-' : addToken(MINUS); break;
            case '+' : addToken(PLUS); break;
            case ';' : addToken(SEMICOLON); break;
            case '*' : addToken(STAR); break;

            // 1/2 Char
            case '!':
                addToken(match('=') ? BANG_EQUAL : BANG);
                break;
            case '=':
                addToken(match('=') ? EQUAL_EQUAL : EQUAL);
                break;
            case '<':
                addToken(match('=') ? LESS_EQUAL : LESS);
                break;
            case '>':
                addToken(match('=') ? GREATER_EQUAL : GREATER);
                break;
            case '/':
                if (match('/'))
                {
                    //Comment or smth
                    while (peek() != '\n' && !isAtEnd()) advance();
                }else{
                    addToken(SLASH);
                }
                break;

            case '"': string(); break;

            // Whitespace      <--------- OH MY GOD OMORI!
            case ' ':
            case '\r':
            case '\t':
                //ignore whitespace
                break;

            case '\n':
                line++;
                break;
            default:
                if (Character.isDigit(c))
                {
                    number();
                } else if (isAlpha(c))
                {
                    identifier();
                }
                else {
                    
                    Britlang.error(line, "Unexpected Character.");
                }
                break;
        }
    }

    private void identifier()
    {
        while (isAlphaNumeric(peek())) advance();

        String text = source.substring(start, current);
        TokenType type = keywords.get(text);
        if (type == null) type = IDENTIFIER;
        addToken(type);
    }

    // Creates a string
    private void string()
    {
        while (peek() != '"' && !isAtEnd())
        {
            if (peek() == '\n') line++;
            advance();
        }

        if(isAtEnd())
        {
            Britlang.error(line, "Unterminated string");
        }

        //closing "
        advance();

        //Trim the surrounding quotes.
        String value = source.substring(start + 1, current - 1);
        addToken(STRING, value);
    }
    private boolean match(char expected)
    {
        if(isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;
        
        current++;
        return true;
    }

    private char peek()
    {
        if(isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private char peekNext() 
    {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }

    private boolean isAlpha(char c)
    {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z' || c == '_');
    }

    private boolean isAlphaNumeric(char c)
    {
        return isAlpha(c) || Character.isDigit(c);
    }

    private void number()
    {
        while(Character.isDigit(peek())) advance();

        //Look for a fractional part.
        if (peek() == '.' && Character.isDigit(peekNext()))
        {
            // consume the "."
            advance();

            while (Character.isDigit(peek())) advance();
        }

        addToken(NUMBER, Double.parseDouble(source.substring(start, current)));
    }

    private boolean isAtEnd()
    {
        return current >= source.length();
    }

    // moves to the next char
    private char advance()
    {
        current++;
        return source.charAt(current -1);
    }

    //adds token to list
    private void addToken(TokenType type)
    {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal)
    {
        String text = source.substring(start, current);
        tokens.add(new Token(type,text, literal, line));
    }
}