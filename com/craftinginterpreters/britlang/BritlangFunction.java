package com.craftinginterpreters.britlang;

import java.util.List;

public class BritlangFunction implements BritlangCallable
{
    private final Stmt.Function declaration;
    private final Environment closure;

    private final boolean isInitializer;

    BritlangFunction(Stmt.Function declaration, Environment closure, boolean isInitializer)
    {
        this.isInitializer = isInitializer;
        this.closure = closure;
        this.declaration = declaration;
    }

    BritlangFunction bind(BritlangInstance instance)
    {
        Environment environment = new Environment(closure);
        environment.define("this", instance);
        return new BritlangFunction(declaration, environment, isInitializer);
    }

    @Override
    public String toString()
    {
        return "<fn " + declaration.name.lexeme + ">";
    }

    @Override
    public int arity()
    {
        return declaration.params.size();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments)
    {
        Environment environment = new Environment(closure);
        for (int i = 0; i < declaration.params.size(); i++)
        {
            environment.define(declaration.params.get(i).lexeme, arguments.get(i));
        }

        try
        {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue)
        {
            if (isInitializer) return closure.getAt(0, "this");
            return returnValue.value;
        }

        if (isInitializer) return closure.getAt(0, "this");
        return null;
    }
}
