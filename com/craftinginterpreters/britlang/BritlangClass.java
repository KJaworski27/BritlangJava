package com.craftinginterpreters.britlang;

import java.util.List;
import java.util.Map;

class BritlangClass implements BritlangCallable
{
    final String name;
    final BritlangClass superclass;
    private final Map<String, BritlangFunction> methods;

    BritlangClass(String name, BritlangClass superclass, Map<String, BritlangFunction> methods)
    {
        this.name = name;
        this.superclass = superclass;
        this.methods = methods;
    }

    BritlangFunction findMethod(String name)
    {
        if(methods.containsKey(name))
        {
            return methods.get(name);
        }

        if (superclass != null)
        {
            return superclass.findMethod(name);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments)
    {
        BritlangInstance instance = new BritlangInstance(this);
        BritlangFunction initializer = findMethod("faff");
        if (initializer != null)
        {
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }

    @Override
    public int arity()
    {
        BritlangFunction initializer = findMethod("faff");
        if (initializer == null) return 0;
        return initializer.arity();
    }
}